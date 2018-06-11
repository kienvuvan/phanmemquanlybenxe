/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carfee.controller;

import carfee.dao.MysqlCarFeeDao;
import carfee.model.CarFee;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class CarFeeController {

    private final MysqlCarFeeDao carFeeDao;

    public CarFeeController() {
        carFeeDao = new MysqlCarFeeDao();
    }

    public List<CarFee> getAllInforCarFee() {
        return carFeeDao.getAllInforCarFee();
    }

    public boolean parkingFeeCar(String cmtAdmin) {
        return carFeeDao.parkingFeeCar(cmtAdmin);
    }

    public void displayParkFeeCar(JTable jtb, List<CarFee> listCarFees) {
        DefaultTableModel dtm = (DefaultTableModel) jtb.getModel();
        dtm.setRowCount(0);
        SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < listCarFees.size(); i++) {
            CarFee carFee = listCarFees.get(i);
            dtm.addRow(new Object[]{carFee.getIdCarOwner(), carFee.getName(), carFee.getFee(), simple.format(carFee.getTime()), carFee.getCmtAdmin()});
        }
        jtb.setModel(dtm);
    }
    
    public int checkDateParkingFee(Date date) {
        return carFeeDao.checkDateParkingFee(date);
    }
}
