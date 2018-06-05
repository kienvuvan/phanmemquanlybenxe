/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.dao;

import admin.model.Admin;
import com.mysql.cj.util.StringUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mysql.Mysql;
import utils.HashPassword;
import utils.Utils;

/**
 *
 * @author kienanh2903
 */
public class MysqlAdminDao implements AdminDao {

    private static final String CHECK_ACCOUNT_ADMIN = "SELECT Cmt, MatKhau FROM admin WHERE Cmt = ? AND MatKhau =?";
    private static final String GET_INFOR_ACCOUNT_ADMIN = "SELECT * FROM admin WHERE Cmt = ?";
    private static final String UPDATE_INFOR_ADMIN = "UPDATE admin SET HoTen = ?, GioiTinh = ?, NgaySinh = ?, Sdt = ?, Email = ?, DiaChi =? WHERE Cmt = ?";

    public static final int RESULT_EMPTY = 0;
    public static final int RESULT_LOGIN_SUCCESS = 1;
    public static final int RESULT_ACCOUNT_INCORECT = 2;
    public static final int RESULT_ERROR_SQL = 3;
    public static final int RESULT_ERROR_SDT = 4;
    public static final int RESULT_ERROR_EMAIL = 5;
    public static final int RESULT_SUCCESS = 6;

    @Override
    public int loginAdmin(String user, String pass) {
        if (StringUtils.isNullOrEmpty(user) || StringUtils.isNullOrEmpty(pass)) {
            return RESULT_EMPTY;
        } else {
            try {
                Connection connection = Mysql.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(CHECK_ACCOUNT_ADMIN);
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
    public Admin getInforAdmin(String cmt) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(GET_INFOR_ACCOUNT_ADMIN);
            pstmt.setString(1, cmt);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String ten = rs.getString("HoTen");
                String gioiTinh = rs.getString("GioiTinh");
                Date ngaySinh = rs.getDate("NgaySinh");
                String sdt = rs.getString("Sdt");
                String email = rs.getString("Email");
                String diaChi = rs.getString("DiaChi");
                Admin admin = new Admin(cmt, ten, gioiTinh, ngaySinh, sdt, email, diaChi);
                return admin;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlAdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Admin(cmt, "", "", null, "", "", "");
    }

    @Override
    public int updateInforAdmin(Admin adminUpdate) {
        if (StringUtils.isNullOrEmpty(adminUpdate.getTen()) || StringUtils.isNullOrEmpty(adminUpdate.getGioiTinh()) || adminUpdate.getNgaySinh() == null
                || StringUtils.isNullOrEmpty(adminUpdate.getSdt()) || StringUtils.isNullOrEmpty(adminUpdate.getEmail()) || StringUtils.isNullOrEmpty(adminUpdate.getDiaChi())) {
            return RESULT_EMPTY;
        } else if (Utils.isPhoneNumber(adminUpdate.getSdt()) == false) {
            return RESULT_ERROR_SDT;
        } else if (Utils.isAdressEmail(adminUpdate.getEmail()) == false) {
            return RESULT_ERROR_EMAIL;
        } else {
            try {
                Connection connection = Mysql.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(UPDATE_INFOR_ADMIN);
                pstmt.setString(1, adminUpdate.getTen());
                pstmt.setString(2, adminUpdate.getGioiTinh());
                pstmt.setDate(3, adminUpdate.getNgaySinh());
                pstmt.setString(4, adminUpdate.getSdt());
                pstmt.setString(5, adminUpdate.getEmail());
                pstmt.setString(6, adminUpdate.getDiaChi());
                pstmt.setString(7, adminUpdate.getCmt());
                int check = pstmt.executeUpdate();
                if(check > 0){
                    return RESULT_SUCCESS;
                }
            } catch (SQLException ex) {
                Logger.getLogger(MysqlAdminDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            return RESULT_ERROR_SQL;
        }
    }
}
