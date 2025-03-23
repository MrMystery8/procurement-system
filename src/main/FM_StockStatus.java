/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import main.Admin;
import main.FinanceManager;
import main.Item;

/**
 *
 * @author ajneya
 */
public class FM_StockStatus extends javax.swing.JFrame {
    private FinanceManager manager;
    private boolean isFilterActive = false;
    DefaultTableModel tablemodel;

    /**
     * Creates new form FM_StockStatus
     */
    public FM_StockStatus(FinanceManager manager) {
         this.manager = manager; 
        initComponents();
        
        usernamedisplay.setText("User: " + manager.FName + " " + manager.LName);
        roledisplay.setText("Role: " + manager.userRole.name());
        
        // Initialize table model
        String[] columnNames = {"Item ID", "Name", "Supplier ID", "Stock Level", "Restock Level", "Price", "Cost"};
        tablemodel = new DefaultTableModel(columnNames, 0);
        UserTable.setModel(tablemodel);

        // Load items into the table
        String[] items = Item.getAllItems();
        for (String itemline : items) {
            String[] itemDetails = itemline.split(";");
            String[] rowData = {
                itemDetails[0], itemDetails[1], itemDetails[2], 
                itemDetails[3], itemDetails[4], itemDetails[5], itemDetails[6]
            };
            tablemodel.addRow(rowData);
        }
    }

    
    private void applyFilter(){
        
        String itemid = tfItemID.getText();
        String stocklevel = tfStockLevel.getText();
        String restocklevel  = tfRestockLevel.getText();
        String name = tfName.getText();
        String supplierid = tfSupplierID.getText();
        String price = tfPrice.getText();
        String cost = tfCost.getText();
        String sortcol = cbSortCol.getSelectedItem().toString();
        String sortorder = cbSortOrder.getSelectedItem().toString();
        
        ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>();
        
        if (itemid.trim().length()>0){
            filters.add(RowFilter.regexFilter("(?i)" + itemid, 0));
        }
        if (name.trim().length()>0){
            filters.add(RowFilter.regexFilter("(?i)" + name, 1));
        }
        if (supplierid.trim().length()>0){
            filters.add(RowFilter.regexFilter("(?i)" + supplierid, 2));
        }
        if (stocklevel.trim().length()>0){
            filters.add(RowFilter.regexFilter("(?i)" + stocklevel, 3));
        }
        if (restocklevel.trim().length()>0){
            filters.add(RowFilter.regexFilter("(?i)" + restocklevel, 4));
        }
        if (price.trim().length()>0){
            filters.add(RowFilter.regexFilter("(?i)" + price, 5));
        }
        if (cost.trim().length()>0){
            filters.add(RowFilter.regexFilter("(?i)" + cost, 6));
        }
        
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tablemodel);
        UserTable.setRowSorter(sorter);
        
        RowFilter<Object, Object> Filter = RowFilter.andFilter(filters);
        sorter.setRowFilter(Filter);
       
        
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
        
        int colnum;
        if (sortcol.equals("Stock")){
            colnum = 3;
        }else if (sortcol.equals("Restock")){ colnum = 4;
        }else if (sortcol.equals("Price")){ colnum =5;}
        else {colnum = 6;}
        if (sortorder.equals("Ascending")){
            sortKeys.add(new RowSorter.SortKey(colnum,SortOrder.ASCENDING ));
        }else{
            sortKeys.add(new RowSorter.SortKey(colnum,SortOrder.DESCENDING ));

        }
        
