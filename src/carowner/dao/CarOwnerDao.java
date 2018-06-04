/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carowner.dao;

import car.model.Car;
import carowner.model.CarOwner;
import java.util.List;

/**
 *
 * @author kienanh2903
 */
public interface CarOwnerDao {

    int addCarOwner(CarOwner carOwner);

    List<CarOwner> getAllListCarOwner();

    List<CarOwner> searchCarOwner();

    List<CarOwner> getAllInforCarOwner();

    List<String> getAllIdCarOwner();

    String getNameByIdCarOwner(String id);
    
    List<Car> getAllCarByIdCarOwner(String id);
    
    int loginCarOwner(String user, String pass);
    
    public CarOwner getInforCarOwner (String cmt);
}
