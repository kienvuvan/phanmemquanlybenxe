/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linh
 */
public class Mysql  {

    private static Mysql instance;
    private Connection connection;
    private static final String URL = "jdbc:mysql://127.0.0.1/";
    private static final String DATABASE_NAME = "quanlybenxe?useUnicode=true&characterEncoding=utf-8";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";

    private Mysql() throws SQLException {
        try {
            Class.forName(DRIVER).newInstance();
            this.connection = DriverManager.getConnection(URL + DATABASE_NAME, USER_NAME, PASSWORD);
            System.out.println("Connect Success");
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Mysql getInstance() throws SQLException {
        if (instance == null) {
            instance = new Mysql();
        } else if (instance.getConnection().isClosed()) {
            instance = new Mysql();
        }

        return instance;
    }
    
}
