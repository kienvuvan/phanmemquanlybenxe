/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carfee.dao;

import carfee.model.CarFeeDetail;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mysql.Mysql;

/**
 *
 * @author kienanh2903
 */
public class MysqlCarFeeDetailDao implements CarFeeDetailDao{
    
    private static final String GET_CAR_FEE_DETAIL_BY_CAROWNER = "SELECT * FROM chitietchiphi WHERE CmtChuXe =? AND Ngay =?";
    
    @Override
    public List<CarFeeDetail> getCarFeeDetailByCarOwner(String cmtChuXe, Date ngayThu) {
        List<CarFeeDetail> listCarFeeDetails = new ArrayList<>();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(GET_CAR_FEE_DETAIL_BY_CAROWNER);
            pstm.setString(1, cmtChuXe);
            pstm.setDate(2, ngayThu);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                String bsx = rs.getString("BienSoXe");
                int soChuyen = rs.getInt("SoChuyen");
                double phi = rs.getDouble("Phi");
                CarFeeDetail carFeeDetail = new CarFeeDetail(bsx, soChuyen, phi);
                listCarFeeDetails.add(carFeeDetail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlCarFeeDetailDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCarFeeDetails;
    }
    
}
