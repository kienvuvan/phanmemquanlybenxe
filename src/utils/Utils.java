/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author kienanh2903
 */
public class Utils {

    public static boolean isAdressEmail(String email) {
        String email_pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
        return email.matches(email_pattern);
    }

    public static boolean isPhoneNumber(String phone) {
        String phone_pattern = "^0(1\\d{9}|9\\d{8})";
        return phone.matches(phone_pattern);
    }

    public static boolean isCmt(String cmt) {
        String cmt_pattern1 = "^[0-9]{9}";
        String cmt_pattern2 = "^[0-9]{12}";
        if (cmt.matches(cmt_pattern1) == false && cmt.matches(cmt_pattern2) == false) {
            return false;
        } else {
            return true;
        }
    }

    public static String generateCarTravelId(String bienSoXe, Date ngayDi) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ngayDi);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        String dayStr = "";
        String monthStr = "";
        String yearStr = "";
        if (day < 10) {
            dayStr = "0" + day;
        }else{
            dayStr = day + "";
        }
        if (month < 10) {
            monthStr = "0" + month;
        }else{
            monthStr = month + "";
        }
        yearStr = (year-2000)+"";
        return bienSoXe+dayStr+monthStr+yearStr;
    }

    public static void main(String[] args) {
        Date dateNow = new Date(Calendar.getInstance().getTimeInMillis());
        System.out.println(generateCarTravelId("17M2-1234", dateNow));
        System.out.println(isCmt("034097001988"));
    }
}
