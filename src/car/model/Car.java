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

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public String getThoiGianDo() {
        return thoiGianDo;
    }

    public void setThoiGianDo(String thoiGianDo) {
        this.thoiGianDo = thoiGianDo;
    }
    private int id; 
    private String bsx;
    private String cmtNhaXe;
    private int soGhe;
    private Double giaVe;
    private String loTrinh;
    private String lichTrinh;
    private String viTri;
    private String thoiGianDo;

    public Car(int id, String bsx, String cmtNhaXe, int soGhe, Double giaVe, String loTrinh, String lichTrinh) {
        this.id = id;
        this.bsx = bsx;
        this.cmtNhaXe = cmtNhaXe;
        this.soGhe = soGhe;
        this.giaVe = giaVe;
        this.loTrinh = loTrinh;
        this.lichTrinh = lichTrinh;
    }

    public Car(int id, String bsx, String cmtNhaXe, int soGhe, Double giaVe, String loTrinh, String lichTrinh, String viTri, String thoiGianDo) {
        this.id = id;
        this.bsx = bsx;
        this.cmtNhaXe = cmtNhaXe;
        this.soGhe = soGhe;
        this.giaVe = giaVe;
        this.loTrinh = loTrinh;
        this.lichTrinh = lichTrinh;
        this.viTri = viTri;
        this.thoiGianDo = thoiGianDo;
    }

    
    
    public Car() {
    }

    public Car(String bsx, String lichTrinh) {
        this.bsx = bsx;
        this.lichTrinh = lichTrinh;
    }

    public Car(String bsx, String viTri, String thoiGianDo) {
        this.bsx = bsx;
        this.viTri = viTri;
        this.thoiGianDo = thoiGianDo;
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
    
    public int addCar(Car car) {
        return carDao().addCar(car);
    }
    
    public List<Car> getAllCarForOwner(String cmt) {
        return carDao().getAllCarForOwner(cmt);
    }
    
    public List<String> getAllCarIdNoSort() {
        return carDao().getAllCarIdNoSort();
    }
    
    public List<String> getAllParkLocation() {
        return carDao().getAllParkLocation();
    }
    
    public List<String> generateTimePark(String bsx) {
        return carDao().generateTimePark(bsx);
    }
    
    public List<String> getTimeParked(String bsx) {
        return carDao().getTimeParked(bsx);
    }
    
    public List<Car> getAllCarParked() {
        return carDao().getAllCarParked();
    }
    
    public int sortCarInPark(Car car) {
        return carDao().sortCarInPark(car);
    }
    
    public int updatePark(Car carUpdate) {
        return carDao().updatePark(carUpdate);
    }
    
    public boolean destroyParkCar(Car car) {
        return carDao().destroyParkCar(car);
    }
    
    public List<Car> getAllInforCarForOwner(String cmt) {
        return carDao().getAllInforCarForOwner(cmt);
    }
    
    public List<Car> searchInforCarForOwner(String cmt, String keySearch) {
        return carDao().searchInforCarForOwner(cmt, keySearch);
    }
    
    public List<Car> getAllCar() {
        return carDao().getAllCar();
    }
    
    public int sendRequesttUpdateCar(String maXe, String bsx, String cmt, double gia, String lichTrinh) {
        return carDao().sendRequesttUpdateCar(maXe, bsx, cmt, gia, lichTrinh);
    }
    
    public int updateSentRequest(String maXe, String bsx, String cmt, double gia, String lichTrinh) {
        return carDao().updateSentRequest(maXe, bsx, cmt, gia, lichTrinh);
    }
    
    public List<CarUpdate> getAllRequest() {
        return carDao().getAllRequest();
    }
}
