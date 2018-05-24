/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartravel.dao;

import cartravel.controller.CarTravelController;
import cartravel.model.CarTravel;
import cartravel.model.CarTravelDetail;
import com.mysql.cj.util.StringUtils;
import guest.controller.GuestController;
import guest.model.Guest;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import mysql.Mysql;
import utils.Utils;

/**
 *
 * @author kienanh2903
 */
public class MysqlCarTravelDao implements CarTravelDao {

    private static final String GET_CARTRAVELID = "SELECT * FROM chuyenxe WHERE BienSoXe = ? AND Ngay = ?";
    private static final String CHECK_CARTRAVEL_ID_EXIT = "SELECT COUNT(*) FROM chuyenxe WHERE MaChuyenXe =?";
    private static final String CHECK_GUEST_IN_CAR_TRAVEL = "SELECT COUNT(*) FROM chitietchuyenxe WHERE MaChuyenXe = ? AND MaHanhKhach = ?";
    private static final String ADD_INFOR_CAR_TRAVEL_DETAIL = "INSERT INTO chitietchuyenxe VALUES(?,?)";
    private static final String ADD_INFOR_CAR_TRAVEL = "INSERT INTO chuyenxe VALUES(?,?,?,?)";

    public static final int RESULT_DATE_NULL = 0;
    public static final int RESULT_DATE_BORN_NULL = 1;
    public static final int RESULT_EMPTY = 2;
    public static final int RESULT_ERROR_CMT = 3;
    public static final int RESULT_ERROR_SDT = 4;
    public static final int RESULT_ERROR_EMAIL = 5;
    public static final int RESULT_GUEST_REGISTED = 6;
    public static final int RESULT_SUCCESS = 7;
    public static final int RESULT_EXCEPTION = 8;

    @Override
    public String generateCarTravelId(String bienSoXe, Date ngayDi, String thoiGian) {
        String maChuyenXe = "";
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(GET_CARTRAVELID);
            pstm.setString(1, bienSoXe);
            pstm.setDate(2, ngayDi);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                rs.afterLast();
                rs.previous();
                String time = rs.getString("ThoiGian");
                if (time.equalsIgnoreCase(thoiGian)) {
                    return rs.getString("MaChuyenXe");
                } else {
                    String maChuyenXeMax = rs.getString("MaChuyenXe");
                    String mcx = Utils.generateCarTravelId(bienSoXe, ngayDi);
                    int stt = Integer.parseInt(maChuyenXeMax.substring(mcx.length(), maChuyenXeMax.length())) + 1;
                    return mcx + stt;
                }
            } else {
                return Utils.generateCarTravelId(bienSoXe, ngayDi) + 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarTravelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maChuyenXe;
    }

    public static void main(String[] args) {
        MysqlCarTravelDao mctd = new MysqlCarTravelDao();
        Date dateNow = new Date(Calendar.getInstance().getTimeInMillis());
        System.out.println(mctd.generateCarTravelId("17M2-1234", dateNow, "6h-9h"));
    }

    @Override
    public int bookTicket(CarTravel carTravel, Guest guest) {
        GuestController gc = new GuestController();
        CarTravelController ctc = new CarTravelController();
        if (carTravel.getNgayDi() == null) {
            return RESULT_DATE_NULL;
        } else if (guest.getCmt() == null) {
            return RESULT_DATE_BORN_NULL;
        } else if (StringUtils.isNullOrEmpty(guest.getCmt()) || StringUtils.isNullOrEmpty(guest.getTen()) || StringUtils.isNullOrEmpty(guest.getSdt())
                || StringUtils.isNullOrEmpty(guest.getEmail()) || StringUtils.isNullOrEmpty(guest.getDiaChi())) {
            return RESULT_EMPTY;
        } else if (Utils.isCmt(guest.getCmt()) == false) {
            return RESULT_ERROR_CMT;
        } else if (Utils.isPhoneNumber(guest.getSdt()) == false) {
            return RESULT_ERROR_SDT;
        } else if (Utils.isAdressEmail(guest.getEmail()) == false) {
            return RESULT_ERROR_EMAIL;
        } else {
            if (ctc.checkCarTravelIdExit(carTravel.getMaChuyenXe())) {
                if (checkGuestInCarTravel(carTravel.getMaChuyenXe(), guest.getCmt())) {
                    return RESULT_GUEST_REGISTED;
                } else {
                    if (gc.checkGuestExit(guest.getCmt())) {
                        if (gc.updateInforGuest(guest) && addInforCarTravelDetail(carTravel.getMaChuyenXe(), guest.getCmt())) {
                            return RESULT_SUCCESS;
                        } else {
                            return RESULT_EXCEPTION;
                        }
                    } else {
                        if (gc.addGuest(guest) && addInforCarTravelDetail(carTravel.getMaChuyenXe(), guest.getCmt())) {
                            return RESULT_SUCCESS;
                        } else {
                            return RESULT_EXCEPTION;
                        }
                    }
                }
            } else {
                if (gc.checkGuestExit(guest.getCmt())) {
                    if (addInforCarTravel(carTravel) && gc.updateInforGuest(guest) && addInforCarTravelDetail(carTravel.getMaChuyenXe(), guest.getCmt())) {
                        return RESULT_SUCCESS;
                    } else {
                        return RESULT_EXCEPTION;
                    }
                } else {
                    if (addInforCarTravel(carTravel) && gc.addGuest(guest) && addInforCarTravelDetail(carTravel.getMaChuyenXe(), guest.getCmt())) {
                        return RESULT_SUCCESS;
                    } else {
                        return RESULT_EXCEPTION;
                    }
                }
            }
        }
    }

    @Override
    public boolean checkCarTravelIdExit(String maChuyenXe) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(CHECK_CARTRAVEL_ID_EXIT);
            pstm.setString(1, maChuyenXe);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) > 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarTravelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean checkGuestInCarTravel(String maChuyenXe, String cmt) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(CHECK_GUEST_IN_CAR_TRAVEL);
            pstm.setString(1, maChuyenXe);
            pstm.setString(2, cmt);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) > 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarTravelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean addInforCarTravelDetail(String maChuyenXe, String maHanhKhach) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(ADD_INFOR_CAR_TRAVEL_DETAIL);
            pstm.setString(1, maChuyenXe);
            pstm.setString(2, maHanhKhach);
            int check = pstm.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarTravelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean addInforCarTravel(CarTravel carTravel) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(ADD_INFOR_CAR_TRAVEL);
            pstm.setString(1, carTravel.getMaChuyenXe());
            pstm.setString(2, carTravel.getBienSoXe());
            pstm.setDate(3, carTravel.getNgayDi());
            pstm.setString(4, carTravel.getThoiGian());
            int check = pstm.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarTravelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
