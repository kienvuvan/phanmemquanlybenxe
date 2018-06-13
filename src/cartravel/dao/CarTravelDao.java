/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartravel.dao;

import cartravel.model.CarTravel;
import guest.model.Guest;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author kienanh2903
 */
public interface CarTravelDao {

    String generateCarTravelId(String bienSoXe, Date ngayDi, String thoiGian);

    boolean creatCarTravel();

    int getMaxIdCarTravel(String bsx, Date ngayDi);

    boolean checkExitCarTravel(String bsx, Date ngayDi, String thoiGian);

    int bookTicket(CarTravel carTravel, Guest guest);

    boolean checkCarTravelIdExit(String maChuyenXe);

    boolean checkGuestInCarTravel(String maChuyenXe, String cmt);

    boolean addInforCarTravelDetail(String maChuyenXe, String maHanhKhach);

    boolean addInforCarTravel(CarTravel carTravel);

    boolean checkTicketForDestroy(String maChuyenXe, String cmt);

    int unbookTicket(String maChuyenXe, String cmt);

    List<CarTravel> getAllCarTravelByIdOwner(String cmt);

    List<CarTravel> searchCarTravelByIdOwner(String cmt, String key);
    
    List<Guest> getAllGuestInCarTravel(String maChuyenXe);
}
