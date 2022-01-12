/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author wku-cslab
 */
public class createConnection {

    private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String user = "Fasika";
    private static final String password = "fayaw45640";
    private static final String driver = "oracle.jdbc.driver.OracleDriver";

    public createConnection() {

    }

    public Connection connMethod() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection conn = null;
        conn = DriverManager.getConnection(url, user, password);
        return conn;
    }
}
