/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carowner.dao;

import mysql.Mysql;
import carowner.model.CarOwner;
import com.mysql.cj.util.StringUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Utils;

/**
 *
 * @author kienanh2903
 */
public class MysqlCarOwnerDao implements CarOwnerDao {

    private static final String ADD_CAROWNER = "INSERT INTO chuxe VALUES (?,?,?,?,?,?,?,?)";

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

}
