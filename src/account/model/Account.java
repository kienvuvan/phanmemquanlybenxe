/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account.model;

/**
 *
 * @author DELL
 */
public class Account {
    String id;
    String pass;
    String typeOfAcc;

    public Account() {
    }

    public Account(String id, String pass, String typeOfAcc) {
        this.id = id;
        this.pass = pass;
        this.typeOfAcc = typeOfAcc;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTypeOfAcc() {
        return typeOfAcc;
    }

    public void setTypeOfAcc(String typeOfAcc) {
        this.typeOfAcc = typeOfAcc;
    }
    
}
