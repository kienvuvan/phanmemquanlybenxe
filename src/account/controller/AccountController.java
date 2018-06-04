/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account.controller;

import account.dao.AccountDao;
import account.model.Account;
import carowner.controller.CarOwnerSubController;

/**
 *
 * @author DELL
 */
public class AccountController {

    public AccountController() {
        
    }


    AccountDao accDao = new AccountDao();
    public static Account acc = new Account();

    public boolean checkOwnerAcc() {
        return accDao.checkOwnerAcc(acc.getId(), acc.getPass());
    }

    public int loginOwnerAcc() {
            if (checkOwnerAcc()) {
                setOwnerAcc();
                return 1;
            } else {
                return 0;
            }
        }

    public void setOwnerAcc() {
        new CarOwnerSubController(acc.getId()).setOwnerAccount();
    }

//    public void setOwnerNewPass(String pass) {
//        accDao.setOwnerPass(acc, pass);
//        acc.setPass(pass);
//    }

    public void logOut() {
        acc = null;
        CarOwnerSubController.acc = null;
        //AdminController.acc = null;
    }
    
    public int setOwnerPass(Account acc, String passNew, String passAgain) {
        return accDao.setOwnerPass(acc, passNew, passAgain);
    }
    
    public int setAdminPass(Account acc, String passNew, String passAgain) {
        return accDao.setAdminPass(acc, passNew, passAgain);
    }
}
