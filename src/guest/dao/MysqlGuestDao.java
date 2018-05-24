/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guest.dao;

import guest.model.Guest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mysql.Mysql;

/**
 *
 * @author kienanh2903
 */
public class MysqlGuestDao implements GuestDao {

    private static final String CHECK_GUEST_EXIT = "SELECT COUNT(*) FROM hanhkhach WHERE Cmt = ?";
    private static final String UPDATE_INFOR_GUEST = "UPDATE hanhkhach set HoTen = ?, NgaySinh = ?, GioiTinh = ?, Sdt = ?, Email = ?, DiaChi =  ? WHERE Cmt = ?";
    private static final String ADD_GUEST = "INSERT INTO hanhkhach VALUES (?,?,?,?,?,?,?)";
    
    @Override
    public boolean checkGuestExit(String cmt) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(CHECK_GUEST_EXIT);
            pstm.setString(1, cmt);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlGuestDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateInforGuest(Guest guestUpdate) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(UPDATE_INFOR_GUEST);
            pstm.setString(1, guestUpdate.getTen());
            pstm.setDate  (2, guestUpdate.getNgaySinh());
            pstm.setString(3, guestUpdate.getGioiTinh());
            pstm.setString(4, guestUpdate.getSdt());
            pstm.setString(5, guestUpdate.getEmail());
            pstm.setString(6, guestUpdate.getDiaChi());
            pstm.setString(7, guestUpdate.getCmt());
            int check = pstm.executeUpdate();
            if(check >0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlGuestDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean addGuest(Guest guest) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(ADD_GUEST);
            pstm.setString(1, guest.getCmt());
            pstm.setString(2, guest.getTen());
            pstm.setDate  (3, guest.getNgaySinh());
            pstm.setString(4, guest.getGioiTinh());
            pstm.setString(5, guest.getSdt());
            pstm.setString(6, guest.getEmail());
            pstm.setString(7, guest.getDiaChi());
            int check = pstm.executeUpdate();
            if(check >0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlGuestDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
