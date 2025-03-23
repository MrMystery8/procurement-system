
package main;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import javax.swing.*;

public class PM_Dashboard extends javax.swing.JFrame {
    private PurchaseManager user;

    public PM_Dashboard(PurchaseManager user) {
        this.user = user;
        initComponents();

        usernamedisplay.setText("User: " + user.FName + " " + user.LName);
        roledisplay.setText("Role: " + user.userRole);

        // Chart panel setup
        Chart.setLayout(new BorderLayout());
        Chart.setPreferredSize(new Dimension(350, 250));
        Chart.setBounds(20, 120, 350, 250);

        displayPOsCreatedPerMonthBarChart();
    }

    private void displayPOsCreatedPerMonthBarChart() {
        // Gather data: Count of POs created each month
        Map<String, Double> poCountsPerMonth = new TreeMap<>(); // Use TreeMap to keep months in order
        String[] allPOs = PurchaseManager.getAllPO();
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM/yyyy");

        for (String poLine : allPOs) {
            String[] poData = poLine.split(";");
            if (poData.length > 6) {
                LocalDate dateCreated = LocalDate.parse(poData[6], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String monthYear = dateCreated.format(monthFormatter);
                poCountsPerMonth.put(monthYear, poCountsPerMonth.getOrDefault(monthYear, 0.0) + 1);
            }
        }

        // Create the bar chart
        ManualBarChart barChart = new ManualBarChart(poCountsPerMonth, "Number of POs Created Per Month", "Month", "Number of POs");

        // Add the chart to the panel
        Chart.removeAll();
        Chart.add(barChart, BorderLayout.CENTER);
        Chart.revalidate();
        Chart.repaint();
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        LeftSide = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        usernamedisplay = new javax.swing.JLabel();
        roledisplay = new javax.swing.JLabel();
        RightSide = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCreateNewPO = new java.awt.Button();
        btnCreatePOFromPR = new java.awt.Button();
        btnViewPO = new java.awt.Button();
        btnEditPO = new java.awt.Button();
        Chart = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(null);

        LeftSide.setBackground(new java.awt.Color(0, 0, 153));
        LeftSide.setMinimumSize(new java.awt.Dimension(200, 500));
        LeftSide.setPreferredSize(new java.awt.Dimension(200, 500));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/image (2).png"))); // NOI18N
        jLabel5.setAlignmentY(0.0F);
        jLabel5.setIconTextGap(0);
        jLabel5.setMinimumSize(new java.awt.Dimension(188, 188));
        jLabel5.setName(""); // NOI18N
        jLabel5.setPreferredSize(new java.awt.Dimension(188, 188));

        btnLogout.setBackground(new java.awt.Color(0, 0, 153));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/logoutwhite64.png"))); // NOI18N
        btnLogout.setToolTipText("Logout");
        btnLogout.setMinimumSize(new java.awt.Dimension(50, 50));
        btnLogout.setPreferredSize(new java.awt.Dimension(60, 60));
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        usernamedisplay.setBackground(new java.awt.Color(255, 255, 255));
        usernamedisplay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        usernamedisplay.setForeground(new java.awt.Color(255, 255, 255));
        usernamedisplay.setText("HI");

        roledisplay.setBackground(new java.awt.Color(255, 255, 255));
        roledisplay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        roledisplay.setForeground(new java.awt.Color(255, 255, 255));
        roledisplay.setText("HI");

        javax.swing.GroupLayout LeftSideLayout = new javax.swing.GroupLayout(LeftSide);
        LeftSide.setLayout(LeftSideLayout);
        LeftSideLayout.setHorizontalGroup(
            LeftSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addGroup(LeftSideLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LeftSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LeftSideLayout.createSequentialGroup()
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 128, Short.MAX_VALUE))
                    .addComponent(roledisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(usernamedisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        LeftSideLayout.setVerticalGroup(
            LeftSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftSideLayout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(usernamedisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roledisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(LeftSide);
        LeftSide.setBounds(0, 0, 200, 500);

        RightSide.setBackground(new java.awt.Color(255, 255, 255));
        RightSide.setPreferredSize(new java.awt.Dimension(600, 500));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Purchase Manager Dashboard");

        btnCreateNewPO.setBackground(new java.awt.Color(0, 0, 153));
        btnCreateNewPO.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCreateNewPO.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnCreateNewPO.setForeground(new java.awt.Color(255, 255, 255));
        btnCreateNewPO.setLabel("Create new PO");
        btnCreateNewPO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateNewPOActionPerformed(evt);
            }
        });

        btnCreatePOFromPR.setBackground(new java.awt.Color(0, 0, 153));
        btnCreatePOFromPR.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCreatePOFromPR.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnCreatePOFromPR.setForeground(new java.awt.Color(255, 255, 255));
        btnCreatePOFromPR.setLabel("Create PO from PR");
        btnCreatePOFromPR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreatePOFromPRActionPerformed(evt);
            }
        });

        btnViewPO.setBackground(new java.awt.Color(0, 0, 153));
        btnViewPO.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnViewPO.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnViewPO.setForeground(new java.awt.Color(255, 255, 255));
        btnViewPO.setLabel("View PO");
        btnViewPO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewPOActionPerformed(evt);
            }
        });

        btnEditPO.setActionCommand("Edit PO");
        btnEditPO.setBackground(new java.awt.Color(0, 0, 153));
        btnEditPO.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEditPO.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnEditPO.setForeground(new java.awt.Color(255, 255, 255));
        btnEditPO.setLabel("Edit PO");
        btnEditPO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditPOActionPerformed(evt);
            }
        });

        Chart.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout ChartLayout = new javax.swing.GroupLayout(Chart);
        Chart.setLayout(ChartLayout);
        ChartLayout.setHorizontalGroup(
            ChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ChartLayout.setVerticalGroup(
            ChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 284, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout RightSideLayout = new javax.swing.GroupLayout(RightSide);
        RightSide.setLayout(RightSideLayout);
        RightSideLayout.setHorizontalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, RightSideLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(57, Short.MAX_VALUE))
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnViewPO, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCreateNewPO, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCreatePOFromPR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditPO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        RightSideLayout.setVerticalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCreatePOFromPR, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreateNewPO, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnViewPO, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditPO, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        jPanel1.add(RightSide);
        RightSide.setBounds(200, 0, 600, 500);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateNewPOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateNewPOActionPerformed

        PM_CreatePO frame = new PM_CreatePO(user);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        this.dispose();     

        
        
    }//GEN-LAST:event_btnCreateNewPOActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        Admin admin = new Admin();
        admin.Logout();
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnCreatePOFromPRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreatePOFromPRActionPerformed
     
        
        PM_CreatePObyPR frame = new PM_CreatePObyPR(user);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        this.dispose();  
    }//GEN-LAST:event_btnCreatePOFromPRActionPerformed

    private void btnViewPOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewPOActionPerformed

        PM_POView frame = new PM_POView(user);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        this.dispose();     

    }//GEN-LAST:event_btnViewPOActionPerformed

    private void btnEditPOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditPOActionPerformed

        PM_EditPO frame = new PM_EditPO(user);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        this.dispose();


    }//GEN-LAST:event_btnEditPOActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Chart;
    private javax.swing.JPanel LeftSide;
    private javax.swing.JPanel RightSide;
    private java.awt.Button btnCreateNewPO;
    private java.awt.Button btnCreatePOFromPR;
    private java.awt.Button btnEditPO;
    private javax.swing.JButton btnLogout;
    private java.awt.Button btnViewPO;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel roledisplay;
    private javax.swing.JLabel usernamedisplay;
    // End of variables declaration//GEN-END:variables
}
