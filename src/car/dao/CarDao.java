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
}
