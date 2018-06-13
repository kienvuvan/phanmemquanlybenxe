/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.dao;

import car.model.Car;
import car.model.CarUpdate;
import com.mysql.cj.util.StringUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
    public static final int RESULT_SENT_REQUEST = 9;

    private static final String SEARCH_CAR_FOR_BOOKTICKET = "SELECT * FROM xe, chuxe WHERE xe.CmtNhaXe = chuxe.Cmt AND "
            + "(chuxe.NhaXe LIKE ? OR BienSoXe LIKE ? OR SoGhe LIKE ? \n"
            + "OR LoTrinh LIKE ? OR LichTrinh LIKE ? OR GiaVe LIKE ? );";
    private static final String ADD_CAR = "INSERT INTO xe VALUES(?,?,?,?,?,?,?)";
    private static final String GET_ID_MAX = "SELECT MAX(Id) FROM xe";
    private static final String GET_CAR_BY_OWNER = " SELECT * FROM xe, chuxe, vitrido WHERE xe.CmtNhaXe = chuxe.Cmt AND xe.BienSoXe = vitrido.BienSoXe AND chuxe.Cmt = ? ";
    private static final String GET_ALL_INFOR_CAR_FOR_OWNER = "SELECT * FROM xe, vitrido WHERE xe.BienSoXe = vitrido.BienSoXe AND xe.CmtNhaXe = ? "
            + "AND (LEFT(ThoiGianDo,5) = RIGHT(xe.LichTrinh,5) "
            + "OR LEFT(ThoiGianDo,4) = RIGHT(xe.LichTrinh,4) "
            + "OR RIGHT(ThoiGianDo,5) = LEFT(xe.LichTrinh,5) "
            + "OR RIGHT(ThoiGianDo,4) = LEFT(xe.LichTrinh,4)) ";
    private static final String SEARCH_INFOR_CAR_FOR_OWNER = "SELECT * FROM xe, vitrido WHERE xe.BienSoXe = vitrido.BienSoXe AND xe.CmtNhaXe = ? "
            + "AND (LEFT(ThoiGianDo,5) = RIGHT(xe.LichTrinh,5) "
            + "OR LEFT(ThoiGianDo,4) = RIGHT(xe.LichTrinh,4) "
            + "OR RIGHT(ThoiGianDo,5) = LEFT(xe.LichTrinh,5) "
            + "OR RIGHT(ThoiGianDo,4) = LEFT(xe.LichTrinh,4)) AND (Id LIKE ? OR xe.BienSoXe LIKE ? OR SoGhe LIKE ? OR GiaVe LIKE ? OR LoTrinh LIKE ? "
            + "OR LichTrinh LIKE ? OR ViTriDoXe LIKE ? OR ThoiGianDo LIKE ?)";
    private static final String GET_ALL_CAR = "SELECT xe.BienSoXe, xe.LichTrinh FROM xe";

    private static final String GET_BSX = "SELECT BienSoXe,COUNT(BienSoXe) FROM xe GROUP BY BienSoXe";
    private static final String GET_BSX_PARKED = "SELECT BienSoXe, COUNT(BienSoXe) FROM vitrido GROUP BY BienSoXe";
    private static final String GET_ALL_PARK_LOCATION = "SELECT ViTriDoXe FROM baixe";
    private static final String GET_ROUTE_BY_BSX = "SELECT LichTrinh FROM xe WHERE BienSoXe = ? ";
    private static final String GET_ALL_CAR_PARKED = "SELECT DISTINCT xe.BienSoXe, vitrido.ViTriDoXe, vitrido.ThoiGianDo FROM xe, vitrido WHERE xe.BienSoXe = vitrido.BienSoXe";
    private static final String CHECK_PARK_LOCATION = "SELECT COUNT(*) FROM vitrido WHERE ViTriDoXe = ? AND ThoiGianDo = ?";
    private static final String CHECK_CAR_PARKED = "SELECT COUNT(*) FROM vitrido WHERE BienSoXe = ? AND ThoiGianDo = ?";
    private static final String SORT_CAR_IN_PARK = "INSERT INTO vitrido VALUES(?,?,?)";
    private static final String UPDATE_PARK = "UPDATE vitrido SET ViTriDoXe = ? WHERE BienSoXe =? AND ThoiGianDo = ?";
    private static final String DESTROY_PARK_CAR = "DELETE FROM vitrido WHERE BienSoXe =? AND ViTriDoXe = ? AND ThoiGianDo = ?";
    private static final String SEND_REQUEST_UPDATE_CAR = "INSERT INTO xechocapnhat VALUES(?,?,?,?,?,?,?)";
    private static final String CHECK_SENT_REQUEST = "SELECT COUNT(*) FROM xechocapnhat WHERE ID = ? AND BienSoXe = ?";
    private static final String UPDATE_SENT_REQUEST = "UPDATE xechocapnhat SET GiaVe = ?, LichTrinh = ? WHERE Id = ? AND BienSoXe = ?";
    private static final String GET_ALL_REQUEST_UPDATE_CAR = "SELECT * FROM xechocapnhat WHERE TinhTrang = 0";
    private static final String GET_ALL_ID_CAROWNER_REQUEST = "SELECT CmtChuXe FROM xechocapnhat WHERE TinhTrang = 0";
    private static final String GET_ALL_ID_CAR_REQUEST_BY_CAROWNER = "SELECT Id FROM xechocapnhat WHERE CmtChuXe =?";
    private static final String GET_TIME_SEND_REQUEST = "SELECT NgayGui FROM xechocapnhat WHERE Id = ?";
    private static final String GET_CAR_BY_ID_CAR = "SELECT * FROM xe WHERE Id = ?";
    private static final String GET_CAR_UPDATE_BY_ID_CAR = "SELECT * FROM xechocapnhat WHERE Id = ?";
    private static final String UPDATE_CAR = "UPDATE xe SET GiaVe = ? WHERE Id = ?";
    private static final String UPDATE_CAR_1 = "UPDATE xe SET GiaVe = ?, LichTrinh = ? WHERE Id = ? ";
    private static final String UPDATE_STATUS_CAR_UPDATE = "UPDATE xechocapnhat SET TinhTrang = ? WHERE Id =?";
    private static final String GET_PARK_LOCATION = "SELECT DISTINCT vitrido.ViTriDoXe FROM vitrido, xe "
            + "WHERE xe.BienSoXe = vitrido.BienSoXe AND (Id = ? OR Id = ?) \n"
            + "AND (LEFT(ThoiGianDo,5) = RIGHT(xe.LichTrinh,5) OR LEFT(ThoiGianDo,4) = RIGHT(xe.LichTrinh,4) OR RIGHT(ThoiGianDo,5) = LEFT(xe.LichTrinh,5) OR RIGHT(ThoiGianDo,4) = LEFT(xe.LichTrinh,4)) ";
    private static final String UPDATE_PARK_LOCATION = "UPDATE vitrido SET ThoiGianDo = ? WHERE ViTriDoXe = ? AND BienSoXe = ? AND ThoiGianDo =?";
    private static final String GET_ALL_REQUEST_BY_ID_CAROWNER = "SELECT * FROM xechocapnhat WHERE CmtChuXe = ?";
    private static final String DELETE_REQUEST_BY_ID_CAROWNER = "DELETE FROM xechocapnhat WHERE CmtChuXe = ? AND (TinhTrang = ? OR TinhTrang = ?)";

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
    public List<String> getTimeParked(String bsx) {
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
                list.add(arrivalTime + "-" + timeOn);
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

    @Override
    public int updatePark(Car carUpdate) {
        if (StringUtils.isNullOrEmpty(carUpdate.getBsx()) || StringUtils.isNullOrEmpty(carUpdate.getViTri()) || StringUtils.isNullOrEmpty(carUpdate.getThoiGianDo())) {
            return RESULT_EMPTY;
        } else if (checkParkLocation(carUpdate.getViTri(), carUpdate.getThoiGianDo()) == false) {
            return RESULT_PARKED;
        } else {
            try {
                Connection connection = Mysql.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareCall(UPDATE_PARK);
                pstm.setString(1, carUpdate.getViTri());
                pstm.setString(2, carUpdate.getBsx());
                pstm.setString(3, carUpdate.getThoiGianDo());
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
    public boolean destroyParkCar(Car car) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(DESTROY_PARK_CAR);
            pstm.setString(1, car.getBsx());
            pstm.setString(2, car.getViTri());
            pstm.setString(3, car.getThoiGianDo());
            int check = pstm.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Car> getAllInforCarForOwner(String cmt) {
        List<Car> listCars = new ArrayList<>();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(GET_ALL_INFOR_CAR_FOR_OWNER);
            pstm.setString(1, cmt);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String bienSoXe = rs.getString("BienSoXe");
                String nhaXe = rs.getString("CmtNhaXe");
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
    public List<Car> searchInforCarForOwner(String cmt, String keySearch) {
        List<Car> listCars = new ArrayList<>();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(SEARCH_INFOR_CAR_FOR_OWNER);
            pstm.setString(1, cmt);
            pstm.setString(2, "%" + keySearch + "%");
            pstm.setString(3, "%" + keySearch + "%");
            pstm.setString(4, "%" + keySearch + "%");
            pstm.setString(5, "%" + keySearch + "%");
            pstm.setString(6, "%" + keySearch + "%");
            pstm.setString(7, "%" + keySearch + "%");
            pstm.setString(8, "%" + keySearch + "%");
            pstm.setString(9, "%" + keySearch + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String bienSoXe = rs.getString("BienSoXe");
                String nhaXe = rs.getString("CmtNhaXe");
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
    public List<Car> getAllCar() {
        List<Car> listCars = new ArrayList();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(GET_ALL_CAR);
            while (rs.next()) {
                String bsx = rs.getString("xe.BienSoXe");
                String lichTrinh = rs.getString("xe.LichTrinh");
                Car car = new Car(bsx, lichTrinh);
                listCars.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCars;
    }

    @Override
    public int sendRequesttUpdateCar(String maXe, String bsx, String cmt, double giaVe, String lichTrinh) {
        if (StringUtils.isNullOrEmpty(maXe) || StringUtils.isNullOrEmpty(bsx) || StringUtils.isNullOrEmpty(giaVe + "") || StringUtils.isNullOrEmpty(lichTrinh)) {
            return RESULT_EMPTY;
        } else if (Utils.isBsx(bsx) == false) {
            return RESULT_ERROR_BSX;
        } else if (giaVe <= 0) {
            return RESULT_ERROR_GIAVE;
        } else if (Utils.invalidTime(lichTrinh) == false) {
            return RESULT_ERROR_TIME;
        } else if (checkSentRequest(maXe, bsx)) {
            return RESULT_SENT_REQUEST;
        } else {
            try {
                Connection connection = Mysql.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareCall(SEND_REQUEST_UPDATE_CAR);
                pstm.setString(1, maXe);
                pstm.setString(2, bsx);
                pstm.setString(3, cmt);
                pstm.setDouble(4, giaVe);
                pstm.setString(5, lichTrinh);
                pstm.setTimestamp(6, new Timestamp(Calendar.getInstance().getTimeInMillis()));
                pstm.setInt(7, 0);
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
    public boolean checkSentRequest(String maXe, String bsx) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(CHECK_SENT_REQUEST);
            pstm.setString(1, maXe);
            pstm.setString(2, bsx);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) > 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public int updateSentRequest(String maXe, String bsx, String cmt, double giaVe, String lichTrinh) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(UPDATE_SENT_REQUEST);
            pstm.setDouble(1, giaVe);
            pstm.setString(2, lichTrinh);
            pstm.setString(3, maXe);
            pstm.setString(4, bsx);
            int check = pstm.executeUpdate();
            if (check > 0) {
                return RESULT_SUCCESS;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return RESULT_ERROR_SQL;
    }

    @Override
    public List<CarUpdate> getAllRequest() {
        List<CarUpdate> listCarUpdates = new ArrayList<>();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(GET_ALL_REQUEST_UPDATE_CAR);
            while (rs.next()) {
                String maXe = rs.getString("Id");
                String bsx = rs.getString("BienSoXe");
                double giaVe = rs.getDouble("GiaVe");
                String lichTrinh = rs.getString("LichTrinh");
                Timestamp ngayGui = rs.getTimestamp("NgayGui");
                int tinhTrang = rs.getInt("TinhTrang");
                CarUpdate carUpdate = new CarUpdate(maXe, bsx, giaVe, lichTrinh, ngayGui, tinhTrang);
                listCarUpdates.add(carUpdate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCarUpdates;
    }

    @Override
    public List<String> getAllIdCarOwnerRequest() {
        List<String> listIdCarOwner = new ArrayList<>();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(GET_ALL_ID_CAROWNER_REQUEST);
            while (rs.next()) {
                listIdCarOwner.add(rs.getString("CmtChuXe"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listIdCarOwner;
    }

    @Override
    public List<String> getAllIdCarRequestByCarOwner(String cmt) {
        List<String> listIdCar = new ArrayList<>();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(GET_ALL_ID_CAR_REQUEST_BY_CAROWNER);
            pstm.setString(1, cmt);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listIdCar.add(rs.getString("Id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listIdCar;
    }

    @Override
    public Timestamp getTimeSendRequest(String id) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(GET_TIME_SEND_REQUEST);
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getTimestamp("NgayGui");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Car> getCarByIdCar(String maXe) {
        List<Car> listCars = new ArrayList<>();
        try {
            List<String> list = Utils.cutRoute(maXe);
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(GET_CAR_BY_ID_CAR);
            for (int i = 0; i < list.size(); i++) {
                pstm.setString(1, list.get(i));
                ResultSet rs = pstm.executeQuery();
                if (rs.next()) {
                    String bsx = rs.getString("BienSoXe");
                    double giaVe = rs.getDouble("GiaVe");
                    String lichTrinh = rs.getString("LichTrinh");
                    Car car = new Car(Integer.parseInt(list.get(i)), bsx, giaVe, lichTrinh);
                    listCars.add(car);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCars;
    }

    @Override
    public List<CarUpdate> getCarUpdateByIdCar(String maXe) {
        List<CarUpdate> listCarUpdates = new ArrayList<>();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(GET_CAR_UPDATE_BY_ID_CAR);
            pstm.setString(1, maXe);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String bsx = rs.getString("BienSoXe");
                double giaVe = rs.getDouble("GiaVe");
                String lichTrinh = rs.getString("LichTrinh");
                CarUpdate carUpdate = new CarUpdate(maXe, bsx, giaVe, lichTrinh);
                listCarUpdates.add(carUpdate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCarUpdates;
    }

    @Override
    public boolean updateCar(double giaVe, String maXe) {
        try {
            int count = 0;
            List<String> list = Utils.cutRoute(maXe);
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(UPDATE_CAR);
            for (int i = 0; i < list.size(); i++) {
                pstm.setDouble(1, giaVe);
                pstm.setString(2, list.get(i));
                int check = pstm.executeUpdate();
                if (check > 0) {
                    count++;
                }
            }
            PreparedStatement pstm1 = connection.prepareCall(UPDATE_STATUS_CAR_UPDATE);
            pstm1.setInt(1, 2);
            pstm1.setString(2, maXe);
            int check1 = pstm1.executeUpdate();
            if (count == list.size() && check1 > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateStatusCarUpdate(String maXe) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(UPDATE_STATUS_CAR_UPDATE);
            pstm.setInt(1, 2);
            pstm.setString(2, maXe);
            int check = pstm.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateCar(double giaVe, String lichTrinh, String maXe) {
        String lichTrinhVe = Utils.generateCarReturn(lichTrinh);
        List<String> list = Utils.cutRoute(maXe);
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(UPDATE_CAR_1);
            pstm.setDouble(1, giaVe);
            pstm.setString(2, lichTrinh);
            pstm.setString(3, list.get(0));
            int check = pstm.executeUpdate();
            PreparedStatement pstm1 = connection.prepareCall(UPDATE_CAR_1);
            pstm1.setDouble(1, giaVe);
            pstm1.setString(2, lichTrinhVe);
            pstm1.setString(3, list.get(1));
            int check1 = pstm1.executeUpdate();
            boolean check2 = updateStatusCarUpdate(maXe);
            if (check > 0 && check1 > 0 && check2) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateParkLocaction(String viTriDo, String bsx, String thoiGianDoCu, String thoiGianDoMoi) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(UPDATE_PARK_LOCATION);
            pstm.setString(1, thoiGianDoMoi);
            pstm.setString(2, viTriDo);
            pstm.setString(3, bsx);
            pstm.setString(4, thoiGianDoCu);
            int check = pstm.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getParkLocation(String maXe) {
        try {
            List<String> list = Utils.cutRoute(maXe);
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(GET_PARK_LOCATION);
            pstm.setString(1, list.get(0));
            pstm.setString(2, list.get(1));
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getString("ViTriDoXe");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @Override
    public boolean updateStatusNoAgree(String maXe) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(UPDATE_STATUS_CAR_UPDATE);
            pstm.setInt(1, 1);
            pstm.setString(2, maXe);
            int check = pstm.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<CarUpdate> getAllRequestByIdCarOwner(String cmt) {
        List<CarUpdate> listCarUpdates = new ArrayList<>();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(GET_ALL_REQUEST_BY_ID_CAROWNER);
            pstm.setString(1, cmt);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("Id");
                String bsx = rs.getString("BienSoXe");
                double giaVe = rs.getDouble("GiaVe");
                String lichTrinh = rs.getString("LichTrinh");
                Timestamp thoiGian = rs.getTimestamp("NgayGui");
                int tinhTrang = rs.getInt("TinhTrang");
                CarUpdate carUpdate = new CarUpdate(id, bsx, giaVe, lichTrinh, thoiGian, tinhTrang);
                listCarUpdates.add(carUpdate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCarUpdates;
    }

    @Override
    public boolean deleteRequestCarUpdate(String cmt) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(DELETE_REQUEST_BY_ID_CAROWNER);
            pstm.setString(1, cmt);
            pstm.setInt(2, 1);
            pstm.setInt(3, 2);
            int check = pstm.executeUpdate();
            if(check >0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
