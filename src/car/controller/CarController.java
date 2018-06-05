/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.controller;

import car.model.Car;
import java.util.List;
import javax.swing.JComboBox;
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

    public void displaySearchCarForBookTicket(JTable jtb, String keySearch) {
        DefaultTableModel dtm = (DefaultTableModel) jtb.getModel();
        dtm.setNumRows(0);
        List<Car> listCars = searchCarForBookTicket(keySearch);
        for (int i = 0; i < listCars.size(); i++) {
            dtm.addRow(new Object[]{listCars.get(i).getBsx(), listCars.get(i).getCmtNhaXe(), listCars.get(i).getSoGhe(),
                listCars.get(i).getLoTrinh(), listCars.get(i).getLichTrinh(), listCars.get(i).getGiaVe()});
        }
        jtb.setModel(dtm);
    }

    public int addCar(Car carAdd) {
        return car.addCar(carAdd);
    }

    public List<Car> getAllCarForOwner(String cmt) {
        return car.getAllCarForOwner(cmt);
    }

    public List<String> getAllCarIdNoSort() {
        return car.getAllCarIdNoSort();
    }

    public void addListItemToJCombobox(JComboBox jcb, List<String> list) {
        jcb.removeAllItems();
        if (list.isEmpty()) {

        } else {
            for (int i = 0; i < list.size(); i++) {
                jcb.addItem(list.get(i));
            }
            jcb.setSelectedIndex(0);
        }
    }
    
    public List<String> getAllParkLocation() {
        return car.getAllParkLocation();
    }
    
    public List<String> generateTimePark(String bsx) {
        return car.generateTimePark(bsx);
    }
    
    public List<Car> getAllCarParked() {
        return car.getAllCarParked();
    }
    
    public void displayInforCarParkedToTable(JTable jtb){
        DefaultTableModel model = (DefaultTableModel)jtb.getModel();
        model.setRowCount(0);
        List<Car> list = getAllCarParked();
        if(list.isEmpty()){
            model.addRow(new Object[]{"", "No data to display", ""});
        }else{
            for(int i =0;i<list.size(); i++){
                Car car = list.get(i);
                model.addRow(new Object[]{car.getBsx(), car.getViTri(), car.getThoiGianDo()});
            }
        }
        jtb.setModel(model);
    }
    
    public int sortCarInPark(Car car) {
        return car.sortCarInPark(car);
    }
}
