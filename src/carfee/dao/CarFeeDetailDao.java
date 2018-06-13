/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carfee.dao;

import carfee.model.CarFeeDetail;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author kienanh2903
 */
public interface CarFeeDetailDao {
    List<CarFeeDetail> getCarFeeDetailByCarOwner(String cmtChuXe, Date ngayThu);
    
    List<CarFeeDetail> getAllCarFeeDetailByCarOwner(String cmtChuXe);
    
    List<CarFeeDetail> searchCarFeeDetailByCarOwner(String cmtChuXe, String key);
}
