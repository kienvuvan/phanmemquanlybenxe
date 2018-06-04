/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carfee.dao;

import carfee.model.CarFee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mysql.Mysql;

/**
 *
 * @author DELL
 */
public class CarFeeDao {

    private static final String GET_CARFEE_FOR_OWNER = "SELECT * FROM chiphi, xe WHERE chiphi.BienSoXe = xe.BienSoXe AND xe.CmtNhaXe = ?";
    private static final String SEARCH_CARFEE_FOR_OWNER = "SELECT * FROM chiphi, xe WHERE chiphi.BienSoXe = xe.BienSoXe AND "
            + "xe.CmtNhaXe = ? AND xe.BienSoXe LIKE ? AND chiphi.Ngay BETWEEN ? AND ?";

    public List<CarFee> getCarFeeFofOwner(String cmt) {
        List<CarFee> feeList = new ArrayList<>();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(GET_CARFEE_FOR_OWNER);
            pstm.setString(1, cmt );
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String carID = rs.getString("BienSoXe");
                double fee = rs.getDouble("Phi");
                String time = rs.getString("Ngay");
                CarFee carFee = new CarFee(carID, time, fee);
                feeList.add(carFee);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarFeeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return feeList;
    }
    
    public List<CarFee> searchFeeFofOwner(String cmt, String carID, String timeStart, String timeEnd) {
        List<CarFee> feeList = new ArrayList<>();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(SEARCH_CARFEE_FOR_OWNER);
            pstm.setString(1, cmt);
            pstm.setString(2, "%" + carID + "%");
            pstm.setString(3,"'" + timeStart + "'");
            pstm.setString(4, "'" + timeEnd + "'");
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String carID1 = rs.getString("BienSoXe");
                double fee = rs.getDouble("Phi");
                String time = rs.getString("Ngay");
                CarFee carFee = new CarFee(carID1, time, fee);
                feeList.add(carFee);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Lỗi dữ liệu nhập vào.");
        }
        return feeList;
    }

}
