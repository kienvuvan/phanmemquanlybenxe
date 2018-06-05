/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import admin.model.Admin;

/**
 *
 * @author kienanh2903
 */
public class AdminController {
    private Admin admin;

    public AdminController() {
        admin = new Admin();
    }
    
    public int loginAdmin(String user, String pass) {
        return admin.loginAdmin(user, pass);
    }
    
    public Admin getInforAdmin(String cmt) {
        return admin.getInforAdmin(cmt);
    }
    
    public int updateInforAdmin(Admin adminUpdate) {
        return admin.updateInforAdmin(adminUpdate);
    }
    
    public String getNameAdmin(String cmt) {
        return admin.getNameAdmin(cmt);
    }
}
