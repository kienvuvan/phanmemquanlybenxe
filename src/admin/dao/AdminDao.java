/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.dao;

import admin.model.Admin;

/**
 *
 * @author kienanh2903
 */
public interface AdminDao {
    int loginAdmin(String user, String pass);
    
    Admin getInforAdmin(String cmt);
    
    int updateInforAdmin(Admin adminUpdate);
}
