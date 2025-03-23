package main;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

public class SM_Dashboard extends javax.swing.JFrame {
    private SalesManager user;

    public SM_Dashboard(SalesManager user) {
        this.user = user;
        initComponents();

        usernamedisplay.setText("User: " + user.FName + " " + user.LName);
        roledisplay.setText("Role: " + user.userRole);

        // Chart panel setup
        Chart.setLayout(new BorderLayout());
        Chart.setPreferredSize(new Dimension(350, 250));
        Chart.setBounds(20, 120, 350, 250);

        displayMonthlySalesTrendsLineChart();
    }

    private void displayMonthlySalesTrendsLineChart() {
        // Gather data: Total sales amount for each month
        Map<String, Double> monthlySales = new TreeMap<>(); // TreeMap to keep months in order
        String[] allSales = user.getAllSales();
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM/yyyy"); // For formatting the month/year

        for (String saleLine : allSales) {
            String[] saleData = saleLine.split(";");
            if (saleData.length > 4) {
                // Date is in the format: dd;MM;yyyy 
                int day = Integer.parseInt(saleData[1]);
                int month = Integer.parseInt(saleData[2]);
                int year = Integer.parseInt(saleData[3]);

                LocalDate saleDate = LocalDate.of(year, month, day); // Create LocalDate directly

                String monthYear = saleDate.format(monthFormatter); // Format to MM/yyyy
                double salesAmount = Double.parseDouble(saleData[10]); // Assuming amount is at index 4
                monthlySales.put(monthYear, monthlySales.getOrDefault(monthYear, 0.0) + salesAmount);
            }
        }

        // Create the line chart
        ManualLineChart lineChart = new ManualLineChart(monthlySales, "Monthly Sales Trends", "Month", "Total Sales ($)");

        // Add the chart to the panel
        Chart.removeAll();
        Chart.add(lineChart, BorderLayout.CENTER);
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
        btnCreatePR = new java.awt.Button();
        btnViewItems = new java.awt.Button();
        btnSalesEntry1 = new java.awt.Button();
        btnEditPR = new java.awt.Button();
        btnViewPR = new java.awt.Button();
        btnViewSales = new java.awt.Button();
        btnEditSales = new java.awt.Button();
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
        jLabel1.setText("Sales Manager Dashboard");

        btnCreatePR.setBackground(new java.awt.Color(0, 0, 153));
        btnCreatePR.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCreatePR.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnCreatePR.setForeground(new java.awt.Color(255, 255, 255));
        btnCreatePR.setLabel("Create PR");
        btnCreatePR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreatePRActionPerformed(evt);
            }
        });

        btnViewItems.setBackground(new java.awt.Color(0, 0, 153));
        btnViewItems.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnViewItems.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnViewItems.setForeground(new java.awt.Color(255, 255, 255));
        btnViewItems.setLabel("View Items");
        btnViewItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewItemsActionPerformed(evt);
            }
        });

        btnSalesEntry1.setBackground(new java.awt.Color(0, 0, 153));
        btnSalesEntry1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalesEntry1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnSalesEntry1.setForeground(new java.awt.Color(255, 255, 255));
        btnSalesEntry1.setLabel("Sales Entry");
        btnSalesEntry1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalesEntry1ActionPerformed(evt);
            }
        });

        btnEditPR.setBackground(new java.awt.Color(0, 0, 153));
        btnEditPR.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEditPR.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnEditPR.setForeground(new java.awt.Color(255, 255, 255));
        btnEditPR.setLabel("Edit PR");
        btnEditPR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditPRActionPerformed(evt);
            }
        });

        btnViewPR.setBackground(new java.awt.Color(0, 0, 153));
        btnViewPR.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnViewPR.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnViewPR.setForeground(new java.awt.Color(255, 255, 255));
        btnViewPR.setLabel("View PR");
        btnViewPR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewPRActionPerformed(evt);
            }
        });

        btnViewSales.setBackground(new java.awt.Color(0, 0, 153));
        btnViewSales.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnViewSales.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnViewSales.setForeground(new java.awt.Color(255, 255, 255));
        btnViewSales.setLabel("View Sales");
        btnViewSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewSalesActionPerformed(evt);
            }
        });

        btnEditSales.setBackground(new java.awt.Color(0, 0, 153));
        btnEditSales.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEditSales.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnEditSales.setForeground(new java.awt.Color(255, 255, 255));
        btnEditSales.setLabel("Edit Sales");
        btnEditSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditSalesActionPerformed(evt);
            }
        });

        Chart.setForeground(new java.awt.Color(255, 255, 255));
        Chart.setPreferredSize(new java.awt.Dimension(560, 280));

        javax.swing.GroupLayout ChartLayout = new javax.swing.GroupLayout(Chart);
        Chart.setLayout(ChartLayout);
        ChartLayout.setHorizontalGroup(
            ChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChartLayout.createSequentialGroup()
                .addGap(0, Short.MAX_VALUE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ChartLayout.setVerticalGroup(
            ChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout RightSideLayout = new javax.swing.GroupLayout(RightSide);
        RightSide.setLayout(RightSideLayout);
        RightSideLayout.setHorizontalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel1)
                        .addGap(0, 69, Short.MAX_VALUE))
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Chart, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCreatePR, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalesEntry1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEditSales, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditPR, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnViewPR, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(RightSideLayout.createSequentialGroup()
                                .addComponent(btnViewSales, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(btnViewItems, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        RightSideLayout.setVerticalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(Chart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnViewPR, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditPR, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreatePR, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalesEntry1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditSales, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewSales, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewItems, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
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

    private void btnCreatePRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreatePRActionPerformed

        SM_CreatePR frame = new SM_CreatePR(user);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        this.dispose();     

        
        
    }//GEN-LAST:event_btnCreatePRActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        Admin admin = new Admin();
        admin.Logout();
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnViewItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewItemsActionPerformed
        SM_ItemsView frame = new SM_ItemsView(user);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        this.dispose();
        

    }//GEN-LAST:event_btnViewItemsActionPerformed

    private void btnSalesEntry1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalesEntry1ActionPerformed
        SM_SalesEntry frame = new SM_SalesEntry(user);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        this.dispose();    }//GEN-LAST:event_btnSalesEntry1ActionPerformed

    private void btnEditPRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditPRActionPerformed
                
        SM_EditPR frame = new SM_EditPR(user);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        this.dispose();
     }//GEN-LAST:event_btnEditPRActionPerformed

    private void btnViewPRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewPRActionPerformed

        SM_PRView frame = new SM_PRView(user);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_btnViewPRActionPerformed

    private void btnViewSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewSalesActionPerformed

        SM_SalesView frame = new SM_SalesView(user);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        this.dispose();

    }//GEN-LAST:event_btnViewSalesActionPerformed

    private void btnEditSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditSalesActionPerformed

        SM_SaleEdit frame = new SM_SaleEdit(user);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        this.dispose();

    }//GEN-LAST:event_btnEditSalesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Chart;
    private javax.swing.JPanel LeftSide;
    private javax.swing.JPanel RightSide;
    private java.awt.Button btnCreatePR;
    private java.awt.Button btnEditPR;
    private java.awt.Button btnEditSales;
    private javax.swing.JButton btnLogout;
    private java.awt.Button btnSalesEntry1;
    private java.awt.Button btnViewItems;
    private java.awt.Button btnViewPR;
    private java.awt.Button btnViewSales;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel roledisplay;
    private javax.swing.JLabel usernamedisplay;
    // End of variables declaration//GEN-END:variables
}
