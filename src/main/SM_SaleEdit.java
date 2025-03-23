package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ajneya
 */
public class SM_SaleEdit extends javax.swing.JFrame {
        private SalesManager manager;
        private boolean isFilterActive = false;
        private DefaultTableModel tablemodel;
   
   public SM_SaleEdit(SalesManager manager) {
        this.manager = manager;
        initComponents();
        usernamedisplay.setText("User: " + manager.FName + " " + manager.LName);
        roledisplay.setText("Role: " + manager.userRole);
        loadTable();

    }

   private void loadTable() {
        String[] columnNames = {"Sale ID", "Day", "Month", "Year", "Hours", "Minutes", "Item ID", "Name", "Qty Sold", "Total Amount", "SM ID", "Stock Level"};
        tablemodel = new DefaultTableModel(columnNames, 0);

        String[] sales = manager.getAllSales();
        if (sales != null) {
            for (String saleLine : sales) {
                String[] saleDetails = saleLine.split(";");
                if (saleDetails.length == 13) { // Assuming the original format with price
                    // Create a new array without the price element
                    String[] saleDetailsWithoutPrice = new String[12];
                    System.arraycopy(saleDetails, 0, saleDetailsWithoutPrice, 0, 9); // Copy elements before price
                    System.arraycopy(saleDetails, 10, saleDetailsWithoutPrice, 9, 3); // Copy elements after price
                    tablemodel.addRow(saleDetailsWithoutPrice);
                } else {
                    System.out.println("Invalid data: " + saleLine);
                }
            }
        } else {
            System.out.println("No sales returned.");
        }

        ItemsTable1.setModel(tablemodel);
    }
   
