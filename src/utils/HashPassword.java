/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linh
 */
public class HashPassword {
    /**
     * algorithm to hash password of user
     * @param password The password to hash
     * @return Hash String of password
     * @exception NoSuchAlgorithmException if system not find md5 algorithm.
     */
    public static String hashPass(String password){
        StringBuilder hexString = new StringBuilder();
    	
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            
            byte byteData[] = md.digest();
            //convert the byte to hex format method 1
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
                       
            //convert the byte to hex format method 2
            
            for (int i=0;i<byteData.length;i++) {
                String hex=Integer.toHexString(0xff & byteData[i]);
                if(hex.length()==1) hexString.append('0');
                hexString.append(hex);
            }
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
     return hexString.toString();
    }
}
