/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guest.controller;

import guest.model.Guest;

/**
 *
 * @author kienanh2903
 */
public class GuestController {
    
    private final Guest guest; 

    public GuestController() {
        guest = new Guest();
    }
    
    public boolean checkGuestExit(String cmt) {
        return guest.checkGuestExit(cmt);
    }
    
    public boolean updateInforGuest(Guest guestUpdate) {
        return guest.updateInforGuest(guestUpdate);
    }
    
    public boolean addGuest(Guest guest) {
        return guest.addGuest(guest);
    }
}
