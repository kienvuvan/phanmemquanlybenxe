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

    private static final String GET_ALL_CAR_FOR_OWNER = "SELECT DISTINCT xe.BienSoXe,xe.SoGhe,ThoiGianDo FROM xe, vitrido WHERE xe.BienSoXe = vitrido.BienSoXe AND xe.CmtNhaXe = ? "
            + "AND (LEFT(ThoiGianDo,5) = RIGHT(xe.LichTrinh,5) "
            + "OR LEFT(ThoiGianDo,4) = RIGHT(xe.LichTrinh,4) "
            + "OR RIGHT(ThoiGianDo,5) = LEFT(xe.LichTrinh,5) "
            + "OR RIGHT(ThoiGianDo,4) = LEFT(xe.LichTrinh,4)) ";
    private static final String PARKING_FEE_CAR = "INSERT INTO chiphi VALUES(?,?,?,?)";
    private static final String GET_ALL_INFOR_CAR_FEE = "SELECT CmtChuXe, chuxe.HoTen, Phi, Ngay, admin.HoTen FROM chiphi,chuxe,admin "
            + "WHERE chiphi.CmtChuXe = chuxe.Cmt AND admin.Cmt = chiphi.CmtAdmin ORDER BY Ngay DESC";
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
                double feePark = 0;
                while (rs.next()) {
                    int soGhe = rs.getInt("SoGhe");
                    if (soGhe < 30) {
                        feePark += 40000 * 30;
                    } else if (soGhe <= 50) {
                        feePark += 60000 * 30;
                    } else {
                        feePark += 80000 * 30;
                    }
                }
                PreparedStatement pstm1 = connection.prepareCall(PARKING_FEE_CAR);
                pstm1.setString(1, listIdCarOwner.get(i));
                pstm1.setDouble(2, feePark);
                pstm1.setDate(3, new Date(Calendar.getInstance().getTimeInMillis()));
                pstm1.setString(4, cmtAdmin);
                int check = pstm1.executeUpdate();
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
                    Date dateMax = rs.getDate(1);
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.setTime(dateMax);
                    Date dateMin = rs.getDate(2);
                    if (date.before(dateMin)) {
                        return NGAY_THU_NHO_HON_NGAY_MIN;
                    } else if (date.after(dateMin) && date.before(dateMax)) {
                        return NGAY_THU_DA_TON_TAI;
                    } else if (calendar1.get(Calendar.MONTH) +1 < calendar.get(Calendar.MONTH)) {
                        return NGAY_THU_LON_HON_QUA_NGAY_MAX;
                    } else {
                        return NGAY_THU_HOP_LE;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(MysqlCarFeeDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ERROR_SQL;
    }
}
