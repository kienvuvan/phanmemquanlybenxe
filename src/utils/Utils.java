/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author kienanh2903
 */
public class Utils {
    public static boolean isAdressEmail(String email) {
        String email_pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
        return email.matches(email_pattern);
    }
    
    public static boolean isPhoneNumber(String phone){
        String phone_pattern ="^0(1\\d{9}|9\\d{8})";
        return phone.matches(phone_pattern);
    }
    
    public static boolean isCmt(String cmt){
        String cmt_pattern1 = "^[0-9]{9}";
        String cmt_pattern2 = "^[0-9]{13}";
        if(cmt.matches(cmt_pattern1) == false && cmt.matches(cmt_pattern2) == false){
            return false;
        }else return true;
    }
}
