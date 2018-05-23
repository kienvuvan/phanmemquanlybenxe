/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.model;

import car.dao.CarDao;
import car.dao.MysqlCarDao;
import java.util.List;

/**
 *
 * @author kienanh2903
 */
public class Car {
    private int id; 
    private String bsx;
    private String cmtNhaXe;
    private int soGhe;
    private Double giaVe;
    private String loTrinh;
    private String lichTrinh;

    public Car(int id, String bsx, String cmtNhaXe, int soGhe, Double giaVe, String loTrinh, String lichTrinh) {
        this.id = id;
        this.bsx = bsx;
        this.cmtNhaXe = cmtNhaXe;
        this.soGhe = soGhe;
        this.giaVe = giaVe;
        this.loTrinh = loTrinh;
        this.lichTrinh = lichTrinh;
    }

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBsx() {
        return bsx;
    }

    public void setBsx(String bsx) {
        this.bsx = bsx;
    }

    public String getCmtNhaXe() {
        return cmtNhaXe;
    }

    public void setCmtNhaXe(String cmtNhaXe) {
        this.cmtNhaXe = cmtNhaXe;
    }

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    public Double getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(Double giaVe) {
        this.giaVe = giaVe;
    }

    public String getLoTrinh() {
        return loTrinh;
    }

    public void setLoTrinh(String loTrinh) {
        this.loTrinh = loTrinh;
    }

    public String getLichTrinh() {
        return lichTrinh;
    }

    public void setLichTrinh(String lichTrinh) {
        this.lichTrinh = lichTrinh;
    }
    
    public CarDao carDao() {
        return MysqlCarDao.getInstance();
    }
    
    public List<Car> searchCarForBookTicket(String keySearch) {
        return carDao().searchCarForBookTicket(keySearch);
    }
}
