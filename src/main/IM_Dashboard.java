package main;

import java.awt.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;

public class IM_Dashboard extends javax.swing.JFrame {
    private InventoryManager manager;

    public IM_Dashboard(InventoryManager manager) {
        this.manager = manager;
        initComponents();

        usernamedisplay.setText("User: " + manager.FName + " " + manager.LName);
        roledisplay.setText("Role: " + manager.userRole.name());

        // Chart panel setup
        Chart.setLayout(new BorderLayout());
        Chart.setPreferredSize(new Dimension(350, 250));

        displayItemsNeedingReorderBarChart();
    }

    private void displayItemsNeedingReorderBarChart() {
        // Gather data: Count of items needing reorder
        List<Item> itemsToReorder = manager.getItemsToReorder();
        Map<String, Double> reorderCounts = new HashMap<>();
        for (Item item : itemsToReorder) {
            reorderCounts.put(item.getItemID(), (double) item.getStockLevel());
        }

        // Create the bar chart
        ManualBarChart barChart = new ManualBarChart(reorderCounts, "Items Needing Reorder", "Item ID", "Stock Level");

        // Add the chart to the panel
        Chart.removeAll();
        Chart.add(barChart, BorderLayout.CENTER);
        Chart.revalidate();
        Chart.repaint();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LeftSide = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        usernamedisplay = new javax.swing.JLabel();
        roledisplay = new javax.swing.JLabel();
        RightSide = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnAdd_Item = new java.awt.Button();
        btnEdit_Suppliers = new java.awt.Button();
        btnEdit_Item1 = new java.awt.Button();
        btnView_Stock = new java.awt.Button();
        btnAdd_Suppliers = new java.awt.Button();
        btnView_Suppliers = new java.awt.Button();
        Chart = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LeftSide.setBackground(new java.awt.Color(0, 0, 153));
        LeftSide.setMinimumSize(new java.awt.Dimension(200, 500));

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
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        RightSide.setBackground(new java.awt.Color(255, 255, 255));
        RightSide.setPreferredSize(new java.awt.Dimension(600, 500));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Inventory Manager Dashboard");

        btnAdd_Item.setActionCommand("Add Item");
        btnAdd_Item.setBackground(new java.awt.Color(0, 0, 153));
        btnAdd_Item.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAdd_Item.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnAdd_Item.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd_Item.setLabel("Add Item");
        btnAdd_Item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd_ItemActionPerformed(evt);
            }
        });

        btnEdit_Suppliers.setActionCommand("Edit Suppliers");
        btnEdit_Suppliers.setBackground(new java.awt.Color(0, 0, 153));
        btnEdit_Suppliers.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEdit_Suppliers.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnEdit_Suppliers.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit_Suppliers.setLabel("Edit Suppliers");
        btnEdit_Suppliers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEdit_SuppliersActionPerformed(evt);
            }
        });

        btnEdit_Item1.setActionCommand("Edit Item");
        btnEdit_Item1.setBackground(new java.awt.Color(0, 0, 153));
        btnEdit_Item1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEdit_Item1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnEdit_Item1.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit_Item1.setLabel("Edit Item");
        btnEdit_Item1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEdit_Item1ActionPerformed(evt);
            }
        });

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

        btnAdd_Suppliers.setActionCommand("Add Suppliers");
        btnAdd_Suppliers.setBackground(new java.awt.Color(0, 0, 153));
        btnAdd_Suppliers.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAdd_Suppliers.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnAdd_Suppliers.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd_Suppliers.setLabel("Add Suppliers");
        btnAdd_Suppliers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd_SuppliersActionPerformed(evt);
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
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEdit_Item1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAdd_Item, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(39, 39, 39)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnView_Stock, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnView_Suppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAdd_Suppliers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEdit_Suppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Chart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        RightSideLayout.setVerticalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd_Item, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd_Suppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnView_Stock, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEdit_Item1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit_Suppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnView_Suppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(LeftSide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RightSide, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LeftSide, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RightSide, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        Admin admin = new Admin();
        admin.Logout();
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    
    private void btnAdd_ItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd_ItemActionPerformed

     new IM_Add_Item(manager).setVisible(true);
      this.dispose();

    }//GEN-LAST:event_btnAdd_ItemActionPerformed

    private void btnEdit_SuppliersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEdit_SuppliersActionPerformed
    new IM_Edit_Supplier(manager).setVisible(true);
      this.dispose();
    }//GEN-LAST:event_btnEdit_SuppliersActionPerformed

    private void btnEdit_Item1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEdit_Item1ActionPerformed
        IM_Edit_Item editItemForm = new IM_Edit_Item(manager);
        editItemForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEdit_Item1ActionPerformed

    private void btnView_StockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnView_StockActionPerformed
    IM_View_Stock viewItemForm = new IM_View_Stock(manager);
        viewItemForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnView_StockActionPerformed

    private void btnAdd_SuppliersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd_SuppliersActionPerformed
       new IM_Add_Supplier(manager).setVisible(true);
      this.dispose();
    }//GEN-LAST:event_btnAdd_SuppliersActionPerformed

    private void btnView_SuppliersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnView_SuppliersActionPerformed
         new IM_View_Suppliers(manager).setVisible(true);
      this.dispose();
    }//GEN-LAST:event_btnView_SuppliersActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Chart;
    private javax.swing.JPanel LeftSide;
    private javax.swing.JPanel RightSide;
    private java.awt.Button btnAdd_Item;
    private java.awt.Button btnAdd_Suppliers;
    private java.awt.Button btnEdit_Item1;
    private java.awt.Button btnEdit_Suppliers;
    private javax.swing.JButton btnLogout;
    private java.awt.Button btnView_Stock;
    private java.awt.Button btnView_Suppliers;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel roledisplay;
    private javax.swing.JLabel usernamedisplay;
    // End of variables declaration//GEN-END:variables
}
