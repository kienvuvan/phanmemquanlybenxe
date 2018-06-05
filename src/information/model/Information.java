/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package information.model;

import information.dao.InformationDao;
import information.dao.MysqlInformationDao;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author kienanh2903
 */
public class Information {
    private int id;
    private Timestamp ngayDang;
    private String tieuDe;
    private String noiDung;
    private String cmtAdmin;

    public Information() {
    }

    public Information(Timestamp ngayDang, String tieuDe, String noiDung, String cmtAdmin) {
        this.ngayDang = ngayDang;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.cmtAdmin = cmtAdmin;
    }

    public Information(int id, Timestamp ngayDang, String tieuDe, String noiDung, String cmtAdmin) {
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
    
    public InformationDao informationDao() {
        return new MysqlInformationDao();
    }
    
    public List<Information> getAllInfor() {
        return informationDao().getAllInfor();
    }
    
    public int postNew(Information information) {
        return informationDao().postNew(information);
    }
}
