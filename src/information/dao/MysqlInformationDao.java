/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package information.dao;

import com.mysql.cj.util.StringUtils;
import information.model.Information;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mysql.Mysql;

/**
 *
 * @author kienanh2903
 */
public class MysqlInformationDao implements InformationDao {

    private static final String GET_ALL_INFOR = "SELECT * FROM thongbao ORDER BY Ngay DESC";
    private static final String POST_NEW = "INSERT INTO thongbao VALUES(?,?,?,?,?)";
    private static final String GET_MAX_ID = "SELECT MAX(id) FROM thongbao";
    private static final String CHECK_POST = "SELECT COUNT(*) FROM thongbao WHERE TieuDe =? AND NoiDung = ?";

    public static final int RESULT_EMPTY = 0;
    public static final int RESULT_POST_SAME = 1;
    public static final int RESULT_POST_SUCCESS = 2;
    public static final int RESULT_SQL_ERROR = 3;

    @Override
    public List<Information> getAllInfor() {
        List<Information> listInformations = new ArrayList<>();
        try {
            Connection connection = Mysql.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(GET_ALL_INFOR);
            while (rs.next()) {
                int id = rs.getInt("id");
                Timestamp ngayDang = rs.getTimestamp("Ngay");
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

    @Override
    public int postNew(Information information) {
        if (StringUtils.isNullOrEmpty(information.getTieuDe()) || StringUtils.isNullOrEmpty(information.getNoiDung())) {
            return RESULT_EMPTY;
        } else if (checkPost(information)) {
            return RESULT_POST_SAME;
        } else {
            try {
                Connection connection = Mysql.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareCall(POST_NEW);
                pstm.setInt(1, getMaxId() + 1);
                pstm.setTimestamp(2, information.getNgayDang());
                pstm.setString(3, information.getTieuDe());
                pstm.setString(4, information.getNoiDung());
                pstm.setString(5, information.getCmtAdmin());
                int check = pstm.executeUpdate();
                if (check > 0) {
                    return RESULT_POST_SUCCESS;
                }
            } catch (SQLException ex) {
                Logger.getLogger(MysqlInformationDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            return RESULT_SQL_ERROR;
        }
    }

    public int getMaxId() {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(GET_MAX_ID);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlInformationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

    public boolean checkPost(Information information) {
        try {
            Connection connection = Mysql.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareCall(CHECK_POST);
            pstm.setString(1, information.getTieuDe());
            pstm.setString(2, information.getNoiDung());
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) > 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlInformationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
