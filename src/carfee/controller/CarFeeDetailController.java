/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carfee.controller;

import carfee.model.CarFeeDetail;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kienanh2903
 */
public class CarFeeDetailController {

    private final CarFeeDetail carFeeDetail;

    public CarFeeDetailController() {
        carFeeDetail = new CarFeeDetail();
    }

    public List<CarFeeDetail> getCarFeeDetailByCarOwner(String cmtChuXe, Date ngayThu) {
        return carFeeDetail.getCarFeeDetailByCarOwner(cmtChuXe, ngayThu);
    }

    public void displayCarFeeDetail(JTable jtb, List<CarFeeDetail> listCarFeeDetails) {
        DefaultTableModel dtm = (DefaultTableModel) jtb.getModel();
        dtm.setRowCount(0);
        for (CarFeeDetail feeDetail : listCarFeeDetails) {
            dtm.addRow(new Object[]{feeDetail.getBsx(), feeDetail.getSoChuyen(), feeDetail.getPhi()});
        }
        jtb.setModel(dtm);
    }
    
    public List<CarFeeDetail> getAllCarFeeDetailByCarOwner(String cmtChuXe) {
        return carFeeDetail.getAllCarFeeDetailByCarOwner(cmtChuXe);
    }
    
    public void displayAllCarFeeDetail(JTable jtb, List<CarFeeDetail> listCarFeeDetails) {
        DefaultTableModel dtm = (DefaultTableModel) jtb.getModel();
        dtm.setRowCount(0);
        SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
        for (CarFeeDetail feeDetail : listCarFeeDetails) {
            dtm.addRow(new Object[]{feeDetail.getBsx(), feeDetail.getSoChuyen(),simple.format(feeDetail.getNgayThu()), feeDetail.getPhi()});
        }
        jtb.setModel(dtm);
    }
    
    public List<CarFeeDetail> searchCarFeeDetailByCarOwner(String cmtChuXe, String key) {
        return carFeeDetail.searchCarFeeDetailByCarOwner(cmtChuXe, key);
    }
    
}
