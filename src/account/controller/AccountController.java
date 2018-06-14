/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account.controller;

import account.dao.MysqlAccountDao;
import account.model.Account;

/**
 *
 * @author DELL
 */
public class AccountController {
    
    public AccountController() {
        
    }


    MysqlAccountDao accDao = new MysqlAccountDao();
    public static Account acc = new Account();

    public boolean checkOwnerAcc() {
        return accDao.checkOwnerAcc(acc.getId(), acc.getPass());
    }
    
    public int setOwnerPass(Account acc, String passNew, String passAgain) {
        return accDao.setOwnerPass(acc, passNew, passAgain);
    }
    
    public int setAdminPass(Account acc, String passNew, String passAgain) {
        return accDao.setAdminPass(acc, passNew, passAgain);
    }
    
    public int forgotPassAdmin(String cmt, String email) {
        return accDao.forgotPassAdmin(cmt, email);
    }
    
    public int forgotPassCarOwner(String cmt, String email) {
        return accDao.forgotPassCarOwner(cmt, email);
    }
}
