/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guest.model;

import account.model.User;
import guest.dao.GuestDao;
import guest.dao.MysqlGuestDao;
import java.sql.Date;

/**
 *
 * @author kienanh2903
 */
public class Guest extends User{

    public Guest() {
    }

    public Guest(String cmt, String ten, Date ngaySinh, String gioiTinh, String sdt, String email, String diaChi) {
        super(cmt, ten, gioiTinh, ngaySinh, sdt, email, diaChi);
    }

    public GuestDao guestDao() {
        return new MysqlGuestDao();
    }
    
    public boolean checkGuestExit(String cmt) {
        return guestDao().checkGuestExit(cmt);
    }
    
    public boolean updateInforGuest(Guest guestUpdate) {
        return guestDao().updateInforGuest(guestUpdate);
    }
    
    public boolean addGuest(Guest guest) {
        return guestDao().addGuest(guest);
    }
}
