/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 //Randima Senanayake
package paymentsystem;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dbConnect.DB_connect;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import org.apache.commons.validator.EmailValidator;

/**
 *
 * @author HP1
 */
public class memberRegistration extends javax.swing.JFrame {
     String OldDmemberName = null;
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    java.sql.Date sqldate;
   
    /**
     * Creates new form memberRegistration
     */
    public memberRegistration() throws SQLException {
        initComponents();
        conn = DB_connect.connect();
        showUser();
        loadNameCombo();
        tableLoad();
    }
    
     boolean isValidEmail(String email)
 {
 
 //String email = "myName@example.com";
boolean valid = EmailValidator.getInstance().isValid(email);
 return valid;
 }
    
        void DeleteMemberFromStatusTable(String memberName){
    
       try {
            String addNewMemberToStatusTablequary = "DELETE FROM `currentstatus` WHERE `MemberName`='"+memberName+"'";
            pst = conn.prepareStatement(addNewMemberToStatusTablequary);
            //JOptionPane.showMessageDialog(null," New Member Added Successfully123.......!!!");
            pst.execute();   //System.out.println(getgender());
            //loadNameCombo() ;
           // tableLoad();
           // JOptionPane.showMessageDialog(null, " New Member Added Successfully.......!!!");
        } catch (Exception e) {
            System.out.println(e);
        }
    
    
    }
    
    
    void addNewMemberToStatusTable(String memberName,String Mstatus){
    
       try {
            String addNewMemberToStatusTablequary = "INSERT INTO `currentstatus` (`MemberName`, `currentstatus`) VALUES ('" + memberName + "','"+Mstatus+"')";
            pst = conn.prepareStatement(addNewMemberToStatusTablequary);
            //JOptionPane.showMessageDialog(null," New Member Added Successfully123.......!!!");
            pst.execute();   //System.out.println(getgender());
            //loadNameCombo() ;
           // tableLoad();
           // JOptionPane.showMessageDialog(null, " New Member Added Successfully.......!!!");
        } catch (Exception e) {
            System.out.println(e);
        }
    
    
    }
    
