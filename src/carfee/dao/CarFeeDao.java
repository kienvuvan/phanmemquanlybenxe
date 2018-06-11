/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carfee.dao;

import carfee.model.CarFee;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author kienanh2903
 */
public interface CarFeeDao {
    boolean parkingFeeCar(String cmtAdmin);
    
    List<CarFee> getAllInforCarFee();
    
    int checkDateParkingFee(Date date);
}
