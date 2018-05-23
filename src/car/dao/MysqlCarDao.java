/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.dao;

import car.model.Car;
import java.sql.Connection;
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
public class MysqlCarDao implements CarDao {

    private static final String SEARCH_CAR_FOR_BOOKTICKET = "SELECT * FROM xe, chuxe WHERE xe.CmtNhaXe = chuxe.Cmt AND "
            + "(chuxe.NhaXe LIKE ? OR BienSoXe LIKE ? OR SoGhe LIKE ? \n"
            + "OR LoTrinh LIKE ? OR LichTrinh LIKE ? OR GiaVe LIKE ? );";
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
            pstm.setString(1, "%"+keySearch+"%");
            pstm.setString(2, "%"+keySearch+"%");
            pstm.setString(3, "%"+keySearch+"%");
            pstm.setString(4, "%"+keySearch+"%");
            pstm.setString(5, "%"+keySearch+"%");
            pstm.setString(6, "%"+keySearch+"%");
            ResultSet rs = pstm.executeQuery();
            int i=0;
            while(rs.next()){
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
}
