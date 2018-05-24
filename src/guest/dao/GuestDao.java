/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guest.dao;

import guest.model.Guest;

/**
 *
 * @author kienanh2903
 */
public interface GuestDao {
    boolean checkGuestExit(String cmt);
    
    boolean updateInforGuest(Guest guestUpdate);
    
    boolean addGuest(Guest guest);
}
