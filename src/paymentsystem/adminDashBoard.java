/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paymentsystem;

import dbConnect.DB_connect;
import java.awt.Color;
import java.util.Calendar;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import paymentsystem.dailyUpdate;

/**
 *
 * @author madsampath
 */
public class adminDashBoard extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form dailyUpdate
     */
    public adminDashBoard() throws SQLException {

        conn = DB_connect.connect();
        initComponents();
        loadDateCombo();
        loadNameCombo();
        tableLoad();
        txt_additional.setText("0");
        //jPanel1.setBackground(new Color(0,0,10,130));
    }
    Boolean checkIfPaid(String name, String date){
    
      try {
            String sql = "SELECT * FROM `monthlypaymenttable` WHERE `memberName` ='"+name+"' and `PaidMonth`= '"+date+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            while (rs.next()) {
                String Mname = rs.getString("memberName");
                System.out.println("Paid this month");
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
        System.out.println("Not this month");
    return false;
    }
    
    String FindMemberEmail(String name) {
        try {
            String sql = "SELECT * FROM `memberdetails`";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            while (rs.next()) {
                String Mname = rs.getString("memberName");
                if (Mname.equals(name)) {
                    return rs.getString("email");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        return null;
    }
    public void tableLoad() {
        try {
            String sqlQuery = "SELECT * FROM `monthlypaymenttable`";
            pst = conn.prepareStatement(sqlQuery);
            rs = pst.executeQuery();
            paidAmountTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    String getCurrentDate() {
        LocalDate localDate = LocalDate.now();
        //System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate));
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);
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

    void loadDateCombo() {
        Calendar now = Calendar.getInstance();   // Gets the current date and time
        int year = now.get(Calendar.YEAR);
        cmb_yearmonth.addItem(year + "-01");
        cmb_yearmonth.addItem(year + "-02");
        cmb_yearmonth.addItem(year + "-03");
        cmb_yearmonth.addItem(year + "-04");
        cmb_yearmonth.addItem(year + "-05");
        cmb_yearmonth.addItem(year + "-06");
        cmb_yearmonth.addItem(year + "-07");
        cmb_yearmonth.addItem(year + "-08");
        cmb_yearmonth.addItem(year + "-09");
        cmb_yearmonth.addItem(year + "-10");
        cmb_yearmonth.addItem(year + "-11");
        cmb_yearmonth.addItem(year + "-12");

        
        
        cmb_yearmonthSearch.addItem(year + "-01");
        cmb_yearmonthSearch.addItem(year + "-02");
        cmb_yearmonthSearch.addItem(year + "-03");
        cmb_yearmonthSearch.addItem(year + "-04");
        cmb_yearmonthSearch.addItem(year + "-05");
        cmb_yearmonthSearch.addItem(year + "-06");
        cmb_yearmonthSearch.addItem(year + "-07");
        cmb_yearmonthSearch.addItem(year + "-08");
        cmb_yearmonthSearch.addItem(year + "-09");
        cmb_yearmonthSearch.addItem(year + "-10");
        cmb_yearmonthSearch.addItem(year + "-11");
        cmb_yearmonthSearch.addItem(year + "-12");
        
                
                
                
    }

    void loadNameCombo() {
        try {
            String sql = "SELECT * FROM `memberdetails`";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("memberName");
                cmbox_name.addItem(name);
                cmb_SearchMemberName.addItem(name);
            }
        } catch (Exception e) {
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
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        paidAmountTable = new javax.swing.JTable();
        cmb_SearchMemberName = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cmb_yearmonthSearch = new javax.swing.JComboBox();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btn_start = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_totNormalHours = new javax.swing.JLabel();
        lbl_OtHours = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbl_netSal = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbl_totOt = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbl_tot = new javax.swing.JLabel();
        btn_start1 = new javax.swing.JButton();
        cmb_yearmonth = new javax.swing.JComboBox();
        cmbox_name = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txt_additional = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        lbl_totHolidaySalary = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbl_holidayHours = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 700));
        setMinimumSize(new java.awt.Dimension(800, 700));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Constantia", 3, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Salary Genarate form");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(180, 20, 490, 50);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home.png"))); // NOI18N
        jButton6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 255, 255), new java.awt.Color(0, 255, 255), new java.awt.Color(0, 255, 255), new java.awt.Color(0, 255, 255)));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(720, 20, 50, 50);

        jPanel2.setLayout(null);

        paidAmountTable.setModel(new javax.swing.table.DefaultTableModel(
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
        paidAmountTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paidAmountTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(paidAmountTable);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(20, 60, 720, 200);

        cmb_SearchMemberName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Member Name" }));
        jPanel2.add(cmb_SearchMemberName);
        cmb_SearchMemberName.setBounds(100, 10, 110, 26);

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 255, 255));
        jLabel4.setText("Search By");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(20, 10, 60, 30);

        cmb_yearmonthSearch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Date" }));
        jPanel2.add(cmb_yearmonthSearch);
        cmb_yearmonthSearch.setBounds(240, 10, 100, 26);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search1.png"))); // NOI18N
        jButton5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 255, 255), new java.awt.Color(51, 255, 255), new java.awt.Color(51, 255, 255), new java.awt.Color(0, 255, 255)));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5);
        jButton5.setBounds(400, 6, 40, 40);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete128.png"))); // NOI18N
        jButton3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 255, 255), new java.awt.Color(102, 255, 255)));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);
        jButton3.setBounds(460, 6, 40, 40);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(20, 80, 760, 280);
        jPanel2.setBackground(new Color(0,0,10,130));

        jPanel1.setLayout(null);

        btn_start.setBackground(new java.awt.Color(0, 204, 153));
        btn_start.setText("Show This Month");
        btn_start.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153)));
        btn_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_startActionPerformed(evt);
            }
        });
        jPanel1.add(btn_start);
        btn_start.setBounds(600, 120, 100, 40);

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 255));
        jLabel6.setText("Member Name");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(280, 10, 100, 40);

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 255));
        jLabel8.setText("Date");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(10, 10, 100, 40);

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 255));
        jLabel9.setText("Total Normal Hours");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(10, 70, 130, 40);

        lbl_totNormalHours.setForeground(new java.awt.Color(0, 153, 51));
        lbl_totNormalHours.setText("0");
        jPanel1.add(lbl_totNormalHours);
        lbl_totNormalHours.setBounds(140, 70, 150, 40);

        lbl_OtHours.setForeground(new java.awt.Color(0, 153, 51));
        lbl_OtHours.setText("0");
        jPanel1.add(lbl_OtHours);
        lbl_OtHours.setBounds(530, 70, 150, 40);

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 255));
        jLabel10.setText("Total OT Hours");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(430, 70, 130, 40);

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 255));
        jLabel11.setText("Net Salary");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(10, 110, 130, 40);

        lbl_netSal.setForeground(new java.awt.Color(0, 153, 51));
        lbl_netSal.setText("0");
        jPanel1.add(lbl_netSal);
        lbl_netSal.setBounds(140, 110, 150, 40);

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 255));
        jLabel12.setText("OT Salary");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(430, 110, 130, 40);

        lbl_totOt.setForeground(new java.awt.Color(0, 153, 51));
        lbl_totOt.setText("0");
        jPanel1.add(lbl_totOt);
        lbl_totOt.setBounds(530, 110, 150, 40);

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 255));
        jLabel13.setText("Aditional");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(10, 150, 130, 40);

        lbl_tot.setForeground(new java.awt.Color(0, 153, 51));
        jPanel1.add(lbl_tot);
        lbl_tot.setBounds(120, 210, 150, 40);

        btn_start1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/genarate.png"))); // NOI18N
        btn_start1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153)));
        btn_start1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_start1ActionPerformed(evt);
            }
        });
        jPanel1.add(btn_start1);
        btn_start1.setBounds(610, 30, 71, 70);

        cmb_yearmonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Date" }));
        jPanel1.add(cmb_yearmonth);
        cmb_yearmonth.setBounds(80, 20, 160, 26);

        cmbox_name.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Member" }));
        jPanel1.add(cmbox_name);
        cmbox_name.setBounds(380, 20, 160, 26);

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 255));
        jLabel14.setText("Total Salary");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(10, 210, 130, 40);

        jButton1.setText("Calculate Total");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 255, 255), new java.awt.Color(0, 255, 255)));
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(600, 180, 100, 40);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emailNew.png"))); // NOI18N
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 255, 255), new java.awt.Color(102, 255, 255)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(540, 180, 40, 40);
        jPanel1.add(txt_additional);
        txt_additional.setBounds(130, 160, 100, 26);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/chekingPaid.png"))); // NOI18N
        jButton4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 255, 255), new java.awt.Color(102, 255, 255), new java.awt.Color(0, 255, 255), new java.awt.Color(51, 255, 255)));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);
        jButton4.setBounds(470, 180, 50, 40);

        lbl_totHolidaySalary.setForeground(new java.awt.Color(0, 153, 51));
        lbl_totHolidaySalary.setText("0");
        jPanel1.add(lbl_totHolidaySalary);
        lbl_totHolidaySalary.setBounds(350, 110, 130, 40);

        jLabel16.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 255));
        jLabel16.setText("Holiday Salary");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(220, 110, 130, 40);

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 255));
        jLabel17.setText("Total Holiday Hours");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(220, 70, 130, 40);

        lbl_holidayHours.setForeground(new java.awt.Color(0, 153, 51));
        lbl_holidayHours.setText("0");
        jPanel1.add(lbl_holidayHours);
        lbl_holidayHours.setBounds(350, 70, 130, 40);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(20, 390, 760, 260);
        //jPanel1.setBackground(new Color(0,0,10,130));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Register.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -40, 940, 810);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_startActionPerformed
        DecimalFormat f = new DecimalFormat("##.00");
        String name = cmbox_name.getSelectedItem().toString();
        System.out.println(name);
        String normHours = null;
        String otHours = null;

        String date = cmb_yearmonth.getSelectedItem().toString();
        System.out.println(date);
        try {
            String holidayHours = "SELECT SUM(`NormalHours`) AS HNormalHours FROM `finaleholidaystatustable` WHERE `MemberName`='" + name + "' AND `Date` LIKE '" + date + "%' AND `AdminStatus`='Approved'";
            String hours = "SELECT SUM(`NormalHours`) AS numberOfHours, SUM(`OtHours`) AS numberOfOtHours FROM `dailyhours` WHERE `MemberName`= '" + name + "' AND `Date` LIKE '" + date + "%'";
            String salNormalHours = "SELECT `ratePerNormalHour`, `ratePerOtHour` FROM `unitpayment` WHERE `MemberName`= '" + name + "'";
            pst = conn.prepareStatement(hours);
            rs = pst.executeQuery(hours);

            while (rs.next()) {
                normHours = rs.getString("numberOfHours");
                otHours = rs.getString("numberOfOtHours");
                lbl_OtHours.setText(f.format(Double.parseDouble(otHours)));
                lbl_totNormalHours.setText(f.format(Double.parseDouble(normHours)));

            }

            pst = null;
            rs = null;

            pst = conn.prepareStatement(salNormalHours);
            rs = pst.executeQuery(salNormalHours);

            while (rs.next()) {
                String salNormHours = rs.getString("ratePerNormalHour");
                String salOtHours = rs.getString("ratePerOtHour");
                double salHours = Double.parseDouble(salNormHours);
                double numberHours = Double.parseDouble(normHours);

                double salOt = Double.parseDouble(salOtHours);
                double numberOt = Double.parseDouble(otHours);
               
                
                String netSal =(f.format(salHours * numberHours));
                lbl_netSal.setText(netSal);
                String netOt = f.format(salOt * numberOt);
                lbl_totOt.setText(netOt);
              ///  lbl_tot.setText(f.format((salHours * numberHours) + (salOt * numberOt)));
                salHours = 0;
                numberHours = 0;
                salOt = 0;
                numberOt = 0;
                netOt = null;

            }
            
            pst = conn.prepareStatement(holidayHours);
            rs = pst.executeQuery(holidayHours);
            while (rs.next()) {
                String salHNormHours = rs.getString("HNormalHours");              
                double salHours = Double.parseDouble(salHNormHours);
                lbl_holidayHours.setText(f.format(salHours));
                lbl_totHolidaySalary.setText(f.format(salHours * 100.00));        
            }

        } catch (Exception e) {
        }


    }//GEN-LAST:event_btn_startActionPerformed

    private void btn_start1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_start1ActionPerformed
       
        String MemberId = FindMemberId(cmbox_name.getSelectedItem().toString());
        String MemberName = cmbox_name.getSelectedItem().toString();
        String Date = getCurrentDate();
        String PaidAmount = lbl_tot.getText();
        String ToPayAmount = txt_additional.getText();
        String NormalHrs = lbl_totNormalHours.getText();
        String OtHrs = lbl_OtHours.getText();
        String cmb_PaidForMonth = cmb_yearmonth.getSelectedItem().toString();
        
           if(checkIfPaid(cmbox_name.getSelectedItem().toString(),cmb_yearmonth.getSelectedItem().toString()))
        {
        
        JOptionPane.showMessageDialog(null, "We Paid to "+cmbox_name.getSelectedItem().toString()+" for "+cmb_yearmonth.getSelectedItem().toString(), "Error", JOptionPane.ERROR_MESSAGE);
        
        }
           else
           {
        try {
            String CurrentStatusQuary = "INSERT INTO `monthlypaymenttable`(`memberId`, `memberName`, `Date`, `NormalHours`, `OtHours`, `PaidMonth`, `PaidAmount`, `ToPay`) VALUES  ('" + MemberId + "','" + MemberName + "','" + Date + "','"+NormalHrs+"','"+OtHrs+"','"+cmb_PaidForMonth+"','" + PaidAmount + "','" + ToPayAmount + "')";
            //UPDATE `currentstatus` SET  `CurrentStatus` = 'OnTime' where `memberName`= '"+memberName+"';

            pst = conn.prepareStatement(CurrentStatusQuary);
            pst.execute();   //System.out.println(getgender());
            JOptionPane.showMessageDialog(null, "Rs." + PaidAmount + " Paid Success fully to "+MemberName);
            tableLoad();
        } catch (Exception e) {
        }
           }
    }//GEN-LAST:event_btn_start1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
            if(checkIfPaid(cmbox_name.getSelectedItem().toString(),cmb_yearmonth.getSelectedItem().toString()))
        {
        
        JOptionPane.showMessageDialog(null, "We Paid to "+cmbox_name.getSelectedItem().toString()+" for "+cmb_yearmonth.getSelectedItem().toString(), "Error", JOptionPane.ERROR_MESSAGE);
        
        }
        double nettot = Double.parseDouble(lbl_netSal.getText());
        double Holidaytot = Double.parseDouble(lbl_totHolidaySalary.getText());
        double ottotal = Double.parseDouble(lbl_totOt.getText());
        double totAdd = Double.parseDouble(txt_additional.getText());
        lbl_tot.setText(Double.toString(nettot+Holidaytot+ottotal+totAdd));
        
       

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String totAditional = txt_additional.getText();
        String totOtSal = lbl_totOt.getText();
        String totNetSal = lbl_netSal.getText();
        String totOtHrs = lbl_OtHours.getText();
        String totNormalHrs = lbl_totNormalHours.getText();
        String totSal = lbl_tot.getText();
        String mailString = 
 "Payment Date           : "+getCurrentDate()+"\n"+
 "Total Working Hours  : "+totNormalHrs+"\n"+
 "Total OT Hours         : "+totOtHrs+"\n"+
 "Net Salary               : "+totNetSal+"\n"+
 "OT Salary                : "+totOtSal+"\n"+
 "Total Salary        : "+totSal+"\n";
        SendMail s1 = new SendMail(FindMemberEmail(cmbox_name.getSelectedItem().toString()),"Salary Slip "+cmb_yearmonth.getSelectedItem().toString(),mailString,"madsampath94@gmail.com","dsmax071");
      
    }//GEN-LAST:event_jButton2ActionPerformed

    private void paidAmountTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paidAmountTableMouseClicked
        // TODO add your handling code here:
       /* 
              DefaultTableModel model = (DefaultTableModel)paidAmountTable.getModel();
           int selectedRowIndex = paidAmountTable.getSelectedRow();
            cmb_yearmonth.setSelectedItem(model.getValueAt(selectedRowIndex, 0).toString());
            cmbox_name.setSelectedItem(model.getValueAt(selectedRowIndex, 2).toString());
            lbl_totNormalHours.setText(model.getValueAt(selectedRowIndex, 4).toString());
            lbl_OtHours.setText(model.getValueAt(selectedRowIndex, 5).toString());
            lbl_netSal.setText(model.getValueAt(selectedRowIndex, 6).toString());
        */
    }//GEN-LAST:event_paidAmountTableMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
                String date= cmb_yearmonthSearch.getSelectedItem().toString();
        String name= cmb_SearchMemberName.getSelectedItem().toString();
        try {
            String searchQuary = "SELECT * FROM `monthlypaymenttable` WHERE `memberName` ='"+name+"' and `Date`  LIKE '" + date + "%'";
            pst = conn.prepareStatement(searchQuary);
            rs = pst.executeQuery();
            paidAmountTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println(e);
        }

        
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        HomePage h1 =new HomePage();
        h1.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         DefaultTableModel model = (DefaultTableModel)paidAmountTable.getModel();
         int selectedRowIndex = paidAmountTable.getSelectedRow();
         //txt_MemberID.setText(model.getValueAt(selectedRowIndex, 0).toString());
        
        String memberName = model.getValueAt(selectedRowIndex, 2).toString();
        String PaidForMonth = model.getValueAt(selectedRowIndex, 6).toString();
          //  String memberName = txt_MemberName.getText();
                 try {
            String deleteQuary = "DELETE FROM `monthlypaymenttable` WHERE `memberName`='"+memberName+"' and `PaidMonth`='"+PaidForMonth+"'";
             pst = conn.prepareStatement(deleteQuary);
            pst.execute();
            JOptionPane.showMessageDialog(null,memberName+"'s"+ " record deleted successfully");
            tableLoad();
        
           
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
        if(checkIfPaid(cmbox_name.getSelectedItem().toString(),cmb_yearmonth.getSelectedItem().toString()))
        {
        
        JOptionPane.showMessageDialog(null, "We Paid to "+cmbox_name.getSelectedItem().toString()+" for "+cmb_yearmonth.getSelectedItem().toString(), "Error", JOptionPane.ERROR_MESSAGE);
        
        }
        else
        {
        JOptionPane.showMessageDialog(null, "We are not yet paid to "+cmbox_name.getSelectedItem().toString()+" for "+cmb_yearmonth.getSelectedItem().toString());
        
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(adminDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new adminDashBoard().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(adminDashBoard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_start;
    private javax.swing.JButton btn_start1;
    private javax.swing.JComboBox cmb_SearchMemberName;
    private javax.swing.JComboBox cmb_yearmonth;
    private javax.swing.JComboBox cmb_yearmonthSearch;
    private javax.swing.JComboBox cmbox_name;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_OtHours;
    private javax.swing.JLabel lbl_holidayHours;
    private javax.swing.JLabel lbl_netSal;
    private javax.swing.JLabel lbl_tot;
    private javax.swing.JLabel lbl_totHolidaySalary;
    private javax.swing.JLabel lbl_totNormalHours;
    private javax.swing.JLabel lbl_totOt;
    private javax.swing.JTable paidAmountTable;
    private javax.swing.JTextField txt_additional;
    // End of variables declaration//GEN-END:variables
}
