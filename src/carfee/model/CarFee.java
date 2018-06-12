/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carfee.model;

import car.dao.MysqlCarDao;
import carfee.dao.CarFeeDao;
import carfee.dao.MysqlCarFeeDao;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author DELL
 */
public class CarFee {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCmtAdmin() {
        return cmtAdmin;
    }

    public void setCmtAdmin(String cmtAdmin) {
        this.cmtAdmin = cmtAdmin;
    }
    private String idCarOwner;
    private String name;
    private Date time;
    private double fee;
    private String cmtAdmin;

    public CarFee() {
    }

    public CarFee(String idCarOwner, Date time, double fee) {
        this.idCarOwner = idCarOwner;
        this.time = time;
        this.fee = fee;
    }

    public CarFee(String idCarOwner, String name, Date time, double fee, String cmtAdmin) {
        this.idCarOwner = idCarOwner;
        this.name = name;
        this.time = time;
        this.fee = fee;
        this.cmtAdmin = cmtAdmin;
    }

    public String getIdCarOwner() {
        return idCarOwner;
    }

    public void setIdCarOwner(String idCarOwner) {
        this.idCarOwner = idCarOwner;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
    
    public CarFeeDao carFeeDao() {
        return new MysqlCarFeeDao();
    }
    
    public List<CarFee> getAllInforCarFee() {
        return carFeeDao().getAllInforCarFee();
    }
    
    public boolean parkingFeeCar(String cmtAdmin) {
        return carFeeDao().parkingFeeCar(cmtAdmin);
    }
    
    public int checkDateParkingFee(Date date) {
        return carFeeDao().checkDateParkingFee(date);
    }
    
    public List<CarFee> searchInforCarFee(String keySearch) {
        return carFeeDao().searchInforCarFee(keySearch);
    }
}