      void UpdateNewMemberInStatusTable(String NewmemberName,String OldmemberName){
    
       try {
            String addNewMemberToStatusTablequary = "UPDATE `currentstatus` SET `MemberName`='"+NewmemberName+"' WHERE `MemberName`='"+OldmemberName+"' ";
            pst = conn.prepareStatement(addNewMemberToStatusTablequary);
            //JOptionPane.showMessageDialog(null," New Member Added Successfully123.......!!!");
            pst.execute();   //System.out.println(getgender());
            //loadNameCombo() ;
           // tableLoad();
           // JOptionPane.showMessageDialog(null, " New Member Added Successfully.......!!!");
        } catch (Exception e) {
            System.out.println(e);
        }
    
    
    }
    
    
      String FindMemberExsist(String ID) {
        try {
            String sql = "SELECT * FROM `memberdetails`";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            while (rs.next()) {
                String Mid = rs.getString("memberId");
                if (ID.equals(Mid)) {
                    return rs.getString("memberId");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        return null;
    }
      
      
        public void tableLoad() {
        try {
            String sqlQuery = "SELECT * FROM `memberdetails`";
            pst = conn.prepareStatement(sqlQuery);
            rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void loadNameCombo() {
        try {
            String sql = "SELECT * FROM `memberdetails`";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("memberName");
                cmb_memberSearch.addItem(name);
                //cmb_memberName.addItem(name);
            }
        } catch (Exception e) {
        }

    }
    public void showUser(){
    User currentUser = new User();
    String cUser = currentUser.getUserName();
    lbl_username.setText(cUser);
         
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        cmb_memberSearch = new javax.swing.JComboBox();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lbl_MemberID = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_ContactNumber = new javax.swing.JTextField();
        txt_MemberID = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txt_MemberName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        dc_JoningDate = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lbl_username = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 700));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel2.setLayout(null);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(30, 50, 520, 170);

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 255, 255));
        jLabel3.setText("Search");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(70, 10, 60, 30);

        cmb_memberSearch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Member Name" }));
        cmb_memberSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_memberSearchActionPerformed(evt);
            }
        });
        jPanel2.add(cmb_memberSearch);
        cmb_memberSearch.setBounds(130, 10, 140, 26);

        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5);
        jButton5.setBounds(330, 10, 40, 30);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(90, 430, 590, 230);
        jPanel2.setBackground(new Color(0,0,10,130));

        jPanel1.setLayout(null);

        lbl_MemberID.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_MemberID.setForeground(new java.awt.Color(153, 255, 255));
        lbl_MemberID.setText("Member ID");
        jPanel1.add(lbl_MemberID);
        lbl_MemberID.setBounds(50, 40, 80, 30);

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 255, 255));
        jLabel5.setText("Member Name");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(50, 80, 100, 30);

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 255, 255));
        jLabel6.setText("Joining Date");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(50, 120, 100, 30);
        jPanel1.add(txt_ContactNumber);
        txt_ContactNumber.setBounds(170, 160, 310, 26);

        txt_MemberID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MemberIDActionPerformed(evt);
            }
        });
        jPanel1.add(txt_MemberID);
        txt_MemberID.setBounds(170, 40, 310, 26);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rset-01-128.png"))); // NOI18N
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(440, 250, 42, 42);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Button-Add-icon.png"))); // NOI18N
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(170, 250, 42, 42);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update128.png"))); // NOI18N
        jButton3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(260, 250, 42, 42);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete128.png"))); // NOI18N
        jButton4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);
        jButton4.setBounds(350, 250, 42, 42);
        jPanel1.add(txt_MemberName);
        txt_MemberName.setBounds(170, 80, 310, 26);

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 255, 255));
        jLabel8.setText("E Mail");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(50, 200, 110, 40);

        dc_JoningDate.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(dc_JoningDate);
        dc_JoningDate.setBounds(170, 120, 310, 26);

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 255, 255));
        jLabel9.setText("Contact Number");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(50, 160, 110, 30);
        jPanel1.add(txt_email);
        txt_email.setBounds(170, 210, 310, 26);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(90, 90, 590, 310);
        jPanel1.setBackground(new Color(0,0,10,130));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home.png"))); // NOI18N
        jButton6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 255, 255), new java.awt.Color(0, 255, 255), new java.awt.Color(0, 255, 255), new java.awt.Color(0, 255, 255)));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(720, 20, 50, 50);

        jLabel2.setFont(new java.awt.Font("Constantia", 3, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Member Registration Form");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(80, 20, 630, 50);
        getContentPane().add(lbl_username);
        lbl_username.setBounds(720, 30, 100, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Register.jpg"))); // NOI18N
        jLabel1.setLabelFor(this);
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(-10, 0, 1150, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
      
        String memberID = txt_MemberID.getText();
        String memberName = txt_MemberName.getText();
        //  String joinDate = dc_JoningDate.getText();
        Calendar calendar = Calendar.getInstance();
        String email = txt_email.getText();
        java.sql.Date joinDateObject = new java.sql.Date(dc_JoningDate.getDate().getTime());
        String contactNumber = txt_ContactNumber.getText();
        System.out.println("Date : " + joinDateObject);

        if(isValidEmail(email))
        {
        if(FindMemberExsist(memberID) == null)
        {
        
        try {
            String NewMemberInsertQuary = "INSERT INTO `memberdetails`(`memberId`, `memberName`, `joinDate`, `contactNumber`,`email`) VALUES ('" + memberID + "','" + memberName + "','" + joinDateObject + "','" + contactNumber + "','"+email+"')";
            pst = conn.prepareStatement(NewMemberInsertQuary);
            //JOptionPane.showMessageDialog(null," New Member Added Successfully123.......!!!");
            pst.execute();   //System.out.println(getgender());
            loadNameCombo() ;
            tableLoad();
            addNewMemberToStatusTable(memberName,"newMember");
            JOptionPane.showMessageDialog(null, " New Member Added Successfully.......!!!");
        } catch (Exception e) {
            System.out.println(e);
        }
        }
        else
        {
          JOptionPane.showMessageDialog(null, "Member ID already exsist", "Error", JOptionPane.ERROR_MESSAGE);
        
        }
        }
        else
        {
        
        JOptionPane.showMessageDialog(null, email+ " this E mail is not valid", "Error", JOptionPane.ERROR_MESSAGE);
        
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_MemberIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MemberIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MemberIDActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         String memberID = txt_MemberID.getText();
          String memberName = txt_MemberName.getText();
       
           java.sql.Date joinDateObject = new java.sql.Date(dc_JoningDate.getDate().getTime());
            String ContactNumber = txt_ContactNumber.getText();
            String email = txt_email.getText();
            
             
                 try {
            String searchQuary = "UPDATE `memberdetails` SET `memberId`='"+memberID+"',`memberName`='"+memberName+"',`joinDate`='"+joinDateObject+"',`contactNumber`='"+ContactNumber+"',`email`='"+email+"' WHERE `memberId`='"+memberID+"'";
            pst = conn.prepareStatement(searchQuary);
            pst.execute();
            JOptionPane.showMessageDialog(null,memberName+"'s"+ " record updated successfully");
            tableLoad();
            UpdateNewMemberInStatusTable(memberName,OldDmemberName);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
       
        //    txt_MemberID.setEnabled(false);
             DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
           int selectedRowIndex = jTable1.getSelectedRow();
            txt_MemberID.setText(model.getValueAt(selectedRowIndex, 0).toString());
            txt_MemberName.setText(model.getValueAt(selectedRowIndex, 1).toString());
            
            java.util.Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(selectedRowIndex, 2).toString());
             dc_JoningDate.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(memberRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            
           
            txt_ContactNumber.setText(model.getValueAt(selectedRowIndex, 3).toString());
            txt_email.setText(model.getValueAt(selectedRowIndex, 4).toString());
            OldDmemberName = model.getValueAt(selectedRowIndex, 1).toString();
        
            
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
            String memberID = txt_MemberID.getText();
            String memberName = txt_MemberName.getText();
                 try {
            String deleteQuary = "DELETE FROM `memberdetails` WHERE `memberId`='"+memberID+"'";
             pst = conn.prepareStatement(deleteQuary);
            pst.execute();
            JOptionPane.showMessageDialog(null,memberName+"'s"+ " record deleted successfully");
            tableLoad();
            loadNameCombo();
            DeleteMemberFromStatusTable(memberName);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cmb_memberSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_memberSearchActionPerformed
       
    }//GEN-LAST:event_cmb_memberSearchActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
     
        String name= cmb_memberSearch.getSelectedItem().toString();
             try {
            String searchQuary = "SELECT * FROM `memberdetails` WHERE `memberName` ='"+name+"'";
            pst = conn.prepareStatement(searchQuary);
            rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        tableLoad();
        loadNameCombo();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        HomePage h1 =new HomePage();
        h1.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(memberRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(memberRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(memberRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(memberRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new memberRegistration().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(memberRegistration.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_memberSearch;
    private com.toedter.calendar.JDateChooser dc_JoningDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_MemberID;
    private javax.swing.JLabel lbl_username;
    private javax.swing.JTextField txt_ContactNumber;
    private javax.swing.JTextField txt_MemberID;
    private javax.swing.JTextField txt_MemberName;
    private javax.swing.JTextField txt_email;
    // End of variables declaration//GEN-END:variables
}
