/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carowner.controller;

import car.model.Car;
import carowner.model.CarOwner;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kienanh2903
 */
public class CarOwnerController {

    private final CarOwner carOwner;

    public CarOwnerController() {
        carOwner = new CarOwner();
    }

    public int addCarOwner(CarOwner carOwner) {
        return this.carOwner.addCarOwner(carOwner);
    }

    public int updateInforCarOwner(CarOwner carOwnerUpdate) {
        return carOwner.updateInforCarOwner(carOwnerUpdate);
    }

    public List<CarOwner> getAllListCarOwner() {
        return carOwner.getAllListCarOwner();
    }

    public void displayInforCarOwnerToJTable(JTable jtb) {
        List<CarOwner> listCarOwners = getAllListCarOwner();
        DefaultTableModel dtm = (DefaultTableModel) jtb.getModel();
        dtm.setRowCount(0);
        for (int i = 0; i < listCarOwners.size(); i++) {
            String nhaXe = listCarOwners.get(i).getNhaXe();
            List<Car> listCar = listCarOwners.get(i).getListCar();
            for (int j = 0; j < listCar.size(); j++) {
                dtm.addRow(new Object[]{listCar.get(j).getBsx(), nhaXe, listCar.get(j).getSoGhe(),
                    listCar.get(j).getLoTrinh(), listCar.get(j).getLichTrinh(), listCar.get(j).getGiaVe()});
            }
        }
        jtb.setModel(dtm);
    }

    public List<CarOwner> getAllInforCarOwner() {
        return carOwner.getAllInforCarOwner();
    }

    public void displayInforCarOwner(JTable jtb) {
        List<CarOwner> listCarOwners = getAllInforCarOwner();
        DefaultTableModel dtm = (DefaultTableModel) jtb.getModel();
        SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
        dtm.setRowCount(0);
        for (int i = 0; i < listCarOwners.size(); i++) {
            CarOwner co = listCarOwners.get(i);
            dtm.addRow(new Object[]{co.getCmt(), co.getTen(), co.getGioiTinh(), co.getNhaXe(), co.getSdt(), co.getEmail(),
                simple.format(co.getNgaySinh()), co.getDiaChi()});
        }
        jtb.setModel(dtm);
    }

    public void addCarOwnerToJTable(CarOwner co, JTable jtb) {
        DefaultTableModel dtm = (DefaultTableModel) jtb.getModel();
        SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
        dtm.addRow(new Object[]{co.getCmt(), co.getTen(), co.getGioiTinh(), co.getNhaXe(), co.getSdt(), co.getEmail(),
            simple.format(co.getNgaySinh()), co.getDiaChi()});
        jtb.setModel(dtm);
    }

    public List<String> getAllIdCarOwner() {
        return carOwner.getAllIdCarOwner();
    }

    public void displayIdCarOwnerToComboBox(JComboBox jcb) {
        jcb.removeAllItems();
        List<String> listIdCarOwner = getAllIdCarOwner();
        for (int i = 0; i < listIdCarOwner.size(); i++) {
            jcb.addItem(listIdCarOwner.get(i));
        }
    }

    public String getNameByIdCarOwner(String id) {
        return carOwner.getNameByIdCarOwner(id);
    }

    public List<Car> getAllCarByIdCarOwner(String id) {
        return carOwner.getAllCarByIdCarOwner(id);
    }

    public void displayListCarToJTable(JTable jtb, String id) {
        List<Car> listCar = getAllCarByIdCarOwner(id);
        DefaultTableModel dtm = (DefaultTableModel) jtb.getModel();
        dtm.setRowCount(0);
        if (listCar.size() > 0) {
            for (int i = 0; i < listCar.size(); i++) {
                Car car = listCar.get(i);
                dtm.addRow(new Object[]{car.getBsx(), car.getCmtNhaXe(), car.getSoGhe(), car.getGiaVe(), car.getLoTrinh(), car.getLichTrinh()});
            }
        }
        jtb.setModel(dtm);
    }

    public int loginCarOwner(String user, String pass) {
        return carOwner.loginCarOwner(user, pass);
    }

    public CarOwner getInforCarOwner(String cmt) {
        return carOwner.getInforCarOwner(cmt);
    }

    public String getGarageByBsx(String bsx) {
        return carOwner.getGarageByBsx(bsx);
    }

    public List<CarOwner> searchCarOwner(String key) {
        return carOwner.searchCarOwner(key);
    }

    public void displayResultSearchInforCarOwner(JTable jtb, List<CarOwner> listCarOwners) {
        DefaultTableModel dtm = (DefaultTableModel) jtb.getModel();
        SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
        dtm.setRowCount(0);
        int width = jtb.getColumnModel().getColumn(2).getPreferredWidth();
        if (listCarOwners.isEmpty()) {
            dtm.addRow(new Object[]{"","","","Không có chủ xe nào được tìm thấy"});
            jtb.getColumnModel().getColumn(3).setPreferredWidth(300);
        } else {
            for (int i = 0; i < listCarOwners.size(); i++) {
                CarOwner co = listCarOwners.get(i);
                dtm.addRow(new Object[]{co.getCmt(), co.getTen(), co.getGioiTinh(), co.getNhaXe(), co.getSdt(), co.getEmail(),
                    simple.format(co.getNgaySinh()), co.getDiaChi()});
                jtb.getColumnModel().getColumn(3).setPreferredWidth(width);
            }
        }
        jtb.setModel(dtm);
    }

}
