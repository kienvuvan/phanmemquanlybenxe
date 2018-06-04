/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carfee.controller;

import carfee.dao.CarFeeDao;
import carfee.model.CarFee;
import java.util.List;

/**
 *
 * @author DELL
 */
public class CarFeeController {
    
    CarFeeDao feeDao = new CarFeeDao();
    
    public List<CarFee> searchFeeFofOwner(String cmt, String carID, String timeStart, String timeEnd) {
        return feeDao.searchFeeFofOwner(cmt, carID, timeStart, timeEnd);
    }
    
    public List<CarFee> getCarFeeFofOwner(String cmt) {
        return feeDao.getCarFeeFofOwner(cmt);
    }
}
