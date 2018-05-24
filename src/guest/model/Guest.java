/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guest.model;

import guest.dao.GuestDao;
import guest.dao.MysqlGuestDao;
import java.sql.Date;

/**
 *
 * @author kienanh2903
 */
public class Guest {

    private String cmt;
    private String ten;
    private Date ngaySinh;
    private String gioiTinh;
    private String sdt;
    private String email;
    private String diaChi;

    public Guest() {
    }

    public Guest(String cmt, String ten, Date ngaySinh, String gioiTinh, String sdt, String email, String diaChi) {
        this.cmt = cmt;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.email = email;
        this.diaChi = diaChi;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public GuestDao guestDao() {
        return new MysqlGuestDao();
    }
    
    public boolean checkGuestExit(String cmt) {
        return guestDao().checkGuestExit(cmt);
    }
    
    public boolean updateInforGuest(Guest guestUpdate) {
        return guestDao().updateInforGuest(guestUpdate);
    }
    
    public boolean addGuest(Guest guest) {
        return guestDao().addGuest(guest);
    }
}
