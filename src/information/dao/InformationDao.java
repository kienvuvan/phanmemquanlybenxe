/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package information.dao;

import information.model.Information;
import java.util.List;

/**
 *
 * @author kienanh2903
 */
public interface InformationDao {
    List<Information> getAllInfor();
    
    int postNew(Information information);
}
