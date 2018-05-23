/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.dao;

/**
 *
 * @author kienanh2903
 */
public class MysqlCarDao implements CarDao{
    private static MysqlCarDao mysqlCarDao;
    
    public static MysqlCarDao getInstance() {
        if (mysqlCarDao == null) {
            mysqlCarDao = new MysqlCarDao();
        }
        return mysqlCarDao;
    }
}
