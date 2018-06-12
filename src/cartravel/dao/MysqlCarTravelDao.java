/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartravel.dao;

import car.controller.CarController;
import car.model.Car;
import cartravel.controller.CarTravelController;
import cartravel.model.CarTravel;
import com.mysql.cj.util.StringUtils;
import guest.controller.GuestController;
import guest.model.Guest;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
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
    private static final String CHECK_TICKET_FOR_DESTROY = "SELECT COUNT(*) FROM chitietchuyenxe WHERE MaChuyenXe = ?  AND MaHanhKhach = ?";
    private static final String UN_BOOKTICKET = "DELETE FROM chitietchuyenxe WHERE MaChuyenXe = ? AND MaHanhKhach = ?";
    private static final String CHECK_EXIT_CAR_TRAVEL = "SELECT COUNT(*) FROM chuyenxe WHERE BienSoXe =? AND Ngay =? AND ThoiGian =?";
    private static final String GET_MAX_ID_CAR_TRAVEL = "SELECT MAX(MaChuyenXe) FROM `chuyenxe` WHERE Ngay = ? AND BienSoXe = ?";

    public static final int RESULT_DATE_NULL = 0;
    public static final int RESULT_DATE_BORN_NULL = 1;
    public static final int RESULT_EMPTY = 2;
    public static final int RESULT_ERROR_CMT = 3;
    public static final int RESULT_ERROR_SDT = 4;
    public static final int RESULT_ERROR_EMAIL = 5;
    public static final int RESULT_GUEST_REGISTED = 6;
    public static final int RESULT_SUCCESS = 7;
    public static final int RESULT_EXCEPTION = 8;
    public static final int RESULT_NOT_EXIT_TICKET = 9;

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

    @Override
    public boolean checkTicketForDestroy(String maChuyenXe, String cmt) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(CHECK_TICKET_FOR_DESTROY);
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
    public int unbookTicket(String maChuyenXe, String cmt) {
        if (StringUtils.isNullOrEmpty(maChuyenXe) || StringUtils.isNullOrEmpty(cmt)) {
            return RESULT_EMPTY;
        } else if (Utils.isCmt(cmt) == false) {
            return RESULT_ERROR_CMT;
        } else if (checkTicketForDestroy(maChuyenXe, cmt) == false) {
            return RESULT_NOT_EXIT_TICKET;
        } else {
            try {
                Connection connection = Mysql.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareCall(UN_BOOKTICKET);
                pstm.setString(1, maChuyenXe);
                pstm.setString(2, cmt);
                int check = pstm.executeUpdate();
                if (check > 0) {
                    return RESULT_SUCCESS;
                }
            } catch (SQLException ex) {
                Logger.getLogger(MysqlCarTravelDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            return RESULT_EXCEPTION;
        }
    }

    @Override
    public boolean creatCarTravel() {
        int checkCount = 0;
        int checkCountExit = 0;
        try {
            Date dateNow = new Date(Calendar.getInstance().getTimeInMillis());
            CarController carController = new CarController();
            List<Car> listCars = carController.getAllCar();
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(ADD_INFOR_CAR_TRAVEL);
            for (Car car : listCars) {
                String bsx = car.getBsx();
                String thoiGian = car.getLichTrinh();
                if (checkExitCarTravel(bsx, dateNow, thoiGian) == false) {
                    int maxIdCarTravel = getMaxIdCarTravel(bsx, dateNow);
                    String idCarTravel = Utils.generateCarTravelId(bsx, dateNow) + maxIdCarTravel;
                    pstm.setString(1, idCarTravel);
                    pstm.setString(2, bsx);
                    pstm.setDate(3, dateNow);
                    pstm.setString(4, thoiGian);
                    int check = pstm.executeUpdate();
                    if (check > 0) {
                        checkCount++;
                    }
                } else {
                    checkCountExit++;
                }
            }
            if (checkCount + checkCountExit == listCars.size()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarTravelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean checkExitCarTravel(String bsx, Date ngayDi, String thoiGian) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(CHECK_EXIT_CAR_TRAVEL);
            pstm.setString(1, bsx);
            pstm.setDate(2, ngayDi);
            pstm.setString(3, thoiGian);
            ResultSet rs = pstm.executeQuery();
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
                if (count > 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarTravelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public int getMaxIdCarTravel(String bsx, Date ngayDi) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(GET_MAX_ID_CAR_TRAVEL);
            pstm.setDate(1, ngayDi);
            pstm.setString(2, bsx);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                if (rs.getString(1) == null) {
                    return 1;
                } else {
                    String idCarTravel = rs.getString(1);
                    String mcx = Utils.generateCarTravelId(bsx, ngayDi);
                    return Integer.parseInt(idCarTravel.substring(mcx.length(), idCarTravel.length())) + 1;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarTravelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
}
