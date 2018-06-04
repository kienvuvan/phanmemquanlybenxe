/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.dao;

import com.mysql.cj.util.StringUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mysql.Mysql;
import utils.HashPassword;

/**
 *
 * @author kienanh2903
 */
public class MysqlAdminDao implements AdminDao {

    private static final String CHECK_ACCOUNT_ADMIN = "SELECT Cmt, MatKhau FROM admin WHERE Cmt = ? AND MatKhau =?";

    public static final int RESULT_EMPTY = 0;
    public static final int RESULT_LOGIN_SUCCESS = 1;
    public static final int RESULT_ACCOUNT_INCORECT = 2;
    public static final int RESULT_ERROR_SQL = 3;

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
                }else{
                    return RESULT_ACCOUNT_INCORECT;
                }
            } catch (SQLException ex) {
                Logger.getLogger(MysqlAdminDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return RESULT_ERROR_SQL;
    }

    public static void main(String[] args) {
        MysqlAdminDao mad = new MysqlAdminDao();
        System.out.println(mad.loginAdmin("tuyen1997", "2"));
    }
}
