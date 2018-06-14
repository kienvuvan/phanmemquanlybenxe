/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notification.model;

import notification.dao.MysqlNotificationDao;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import notification.dao.NotificationDao;

/**
 *
 * @author kienanh2903
 */
public class Notification {
    private int id;
    private Timestamp ngayDang;
    private String tieuDe;
    private String noiDung;
    private String cmtAdmin;

    public Notification() {
    }

    public Notification(Timestamp ngayDang, String tieuDe, String noiDung, String cmtAdmin) {
        this.ngayDang = ngayDang;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.cmtAdmin = cmtAdmin;
    }

    public Notification(int id, Timestamp ngayDang, String tieuDe, String noiDung, String cmtAdmin) {
        this.id = id;
        this.ngayDang = ngayDang;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.cmtAdmin = cmtAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(Timestamp ngayDang) {
        this.ngayDang = ngayDang;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getCmtAdmin() {
        return cmtAdmin;
    }

    public void setCmtAdmin(String cmtAdmin) {
        this.cmtAdmin = cmtAdmin;
    }
    
    public NotificationDao informationDao() {
        return new MysqlNotificationDao();
    }
    
    public List<Notification> getAllInfor() {
        return informationDao().getAllInfor();
    }
    
    public int postNew(Notification information) {
        return informationDao().postNew(information);
    }
    
    public int updateNew(Notification information) {
        return informationDao().updateNew(information);
    }
    
    public boolean deleteNew(int id) {
        return informationDao().deleteNew(id);
    }
}
