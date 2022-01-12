/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author wku-cslab
 */
public class Check {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO code application logic here

        createConnection cobj = new createConnection();
        Connection conn = cobj.connMethod();
        if (conn != null) {
            JOptionPane.showMessageDialog(null, "connected");
        } else {
            JOptionPane.showMessageDialog(null, "not connected");
        }

    }
}
