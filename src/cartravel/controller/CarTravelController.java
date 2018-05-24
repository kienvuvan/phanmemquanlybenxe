/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartravel.controller;

import cartravel.model.CarTravel;
import cartravel.model.CarTravelDetail;
import guest.model.Guest;
import java.sql.Date;

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
        return carTravel.bookTicket(carTravel, guest);
    }
}
