package main;

import java.awt.*;
import java.util.Map;
import java.util.HashMap;

public class Admin_Dashboard extends javax.swing.JFrame {
    private Admin user;

    public Admin_Dashboard(Admin user) {
    initComponents();
    this.user = user;
    usernamedisplay.setText("User: " + user.FName + " " + user.LName);
    roledisplay.setText("Role: " + user.userRole);

    // Set layout for Chart panel
    Chart.setLayout(new BorderLayout());

    // Set the preferred size and bounds of the Chart panel
    Chart.setBounds(20, 120, 350, 250);

    displayUserRoleDistributionBarChart();
}

    private void displayUserRoleDistributionBarChart() {
        // Initialize roleCounts with all roles
        Map<String, Double> roleCounts = new HashMap<>();
        roleCounts.put("Admin", 0.0);
        roleCounts.put("SalesManager", 0.0);
        roleCounts.put("PurchaseManager", 0.0);
        roleCounts.put("InventoryManager", 0.0);
        roleCounts.put("FinanceManager", 0.0);

        // Count user roles
        String[] allUsers = user.getAllUsers();
        for (String userLine : allUsers) {
            String[] userData = userLine.split(";");
            if (userData.length > 2) {
                String role = userData[2];
                if (roleCounts.containsKey(role)) {
                    roleCounts.put(role, roleCounts.get(role) + 1);
                }
            }
        }

        // Create the manual bar chart
        ManualBarChart barChart = new ManualBarChart(roleCounts, "User Role Distribution", "Roles", "Count");

        // Clear the Chart panel before adding a new chart
        Chart.removeAll();

        // Add the chart to the Chart panel with BorderLayout.CENTER
        Chart.add(barChart, BorderLayout.CENTER);
        Chart.revalidate();
        Chart.repaint();
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnView_Stock = new java.awt.Button();
        jPanel1 = new javax.swing.JPanel();
        LeftSide = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        usernamedisplay = new javax.swing.JLabel();
        roledisplay = new javax.swing.JLabel();
        RightSide = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnRegistration = new java.awt.Button();
        btnViewUsers = new java.awt.Button();
        btnUserEdit = new java.awt.Button();
        btnView_PR = new java.awt.Button();
        btnView_Suppliers = new java.awt.Button();
        btnView_Stock2 = new java.awt.Button();
        btnView_PO1 = new java.awt.Button();
        Chart = new javax.swing.JPanel();

        btnView_Stock.setActionCommand("View Stock");
        btnView_Stock.setBackground(new java.awt.Color(0, 0, 153));
        btnView_Stock.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnView_Stock.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnView_Stock.setForeground(new java.awt.Color(255, 255, 255));
        btnView_Stock.setLabel("View Stock");
        btnView_Stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnView_StockActionPerformed(evt);
            }
        });

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
        jLabel1.setText("ADMIN DASHBOARD");

        btnRegistration.setBackground(new java.awt.Color(0, 0, 153));
        btnRegistration.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegistration.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnRegistration.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistration.setLabel("Registration");
        btnRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrationActionPerformed(evt);
            }
        });

        btnViewUsers.setBackground(new java.awt.Color(0, 0, 153));
        btnViewUsers.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnViewUsers.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnViewUsers.setForeground(new java.awt.Color(255, 255, 255));
        btnViewUsers.setLabel("View Users");
        btnViewUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUsersActionPerformed(evt);
            }
        });

        btnUserEdit.setBackground(new java.awt.Color(0, 0, 153));
        btnUserEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnUserEdit.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnUserEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnUserEdit.setLabel("Edit User");
        btnUserEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserEditActionPerformed(evt);
            }
        });

        btnView_PR.setActionCommand("View PR");
        btnView_PR.setBackground(new java.awt.Color(0, 0, 153));
        btnView_PR.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnView_PR.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnView_PR.setForeground(new java.awt.Color(255, 255, 255));
        btnView_PR.setLabel("View PR");
        btnView_PR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnView_PRActionPerformed(evt);
            }
        });

        btnView_Suppliers.setActionCommand("View Suppliers");
        btnView_Suppliers.setBackground(new java.awt.Color(0, 0, 153));
        btnView_Suppliers.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnView_Suppliers.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnView_Suppliers.setForeground(new java.awt.Color(255, 255, 255));
        btnView_Suppliers.setLabel("View Suppliers");
        btnView_Suppliers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnView_SuppliersActionPerformed(evt);
            }
        });

        btnView_Stock2.setActionCommand("View Stock");
        btnView_Stock2.setBackground(new java.awt.Color(0, 0, 153));
        btnView_Stock2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnView_Stock2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnView_Stock2.setForeground(new java.awt.Color(255, 255, 255));
        btnView_Stock2.setLabel("View Stock");
        btnView_Stock2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnView_Stock2ActionPerformed(evt);
            }
        });

        btnView_PO1.setActionCommand("View PO");
        btnView_PO1.setBackground(new java.awt.Color(0, 0, 153));
        btnView_PO1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnView_PO1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnView_PO1.setForeground(new java.awt.Color(255, 255, 255));
        btnView_PO1.setLabel("View PO");
        btnView_PO1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnView_PO1ActionPerformed(evt);
            }
        });

        Chart.setForeground(new java.awt.Color(255, 255, 255));
        Chart.setPreferredSize(new java.awt.Dimension(580, 240));

        javax.swing.GroupLayout ChartLayout = new javax.swing.GroupLayout(Chart);
        Chart.setLayout(ChartLayout);
        ChartLayout.setHorizontalGroup(
            ChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );
        ChartLayout.setVerticalGroup(
            ChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout RightSideLayout = new javax.swing.GroupLayout(RightSide);
        RightSide.setLayout(RightSideLayout);
        RightSideLayout.setHorizontalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnView_Stock2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnView_Suppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnView_PO1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnView_PR, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUserEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Chart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        RightSideLayout.setVerticalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(Chart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnViewUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnView_Stock2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnView_PO1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUserEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegistration, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnView_Suppliers, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnView_PR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

    private void btnRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrationActionPerformed

        Admin_UserRegistration frame = new Admin_UserRegistration(user);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        this.dispose();     

        
        
    }//GEN-LAST:event_btnRegistrationActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        Admin admin = new Admin();
        admin.Logout();
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnViewUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUsersActionPerformed
        Admin_UserView frame = new Admin_UserView(user);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        this.dispose();
        

    }//GEN-LAST:event_btnViewUsersActionPerformed

    private void btnUserEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserEditActionPerformed
        Admin_Edit_User frame = new Admin_Edit_User(user);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        this.dispose();    }//GEN-LAST:event_btnUserEditActionPerformed

    private void btnView_StockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnView_StockActionPerformed
    Admin_View_Stock adminStockForm = new Admin_View_Stock(user);
        adminStockForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnView_StockActionPerformed

    private void btnView_PRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnView_PRActionPerformed
      Admin_ViewPR adminPRForm = new Admin_ViewPR(user);
      adminPRForm.setVisible(true);
      this.dispose();
    }//GEN-LAST:event_btnView_PRActionPerformed

    private void btnView_SuppliersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnView_SuppliersActionPerformed
       Admin_View_Supplier adminSupplierForm = new Admin_View_Supplier(user);
        adminSupplierForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnView_SuppliersActionPerformed

    private void btnView_Stock2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnView_Stock2ActionPerformed
         Admin_View_Stock adminStockForm = new Admin_View_Stock(user);
        adminStockForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnView_Stock2ActionPerformed

    private void btnView_PO1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnView_PO1ActionPerformed
        Admin_ViewPO adminPOForm = new Admin_ViewPO(user);
        adminPOForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnView_PO1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Chart;
    private javax.swing.JPanel LeftSide;
    private javax.swing.JPanel RightSide;
    private javax.swing.JButton btnLogout;
    private java.awt.Button btnRegistration;
    private java.awt.Button btnUserEdit;
    private java.awt.Button btnViewUsers;
    private java.awt.Button btnView_PO1;
    private java.awt.Button btnView_PR;
    private java.awt.Button btnView_Stock;
    private java.awt.Button btnView_Stock2;
    private java.awt.Button btnView_Suppliers;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel roledisplay;
    private javax.swing.JLabel usernamedisplay;
    // End of variables declaration//GEN-END:variables
}
