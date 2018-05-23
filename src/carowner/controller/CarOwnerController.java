/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carowner.controller;

import car.model.Car;
import carowner.model.CarOwner;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
    
    public List<CarOwner> getAllListCarOwner() {
        return carOwner.getAllListCarOwner();
    }
    
    public void displayInforCarOwnerToJTable(JTable jtb){
        List<CarOwner> listCarOwners = getAllListCarOwner();
        DefaultTableModel dtm = (DefaultTableModel)jtb.getModel();
        for(int i=0;i<listCarOwners.size(); i++){
            String nhaXe = listCarOwners.get(i).getNhaXe();
            List<Car> listCar = listCarOwners.get(i).getListCar();
            for(int j=0;j<listCar.size();j++){
                dtm.addRow(new Object[]{listCar.get(j).getBsx(), nhaXe, listCar.get(j).getSoGhe(), 
                    listCar.get(j).getLoTrinh(), listCar.get(j).getLichTrinh(),listCar.get(j).getGiaVe()});
            }
        }
        jtb.setModel(dtm);
    }
    
}
