/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author wku-cslab
 */
public class DBConnectionC {

    private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String user = "Fasika";
    private static final String password = "fayaw45640";

    public Connection connMethod() throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = null;

        try {
            con = DriverManager.getConnection(url, user, password);
            //JOptionPane.showMessageDialog(null, "connected");

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, "Error: " + ex);
        }
        return con;
    }
}
