/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paymentsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class PaymentSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

 try {
            Class.forName("com.mysql.jdbc.Driver" );
        } catch (ClassNotFoundException e) {
            System.out.println("not JDBC driver detected..!!");
            e.printStackTrace();
            return;
        }
        System.out.println("JDBC driver detected OK");
         Connection conn = null;
         
         try {
                conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/supreme?","root", "");
        } catch (SQLException e) {
            System.out.println("Connection Fail.. check output consol..!");
            e.printStackTrace(); 
            return;
            
        }
         
         if(conn!=null){
       System.out.println("Connection OK.. u made it");
         }
         else
         {
         System.out.println("not made it");
         }
       


// TODO code application logic hereq
    }
    
}
