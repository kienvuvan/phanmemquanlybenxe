/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.dao;

import car.model.Car;
import com.mysql.cj.util.StringUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mysql.Mysql;
import utils.Utils;

/**
 *
 * @author kienanh2903
 */
public class MysqlCarDao implements CarDao {

    public static final int RESULT_EMPTY = 0;
    public static final int RESULT_ERROR_BSX = 1;
    public static final int RESULT_ERROR_CMT = 2;
    public static final int RESULT_ERROR_SOGHE = 3;
    public static final int RESULT_ERROR_GIAVE = 4;
    public static final int RESULT_ERROR_TIME = 5;
    public static final int RESULT_SUCCESS = 6;
    public static final int RESULT_ERROR_SQL = 7;

    private static final String SEARCH_CAR_FOR_BOOKTICKET = "SELECT * FROM xe, chuxe WHERE xe.CmtNhaXe = chuxe.Cmt AND "
            + "(chuxe.NhaXe LIKE ? OR BienSoXe LIKE ? OR SoGhe LIKE ? \n"
            + "OR LoTrinh LIKE ? OR LichTrinh LIKE ? OR GiaVe LIKE ? );";
    private static final String ADD_CAR = "INSERT INTO xe VALUES(?,?,?,?,?,?,?)";
    private static final String GET_ID_MAX = "SELECT MAX(Id) FROM xe";
    private static final String GET_CAR_BY_OWNER = " SELECT * FROM xe, chuxe, vitrido WHERE xe.CmtNhaXe = chuxe.Cmt AND xe.BienSoXe = vitrido.BienSoXe AND chuxe.Cmt = ? ";

    private static MysqlCarDao mysqlCarDao;

    public static MysqlCarDao getInstance() {
        if (mysqlCarDao == null) {
            mysqlCarDao = new MysqlCarDao();
        }
        return mysqlCarDao;
    }

    @Override
    public List<Car> searchCarForBookTicket(String keySearch) {
        List<Car> listCars = new ArrayList<>();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(SEARCH_CAR_FOR_BOOKTICKET);
            pstm.setString(1, "%" + keySearch + "%");
            pstm.setString(2, "%" + keySearch + "%");
            pstm.setString(3, "%" + keySearch + "%");
            pstm.setString(4, "%" + keySearch + "%");
            pstm.setString(5, "%" + keySearch + "%");
            pstm.setString(6, "%" + keySearch + "%");
            ResultSet rs = pstm.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                String bienSoXe = rs.getString("BienSoXe");
                String nhaXe = rs.getString("chuxe.NhaXe");
                int soGhe = rs.getInt("SoGhe");
                String loTrinh = rs.getString("LoTrinh");
                String lichTrinh = rs.getString("LichTrinh");
                Double giaVe = rs.getDouble("GiaVe");
                Car car = new Car(i, bienSoXe, nhaXe, soGhe, giaVe, loTrinh, lichTrinh);
                listCars.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCars;
    }

    @Override
    public int addCar(Car car) {
        if (StringUtils.isNullOrEmpty(car.getBsx()) || StringUtils.isNullOrEmpty(car.getCmtNhaXe()) || StringUtils.isNullOrEmpty(car.getSoGhe() + "")
                || StringUtils.isNullOrEmpty(car.getGiaVe() + "")
                || StringUtils.isNullOrEmpty(car.getLoTrinh()) || StringUtils.isNullOrEmpty(car.getLichTrinh())) {
            return RESULT_EMPTY;
        } else if (Utils.isBsx(car.getBsx()) == false) {
            return RESULT_ERROR_BSX;
        } else if (Utils.isCmt(car.getCmtNhaXe()) == false) {
            return RESULT_ERROR_CMT;
        } else if (car.getSoGhe() <= 0) {
            return RESULT_ERROR_SOGHE;
        } else if (car.getGiaVe() <= 0) {
            return RESULT_ERROR_GIAVE;
        } else if (Utils.invalidTime(car.getLichTrinh()) == false) {
            return RESULT_ERROR_TIME;
        } else {
            try {
                Connection connection = Mysql.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareCall(ADD_CAR);
                int id = getIdMax() + 1;
                pstm.setInt(1, id);
                pstm.setString(2, car.getBsx());
                pstm.setString(3, car.getCmtNhaXe());
                pstm.setInt(4, car.getSoGhe());
                pstm.setDouble(5, car.getGiaVe());
                pstm.setString(6, car.getLoTrinh());
                pstm.setString(7, car.getLichTrinh());
                PreparedStatement pstm1 = connection.prepareCall(ADD_CAR);
                pstm1.setInt(1, id + 1);
                pstm1.setString(2, car.getBsx());
                pstm1.setString(3, car.getCmtNhaXe());
                pstm1.setInt(4, car.getSoGhe());
                pstm1.setDouble(5, car.getGiaVe());
                pstm1.setString(6, Utils.reverseRoute(car.getLoTrinh()));
                pstm1.setString(7, Utils.generateCarReturn(car.getLichTrinh()));
                int check = pstm.executeUpdate();
                int check1 = pstm1.executeUpdate();
                if (check > 0 && check1 > 0) {
                    return RESULT_SUCCESS;
                }
            } catch (SQLException ex) {
                Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return RESULT_ERROR_SQL;
    }

    @Override
    public int getIdMax() {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(GET_ID_MAX);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public List<Car> getAllCarForOwner(String cmt) {
        List<Car> listCars = new ArrayList<>();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(GET_CAR_BY_OWNER);
            pstm.setString(1, cmt);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String bienSoXe = rs.getString("BienSoXe");
                String nhaXe = rs.getString("chuxe.NhaXe");
                int soGhe = rs.getInt("SoGhe");
                String loTrinh = rs.getString("LoTrinh");
                String lichTrinh = rs.getString("LichTrinh");
                Double giaVe = rs.getDouble("GiaVe");
                String vitri = rs.getString("ViTriDoXe");
                String thoiGianDo = rs.getString("ThoiGianDo");
                Car car = new Car(id, bienSoXe, nhaXe, soGhe, giaVe, loTrinh, lichTrinh, vitri, thoiGianDo);
                listCars.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCars;
    }

}
