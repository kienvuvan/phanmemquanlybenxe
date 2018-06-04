/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account.dao;

import account.model.Account;
import com.mysql.cj.util.StringUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mysql.Mysql;
import utils.HashPassword;

/**
 *
 * @author DELL
 */
public class AccountDao {

    private static final String CHECK_OWNER_ACC = "SELECT COUNT(*) FROM chuxe WHERE Cmt = ? AND MatKhau = ? ";
    private static final String CHECK_ADMIN_ACC = "SELECT COUNT(*) FROM admin WHERE Cmt = ? AND MatKhau = ? ";
    private static final String GET_OWNER_ACC = "SELECT * FROM chuxe WHERE Cmt = ? AND MatKhau = ? ";
    private static final String CHANGE_OWNER_PASS = "UPDATE chuxe SET MatKhau = ? WHERE Cmt = ?";
    private static final String CHANGE_ADMIN_PASS = "UPDATE admin SET MatKhau = ? WHERE Cmt = ?";
    
    public static final int RESULT_EMPTY = 0;
    public static final int RESULT_ACCOUNT_INCORRECT = 1;
    public static final int RESULT_PASSAGAIN_NOT_SAME = 2;
    public static final int RESULT_SUCCESS = 3;
    public static final int RESULT_SQL_ERROR = 4;

    public boolean checkOwnerAcc(String cmt, String pass) {
        int count = 0;
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(CHECK_OWNER_ACC);
            pstm.setString(1, cmt);
            pstm.setString(2, HashPassword.hashPass(pass));
            ResultSet rs1 = pstm.executeQuery();

            while (rs1.next()) {
                count = rs1.getInt(1);
            }
            if (count > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkAdminAcc(String cmt, String pass) {
        int count = 0;
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(CHECK_ADMIN_ACC);
            pstm.setString(1, cmt);
            pstm.setString(2, HashPassword.hashPass(pass));
            ResultSet rs1 = pstm.executeQuery();

            while (rs1.next()) {
                count = rs1.getInt(1);
            }
            if (count > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Account getOwnerAccount(String cmt, String pass) {
        Account acc = new Account();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(GET_OWNER_ACC);
            pstm.setString(1, cmt);
            pstm.setString(2, pass);
            ResultSet rs1 = pstm.executeQuery();
            while (rs1.next()) {
                acc.setId(cmt);
                acc.setPass(pass);
            }
            return acc;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int setOwnerPass(Account acc, String passNew, String passAgain) {
        if (StringUtils.isNullOrEmpty(acc.getPass()) || StringUtils.isNullOrEmpty(passNew) || StringUtils.isNullOrEmpty(passAgain)) {
            return RESULT_EMPTY;
        } else {
            if (checkOwnerAcc(acc.getId(), acc.getPass()) == false) {
                return RESULT_ACCOUNT_INCORRECT;
            } else if (passNew.equalsIgnoreCase(passAgain) == false) {
                return RESULT_PASSAGAIN_NOT_SAME;
            } else {
                try {
                    Connection connection = Mysql.getInstance().getConnection();
                    PreparedStatement pstm = connection.prepareStatement(CHANGE_OWNER_PASS);
                    pstm.setString(1, HashPassword.hashPass(passNew));
                    pstm.setString(2, acc.getId());
                    int check = pstm.executeUpdate();
                    if (check > 0) {
                        return RESULT_SUCCESS;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return RESULT_SQL_ERROR;
    }
    
    public int setAdminPass(Account acc, String passNew, String passAgain) {
        if (StringUtils.isNullOrEmpty(acc.getPass()) || StringUtils.isNullOrEmpty(passNew) || StringUtils.isNullOrEmpty(passAgain)) {
            return RESULT_EMPTY;
        } else {
            if (checkAdminAcc(acc.getId(), acc.getPass()) == false) {
                return RESULT_ACCOUNT_INCORRECT;
            } else if (passNew.equalsIgnoreCase(passAgain) == false) {
                return RESULT_PASSAGAIN_NOT_SAME;
            } else {
                try {
                    Connection connection = Mysql.getInstance().getConnection();
                    PreparedStatement pstm = connection.prepareStatement(CHANGE_ADMIN_PASS);
                    pstm.setString(1, HashPassword.hashPass(passNew));
                    pstm.setString(2, acc.getId());
                    int check = pstm.executeUpdate();
                    if (check > 0) {
                        return RESULT_SUCCESS;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return RESULT_SQL_ERROR;
    }
}
