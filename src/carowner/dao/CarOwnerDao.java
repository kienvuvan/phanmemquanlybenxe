/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carowner.dao;

import carowner.model.CarOwner;
import java.util.List;

/**
 *
 * @author kienanh2903
 */
public interface CarOwnerDao {
    int addCarOwner(CarOwner carOwner);
    
    List<CarOwner> getAllListCarOwner();
}
