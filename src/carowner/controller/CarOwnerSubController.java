/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carowner.controller;

import car.controller.CarController;
import car.model.Car;
import carfee.controller.CarFeeController;
import carfee.model.CarFee;
import carowner.dao.MysqlCarOwnerDao;
import carowner.model.CarOwner;
import cartravel.dao.MysqlCarTravelDao;
import cartravel.model.CarTravel;
import guest.dao.MysqlGuestDao;
import guest.model.Guest;
import java.util.List;


/**
 *
 * @author DELL
 */
public class CarOwnerSubController {

    public static CarOwner acc;
    private String id;
    MysqlCarOwnerDao carOwnerDao = new MysqlCarOwnerDao();
    CarFeeController carFeeCon = new CarFeeController();
    CarController carCon = new CarController();
    MysqlCarTravelDao travelDao = new MysqlCarTravelDao();
    MysqlGuestDao guestDao = new MysqlGuestDao();

    public CarOwnerSubController(String id) {
        this.id = id;
    }

    public void setOwnerAccount() {
        acc = carOwnerDao.getInforCarOwner(id);
    }

    public void editOwnerAcc() {
        carOwnerDao.editCarOwner(acc);
    }

    public List<Car> getAllCar() {
        return carCon.getAllCarForOwner(acc.getCmt());
    }

//    public List<Car> searchCar(String keySearch) {
//        return carCon.searchCarForOwner(acc.getCmt(), keySearch);
//    }

    public List<CarFee> getAllFee() {
        return carFeeCon.getCarFeeFofOwner(acc.getCmt());
    }

    public List<CarFee> searchFee(String carID, String timeStart, String timeEnd) {
        return carFeeCon.searchFeeFofOwner(acc.getCmt(), carID, timeStart, timeEnd);

    }

    public double sumMoney(List<CarFee> feeList) {
        double sum = 0;
        for (int i = 0; i < feeList.size(); i++) {
            sum = sum + feeList.get(i).getFee();
        }
        return sum;
    }

//    public List<CarTravel> getCarTravel(String dateStart, String dateEnd){
//        return travelDao.getTravelForOwner(acc.getCmt(), dateStart, dateEnd);
//    }
//    
//    public List<Guest> getGuest(String maChuyenXe){
//        return guestDao.getGuest(maChuyenXe);
//    }
}
