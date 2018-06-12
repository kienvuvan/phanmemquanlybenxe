/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carfee.dao;

import carfee.model.CarFee;
import carowner.controller.CarOwnerController;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mysql.Mysql;

/**
 *
 * @author DELL
 */
public class MysqlCarFeeDao implements CarFeeDao {

    private static final String GET_ALL_CAR_FOR_OWNER = "SELECT DISTINCT xe.BienSoXe,xe.SoGhe,ThoiGianDo , COUNT(xe.BienSoXe) FROM xe,vitrido WHERE xe.BienSoXe = vitrido.BienSoXe AND xe.CmtNhaXe =? \n"
            + "AND (LEFT(ThoiGianDo,5) = RIGHT(xe.LichTrinh,5) \n"
            + "OR LEFT(ThoiGianDo,4) = RIGHT(xe.LichTrinh,4) \n"
            + "OR RIGHT(ThoiGianDo,5) = LEFT(xe.LichTrinh,5) \n"
            + "OR RIGHT(ThoiGianDo,4) = LEFT(xe.LichTrinh,4)) GROUP BY xe.BienSoXe";
    private static final String INSERT_FEE_CAR_DETAIL = "INSERT INTO chitietchiphi VALUES(?,?,?,?,?)";
    private static final String PARKING_FEE_CAR = "INSERT INTO chiphi VALUES(?,?,?,?)";
    private static final String GET_ALL_INFOR_CAR_FEE = "SELECT CmtChuXe, chuxe.HoTen, Phi, Ngay, admin.HoTen FROM chiphi,chuxe,admin "
            + "WHERE chiphi.CmtChuXe = chuxe.Cmt AND admin.Cmt = chiphi.CmtAdmin ORDER BY Ngay DESC";
    private static final String SEARCH_INFOR_CAR_FEE = "SELECT CmtChuXe, chuxe.HoTen, Phi, Ngay, admin.HoTen FROM chiphi,chuxe,admin "
            + "WHERE chiphi.CmtChuXe = chuxe.Cmt AND admin.Cmt = chiphi.CmtAdmin AND (CmtChuXe LIKE ? OR chuxe.HoTen LIKE ? "
            + "OR Phi LIKE ? OR Ngay LIKE ? OR admin.HoTen LIKE ? )ORDER BY Ngay DESC";
    private static final String CHECK_DATE_PARKING_FEE = "SELECT MAX(Ngay), MIN(Ngay) FROM chiphi";

    public static final int NOT_ENOUGH_DAY = 0;
    public static final int NGAY_THU_NHO_HON_NGAY_MIN = 1;
    public static final int NGAY_THU_LON_HON_QUA_NGAY_MAX = 2;
    public static final int NGAY_THU_DA_TON_TAI = 3;
    public static final int NGAY_THU_HOP_LE = 4;
    public static final int ERROR_SQL = 5;

    @Override
    public boolean parkingFeeCar(String cmtAdmin) {
        try {
            CarOwnerController carOwnerController = new CarOwnerController();
            List<String> listIdCarOwner = carOwnerController.getAllIdCarOwner();
            int checkCount = 0;
            Connection connection = Mysql.getInstance().getConnection();
            for (int i = 0; i < listIdCarOwner.size(); i++) {
                PreparedStatement pstm = connection.prepareCall(GET_ALL_CAR_FOR_OWNER);
                pstm.setString(1, listIdCarOwner.get(i));
                ResultSet rs = pstm.executeQuery();
                double feeParkOwner = 0;
                while (rs.next()) {
                    String bsx = rs.getString("BienSoXe");
                    int soGhe = rs.getInt("SoGhe");
                    int soChuyen = rs.getInt(4) / 2;
                    double feeCar = 0;
                    if (soGhe < 30) {
                        feeCar = 40000 * 30;
                    } else if (soGhe <= 50) {
                        feeCar = 60000 * 30;
                    } else {
                        feeCar = 80000 * 30;
                    }
                    feeParkOwner += feeCar * soChuyen;
                    PreparedStatement pstm1 = connection.prepareCall(INSERT_FEE_CAR_DETAIL);
                    pstm1.setString(1, listIdCarOwner.get(i));
                    pstm1.setString(2, bsx);
                    pstm1.setDate(3, new Date(Calendar.getInstance().getTimeInMillis()));
                    pstm1.setInt(4, soChuyen);
                    pstm1.setDouble(5, feeCar);
                    pstm1.executeUpdate();
                }
                PreparedStatement pstm2 = connection.prepareCall(PARKING_FEE_CAR);
                pstm2.setString(1, listIdCarOwner.get(i));
                pstm2.setDouble(2, feeParkOwner);
                pstm2.setDate(3, new Date(Calendar.getInstance().getTimeInMillis()));
                pstm2.setString(4, cmtAdmin);
                int check = pstm2.executeUpdate();
                if (check > 0) {
                    checkCount++;
                }
                if (checkCount == listIdCarOwner.size()) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarFeeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<CarFee> getAllInforCarFee() {
        List<CarFee> listCarFee = new ArrayList<>();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(GET_ALL_INFOR_CAR_FEE);
            while (rs.next()) {
                String cmtChuXe = rs.getString("CmtChuXe");
                String ten = rs.getString("chuxe.HoTen");
                double phi = rs.getDouble("Phi");
                Date ngay = rs.getDate("Ngay");
                String tenAdmin = rs.getString("admin.HoTen");
                CarFee carFee = new CarFee(cmtChuXe, ten, ngay, phi, tenAdmin);
                listCarFee.add(carFee);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarFeeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCarFee;
    }

    @Override
    public int checkDateParkingFee(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.get(Calendar.DATE) != 15) {
            return NOT_ENOUGH_DAY;
        } else {
            try {
                Connection connection = Mysql.getInstance().getConnection();
                Statement stm = connection.createStatement();
                ResultSet rs = stm.executeQuery(CHECK_DATE_PARKING_FEE);
                if (rs.next()) {
                    if (rs.getDate(1) == null) {
                        return NGAY_THU_HOP_LE;
                    } else {
                        Date dateMax = rs.getDate(1);
                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.setTime(dateMax);
                        Date dateMin = rs.getDate(2);
                        if (date.before(dateMin)) {
                            return NGAY_THU_NHO_HON_NGAY_MIN;
                        } else if (date.after(dateMin) && date.before(dateMax)) {
                            return NGAY_THU_DA_TON_TAI;
                        } else if (calendar1.get(Calendar.MONTH) + 1 < calendar.get(Calendar.MONTH)) {
                            return NGAY_THU_LON_HON_QUA_NGAY_MAX;
                        } else {
                            return NGAY_THU_HOP_LE;
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(MysqlCarFeeDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ERROR_SQL;
    }

    @Override
    public List<CarFee> searchInforCarFee(String keySearch) {
        List<CarFee> listCarFee = new ArrayList<>();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(SEARCH_INFOR_CAR_FEE);
            pstm.setString(1, "%" + keySearch + "%");
            pstm.setString(2, "%" + keySearch + "%");
            pstm.setString(3, "%" + keySearch + "%");
            pstm.setString(4, "%" + keySearch + "%");
            pstm.setString(5, "%" + keySearch + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String cmtChuXe = rs.getString("CmtChuXe");
                String ten = rs.getString("chuxe.HoTen");
                double phi = rs.getDouble("Phi");
                Date ngay = rs.getDate("Ngay");
                String tenAdmin = rs.getString("admin.HoTen");
                CarFee carFee = new CarFee(cmtChuXe, ten, ngay, phi, tenAdmin);
                listCarFee.add(carFee);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarFeeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCarFee;
    }
}
