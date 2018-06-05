/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.dao;

import car.model.Car;
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
}
