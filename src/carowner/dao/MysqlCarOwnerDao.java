/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carowner.dao;

import admin.dao.MysqlAdminDao;
import static admin.dao.MysqlAdminDao.RESULT_ACCOUNT_INCORECT;
import static admin.dao.MysqlAdminDao.RESULT_LOGIN_SUCCESS;
import car.model.Car;
import mysql.Mysql;
import carowner.model.CarOwner;
import com.mysql.cj.util.StringUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utils.HashPassword;
import utils.Utils;

/**
 *
 * @author kienanh2903
 */
public class MysqlCarOwnerDao implements CarOwnerDao {

    private static final String COLUMN_CMT_CAROWNER = "Chuxe.Cmt";
    private static final String COLUMN_NAME_CAROWNER = "HoTen";
    private static final String COLUMN_NHAXE_CAROWNER = "NhaXe";
    private static final String COLUMN_SDT_CAROWNER = "Sdt";
    private static final String COLUMN_EMAIL_CAROWNER = "Email";
    private static final String COLUMN_GIOITINH_CAROWNER = "GioiTinh";
    private static final String COLUMN_NGAYSINH_CAROWNER = "NgaySinh";
    private static final String COLUMN_DIACHI_CAROWNER = "DiaChi";

    private static final String ADD_CAROWNER = "INSERT INTO chuxe VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_INFOR_CAROWNER = "UPDATE chuxe SET HoTen = ?, NhaXe = ?, Sdt = ?, Email = ?, GioiTinh = ?, NgaySinh =?, DiaChi =? "
            + "WHERE Cmt = ?";
    private static final String GET_ALL_CAROWNER = "SELECT * FROM chuxe";
    private static final String GET_ALL_CAR_BY_CAROWNER = "SELECT * FROM xe WHERE CmtNhaXe =?";
    private static final String GET_NAME_BY_ID_CAROWNER = "SELECT HoTen FROM chuxe WHERE Cmt =?";
    private static final String GET_ALL_CAR_BY_ID_CAROWNER = "SELECT * FROM xe,chuxe WHERE xe.CmtNhaXe = chuxe.Cmt And CmtNhaXe = ?";
    private static final String EDIT_CAR_OWNER = "UPDATE chuxe SET HoTen = ?, NhaXe = ?, Sdt = ?, Email = ?, GioiTinh = ?, NgaySinh = ?, DiaChi = ? WHERE Cmt = ?";
    private static final String GET_CAR_OWNER = "SELECT * FROM chuxe WHERE Cmt = ?";
    private static final String CHECK_ACCOUNT_CAROWNER = "SELECT Cmt, MatKhau FROM chuxe WHERE Cmt = ? AND MatKhau =?";
    private static final String GET_GRAGE_BY_CMT = "SELECT DISTINCT chuxe.NhaXe FROM chuxe, xe WHERE xe.CmtNhaXe = chuxe.Cmt AND xe.BienSoXe = ?";
    
    public static final int RESULT_EMPTY = 0;
    public static final int RESULT_ERROR_CMT = 1;
    public static final int RESULT_ERROR_SDT = 2;
    public static final int RESULT_ERROR_EMAIL = 3;
    public static final int RESULT_SUCCESS = 4;
    public static final int RESULT_ERROR_SQL = 5;