   private void clearFields() {
        tfSaleID.setText("");
        tfDay.setText("");
        tfMonth.setText("");
        tfYear.setText("");
        tfHours.setText("");
        tfMinutes.setText("");
        tfItemID.setText("");
        tfName.setText("");
        tfQty.setText("");
        tfTotalAmount.setText("");
        tfSMID.setText("");
        tfStockLevel.setText("");

        tfSaleID.setEditable(true);
    }
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        LeftSide = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        usernamedisplay = new javax.swing.JLabel();
        roledisplay = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        RightSide1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        btnClear1 = new java.awt.Button();
        jScrollPane3 = new javax.swing.JScrollPane();
        ItemsTable1 = new javax.swing.JTable();
        btnDelete = new java.awt.Button();
        btnEdit1 = new java.awt.Button();
        btnRestock = new java.awt.Button();
        tfItemID = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfSMID = new javax.swing.JTextField();
        tfTotalAmount = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tfQty = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tfDay = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        tfYear = new javax.swing.JTextField();
        tfName = new javax.swing.JTextField();
        tfHours = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        tfMonth = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        tfMinutes = new javax.swing.JTextField();
        tfStockLevel = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tfSaleID = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jEditorPane1);

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

        btnBack.setBackground(new java.awt.Color(0, 0, 153));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/undo.png"))); // NOI18N
        btnBack.setToolTipText("Go back");
        btnBack.setMinimumSize(new java.awt.Dimension(50, 50));
        btnBack.setPreferredSize(new java.awt.Dimension(60, 60));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(LeftSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        RightSide1.setBackground(new java.awt.Color(255, 255, 255));
        RightSide1.setPreferredSize(new java.awt.Dimension(600, 500));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 153));
        jLabel15.setText("SALE EDIT");

        btnClear1.setActionCommand("Clear");
        btnClear1.setBackground(new java.awt.Color(0, 0, 153));
        btnClear1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnClear1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnClear1.setForeground(new java.awt.Color(255, 255, 255));
        btnClear1.setLabel("Clear");
        btnClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear1ActionPerformed(evt);
            }
        });

        ItemsTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ItemID", "Name", "Supplier ID", "Stock Level", "Reorder Level"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ItemsTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ItemsTable1MouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(ItemsTable1);

        btnDelete.setActionCommand("Delete");
        btnDelete.setBackground(new java.awt.Color(0, 0, 153));
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDelete.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setLabel("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnEdit1.setActionCommand("Edit");
        btnEdit1.setBackground(new java.awt.Color(0, 0, 153));
        btnEdit1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEdit1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnEdit1.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit1.setLabel("Edit");
        btnEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEdit1ActionPerformed(evt);
            }
        });

        btnRestock.setActionCommand("Restock");
        btnRestock.setBackground(new java.awt.Color(0, 0, 153));
        btnRestock.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRestock.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnRestock.setForeground(new java.awt.Color(255, 255, 255));
        btnRestock.setLabel("Restock");
        btnRestock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestockActionPerformed(evt);
            }
        });

        tfItemID.setBackground(new java.awt.Color(240, 255, 255));
        tfItemID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfItemID.setForeground(new java.awt.Color(51, 51, 51));
        tfItemID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfItemID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfItemIDActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Item ID");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("SM ID");

        tfSMID.setBackground(new java.awt.Color(240, 255, 255));
        tfSMID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfSMID.setForeground(new java.awt.Color(51, 51, 51));
        tfSMID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfSMID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSMIDActionPerformed(evt);
            }
        });

        tfTotalAmount.setBackground(new java.awt.Color(240, 255, 255));
        tfTotalAmount.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfTotalAmount.setForeground(new java.awt.Color(51, 51, 51));
        tfTotalAmount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfTotalAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTotalAmountActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Total Amount");

        tfQty.setBackground(new java.awt.Color(240, 255, 255));
        tfQty.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfQty.setForeground(new java.awt.Color(51, 51, 51));
        tfQty.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfQtyActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Quantity");

        tfDay.setBackground(new java.awt.Color(240, 255, 255));
        tfDay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfDay.setForeground(new java.awt.Color(51, 51, 51));
        tfDay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDayActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(51, 51, 51));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Day");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Year");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Name");

        tfYear.setBackground(new java.awt.Color(240, 255, 255));
        tfYear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfYear.setForeground(new java.awt.Color(51, 51, 51));
        tfYear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfYearActionPerformed(evt);
            }
        });

        tfName.setBackground(new java.awt.Color(240, 255, 255));
        tfName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfName.setForeground(new java.awt.Color(51, 51, 51));
        tfName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNameActionPerformed(evt);
            }
        });

        tfHours.setBackground(new java.awt.Color(240, 255, 255));
        tfHours.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfHours.setForeground(new java.awt.Color(51, 51, 51));
        tfHours.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfHours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfHoursActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("Hours");

        tfMonth.setBackground(new java.awt.Color(240, 255, 255));
        tfMonth.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfMonth.setForeground(new java.awt.Color(51, 51, 51));
        tfMonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMonthActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("Month");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("Minutes");

        tfMinutes.setBackground(new java.awt.Color(240, 255, 255));
        tfMinutes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfMinutes.setForeground(new java.awt.Color(51, 51, 51));
        tfMinutes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfMinutes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMinutesActionPerformed(evt);
            }
        });

        tfStockLevel.setBackground(new java.awt.Color(240, 255, 255));
        tfStockLevel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfStockLevel.setForeground(new java.awt.Color(51, 51, 51));
        tfStockLevel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfStockLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfStockLevelActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Stock Level");

        tfSaleID.setBackground(new java.awt.Color(240, 255, 255));
        tfSaleID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfSaleID.setForeground(new java.awt.Color(51, 51, 51));
        tfSaleID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfSaleID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSaleIDActionPerformed(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(51, 51, 51));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Sale ID");

        javax.swing.GroupLayout RightSide1Layout = new javax.swing.GroupLayout(RightSide1);
        RightSide1.setLayout(RightSide1Layout);
        RightSide1Layout.setHorizontalGroup(
            RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSide1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                    .addGroup(RightSide1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRestock, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85))
                    .addGroup(RightSide1Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(btnEdit1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(btnClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(RightSide1Layout.createSequentialGroup()
                        .addGroup(RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSide1Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfSaleID, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSide1Layout.createSequentialGroup()
                                .addGroup(RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel9))
                                .addGap(6, 6, 6)
                                .addGroup(RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfYear)
                                    .addComponent(tfDay)
                                    .addComponent(tfMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSide1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfSMID, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(RightSide1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(tfMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfItemID, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfHours, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfQty, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSide1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfStockLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSide1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(213, 213, 213))
        );
        RightSide1Layout.setVerticalGroup(
            RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSide1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSide1Layout.createSequentialGroup()
                        .addGroup(RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(RightSide1Layout.createSequentialGroup()
                                .addComponent(tfMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addGroup(RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfItemID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addGap(4, 4, 4)
                                .addGroup(RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13)))
                            .addGroup(RightSide1Layout.createSequentialGroup()
                                .addGroup(RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20)
                                    .addComponent(tfSaleID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfStockLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSide1Layout.createSequentialGroup()
                        .addGroup(RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfDay, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfYear, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(4, 4, 4)
                        .addGroup(RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(4, 4, 4)
                        .addGroup(RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfSMID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClear1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEdit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnRestock, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(LeftSide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RightSide1, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LeftSide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(RightSide1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        Login loginForm = new Login();
        loginForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        SM_Dashboard smForm = new SM_Dashboard(manager);
        smForm.setLocationRelativeTo(null);
        smForm.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_btnBackActionPerformed

        
        
    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
         clearFields();
    }//GEN-LAST:event_btnClear1ActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String saleID = tfSaleID.getText().trim();

        if (saleID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a sale to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this sale?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            boolean success = manager.deleteSale(saleID);

            if (success) {
                JOptionPane.showMessageDialog(this, "Sale deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadTable(); // Reload the table
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Sale deletion failed. Sale may not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEdit1ActionPerformed
          try {
            // Validate if any text fields are empty
            if (tfSaleID.getText().trim().isEmpty() || tfDay.getText().trim().isEmpty() || tfMonth.getText().trim().isEmpty() ||
                tfYear.getText().trim().isEmpty() || tfHours.getText().trim().isEmpty() || tfMinutes.getText().trim().isEmpty() ||
                tfItemID.getText().trim().isEmpty() || tfName.getText().trim().isEmpty() || tfQty.getText().trim().isEmpty() ||
                tfTotalAmount.getText().trim().isEmpty() || tfSMID.getText().trim().isEmpty() || tfStockLevel.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Parse and validate numeric fields
            int day, month, year, hours, minutes, qty, stockLevel;
            double totalAmount;

            try {
                day = Integer.parseInt(tfDay.getText().trim());
                month = Integer.parseInt(tfMonth.getText().trim());
                year = Integer.parseInt(tfYear.getText().trim());
                hours = Integer.parseInt(tfHours.getText().trim());
                minutes = Integer.parseInt(tfMinutes.getText().trim());
                qty = Integer.parseInt(tfQty.getText().trim());
                stockLevel = Integer.parseInt(tfStockLevel.getText().trim());
                totalAmount = Double.parseDouble(tfTotalAmount.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Numeric fields must be valid numbers!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate date and time fields
            if (day < 1 || day > 31) {
                JOptionPane.showMessageDialog(this, "Day must be between 1 and 31!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (month < 1 || month > 12) {
                JOptionPane.showMessageDialog(this, "Month must be between 1 and 12!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (year < 0) {
                JOptionPane.showMessageDialog(this, "Year must be a positive number!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (hours < 0 || hours > 23) {
                JOptionPane.showMessageDialog(this, "Hours must be between 0 and 23!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (minutes < 0 || minutes > 59) {
                JOptionPane.showMessageDialog(this, "Minutes must be between 0 and 59!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (qty < 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be a positive number!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (stockLevel < 0) {
                JOptionPane.showMessageDialog(this, "Stock Level must be a positive number!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (totalAmount < 0) {
                JOptionPane.showMessageDialog(this, "Total amount must be a positive number!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create a new Sales object with the updated values
            Sales updatedSale = new Sales(tfSaleID.getText().trim(), day, month, year, hours, minutes, tfItemID.getText().trim(), tfName.getText().trim(), qty, tfSMID.getText().trim(), stockLevel);

            // Attempt to update the sale
            boolean success = manager.editSale(updatedSale);

            if (success) {
                JOptionPane.showMessageDialog(this, "Sale updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadTable(); // Reload the table with updated data
                clearFields(); // Clear the input fields
            } else {
                JOptionPane.showMessageDialog(this, "Sale update failed. Please check the details.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEdit1ActionPerformed

    private void ItemsTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ItemsTable1MouseReleased
    int row = ItemsTable1.rowAtPoint(evt.getPoint());

    if (row >= 0) {
        tfSaleID.setText(ItemsTable1.getValueAt(row, 0).toString());
        tfDay.setText(ItemsTable1.getValueAt(row, 1).toString());
        tfMonth.setText(ItemsTable1.getValueAt(row, 2).toString());
        tfYear.setText(ItemsTable1.getValueAt(row, 3).toString());
        tfHours.setText(ItemsTable1.getValueAt(row, 4).toString());
        tfMinutes.setText(ItemsTable1.getValueAt(row, 5).toString());
        tfItemID.setText(ItemsTable1.getValueAt(row, 6).toString());
        tfName.setText(ItemsTable1.getValueAt(row, 7).toString());
        tfQty.setText(ItemsTable1.getValueAt(row, 8).toString());
        tfTotalAmount.setText(ItemsTable1.getValueAt(row, 9).toString());
        tfSMID.setText(ItemsTable1.getValueAt(row, 10).toString());
        tfStockLevel.setText(ItemsTable1.getValueAt(row, 11).toString());

        tfSaleID.setEditable(false); // Assuming Sale ID should not be editable
    }
    }//GEN-LAST:event_ItemsTable1MouseReleased

    private void btnRestockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestockActionPerformed
         TableRowSorter<TableModel> sorter = new TableRowSorter<>(tablemodel);
        ItemsTable1.setRowSorter(sorter);

        if (isFilterActive) {
            sorter.setRowFilter(null);
            isFilterActive = false;
        } else {
            RowFilter<TableModel, Integer> restockFilter = new RowFilter<TableModel, Integer>() {
                @Override
                public boolean include(RowFilter.Entry<? extends TableModel, ? extends Integer> entry) {
                    try {
                        int stockLevel = Integer.parseInt(entry.getStringValue(3));
                        int restockLevel = Integer.parseInt(entry.getStringValue(4));
                        return stockLevel <= restockLevel;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }
            };
            sorter.setRowFilter(restockFilter);
            isFilterActive = true;
        }
    }//GEN-LAST:event_btnRestockActionPerformed

    private void tfItemIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfItemIDActionPerformed

        

    }//GEN-LAST:event_tfItemIDActionPerformed

    private void tfSMIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSMIDActionPerformed

        
    }//GEN-LAST:event_tfSMIDActionPerformed

    private void tfTotalAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTotalAmountActionPerformed
        
    }//GEN-LAST:event_tfTotalAmountActionPerformed

    private void tfQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfQtyActionPerformed

        
    }//GEN-LAST:event_tfQtyActionPerformed

    private void tfDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDayActionPerformed

        
    }//GEN-LAST:event_tfDayActionPerformed

    private void tfYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfYearActionPerformed

        
    }//GEN-LAST:event_tfYearActionPerformed

    private void tfNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNameActionPerformed
        
    }//GEN-LAST:event_tfNameActionPerformed

    private void tfHoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfHoursActionPerformed

        
    }//GEN-LAST:event_tfHoursActionPerformed

    private void tfMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMonthActionPerformed

        

    }//GEN-LAST:event_tfMonthActionPerformed

    private void tfMinutesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMinutesActionPerformed

        

    }//GEN-LAST:event_tfMinutesActionPerformed

    private void tfStockLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfStockLevelActionPerformed
    }//GEN-LAST:event_tfStockLevelActionPerformed

    private void tfSaleIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSaleIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSaleIDActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ItemsTable1;
    private javax.swing.JPanel LeftSide;
    private javax.swing.JPanel RightSide1;
    private javax.swing.JButton btnBack;
    private java.awt.Button btnClear1;
    private java.awt.Button btnDelete;
    private java.awt.Button btnEdit1;
    private javax.swing.JButton btnLogout;
    private java.awt.Button btnRestock;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel roledisplay;
    private javax.swing.JTextField tfDay;
    private javax.swing.JTextField tfHours;
    private javax.swing.JTextField tfItemID;
    private javax.swing.JTextField tfMinutes;
    private javax.swing.JTextField tfMonth;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfQty;
    private javax.swing.JTextField tfSMID;
    private javax.swing.JTextField tfSaleID;
    private javax.swing.JTextField tfStockLevel;
    private javax.swing.JTextField tfTotalAmount;
    private javax.swing.JTextField tfYear;
    private javax.swing.JLabel usernamedisplay;
    // End of variables declaration//GEN-END:variables
}
