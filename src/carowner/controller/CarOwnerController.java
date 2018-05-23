/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carowner.controller;

import carowner.model.CarOwner;

/**
 *
 * @author kienanh2903
 */
public class CarOwnerController {
    
    private CarOwner carOwner;

    public CarOwnerController() {
        carOwner = new CarOwner();
    }
    
    public int addCarOwner(CarOwner carOwner){
        return this.carOwner.addCarOwner(carOwner);
    }
    
    public void displayInforCarOwnerToJTable(){
        
    }
    
}
