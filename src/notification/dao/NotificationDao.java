/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notification.dao;

import notification.model.Notification;
import java.util.List;

/**
 *
 * @author kienanh2903
 */
public interface NotificationDao {
    List<Notification> getAllInfor();
    
    int postNew(Notification information);
    
    int updateNew(Notification information);
    
    boolean deleteNew(int id);
}
