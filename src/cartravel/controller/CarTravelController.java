/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartravel.controller;

import cartravel.model.CarTravel;
import guest.model.Guest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kienanh2903
 */
public class CarTravelController {
    
    private CarTravel carTravel;
    
    public CarTravelController() {
        carTravel = new CarTravel();
    }
    
    public String generateCarTravelId(String bienSoXe, Date ngayDi, String thoiGian) {
        return carTravel.generateCarTravelId(bienSoXe, ngayDi, thoiGian);
    }
    
    public boolean checkCarTravelIdExit(String maChuyenXe) {
        return carTravel.checkCarTravelIdExit(maChuyenXe);
    }
    
    public int bookTicket(CarTravel carTravel, Guest guest) {
        return this.carTravel.bookTicket(carTravel, guest);
    }
    
    public boolean checkTicketForDestroy(String maChuyenXe, String cmt) {
        return carTravel.checkTicketForDestroy(maChuyenXe, cmt);
    }
    
    public int unbookTicket(String maChuyenXe, String cmt) {
        return carTravel.unbookTicket(maChuyenXe, cmt);
    }
    
    public boolean creatCarTravel() {
        return carTravel.creatCarTravel();
    }
    
    public List<CarTravel> getAllCarTravelByIdOwner(String cmt) {
        return carTravel.getAllCarTravelByIdOwner(cmt);
    }
    
    public List<CarTravel> searchCarTravelByIdOwner(String cmt, String key) {
        return carTravel.searchCarTravelByIdOwner(cmt, key);
    }
    
    public void displayInforCarTravel(JTable jtb, List<CarTravel> listCarTravels) {
        DefaultTableModel dtm = (DefaultTableModel) jtb.getModel();
        dtm.setRowCount(0);
        SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
        for (CarTravel carTravel1 : listCarTravels) {
            dtm.addRow(new Object[]{carTravel1.getMaChuyenXe(), carTravel1.getBienSoXe(), simple.format(carTravel1.getNgayDi()), carTravel1.getThoiGian(), carTravel1.getListDetail().size()});
        }
        jtb.setModel(dtm);
    }
    
    public List<Guest> getAllGuestInCarTravel(String maChuyenXe) {
        return carTravel.getAllGuestInCarTravel(maChuyenXe);
    }
    
    public void displayInforGuestInCarTravel(JTable jtb, List<Guest> listGuests) {
        DefaultTableModel dtm = (DefaultTableModel) jtb.getModel();
        dtm.setRowCount(0);
        SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
        if (listGuests.isEmpty()) {
            dtm.addRow(new Object[]{"", "", "","No data in display", "", "", ""});
        }else{
            for (Guest guest : listGuests) {
                dtm.addRow(new Object[]{guest.getCmt(), guest.getTen(), simple.format(guest.getNgaySinh()), guest.getGioiTinh(), guest.getSdt(),
                guest.getEmail(), guest.getDiaChi()});
            }
        }
        jtb.setModel(dtm);
    }
    
}
