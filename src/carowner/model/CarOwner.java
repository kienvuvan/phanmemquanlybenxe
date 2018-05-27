/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carowner.model;

import car.model.Car;
import carowner.dao.CarOwnerDao;
import carowner.dao.MysqlCarOwnerDao;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author kienanh2903
 */
public class CarOwner {
    private String cmt;
    private String ten;
    private String nhaXe;
    private String sdt;
    private String email;
    private String gioitinh;
    private Date ngaySinh;
    private String diaChi;
    private List<Car> listCar;
    
    public CarOwner() {
    }

    public CarOwner(String cmt, String nhaXe, List<Car> listCar) {
        this.cmt = cmt;
        this.nhaXe = nhaXe;
        this.listCar = listCar;
    }

    public CarOwner(String cmt, String ten, String nhaXe, String sdt, String email, String gioitinh, Date ngaySinh, String diaChi) {
        this.cmt = cmt;
        this.ten = ten;
        this.nhaXe = nhaXe;
        this.sdt = sdt;
        this.email = email;
        this.gioitinh = gioitinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
    }

    public List<Car> getListCar() {
        return listCar;
    }

    public void setListCar(List<Car> listCar) {
        this.listCar = listCar;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNhaXe() {
        return nhaXe;
    }

    public void setNhaXe(String nhaXe) {
        this.nhaXe = nhaXe;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    private CarOwnerDao carOwnerDao() {
        return new MysqlCarOwnerDao();
    }
    
    public int addCarOwner(CarOwner carOwner){
        return carOwnerDao().addCarOwner(carOwner);
    }
    
    public List<CarOwner> getAllListCarOwner() {
        return carOwnerDao().getAllListCarOwner();
    }
    
    public List<CarOwner> getAllInforCarOwner() {
        return carOwnerDao().getAllInforCarOwner();
    }
    
    public List<String> getAllIdCarOwner() {
        return carOwnerDao().getAllIdCarOwner();
    }
    
    public String getNameByIdCarOwner(String id) {
        return carOwnerDao().getNameByIdCarOwner(id);
    }
    
    public List<Car> getAllCarByIdCarOwner(String id) {
        return carOwnerDao().getAllCarByIdCarOwner(id);
    }
}