        sorter.setSortKeys(sortKeys);
    }
    
    private void saveTableAsCSV(JTable table) {
    System.out.println("Save button clicked: Method invoked."); // Debug: Method call check

    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save Table as CSV");

    int userSelection = fileChooser.showSaveDialog(null);
    System.out.println("File chooser result: " + userSelection); // Debug: File chooser result

    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File fileToSave = fileChooser.getSelectedFile();
        String filePath = fileToSave.getAbsolutePath();

        // Add .csv extension if missing
        if (!filePath.endsWith(".csv")) {
            filePath += ".csv";
        }

        System.out.println("Saving file to: " + filePath); // Debug: File path check

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            TableModel model = table.getModel();
            TableRowSorter<? extends TableModel> sorter = (TableRowSorter<? extends TableModel>) table.getRowSorter();

            // Write column headers
            for (int i = 0; i < model.getColumnCount(); i++) {
                writer.write(model.getColumnName(i));
                if (i < model.getColumnCount() - 1) {
                    writer.write(",");
                }
            }
            writer.newLine();

            // If there is a sorter (filtered data exists), save only visible rows
            if (sorter != null) {
                for (int i = 0; i < table.getRowCount(); i++) {
                    int modelRow = table.convertRowIndexToModel(i); // Get the model index for the current view row
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Object cellValue = model.getValueAt(modelRow, j);
                        writer.write(cellValue != null ? cellValue.toString() : ""); // Handle null cells
                        if (j < model.getColumnCount() - 1) {
                            writer.write(",");
                        }
                    }
                    writer.newLine();
                }
            } else {
                // If no filter applied, save all rows
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Object cellValue = model.getValueAt(i, j);
                        writer.write(cellValue != null ? cellValue.toString() : ""); // Handle null cells
                        if (j < model.getColumnCount() - 1) {
                            writer.write(",");
                        }
                    }
                    writer.newLine();
                }
            }

            System.out.println("File saved successfully!"); // Debug: File saved
            JOptionPane.showMessageDialog(null, "Table saved successfully to " + filePath, 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage()); // Debug: IO exception
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving table: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            System.err.println("Unexpected error: " + ex.getMessage()); // Debug: Other exceptions
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unexpected error: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        System.out.println("Save operation cancelled by user."); // Debug: User cancelled
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

        LeftSide = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        usernamedisplay = new javax.swing.JLabel();
        roledisplay = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        RightSide = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        UserTable = new javax.swing.JTable();
        tfItemID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfStockLevel = new javax.swing.JTextField();
        tfRestockLevel = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfSupplierID = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        cbSortCol = new javax.swing.JComboBox<>();
        cbSortOrder = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tfPrice = new javax.swing.JTextField();
        tfCost = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnSave5 = new java.awt.Button();
        btnRestock = new java.awt.Button();

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
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
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

        RightSide.setBackground(new java.awt.Color(255, 255, 255));
        RightSide.setPreferredSize(new java.awt.Dimension(600, 500));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("STOCK STATUS");

        UserTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Item ID", "Name", "Supplier ID", "Stock Level", "Restock Level", "", "Price", "Cost"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(UserTable);

        tfItemID.setBackground(new java.awt.Color(240, 255, 255));
        tfItemID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfItemID.setForeground(new java.awt.Color(51, 51, 51));
        tfItemID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfItemID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfItemIDActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Item ID");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Stock Level");

        tfStockLevel.setBackground(new java.awt.Color(240, 255, 255));
        tfStockLevel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfStockLevel.setForeground(new java.awt.Color(51, 51, 51));
        tfStockLevel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfStockLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfStockLevelActionPerformed(evt);
            }
        });

        tfRestockLevel.setBackground(new java.awt.Color(240, 255, 255));
        tfRestockLevel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfRestockLevel.setForeground(new java.awt.Color(51, 51, 51));
        tfRestockLevel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfRestockLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfRestockLevelActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Restock Level");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Name");

        tfSupplierID.setBackground(new java.awt.Color(240, 255, 255));
        tfSupplierID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfSupplierID.setForeground(new java.awt.Color(51, 51, 51));
        tfSupplierID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfSupplierID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSupplierIDActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Supplier ID");

        tfName.setBackground(new java.awt.Color(240, 255, 255));
        tfName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfName.setForeground(new java.awt.Color(51, 51, 51));
        tfName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNameActionPerformed(evt);
            }
        });

        cbSortCol.setBackground(new java.awt.Color(240, 255, 255));
        cbSortCol.setForeground(new java.awt.Color(50, 50, 50));
        cbSortCol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Stock", "Restock", "Price", "Cost" }));
        cbSortCol.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cbSortCol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSortColActionPerformed(evt);
            }
        });

        cbSortOrder.setBackground(new java.awt.Color(240, 255, 255));
        cbSortOrder.setForeground(new java.awt.Color(50, 50, 50));
        cbSortOrder.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascending", "Descending" }));
        cbSortOrder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cbSortOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSortOrderActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Sort");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Price");

        tfPrice.setBackground(new java.awt.Color(240, 255, 255));
        tfPrice.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfPrice.setForeground(new java.awt.Color(51, 51, 51));
        tfPrice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPriceActionPerformed(evt);
            }
        });

        tfCost.setBackground(new java.awt.Color(240, 255, 255));
        tfCost.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfCost.setForeground(new java.awt.Color(51, 51, 51));
        tfCost.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCostActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Cost");

        btnSave5.setActionCommand("Save");
        btnSave5.setBackground(new java.awt.Color(0, 0, 153));
        btnSave5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSave5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnSave5.setForeground(new java.awt.Color(255, 255, 255));
        btnSave5.setLabel("Save");
        btnSave5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave5ActionPerformed(evt);
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

        javax.swing.GroupLayout RightSideLayout = new javax.swing.GroupLayout(RightSide);
        RightSide.setLayout(RightSideLayout);
        RightSideLayout.setHorizontalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(RightSideLayout.createSequentialGroup()
                                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(RightSideLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(tfPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(RightSideLayout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tfStockLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(RightSideLayout.createSequentialGroup()
                                            .addGap(29, 29, 29)
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tfItemID, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(RightSideLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfSupplierID, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel9))
                                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(RightSideLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfCost, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfRestockLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(RightSideLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbSortCol, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbSortOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(8, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(153, 153, 153))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                                .addComponent(btnRestock, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSave5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        RightSideLayout.setVerticalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(tfRestockLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(tfCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tfItemID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(tfStockLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfSupplierID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(cbSortCol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSortOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSave5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRestock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(LeftSide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RightSide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LeftSide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(RightSide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        Admin admin = new Admin();
        admin.Logout();
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
         FM_DashBoard dashboard = new FM_DashBoard(manager);
        dashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void tfItemIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfItemIDActionPerformed
        applyFilter();

    }//GEN-LAST:event_tfItemIDActionPerformed

    private void tfStockLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfStockLevelActionPerformed
        applyFilter();

    }//GEN-LAST:event_tfStockLevelActionPerformed

    private void tfRestockLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfRestockLevelActionPerformed

        applyFilter();
    }//GEN-LAST:event_tfRestockLevelActionPerformed

    private void tfSupplierIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSupplierIDActionPerformed

        applyFilter();
    }//GEN-LAST:event_tfSupplierIDActionPerformed

    private void tfNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNameActionPerformed

        applyFilter();
    }//GEN-LAST:event_tfNameActionPerformed

    private void cbSortColActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSortColActionPerformed

        applyFilter();
    }//GEN-LAST:event_cbSortColActionPerformed

    private void cbSortOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSortOrderActionPerformed

        applyFilter();
    }//GEN-LAST:event_cbSortOrderActionPerformed

    private void tfPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPriceActionPerformed
        applyFilter();
    }//GEN-LAST:event_tfPriceActionPerformed

    private void tfCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCostActionPerformed
        applyFilter();
    }//GEN-LAST:event_tfCostActionPerformed

    private void btnSave5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave5ActionPerformed
        saveTableAsCSV(UserTable);
    }//GEN-LAST:event_btnSave5ActionPerformed

    private void btnRestockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestockActionPerformed
       TableRowSorter<TableModel> sorter = new TableRowSorter<>(tablemodel);
        UserTable.setRowSorter(sorter);

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

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LeftSide;
    private javax.swing.JPanel RightSide;
    private javax.swing.JTable UserTable;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLogout;
    private java.awt.Button btnRestock;
    private java.awt.Button btnSave5;
    private javax.swing.JComboBox<String> cbSortCol;
    private javax.swing.JComboBox<String> cbSortOrder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel roledisplay;
    private javax.swing.JTextField tfCost;
    private javax.swing.JTextField tfItemID;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfPrice;
    private javax.swing.JTextField tfRestockLevel;
    private javax.swing.JTextField tfStockLevel;
    private javax.swing.JTextField tfSupplierID;
    private javax.swing.JLabel usernamedisplay;
    // End of variables declaration//GEN-END:variables
}
