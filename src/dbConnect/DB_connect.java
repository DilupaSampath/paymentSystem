/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class DB_connect {
    public static Connection connect() throws SQLException
    {
        Connection conn = null;
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver" );
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment?","root", "");
            
            
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e + "not JDBC driver detected..!!");
        }
        
        return conn;
    }
}
