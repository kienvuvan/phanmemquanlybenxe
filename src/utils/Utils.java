/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

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

    public static boolean isBsx(String bienSoXe) {
        String bsx_pattern = "^[0-9]{2}\\w\\d-[0-9]{4,5}";
        return bienSoXe.matches(bsx_pattern);
    }

    public static boolean isStringDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isStringInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static String reverseRoute(String loTrinh) {
        StringTokenizer stk = new StringTokenizer(loTrinh, "-");
        List<String> listToken = new ArrayList<>();
        while (stk.hasMoreTokens()) {
            listToken.add(stk.nextToken());
        }
        return listToken.get(1).trim() + " - " + listToken.get(0).trim();
    }

    public static String generateCarReturn(String lichTrinh) {
        StringTokenizer stk = new StringTokenizer(lichTrinh, "-");
        List<String> thoiGian = new ArrayList<>();
        while (stk.hasMoreTokens()) {
            thoiGian.add(stk.nextToken());
        }

        List<String> thoiGianDi = new ArrayList<>();
        StringTokenizer stk1 = new StringTokenizer(thoiGian.get(0), "h");
        while (stk1.hasMoreTokens()) {
            thoiGianDi.add(stk1.nextToken());
        }
        Time timeArrive = new Time(Integer.valueOf(thoiGianDi.get(0)), Integer.valueOf(thoiGianDi.get(1)), 0);

        List<String> thoiGianDen = new ArrayList<>();
        StringTokenizer stk2 = new StringTokenizer(thoiGian.get(1), "h");
        while (stk2.hasMoreTokens()) {
            thoiGianDen.add(stk2.nextToken());
        }
        Time timeUntil = new Time(Integer.valueOf(thoiGianDen.get(0)), Integer.valueOf(thoiGianDen.get(1)), 0);

        Time thoiGianBatDauVe = new Time(timeUntil.getTime() + 30 * 60 * 1000);
        Time thoiGianVe = new Time(thoiGianBatDauVe.getTime() + timeUntil.getTime() - timeArrive.getTime());
        System.out.println(thoiGianVe.toString());
        return thoiGianBatDauVe.getHours() + "h" + thoiGianBatDauVe.getMinutes() + "-" + thoiGianVe.getHours() + "h" + thoiGianVe.getMinutes();
    }

    public static boolean invalidTime(String lichTrinh){
        StringTokenizer stk = new StringTokenizer(lichTrinh, "-");
        List<String> thoiGian = new ArrayList<>();
        while (stk.hasMoreTokens()) {
            thoiGian.add(stk.nextToken());
        }

        List<String> thoiGianDi = new ArrayList<>();
        StringTokenizer stk1 = new StringTokenizer(thoiGian.get(0), "h");
        while (stk1.hasMoreTokens()) {
            thoiGianDi.add(stk1.nextToken());
        }
        Time timeArrive = new Time(Integer.valueOf(thoiGianDi.get(0)), Integer.valueOf(thoiGianDi.get(1)), 0);

        List<String> thoiGianDen = new ArrayList<>();
        StringTokenizer stk2 = new StringTokenizer(thoiGian.get(1), "h");
        while (stk2.hasMoreTokens()) {
            thoiGianDen.add(stk2.nextToken());
        }
        Time timeUntil = new Time(Integer.valueOf(thoiGianDen.get(0)), Integer.valueOf(thoiGianDen.get(1)), 0);
        if(timeUntil.getTime() < timeArrive.getTime()){
            return false;
        }else return true;
    }
    
    public static String generateCarTravelId(String bienSoXe, Date ngayDi) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ngayDi);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        String dayStr = "";
        String monthStr = "";
        String yearStr = "";
        if (day < 10) {
            dayStr = "0" + day;
        } else {
            dayStr = day + "";
        }
        if (month < 10) {
            monthStr = "0" + month;
        } else {
            monthStr = month + "";
        }
        yearStr = (year - 2000) + "";
        return bienSoXe + dayStr + monthStr + yearStr;
    }

    public static List<String> cutRoute(String loTrinh){
        StringTokenizer stk = new StringTokenizer(loTrinh, "-");
        List<String> listToken = new ArrayList<>();
        while (stk.hasMoreTokens()) {
            listToken.add(stk.nextToken());
        }
        return listToken;
    }
    
    public static void main(String[] args) {
        Date dateNow = new Date(Calendar.getInstance().getTimeInMillis());
        System.out.println(generateCarTravelId("17M2-1234", dateNow));
////        System.out.println(isCmt("034097001988"));
//        System.out.println(isBsx("17M2-12345"));
//        System.out.println(reverseRoute("Hà Nội - Thái Bình"));
//        System.out.println(generateCarReturn("6h31-9h00"));
    }
}
