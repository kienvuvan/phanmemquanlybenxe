/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package information.dao;

import information.model.Information;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mysql.Mysql;

/**
 *
 * @author kienanh2903
 */
public class MysqlInformationDao implements InformationDao{
    
    private static final String GET_ALL_INFOR = "SELECT * FROM thongbao";

    @Override
    public List<Information> getAllInfor() {
        List<Information> listInformations = new ArrayList<>();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(GET_ALL_INFOR);
            while(rs.next()){
                int id = rs.getInt("id");
                Date ngayDang = rs.getDate("Ngay");
                String tieuDe = rs.getString("TieuDe");
                String noiDung = rs.getString("NoiDung");
                String cmt = rs.getString("CmtNhanVien");
                Information information = new Information(id, ngayDang, tieuDe, noiDung, cmt);
                listInformations.add(information);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlInformationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listInformations;
    }
    
}
