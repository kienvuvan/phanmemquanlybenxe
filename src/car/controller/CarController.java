/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.controller;

import car.model.Car;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kienanh2903
 */
public class CarController {
    
    private Car car;
    public CarController() {
        car = new Car();
    }
    
    public List<Car> searchCarForBookTicket(String keySearch) {
        return car.searchCarForBookTicket(keySearch);
    }
    
    public void displaySearchCarForBookTicket(JTable jtb, String keySearch){
        DefaultTableModel dtm = (DefaultTableModel) jtb.getModel();
        dtm.setNumRows(0);
        List<Car> listCars = searchCarForBookTicket(keySearch);
        for(int i=0; i<listCars.size(); i++){
            dtm.addRow(new Object[]{listCars.get(i).getBsx(), listCars.get(i).getCmtNhaXe(), listCars.get(i).getSoGhe(),
                listCars.get(i).getLoTrinh(), listCars.get(i).getLichTrinh(), listCars.get(i).getGiaVe()});
        }
        jtb.setModel(dtm);
    }
}
