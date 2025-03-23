
package main;


import javax.swing.JOptionPane;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class SM_SalesEntry extends javax.swing.JFrame {
    private SalesManager user;
    private boolean isFilterActive = false;
    DefaultTableModel tablemodel;
    public SM_SalesEntry(SalesManager user) {        
        initComponents();
        this.user = user;
        usernamedisplay.setText("User: "+ user.FName +" "+ user.LName);
        roledisplay.setText("Role: " + user.userRole);
        loadtable();
        
        
        

    }
    
    
    
    
    private void loadtable(){
    String[] columnNames = {"Item ID", "Name", "Supplier ID", "Stock Level", "Restock Level", "Price"};
    tablemodel = new DefaultTableModel(columnNames, 0);
    ItemsTable.setModel(tablemodel);
    String stringln;
    String[] items = user.getAllItems(); 
    for (String itemline : items) { 
        String[] itemDetails = itemline.split(";"); 
        String[] rowData = {itemDetails[0], itemDetails[1], itemDetails[2], itemDetails[3], itemDetails[4], itemDetails[5]}; 
        tablemodel.addRow(rowData); 
    }
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
        btnBack = new javax.swing.JButton();
        RightSide = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnEnterSales = new java.awt.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        ItemsTable = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        tfItemID = new javax.swing.JTextField();
        tfQtySold = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnRestock = new java.awt.Button();
        tfSaleID = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

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
        jLabel1.setText("DAILY SALES ENTRY");

        btnEnterSales.setBackground(new java.awt.Color(0, 0, 153));
        btnEnterSales.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEnterSales.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnEnterSales.setForeground(new java.awt.Color(255, 255, 255));
        btnEnterSales.setLabel("Enter Sale");
        btnEnterSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnterSalesActionPerformed(evt);
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
        ItemsTable.setEnabled(false);
        ItemsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ItemsTableMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(ItemsTable);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Item ID");

        tfItemID.setBackground(new java.awt.Color(240, 255, 255));
        tfItemID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfItemID.setForeground(new java.awt.Color(51, 51, 51));
        tfItemID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfItemID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfItemIDActionPerformed(evt);
            }
        });

        tfQtySold.setBackground(new java.awt.Color(240, 255, 255));
        tfQtySold.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfQtySold.setForeground(new java.awt.Color(51, 51, 51));
        tfQtySold.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfQtySold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfQtySoldActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Quantity Sold");

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

        tfSaleID.setBackground(new java.awt.Color(240, 255, 255));
        tfSaleID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfSaleID.setForeground(new java.awt.Color(51, 51, 51));
        tfSaleID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfSaleID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSaleIDActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Sale ID");

        javax.swing.GroupLayout RightSideLayout = new javax.swing.GroupLayout(RightSide);
        RightSide.setLayout(RightSideLayout);
        RightSideLayout.setHorizontalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                        .addGap(0, 17, Short.MAX_VALUE)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfItemID, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfQtySold, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                                .addComponent(btnEnterSales, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(btnRestock, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(124, 124, 124))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfSaleID, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(328, 328, 328))))))
            .addGroup(RightSideLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        RightSideLayout.setVerticalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSaleID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfItemID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                        .addComponent(tfQtySold, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRestock, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnterSales, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
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

    private void btnEnterSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnterSalesActionPerformed
        if (tfItemID.getText().isBlank() || tfQtySold.getText().isBlank() || tfSaleID.getText().isBlank()) {
        JOptionPane.showMessageDialog(null, "Please enter a value for each field.", "Information", JOptionPane.INFORMATION_MESSAGE);
        return;
    }
     

    try {
        int quantitySold = Integer.parseInt(tfQtySold.getText().trim());

        // Validate that the quantity sold is greater than 0
        if (quantitySold <= 0) {
            JOptionPane.showMessageDialog(null, "Quantity must be a positive number greater than 0.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Fetch the item using the item ID
        Item item = Item.getItem(tfItemID.getText().trim()); 
        if (item == null) {
            JOptionPane.showMessageDialog(null, "Invalid Item ID. Please select a valid item.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create a new sales entry
        Sales newEntry = new Sales(tfSaleID.getText(), item, quantitySold, user);
        String message = user.StockUpdate(newEntry, quantitySold);

        // Reload the table and display a success message
        loadtable();
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Please enter a valid number in the Quantity field.", "Validation Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnEnterSalesActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        Admin admin = new Admin();
        admin.Logout();
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        SM_Dashboard pageFrame = new SM_Dashboard(user);
        pageFrame.setVisible(true);
        pageFrame.pack();
        pageFrame.setLocationRelativeTo(null);
        this.dispose();   
        return;
               
               
    }//GEN-LAST:event_btnBackActionPerformed

    private void tfItemIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfItemIDActionPerformed

    }//GEN-LAST:event_tfItemIDActionPerformed

    private void tfQtySoldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfQtySoldActionPerformed

    }//GEN-LAST:event_tfQtySoldActionPerformed

    private void ItemsTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ItemsTableMouseReleased

        int row = ItemsTable.rowAtPoint(evt.getPoint());

        if (row >= 0) {

            tfItemID.setText(ItemsTable.getValueAt(row, 0).toString());

        }
    }//GEN-LAST:event_ItemsTableMouseReleased

    private void btnRestockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestockActionPerformed
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
    }//GEN-LAST:event_btnRestockActionPerformed

    private void tfSaleIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSaleIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSaleIDActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ItemsTable;
    private javax.swing.JPanel LeftSide;
    private javax.swing.JPanel RightSide;
    private javax.swing.JButton btnBack;
    private java.awt.Button btnEnterSales;
    private javax.swing.JButton btnLogout;
    private java.awt.Button btnRestock;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel roledisplay;
    private javax.swing.JTextField tfItemID;
    private javax.swing.JTextField tfQtySold;
    private javax.swing.JTextField tfSaleID;
    private javax.swing.JLabel usernamedisplay;
    // End of variables declaration//GEN-END:variables
}
