/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.model;

import admin.dao.AdminDao;
import admin.dao.MysqlAdminDao;
import java.sql.Date;

/**
 *
 * @author kienanh2903
 */
public class Admin {

    private String cmt;
    private String ten;
    private String gioiTinh;
    private Date ngaySinh;
    private String sdt;
    private String email;
    private String diaChi;
    private String matKhau;

    public Admin() {
    }

    public Admin(String cmt, String ten, String gioiTinh, Date ngaySinh, String sdt, String email, String diaChi) {
        this.cmt = cmt;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.email = email;
        this.diaChi = diaChi;
    }

    public Admin(String cmt, String matKhau) {
        this.cmt = cmt;
        this.matKhau = matKhau;
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

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
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

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public AdminDao adminDao() {
        return new MysqlAdminDao();
    }

    public int loginAdmin(String user, String pass) {
        return adminDao().loginAdmin(user, pass);
    }

    public Admin getInforAdmin(String cmt) {
        return adminDao().getInforAdmin(cmt);
    }

    public int updateInforAdmin(Admin adminUpdate) {
        return adminDao().updateInforAdmin(adminUpdate);
    }

}
