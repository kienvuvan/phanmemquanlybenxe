/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carowner.model;

import account.model.User;
import car.model.Car;
import carowner.dao.CarOwnerDao;
import carowner.dao.MysqlCarOwnerDao;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author kienanh2903
 */
public class CarOwner extends User{
    private String nhaXe;
    private List<Car> listCar;
    
    public CarOwner() {
    }

    public CarOwner(String cmt, String nhaXe, List<Car> listCar) {
        super();
        this.nhaXe = nhaXe;
        this.listCar = listCar;
    }

    public CarOwner(String cmt, String ten, String nhaXe, String sdt, String email, String gioitinh, Date ngaySinh, String diaChi) {
        super(cmt, ten, gioitinh, ngaySinh, sdt, email, diaChi);
        this.nhaXe = nhaXe;
    }

    public List<Car> getListCar() {
        return listCar;
    }

    public void setListCar(List<Car> listCar) {
        this.listCar = listCar;
    }

    public String getNhaXe() {
        return nhaXe;
    }

    public void setNhaXe(String nhaXe) {
        this.nhaXe = nhaXe;
    }

    private CarOwnerDao carOwnerDao() {
        return new MysqlCarOwnerDao();
    }
    
    public int addCarOwner(CarOwner carOwner){
        return carOwnerDao().addCarOwner(carOwner);
    }
    
    public int updateInforCarOwner(CarOwner carOwnerUpdate) {
        return carOwnerDao().updateInforCarOwner(carOwnerUpdate);
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
    
    public int loginCarOwner(String user, String pass) {
        return carOwnerDao().loginCarOwner(user, pass);
    }
    
    public CarOwner getInforCarOwner (String cmt){
        return carOwnerDao().getInforCarOwner(cmt);
    }
    
    public String getGarageByBsx(String bsx) {
        return carOwnerDao().getGarageByBsx(bsx);
    }
    
    public List<CarOwner> searchCarOwner(String key) {
        return carOwnerDao().searchCarOwner(key);
    }
}
