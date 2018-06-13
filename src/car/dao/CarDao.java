/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.dao;

import car.model.Car;
import car.model.CarUpdate;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author kienanh2903
 */
public interface CarDao {
    List<Car> searchCarForBookTicket(String keySearch);
    
    int addCar(Car car);
    
    int getIdMax();

    public List<Car> getAllCarForOwner(String cmt);
    
    List<String> getAllCarIdNoSort();
    
    List<String> getAllParkLocation();
    
    List<String> generateTimePark(String bsx);
    
    List<String> getTimeParked(String bsx);
    
    List<Car> getAllCarParked();
    
    boolean checkParkLocation(String viTriDo, String thoiGianDo);
    
    boolean checkCarParked(String bsx, String thoiGianDo);
    
    int sortCarInPark(Car car);
    
    int updatePark(Car carUpdate);
    
    boolean destroyParkCar(Car car);
    
    List<Car> getAllInforCarForOwner(String cmt);
    
    List<Car> searchInforCarForOwner(String cmt, String keySearch);
    
    List<Car> getAllCar();
    
    boolean checkSentRequest(String maXe, String bsx);
    
    int updateSentRequest(String maXe, String bsx, String cmt, double giaVe, String lichTrinh);
    
    int sendRequesttUpdateCar(String maXe, String bsx, String cmt, double giaVe, String lichTrinh);
    
    List<CarUpdate> getAllRequest();
    
    List<String> getAllIdCarOwnerRequest();
    
    List<String> getAllIdCarRequestByCarOwner(String cmt);
    
    Timestamp getTimeSendRequest(String id);
    
    List<Car> getCarByIdCar(String maXe);
    
    List<CarUpdate> getCarUpdateByIdCar(String maXe);
    
    boolean updateCar(double giaVe, String maXe);
    
    boolean updateCar(double giaVe, String lichTrinh, String maXe);
    
    String getParkLocation(String maXe);
    
    boolean updateParkLocaction(String viTriDo, String bsx, String thoiGianDoCu, String thoiGianDoMoi);
    
    boolean updateStatusNoAgree(String maXe);
}
