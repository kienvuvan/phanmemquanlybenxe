/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.model;

import account.model.User;
import admin.dao.AdminDao;
import admin.dao.MysqlAdminDao;
import java.sql.Date;

/**
 *
 * @author kienanh2903
 */
public class Admin extends User{

    private String matKhau;

    public Admin(){
    }
    
    public Admin(String cmt, String ten, String gioiTinh, Date ngaySinh, String sdt, String email, String diaChi) {
        super(cmt,ten, gioiTinh, ngaySinh, sdt, email, diaChi);
    }

    public Admin(String cmt, String matKhau) {
        super();
        this.matKhau = matKhau;
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

    public String getNameAdmin(String cmt) {
        return adminDao().getNameAdmin(cmt);
    }
    
}
