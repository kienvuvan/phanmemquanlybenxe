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
import java.util.HashMap;
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
    public static final int RESULT_PARKED = 8;

    private static final String SEARCH_CAR_FOR_BOOKTICKET = "SELECT * FROM xe, chuxe WHERE xe.CmtNhaXe = chuxe.Cmt AND "
            + "(chuxe.NhaXe LIKE ? OR BienSoXe LIKE ? OR SoGhe LIKE ? \n"
            + "OR LoTrinh LIKE ? OR LichTrinh LIKE ? OR GiaVe LIKE ? );";
    private static final String ADD_CAR = "INSERT INTO xe VALUES(?,?,?,?,?,?,?)";
    private static final String GET_ID_MAX = "SELECT MAX(Id) FROM xe";
    private static final String GET_CAR_BY_OWNER = " SELECT * FROM xe, chuxe, vitrido WHERE xe.CmtNhaXe = chuxe.Cmt AND xe.BienSoXe = vitrido.BienSoXe AND chuxe.Cmt = ? ";
    private static final String GET_BSX = "SELECT BienSoXe,COUNT(BienSoXe) FROM xe GROUP BY BienSoXe";
    private static final String GET_BSX_PARKED = "SELECT BienSoXe, COUNT(BienSoXe) FROM vitrido GROUP BY BienSoXe";
    private static final String GET_ALL_PARK_LOCATION = "SELECT ViTriDoXe FROM baixe";
    private static final String GET_ROUTE_BY_BSX = "SELECT LichTrinh FROM xe WHERE BienSoXe = ? ";
    private static final String GET_ALL_CAR_PARKED = "SELECT DISTINCT xe.BienSoXe, vitrido.ViTriDoXe, vitrido.ThoiGianDo FROM xe, vitrido WHERE xe.BienSoXe = vitrido.BienSoXe";
    private static final String CHECK_PARK_LOCATION = "SELECT COUNT(*) FROM vitrido WHERE ViTriDoXe = ? AND ThoiGianDo = ?";
    private static final String CHECK_CAR_PARKED = "SELECT COUNT(*) FROM vitrido WHERE BienSoXe = ? AND ThoiGianDo = ?";
    private static final String SORT_CAR_IN_PARK = "INSERT INTO vitrido VALUES(?,?,?)";

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

    @Override
    public List<String> getAllCarIdNoSort() {
        List<String> listBsx = new ArrayList<>();
        HashMap<String, Integer> hash = new HashMap();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(GET_BSX);
            while (rs.next()) {
                String bsx = rs.getString(1);
                int count = rs.getInt(2);
                hash.put(bsx, count / 2);
            }
            ResultSet rs1 = stm.executeQuery(GET_BSX_PARKED);
            while (rs1.next()) {
                String bsx1 = rs1.getString(1);
                int count1 = rs1.getInt(2);
                if (hash.containsKey(bsx1)) {
                    hash.put(bsx1, (int) hash.get(bsx1) - count1);
                }
            }
            for (String key : hash.keySet()) {
                int checkCount = hash.get(key);
                if (checkCount > 0) {
                    listBsx.add(key);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listBsx;
    }

    @Override
    public List<String> getAllParkLocation() {
        List<String> listParkLocation = new ArrayList<>();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(GET_ALL_PARK_LOCATION);
            while (rs.next()) {
                listParkLocation.add(rs.getString("ViTriDoXe"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listParkLocation;
    }

    @Override
    public List<String> generateTimePark(String bsx) {
        List<String> list = new ArrayList<>();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(GET_ROUTE_BY_BSX);
            pstm.setString(1, bsx);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String carRun = rs.getString("LichTrinh");
                String arrivalTime = Utils.cutRoute(carRun).get(1);
                rs.next();
                String carOn = rs.getString("LichTrinh");
                String timeOn = Utils.cutRoute(carOn).get(0);
                if (checkCarParked(bsx, arrivalTime + "-" + timeOn)) {
                    list.add(arrivalTime + "-" + timeOn);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Car> getAllCarParked() {
        List<Car> list = new ArrayList<>();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            Statement pstm = connection.createStatement();
            ResultSet rs = pstm.executeQuery(GET_ALL_CAR_PARKED);
            while (rs.next()) {
                String bsx = rs.getString("BienSoXe");
                String viTriDoXe = rs.getString("ViTriDoXe");
                String thoiGianDo = rs.getString("ThoiGianDo");
                Car car = new Car(bsx, viTriDoXe, thoiGianDo);
                list.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean checkParkLocation(String viTriDo, String thoiGianDo) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(CHECK_PARK_LOCATION);
            pstm.setString(1, viTriDo);
            pstm.setString(2, thoiGianDo);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) > 0) {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public int sortCarInPark(Car car) {
        if (StringUtils.isNullOrEmpty(car.getBsx()) || StringUtils.isNullOrEmpty(car.getViTri()) || StringUtils.isNullOrEmpty(car.getThoiGianDo())) {
            return RESULT_EMPTY;
        } else if (checkParkLocation(car.getViTri(), car.getThoiGianDo()) == false) {
            return RESULT_PARKED;
        } else {
            try {
                Connection connection = Mysql.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareCall(SORT_CAR_IN_PARK);
                pstm.setString(2, car.getBsx());
                pstm.setString(1, car.getViTri());
                pstm.setString(3, car.getThoiGianDo());
                int check = pstm.executeUpdate();
                if (check > 0) {
                    return RESULT_SUCCESS;
                }
            } catch (SQLException ex) {
                Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return RESULT_ERROR_SQL;
    }

    @Override
    public boolean checkCarParked(String bsx, String thoiGianDo) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(CHECK_CAR_PARKED);
            pstm.setString(1, bsx);
            pstm.setString(2, thoiGianDo);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) > 0) {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
