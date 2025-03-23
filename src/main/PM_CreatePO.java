
package main;


import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class PM_CreatePO extends javax.swing.JFrame {
    private PurchaseManager user;
    private boolean isFilterActive = false;
    DefaultTableModel tablemodel;
    public PM_CreatePO(PurchaseManager user) {        
        initComponents();
        this.user = user;
        usernamedisplay.setText("User: "+ user.FName +" "+ user.LName);
        roledisplay.setText("Role: " + user.userRole);
        loadtable();
        
        
        

    }
    
    
    
    
private void loadtable() {
    String[] columnNames = {"Item ID", "Name", "Supplier ID", "Stock Level", "Restock Level", "Price", "Cost", "Quantity"};
    tablemodel = new DefaultTableModel(columnNames, 0) {
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 7) { // Assuming "Quantity" is the 8th column (index 7)
                return Integer.class;
            }
            return super.getColumnClass(columnIndex);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            // Make only the "Quantity" column editable
            return column == 7;
        }
    };
    ItemsTable.setModel(tablemodel);

    String stringln;
    String[] items = Item.getAllItems(); 
    for (String itemline : items) { 
        String[] itemDetails = itemline.split(";"); 
        // Add a placeholder for quantity (null)
        String[] rowData = {itemDetails[0], itemDetails[1], itemDetails[2], itemDetails[3], itemDetails[4], itemDetails[5], itemDetails[6], null}; 
        tablemodel.addRow(rowData); 
    }
}
    private void applyFilter() {
        String supplierID = tfSupplierID.getText();

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tablemodel);
        ItemsTable.setRowSorter(sorter);

        if (supplierID.trim().length() > 0) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + supplierID, 2)); // Filter by supplier ID (column index 2)
        } else {
            sorter.setRowFilter(null); // Clear filter if supplier ID is empty
        }
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRestock = new java.awt.Button();
        jPanel1 = new javax.swing.JPanel();
        LeftSide = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        usernamedisplay = new javax.swing.JLabel();
        roledisplay = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        RightSide = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCreatePO = new java.awt.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        ItemsTable = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        tfPOID = new javax.swing.JTextField();
        tfDay = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfMonth = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfYear = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tfSupplierID = new javax.swing.JTextField();
        btnRestock2 = new java.awt.Button();

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

        jPanel1.add(LeftSide);
        LeftSide.setBounds(0, 0, 200, 500);

        RightSide.setBackground(new java.awt.Color(255, 255, 255));
        RightSide.setPreferredSize(new java.awt.Dimension(600, 500));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("CREATE PO");

        btnCreatePO.setBackground(new java.awt.Color(0, 0, 153));
        btnCreatePO.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCreatePO.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnCreatePO.setForeground(new java.awt.Color(255, 255, 255));
        btnCreatePO.setLabel("Create PO");
        btnCreatePO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreatePOActionPerformed(evt);
            }
        });

        ItemsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Username", "Password", "Role", "First Name", "Last Name", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ItemsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ItemsTableMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(ItemsTable);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("PO ID");

        tfPOID.setBackground(new java.awt.Color(240, 255, 255));
        tfPOID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfPOID.setForeground(new java.awt.Color(51, 51, 51));
        tfPOID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfPOID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPOIDActionPerformed(evt);
            }
        });

        tfDay.setBackground(new java.awt.Color(240, 255, 255));
        tfDay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfDay.setForeground(new java.awt.Color(51, 51, 51));
        tfDay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDayActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Day");

        tfMonth.setBackground(new java.awt.Color(240, 255, 255));
        tfMonth.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfMonth.setForeground(new java.awt.Color(51, 51, 51));
        tfMonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMonthActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Month");

        tfYear.setBackground(new java.awt.Color(240, 255, 255));
        tfYear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfYear.setForeground(new java.awt.Color(51, 51, 51));
        tfYear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfYearActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Year");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Supplier ID");

        tfSupplierID.setBackground(new java.awt.Color(240, 255, 255));
        tfSupplierID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfSupplierID.setForeground(new java.awt.Color(51, 51, 51));
        tfSupplierID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfSupplierID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSupplierIDActionPerformed(evt);
            }
        });

        btnRestock2.setActionCommand("Restock");
        btnRestock2.setBackground(new java.awt.Color(0, 0, 153));
        btnRestock2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRestock2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnRestock2.setForeground(new java.awt.Color(255, 255, 255));
        btnRestock2.setLabel("Restock");
        btnRestock2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestock2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RightSideLayout = new javax.swing.GroupLayout(RightSide);
        RightSide.setLayout(RightSideLayout);
        RightSideLayout.setHorizontalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRestock2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnCreatePO, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140))
            .addGroup(RightSideLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPOID, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfYear, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfDay, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
            .addGroup(RightSideLayout.createSequentialGroup()
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfSupplierID, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        RightSideLayout.setVerticalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSupplierID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfPOID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfYear, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfDay, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(tfMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCreatePO, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(btnRestock2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btnCreatePOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreatePOActionPerformed
        
        if (tfPOID.getText().isBlank() || tfDay.getText().isBlank() || tfMonth.getText().isBlank()
                || tfYear.getText().isBlank() || tfSupplierID.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Please fill in all the required fields (PO ID, Day, Month, Year, Supplier ID, Status).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String poId = tfPOID.getText();

        if (user.poExists(poId)) {
                JOptionPane.showMessageDialog(null, "A PO with ID '" + poId + "' already exists.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
        }
        

        int day, month, year;
        try {
            day = Integer.parseInt(tfDay.getText());
            month = Integer.parseInt(tfMonth.getText());
            year = Integer.parseInt(tfYear.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numbers for Day, Month, and Year.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        ArrayList<POItem> poItems = new ArrayList<>();
        String supplierId = null;
        boolean validSupplier = true;
         for (int i = 0; i < ItemsTable.getRowCount(); i++) {
             
            if (ItemsTable.getValueAt(i, 7) != null) {
               
                String itemId = (String) ItemsTable.getValueAt(i, 0);
                int quantity;
                try {
                    quantity = (Integer) ItemsTable.getValueAt(i, 7);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for quantity in row " + (i + 1), "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                 if (quantity < 0){
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for quantity in row " + (i+1), "Error",
                            JOptionPane.ERROR_MESSAGE);
                   return;
                }
               
               String supplier = (String) ItemsTable.getValueAt(i, 2); // Get supplier ID from table

                if (supplierId == null){
                     supplierId = supplier;
                }
               else if (!supplier.equals(supplierId)){
                   validSupplier = false;
                      JOptionPane.showMessageDialog(null, "All items must have the same Supplier ID. Please filter by Supplier ID before Creating PO.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                 Item item = Item.getItem(itemId); // Fetch the Item object
                   if (item != null) {
                    POItem poItem = new POItem(itemId, quantity, item.getCost());
                     poItems.add(poItem);
                      }
                    else {
                    JOptionPane.showMessageDialog(null, "The item with id: "+ itemId + " does not exist", "Error", JOptionPane.ERROR_MESSAGE);
                       return;
                      }
            }

        }

        if (poItems.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter quantities for at least one item.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!validSupplier) {
                JOptionPane.showMessageDialog(null,
                        "All items must have the same Supplier ID. Please filter by Supplier ID before Creating PO.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return; // Stop Execution if different suppliers are selected.
        }
            String status = "Pending";
            
            // Create the PO object here 
            String message = user.createPO(poId, supplierId, user.getUserID(), day, month, year, poItems, status);

           if (message.startsWith("Error")) {
            JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
         } else {
            JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
            tfPOID.setText("");
            tfDay.setText("");
            tfMonth.setText("");
            tfYear.setText("");
            tfSupplierID.setText("");
            

             for (int i = 0; i < ItemsTable.getRowCount(); i++){
            ItemsTable.setValueAt(null, i, 7);
        }
        }
        
        
        
    }//GEN-LAST:event_btnCreatePOActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        Admin admin = new Admin();
        admin.Logout();
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        PM_Dashboard pageFrame = new PM_Dashboard(user);
        pageFrame.setVisible(true);
        pageFrame.pack();
        pageFrame.setLocationRelativeTo(null);
        this.dispose();   
        return;
               
               
    }//GEN-LAST:event_btnBackActionPerformed

    private void ItemsTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ItemsTableMouseReleased

    }//GEN-LAST:event_ItemsTableMouseReleased

    private void tfPOIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPOIDActionPerformed

    }//GEN-LAST:event_tfPOIDActionPerformed

    private void tfDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDayActionPerformed

    }//GEN-LAST:event_tfDayActionPerformed

    private void tfMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfMonthActionPerformed

    private void tfYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfYearActionPerformed

    private void tfSupplierIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSupplierIDActionPerformed

        applyFilter();

    }//GEN-LAST:event_tfSupplierIDActionPerformed

    private void btnRestockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestockActionPerformed
  
    }//GEN-LAST:event_btnRestockActionPerformed

    private void btnRestock2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestock2ActionPerformed
      TableRowSorter<TableModel> sorter = new TableRowSorter<>(tablemodel);
        ItemsTable.setRowSorter(sorter);

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
    }//GEN-LAST:event_btnRestock2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ItemsTable;
    private javax.swing.JPanel LeftSide;
    private javax.swing.JPanel RightSide;
    private javax.swing.JButton btnBack;
    private java.awt.Button btnCreatePO;
    private javax.swing.JButton btnLogout;
    private java.awt.Button btnRestock;
    private java.awt.Button btnRestock2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel roledisplay;
    private javax.swing.JTextField tfDay;
    private javax.swing.JTextField tfMonth;
    private javax.swing.JTextField tfPOID;
    private javax.swing.JTextField tfSupplierID;
    private javax.swing.JTextField tfYear;
    private javax.swing.JLabel usernamedisplay;
    // End of variables declaration//GEN-END:variables
}
