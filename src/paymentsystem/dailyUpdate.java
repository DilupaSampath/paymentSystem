/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paymentsystem;

import dbConnect.DB_connect;
import java.awt.Color;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author madsampath
 */
public class dailyUpdate extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    java.sql.Date sqldate;
//Date date = new Date();

    int start;
    int end;
    int result;
    int pause;
    int res;
    int restart;

    /**
     * Creates new form dailyUpdate
     */
    public dailyUpdate() throws SQLException {
        initComponents();
        conn = DB_connect.connect();
        loadcombobox();
        tableLoad();

    }

    String CurrentTime() {

        LocalDateTime LocalTime = LocalDateTime.now();
        System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalTime));
        return DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalTime);

    }

    void updateDailyStatusTable(String status) {
        String mId = FindMemberId(cmbx_currentMembers.getSelectedItem().toString());
        String mDate = getCurrentDate();
        String mName = cmbx_currentMembers.getSelectedItem().toString();
        String mTime = CurrentTime();

        try {
            String CurrentStatusQuary = "INSERT INTO `dailystatus`(`MemberId`, `MemberName`, `Date`, `Time`, `Status`) VALUES ('" + mId + "','" + mName + "','" + mDate + "','" + mTime + "','" + status + "')";
            //UPDATE `currentstatus` SET  `CurrentStatus` = 'OnTime' where `memberName`= '"+memberName+"';

            pst = conn.prepareStatement(CurrentStatusQuary);
            pst.execute();   //System.out.println(getgender());
            JOptionPane.showMessageDialog(null, "OK");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    double getDailyOutHours(String name) {

        String mId = FindMemberId(cmbx_currentMembers.getSelectedItem().toString());
        String mDate = getCurrentDate();
        try {
            String sql = "SELECT * FROM `dailystatus` where `MemberId`='" + mId + "' and `Date` = '" + mDate + "'  and `Status` ='PauseTime' or `status`='ResumeTime'  ORDER BY `Time` ASC ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            double array[] = new double[10];
            int index = 0;
            int PtotSecs = 0;
            int RtotSecs = 0;
            double resultNew = 0;
            while (rs.next()) {
                String Dstatus = rs.getString("Status");
                String DTime = rs.getString("Time");
//return Integer.parseInt(UFixedHours);
                if (Dstatus.equals("PauseTime")) {

                    PtotSecs = (Integer.parseInt(DTime.split(":")[0]) * 3600) + (Integer.parseInt(DTime.split(":")[1]) * 60) + (Integer.parseInt(DTime.split(":")[2]));
                    int Phours = (PtotSecs / 3600);
                    int Pminutes = (PtotSecs % 3600) / 60;
                    int Pseconds = PtotSecs % 60;

                } else if (Dstatus.equals("ResumeTime")) {

                    RtotSecs = (Integer.parseInt(DTime.split(":")[0]) * 3600) + (Integer.parseInt(DTime.split(":")[1]) * 60) + (Integer.parseInt(DTime.split(":")[2]));
                    int Rhours = (RtotSecs / 3600);
                    int Rminutes = (RtotSecs % 3600) / 60;
                    int Rseconds = RtotSecs % 60;
                    array[index] = RtotSecs - PtotSecs;
                    index++;
                }

            }
            for (int i = 0; i < array.length; i++) {
                resultNew = array[i] + resultNew;

            }
            return resultNew;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        return 0;
    }

    String getCurrentDate() {
        LocalDate localDate = LocalDate.now();
        //System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate));
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);
    }

    int getFixedHours(String name) {

        try {
            String sql = "SELECT * FROM `unitpayment` WHERE `MemberName`='" + name + "' ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            while (rs.next()) {
                String UFixedHours = rs.getString("FixedHours");
                return Integer.parseInt(UFixedHours);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        return 1;
    }

    void setHoursAndOtHoursToDatabase(String MemberId, String MemberName, String Date) {

        int NormalHours = (int) ((FindFullHrs() / 3600) - (getDailyOutHours(MemberName) / 3600));
        int OtHours = (int) (((FindFullHrs() / 3600) - (getDailyOutHours(MemberName) / 3600)) - getFixedHours(MemberName));
        try {
            String CurrentStatusQuary = "INSERT INTO `dailyhours`(`MemberId`, `MemberName`, `Date`, `NormalHours`, `OtHours`) VALUES ('" + MemberId + "','" + MemberName + "','" + Date + "','" + NormalHours + "','" + OtHours + "')";
            //UPDATE `currentstatus` SET  `CurrentStatus` = 'OnTime' where `memberName`= '"+memberName+"';

            pst = conn.prepareStatement(CurrentStatusQuary);
            pst.execute();   //System.out.println(getgender());
            JOptionPane.showMessageDialog(null, "OK");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    int getSeconds(String time, int res) {

        int MtotSecs = (Integer.parseInt(time.split(":")[0]) * 3600) + (Integer.parseInt(time.split(":")[1]) * 60) + (Integer.parseInt(time.split(":")[2]));
        res = MtotSecs;
        return res;
    }

    int FindFullHrs() {
        String mId = FindMemberId(cmbx_currentMembers.getSelectedItem().toString());
        String mDate = getCurrentDate();
        try {
            String sql = "SELECT * FROM `dailystatus` where `MemberId`='" + mId + "' and `Date` = '" + mDate + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                String MStatus = rs.getString("Status");
                String Mtime = rs.getString("Time");

                if (MStatus.equals("OnTime")) {

                    int totSecs = (Integer.parseInt(Mtime.split(":")[0]) * 3600) + (Integer.parseInt(Mtime.split(":")[1]) * 60) + (Integer.parseInt(Mtime.split(":")[2]));
                    int hours = (totSecs / 3600);
                    int minutes = (totSecs % 3600) / 60;
                    int seconds = totSecs % 60;
                    start = totSecs;
                    i = i++;
                } else if (MStatus.equals("OffTime")) {

                    int totSecs = (Integer.parseInt(Mtime.split(":")[0]) * 3600) + (Integer.parseInt(Mtime.split(":")[1]) * 60) + (Integer.parseInt(Mtime.split(":")[2]));
                    int hours = (totSecs / 3600);
                    int minutes = (totSecs % 3600) / 60;
                    int seconds = totSecs % 60;

                    end = totSecs;
                }
            }
            result = end - start;
            return result;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        return 0;

    }

    String FindMemberId(String name) {
        try {
            String sql = "SELECT * FROM `memberdetails`";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            while (rs.next()) {
                String Mname = rs.getString("memberName");
                if (Mname.equals(name)) {
                    return rs.getString("memberId");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        return null;
    }

    int FindOtHrs(String arr[]) {

        pause = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                int totSecs = (Integer.parseInt(arr[i].split(":")[0]) * 3600) + (Integer.parseInt(arr[i].split(":")[1]) * 60) + (Integer.parseInt(arr[i].split(":")[2]));
                pause = totSecs;
            } else {
                int totSecs = (Integer.parseInt(arr[i].split(":")[0]) * 3600) + (Integer.parseInt(arr[i].split(":")[1]) * 60) + (Integer.parseInt(arr[i].split(":")[2]));
                pause = totSecs - pause;
            }

        }
        return pause;
    }

    public void tableLoad() {
        try {
            String sqlQuery = "SELECT * FROM `dailystatus`";
            pst = conn.prepareStatement(sqlQuery);
            rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void loadcombobox() {
        try {
            String sql = "SELECT * FROM `memberdetails`";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            while (rs.next()) {
                String Mname = rs.getString("memberName");

                String name = Mname;
                cmbx_currentMembers.addItem(name);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txt_Search = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        btn_start = new javax.swing.JButton();
        btn_pause = new javax.swing.JButton();
        btn_stop = new javax.swing.JButton();
        cmbx_currentMembers = new javax.swing.JComboBox();
        btn_Restart = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 700));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Constantia", 3, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Daily update form");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(220, 20, 450, 50);

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
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(20, 100, 480, 160);

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 255, 255));
        jLabel3.setText("Search By");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(290, 30, 60, 30);
        jPanel2.add(txt_Search);
        txt_Search.setBounds(70, 30, 160, 26);

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 255, 255));
        jLabel7.setText("Search ");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(20, 30, 50, 30);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Member ID", "Member Name" }));
        jPanel2.add(jComboBox2);
        jComboBox2.setBounds(370, 30, 140, 26);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(150, 380, 540, 280);
        jPanel2.setBackground(new Color(0,0,10,130));

        jPanel1.setLayout(null);

        btn_start.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/OntimeButton.jpg"))); // NOI18N
        btn_start.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153)));
        btn_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_startActionPerformed(evt);
            }
        });
        jPanel1.add(btn_start);
        btn_start.setBounds(60, 110, 70, 70);

        btn_pause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/GoOut.jpg"))); // NOI18N
        btn_pause.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153)));
        btn_pause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pauseActionPerformed(evt);
            }
        });
        jPanel1.add(btn_pause);
        btn_pause.setBounds(230, 110, 70, 70);

        btn_stop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/StopToday.jpg"))); // NOI18N
        btn_stop.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(153, 153, 153)));
        btn_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_stopActionPerformed(evt);
            }
        });
        jPanel1.add(btn_stop);
        btn_stop.setBounds(400, 110, 70, 70);

        cmbx_currentMembers.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Member Name" }));
        cmbx_currentMembers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbx_currentMembersActionPerformed(evt);
            }
        });
        jPanel1.add(cmbx_currentMembers);
        cmbx_currentMembers.setBounds(140, 20, 230, 26);

        btn_Restart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ReStart.png"))); // NOI18N
        btn_Restart.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153)));
        btn_Restart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RestartActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Restart);
        btn_Restart.setBounds(230, 110, 70, 70);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(160, 210, 73, 23);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(150, 90, 540, 250);
        //jPanel1.setBackground(new Color(0,0,10,130));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Register.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -40, 940, 810);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_startActionPerformed

        btn_pause.setVisible(true);
        btn_Restart.setVisible(true);
        btn_start.setVisible(false);
        btn_stop.setVisible(true);

        
        
        String memberName = cmbx_currentMembers.getSelectedItem().toString();

        try {
            //String CurrentStatusQuary = "INSERT INTO `currentstatus`(`memberName`, `CurrentStatus`) VALUES ('"+memberName+"','OnTime')";
            //UPDATE `currentstatus` SET  `CurrentStatus` = 'OnTime' where `memberName`= '"+memberName+"';
            String CurrentStatusQuary = "UPDATE `currentstatus` SET  `CurrentStatus` = 'OnTime' where `memberName`= '" + memberName + "'";
            pst = conn.prepareStatement(CurrentStatusQuary);
            //JOptionPane.showMessageDialog(null," New Member Added Successfully123.......!!!");
            pst.execute();   //System.out.println(getgender());
            JOptionPane.showMessageDialog(null, " good Morning  " + memberName + "");
        } catch (Exception e) {
            System.out.println(e);
        }


    }//GEN-LAST:event_btn_startActionPerformed

    private void btn_pauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pauseActionPerformed
        // TODO add your handling code here:
        btn_pause.setVisible(false);
        btn_Restart.setVisible(true);
        btn_start.setVisible(false);
        btn_stop.setVisible(false);

        String cmbName = cmbx_currentMembers.getSelectedItem().toString();
        System.out.println(cmbName);

        try {
            String pause = "UPDATE `currentstatus` SET `CurrentStatus`='PauseTime' WHERE `MemberName` = '" + cmbName + "' ";
            pst = conn.prepareStatement(pause);
            pst.execute();
            JOptionPane.showMessageDialog(null, "You are taking a break " + cmbName);
        } catch (SQLException ex) {
            Logger.getLogger(dailyUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btn_pauseActionPerformed

    private void btn_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stopActionPerformed
      
        btn_pause.setVisible(true);
        btn_Restart.setVisible(true);
        btn_start.setVisible(true);
        btn_stop.setVisible(true);
        
        updateDailyStatusTable("OffTime");
        String cmbName = cmbx_currentMembers.getSelectedItem().toString();
        System.out.println(cmbName);

        try {
            String pause = "UPDATE `currentstatus` SET `CurrentStatus`='OffTime' WHERE `MemberName` = '" + cmbName + "' ";
            pst = conn.prepareStatement(pause);
            pst.execute();
            JOptionPane.showMessageDialog(null, "You are off for the day " + cmbName);
            setHoursAndOtHoursToDatabase(FindMemberId(cmbx_currentMembers.getSelectedItem().toString()), cmbx_currentMembers.getSelectedItem().toString(), getCurrentDate());
            System.out.println("done 2nd quary");
        } catch (SQLException ex) {
            Logger.getLogger(dailyUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btn_stopActionPerformed

    private void btn_RestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RestartActionPerformed
        // TODO add your handling code here:

        btn_pause.setVisible(true);
        btn_Restart.setVisible(false);
        btn_start.setVisible(true);
        btn_stop.setVisible(true);

        String cmbName = cmbx_currentMembers.getSelectedItem().toString();
        System.out.println(cmbName);

        try {
            String pause = "UPDATE `currentstatus` SET `CurrentStatus`='ResumeTime' WHERE `MemberName` = '" + cmbName + "' ";
            pst = conn.prepareStatement(pause);
            pst.execute();
            JOptionPane.showMessageDialog(null, "You are resuming work " + cmbName);
        } catch (SQLException ex) {
            Logger.getLogger(dailyUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btn_RestartActionPerformed

    private void cmbx_currentMembersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbx_currentMembersActionPerformed
        // TODO add your handling code here:
        String cmnName = cmbx_currentMembers.getSelectedItem().toString();

        try {
            String statusQuery = "SELECT `CurrentStatus` FROM `currentstatus` WHERE `MemberName` = '" + cmnName + "' ";
            pst = conn.prepareStatement(statusQuery);
            rs = pst.executeQuery();
            System.out.println(cmnName);
            while (rs.next()) {
                String status = rs.getString("CurrentStatus");
                System.out.println(status);
                if (status.equals("OnTime")) {
                    btn_start.setVisible(false);
                    btn_Restart.setVisible(false);
                    btn_stop.setVisible(true);
                    btn_pause.setVisible(true);
                } else if (status.equals("PauseTime")) {
                    btn_start.setVisible(false);
                    btn_Restart.setVisible(true);
                    btn_stop.setVisible(false);
                    btn_pause.setVisible(false);
                } else if (status.equals("ResumeTime")) {
                    btn_start.setVisible(false);
                    btn_Restart.setVisible(false);
                    btn_stop.setVisible(true);
                    btn_pause.setVisible(true);
                } else if (status.equals("OffTime")) {
                    btn_start.setVisible(true);
                    btn_Restart.setVisible(false);
                    btn_stop.setVisible(true);
                    btn_pause.setVisible(true);
                }
                status =null;
            }

        } catch (Exception e) {
        }


    }//GEN-LAST:event_cmbx_currentMembersActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        System.out.println(CurrentTime());
       
       //System.out.println(getDailyOutHours("Sampath") / 3600 + " -> Out hours");
        String cmbName = cmbx_currentMembers.getSelectedItem().toString();
        
        
        //Calendar now = Calendar.getInstance();   // Gets the current date and time
       // int year = now.get(Calendar.YEAR); 
       // System.out.println(year+" ->yrar");  
      //  System.out.println(getFixedHours(cmbName)+" -> fixed hours");
        System.out.println(((FindFullHrs()/3600)-(getDailyOutHours(cmbName)/3600))-getFixedHours(cmbName)+" -> OT hours");
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(dailyUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dailyUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dailyUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dailyUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new dailyUpdate().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(dailyUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Restart;
    private javax.swing.JButton btn_pause;
    private javax.swing.JButton btn_start;
    private javax.swing.JButton btn_stop;
    private javax.swing.JComboBox cmbx_currentMembers;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_Search;
    // End of variables declaration//GEN-END:variables
}