    @Override
    public int addCarOwner(CarOwner carOwner) {
        if (StringUtils.isNullOrEmpty(carOwner.getCmt()) || StringUtils.isNullOrEmpty(carOwner.getTen()) || StringUtils.isNullOrEmpty(carOwner.getNhaXe())
                || StringUtils.isNullOrEmpty(carOwner.getSdt()) || StringUtils.isNullOrEmpty(carOwner.getEmail()) || StringUtils.isNullOrEmpty(carOwner.getGioitinh())
                || StringUtils.isNullOrEmpty(carOwner.getDiaChi()) || carOwner.getNgaySinh() == null) {
            return RESULT_EMPTY;
        } else if (Utils.isCmt(carOwner.getCmt()) == false) {
            return RESULT_ERROR_CMT;
        } else if (Utils.isPhoneNumber(carOwner.getSdt()) == false) {
            return RESULT_ERROR_SDT;
        } else if (Utils.isAdressEmail(carOwner.getEmail()) == false) {
            return RESULT_ERROR_EMAIL;
        } else {
            try {
                Connection connection = Mysql.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(ADD_CAROWNER);
                pstmt.setString(1, carOwner.getCmt());
                pstmt.setString(2, carOwner.getTen());
                pstmt.setString(3, carOwner.getNhaXe());
                pstmt.setString(4, carOwner.getSdt());
                pstmt.setString(5, carOwner.getEmail());
                pstmt.setString(6, carOwner.getGioitinh());
                pstmt.setDate(7, carOwner.getNgaySinh());
                pstmt.setString(8, carOwner.getDiaChi());
                pstmt.setString(9, HashPassword.hashPass("1"));
                int check = pstmt.executeUpdate();
                if (check > 0) {
                    return RESULT_SUCCESS;
                }
            } catch (SQLException ex) {
                Logger.getLogger(MysqlCarOwnerDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return RESULT_ERROR_SQL;
    }

    @Override
    public int updateInforCarOwner(CarOwner carOwnerUpdate) {
        if (StringUtils.isNullOrEmpty(carOwnerUpdate.getTen()) || StringUtils.isNullOrEmpty(carOwnerUpdate.getNhaXe())
                || StringUtils.isNullOrEmpty(carOwnerUpdate.getSdt()) || StringUtils.isNullOrEmpty(carOwnerUpdate.getEmail()) || StringUtils.isNullOrEmpty(carOwnerUpdate.getGioitinh())
                || StringUtils.isNullOrEmpty(carOwnerUpdate.getDiaChi()) || carOwnerUpdate.getNgaySinh() == null) {
            return RESULT_EMPTY;
        } else if (Utils.isCmt(carOwnerUpdate.getCmt()) == false) {
            return RESULT_ERROR_CMT;
        } else if (Utils.isPhoneNumber(carOwnerUpdate.getSdt()) == false) {
            return RESULT_ERROR_SDT;
        } else if (Utils.isAdressEmail(carOwnerUpdate.getEmail()) == false) {
            return RESULT_ERROR_EMAIL;
        } else {
            try {
                Connection connection = Mysql.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(UPDATE_INFOR_CAROWNER);
                pstmt.setString(1, carOwnerUpdate.getTen());
                pstmt.setString(2, carOwnerUpdate.getNhaXe());
                pstmt.setString(3, carOwnerUpdate.getSdt());
                pstmt.setString(4, carOwnerUpdate.getEmail());
                pstmt.setString(5, carOwnerUpdate.getGioitinh());
                pstmt.setDate(6, carOwnerUpdate.getNgaySinh());
                pstmt.setString(7, carOwnerUpdate.getDiaChi());
                pstmt.setString(8, carOwnerUpdate.getCmt());
                int check = pstmt.executeUpdate();
                if (check > 0) {
                    return RESULT_SUCCESS;
                }
            } catch (SQLException ex) {
                Logger.getLogger(MysqlCarOwnerDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return RESULT_ERROR_SQL;
    }

    @Override
    public List<CarOwner> getAllListCarOwner() {
        List<CarOwner> listCarOwners = new ArrayList<>();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(GET_ALL_CAROWNER);
            while (rs.next()) {
                String cmtNhaXe = rs.getString(COLUMN_CMT_CAROWNER);
                String nhaXe = rs.getString(COLUMN_NHAXE_CAROWNER);
                PreparedStatement pstm = connection.prepareCall(GET_ALL_CAR_BY_CAROWNER);
                pstm.setString(1, cmtNhaXe);
                ResultSet rs1 = pstm.executeQuery();
                List<Car> listCars = new ArrayList<>();
                while (rs1.next()) {
                    int id = rs1.getInt("id");
                    String bienSoXe = rs1.getString("BienSoXe");
                    int soGhe = rs1.getInt("SoGhe");
                    Double giaVe = rs1.getDouble("GiaVe");
                    String loTrinh = rs1.getString("LoTrinh");
                    String lichTrinh = rs1.getString("LichTrinh");
                    Car car = new Car(id, bienSoXe, cmtNhaXe, soGhe, giaVe, loTrinh, lichTrinh);
                    listCars.add(car);
                }
                CarOwner carOwner = new CarOwner(cmtNhaXe, nhaXe, listCars);
                listCarOwners.add(carOwner);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarOwnerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCarOwners;
    }

    @Override
    public List<CarOwner> searchCarOwner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CarOwner> getAllInforCarOwner() {
        List<CarOwner> listCarOwners = new ArrayList<>();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(GET_ALL_CAROWNER);
            while (rs.next()) {
                String cmtNhaXe = rs.getString(COLUMN_CMT_CAROWNER);
                String ten = rs.getString(COLUMN_NAME_CAROWNER);
                String nhaXe = rs.getString(COLUMN_NHAXE_CAROWNER);
                String sdt = rs.getString(COLUMN_SDT_CAROWNER);
                String email = rs.getString(COLUMN_EMAIL_CAROWNER);
                String gioiTinh = rs.getString(COLUMN_GIOITINH_CAROWNER);
                Date ngaySinh = rs.getDate(COLUMN_NGAYSINH_CAROWNER);
                String diaChi = rs.getString(COLUMN_DIACHI_CAROWNER);
                CarOwner carOwner = new CarOwner(cmtNhaXe, ten, nhaXe, sdt, email, gioiTinh, ngaySinh, diaChi);
                listCarOwners.add(carOwner);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarOwnerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCarOwners;
    }

    @Override
    public List<String> getAllIdCarOwner() {
        List<String> listIdCarOwner = new ArrayList<>();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(GET_ALL_CAROWNER);
            while (rs.next()) {
                String cmtNhaXe = rs.getString(COLUMN_CMT_CAROWNER);
                listIdCarOwner.add(cmtNhaXe);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarOwnerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listIdCarOwner;
    }

    @Override
    public String getNameByIdCarOwner(String id) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(GET_NAME_BY_ID_CAROWNER);
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getString("HoTen");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarOwnerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @Override
    public List<Car> getAllCarByIdCarOwner(String id) {
        List<Car> listCars = new ArrayList<>();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(GET_ALL_CAR_BY_ID_CAROWNER);
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int stt = rs.getInt("Id");
                String bsx = rs.getString("BienSoXe");
                String chuXe = rs.getString("HoTen");
                int soGhe = rs.getInt("SoGhe");
                Double giaVe = rs.getDouble("GiaVe");
                String loTrinh = rs.getString("LoTrinh");
                String lichTrinh = rs.getString("LichTrinh");
                Car car = new Car(stt, bsx, chuXe, soGhe, giaVe, loTrinh, lichTrinh);
                listCars.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarOwnerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCars;
    }

    public void editCarOwner(CarOwner carOwner) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(EDIT_CAR_OWNER);
            pstm.setString(1, carOwner.getTen());
            pstm.setString(2, carOwner.getNhaXe());
            pstm.setString(3, carOwner.getSdt());
            pstm.setString(4, carOwner.getEmail());
            pstm.setString(5, carOwner.getGioitinh());
            pstm.setDate(6, carOwner.getNgaySinh());
            pstm.setString(7, carOwner.getDiaChi());
            pstm.setString(8, carOwner.getCmt());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi dữ liệu nhập vào.");
        }
    }

    @Override
    public CarOwner getInforCarOwner(String cmt) {
        CarOwner carOwner = new CarOwner();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(GET_CAR_OWNER);
            pstm.setString(1, cmt);
            ResultSet rs1 = pstm.executeQuery();
            while (rs1.next()) {
                String HoTen = rs1.getString("HoTen");
                String NhaXe = rs1.getString("NhaXe");
                String Sdt = rs1.getString("Sdt");
                String Email = rs1.getString("Email");
                String GioiTinh = rs1.getString("GioiTinh");
                Date NgaySinh = rs1.getDate("NgaySinh");
                String DiaChi = rs1.getString("DiaChi");
                carOwner = new CarOwner(cmt, HoTen, NhaXe, Sdt, Email, GioiTinh, NgaySinh, DiaChi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarOwnerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carOwner;
    }

    @Override
    public int loginCarOwner(String user, String pass) {
        if (StringUtils.isNullOrEmpty(user) || StringUtils.isNullOrEmpty(pass)) {
            return RESULT_EMPTY;
        } else {
            try {
                Connection connection = Mysql.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(CHECK_ACCOUNT_CAROWNER);
                pstmt.setString(1, user);
                pstmt.setString(2, HashPassword.hashPass(pass));
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return RESULT_LOGIN_SUCCESS;
                } else {
                    return RESULT_ACCOUNT_INCORECT;
                }
            } catch (SQLException ex) {
                Logger.getLogger(MysqlAdminDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return RESULT_ERROR_SQL;
    }

    @Override
    public String getGarageByBsx(String bsx) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(GET_GRAGE_BY_CMT);
            pstm.setString(1, bsx);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                return rs.getString("NhaXe");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarOwnerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

}
