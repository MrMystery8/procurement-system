
package main;

import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.JFileChooser;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import javax.swing.table.TableModel;



public class SM_SalesView extends javax.swing.JFrame {
    private SalesManager user;

    DefaultTableModel tablemodel;

    public SM_SalesView(SalesManager user) {
        initComponents();
        this.user = user;
        usernamedisplay.setText("User: " + user.FName + " " + user.LName);
        roledisplay.setText("Role: " + user.userRole);
        
        

        // Update column names for Sales table
        String[] columnNames = {"SaleID", "Day", "Month", "Year", "Hours", "Mins", "Item ID", "Name", "Qty", "Amount", "SM ID","Stock Level"};
        tablemodel = new DefaultTableModel(columnNames, 0);
        UserTable.setModel(tablemodel);
        
        TableColumn NameColumn = UserTable.getColumnModel().getColumn(7); 
        NameColumn.setPreferredWidth(150); 
        NameColumn.setMaxWidth(150);
        
        
        loadSales();
    }

    private void loadSales() {
        tablemodel.setRowCount(0); // Clear existing data

        String[] salesLines = user.getAllSales(); // Assuming you add a getAllSales() method in SalesManager

        for (String line : salesLines) {
            Sales sale = parseSaleFromString(line);

            // Add a row to the table
            Object[] rowData = {
                sale.getSaleID(),
                sale.getDay(),
                sale.getMonth(),
                sale.getYear(),
                sale.getHour(),
                sale.getMinute(),
                sale.getItemID(),
                sale.getName(),
                sale.getQtysold(),
                sale.getTotalamount(),
                sale.getSalesmanagerid(),
                sale.getStocklevel()
            };
            tablemodel.addRow(rowData);
        }
    }

    // Parses a Sale from a line of text
    private Sales parseSaleFromString(String line) {
        String[] parts = line.split(";");
        Sales sale = new Sales();

        sale.setSaleID(parts[0]);
        sale.setDay(Integer.parseInt(parts[1]));
        sale.setMonth(Integer.parseInt(parts[2]));
        sale.setYear(Integer.parseInt(parts[3]));
        sale.setHour(Integer.parseInt(parts[4]));
        sale.setMinute(Integer.parseInt(parts[5]));
        sale.setItemID(parts[6]);
        sale.setName(parts[7]);
        sale.setQtysold(Integer.parseInt(parts[8]));
        sale.setPrice(Double.parseDouble(parts[9]));
        sale.setTotalamount(Double.parseDouble(parts[10]));
        sale.setSalesmanagerid(parts[11]);
        sale.setStocklevel(Integer.parseInt(parts[12]));

        return sale;
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

  
    private void applyFilter() {
        String saleId = tfSaleID.getText(); // Get Sale ID from tfSaleID
        String day = tfDay.getText();
        String month = tfMonth.getText();
        String year = tfYear.getText();
        String hours = tfHours.getText();
        String minutes = tfMinutes.getText();
        String itemid = tfItemID.getText();
        String name = tfName.getText();
        String qty = tfQty.getText();
        String totalamount = tfTotalAmount.getText();
        String smid = tfSMID.getText();
        String stocklevel = tfStockLevel.getText();
        String sortcol = cbSortCol.getSelectedItem().toString();
        String sortorder = cbSortOrder.getSelectedItem().toString();

        ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>();

        // Add filter for Sale ID
        if (!saleId.isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + saleId, 0)); // Sale ID at index 0
        }
        if (!day.isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + day, 1)); // Sale ID at index 0
        }
        if (!month.isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + month, 2));
        }
        if (!year.isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + year, 3));
        }
        if (!hours.isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + hours, 4));
        }
        if (!minutes.isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + minutes, 5));
        }
        if (!itemid.isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + itemid, 6));
        }
        if (!name.isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + name, 7));
        }
        if (!qty.isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + qty, 8));
        }
        if (!totalamount.isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + totalamount, 9));
        }
        if (!smid.isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + smid, 10));
        }
        if (!stocklevel.isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + stocklevel, 11));
        }

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tablemodel);
        UserTable.setRowSorter(sorter);

        RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);
        sorter.setRowFilter(combinedFilter);

        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();

        int columnIndexToSort = -1;

        // Determine column index to sort (including Sale ID)
        switch (sortcol) {
            case "Sale ID":
                columnIndexToSort = 0;
                break;
            case "Day":
                columnIndexToSort = 1;
                break;
            case "Month":
                columnIndexToSort = 2;
                break;
            case "Year":
                columnIndexToSort = 3;
                break;
            case "Hours":
                columnIndexToSort = 4;
                break;
            case "Mins":
                columnIndexToSort = 5;
                break;
            case "Item ID":
                columnIndexToSort = 6;
                break;
            case "Name":
                columnIndexToSort = 7;
                break;
            case "Qty":
                columnIndexToSort = 8;
                break;
            case "Amount":
                columnIndexToSort = 9;
                break;
            case "SM ID":
                columnIndexToSort = 10;
                break;
            case "Stock Level":
                columnIndexToSort = 11;
                break;
        }

        if (columnIndexToSort != 6 && columnIndexToSort != 7 && columnIndexToSort != 0 && columnIndexToSort != 10) {
            sorter.setComparator(columnIndexToSort, new Comparator<Object>() {
                @Override
                public int compare(Object o1, Object o2) {
                    try {
                        // Handle Integer and String
                        Double d1 = (o1 instanceof String) ? Double.parseDouble((String) o1) : ((Integer) o1).doubleValue();
                        Double d2 = (o2 instanceof String) ? Double.parseDouble((String) o2) : ((Integer) o2).doubleValue();
                        return d1.compareTo(d2);
                    } catch (NumberFormatException | ClassCastException e) {
                        // Default to string representation comparison
                        return o1.toString().compareTo(o2.toString());
                    }
                }
            });
        }

        // Apply sort keys
        if (columnIndexToSort != -1) {
            sortKeys.add(new RowSorter.SortKey(columnIndexToSort,
                    sortorder.equalsIgnoreCase("Ascending") ? SortOrder.ASCENDING : SortOrder.DESCENDING));
            sorter.setSortKeys(sortKeys);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        UserTable = new javax.swing.JTable();
        tfSaleID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfYear = new javax.swing.JTextField();
        tfHours = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfMonth = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfMinutes = new javax.swing.JTextField();
        tfItemID = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfSMID = new javax.swing.JTextField();
        btnSave2 = new java.awt.Button();
        tfTotalAmount = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tfQty = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tfStockLevel = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cbSortCol = new javax.swing.JComboBox<>();
        cbSortOrder = new javax.swing.JComboBox<>();
        tfDay = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

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

        jPanel1.add(LeftSide);
        LeftSide.setBounds(0, 0, 200, 500);

        RightSide.setBackground(new java.awt.Color(255, 255, 255));
        RightSide.setPreferredSize(new java.awt.Dimension(600, 500));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("SALES VIEW");

        UserTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(UserTable);

        tfSaleID.setBackground(new java.awt.Color(240, 255, 255));
        tfSaleID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfSaleID.setForeground(new java.awt.Color(51, 51, 51));
        tfSaleID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfSaleID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSaleIDActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Sale ID");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Year");

        tfYear.setBackground(new java.awt.Color(240, 255, 255));
        tfYear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfYear.setForeground(new java.awt.Color(51, 51, 51));
        tfYear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfYearActionPerformed(evt);
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

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Hours");

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

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Minutes");

        tfMinutes.setBackground(new java.awt.Color(240, 255, 255));
        tfMinutes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfMinutes.setForeground(new java.awt.Color(51, 51, 51));
        tfMinutes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfMinutes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMinutesActionPerformed(evt);
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

        btnSave2.setActionCommand("Save");
        btnSave2.setBackground(new java.awt.Color(0, 0, 153));
        btnSave2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSave2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnSave2.setForeground(new java.awt.Color(255, 255, 255));
        btnSave2.setLabel("Save");
        btnSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave2ActionPerformed(evt);
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

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Stock Level");

        tfStockLevel.setBackground(new java.awt.Color(240, 255, 255));
        tfStockLevel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfStockLevel.setForeground(new java.awt.Color(51, 51, 51));
        tfStockLevel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfStockLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfStockLevelActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Name");

        tfName.setBackground(new java.awt.Color(240, 255, 255));
        tfName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfName.setForeground(new java.awt.Color(51, 51, 51));
        tfName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNameActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Sort");

        cbSortCol.setBackground(new java.awt.Color(240, 255, 255));
        cbSortCol.setForeground(new java.awt.Color(50, 50, 50));
        cbSortCol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sale ID", "Day", "Month", "Year", "Hours", "Mins", "Item ID", "Name", "Qty", "Amount", "SM ID", "Stock Level" }));
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

        tfDay.setBackground(new java.awt.Color(240, 255, 255));
        tfDay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfDay.setForeground(new java.awt.Color(51, 51, 51));
        tfDay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDayActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Day");

        javax.swing.GroupLayout RightSideLayout = new javax.swing.GroupLayout(RightSide);
        RightSide.setLayout(RightSideLayout);
        RightSideLayout.setHorizontalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(RightSideLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfStockLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(6, 6, 6)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfYear)
                            .addComponent(tfSaleID)
                            .addComponent(tfMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfDay, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfSMID, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(RightSideLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel8)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(6, 6, 6)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(tfMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfItemID, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfHours, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfQty, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addGap(6, 6, 6)
                        .addComponent(cbSortCol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbSortOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(RightSideLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSave2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        RightSideLayout.setVerticalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfSaleID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfDay, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfYear, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(4, 4, 4)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(4, 4, 4)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)))
                    .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(RightSideLayout.createSequentialGroup()
                            .addComponent(tfMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfItemID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))
                            .addGap(4, 4, 4)
                            .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13)))
                        .addGroup(RightSideLayout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel7))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSMID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(tfTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfStockLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(cbSortCol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSortOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnSave2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        user.Logout();
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        SM_Dashboard pageFrame = new SM_Dashboard(user);
        pageFrame.setVisible(true);
        pageFrame.pack();
        pageFrame.setLocationRelativeTo(null);
        this.dispose();   
               
               
    }//GEN-LAST:event_btnBackActionPerformed

    private void tfItemIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfItemIDActionPerformed

        applyFilter();


    }//GEN-LAST:event_tfItemIDActionPerformed

    private void tfMinutesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMinutesActionPerformed

        
        applyFilter();


    }//GEN-LAST:event_tfMinutesActionPerformed

    private void tfMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMonthActionPerformed

        applyFilter();


    }//GEN-LAST:event_tfMonthActionPerformed

    private void tfHoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfHoursActionPerformed

        applyFilter();
    }//GEN-LAST:event_tfHoursActionPerformed

    private void tfYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfYearActionPerformed

        applyFilter();
    }//GEN-LAST:event_tfYearActionPerformed

    private void tfSaleIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSaleIDActionPerformed

        applyFilter();
    }//GEN-LAST:event_tfSaleIDActionPerformed

    private void tfSMIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSMIDActionPerformed


        applyFilter();

    }//GEN-LAST:event_tfSMIDActionPerformed

    private void btnSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave2ActionPerformed

        saveTableAsCSV(UserTable);
    }//GEN-LAST:event_btnSave2ActionPerformed

    private void tfTotalAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTotalAmountActionPerformed
        applyFilter();
    }//GEN-LAST:event_tfTotalAmountActionPerformed

    private void tfQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfQtyActionPerformed

        applyFilter();

    }//GEN-LAST:event_tfQtyActionPerformed

    private void tfStockLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfStockLevelActionPerformed
        applyFilter();
    }//GEN-LAST:event_tfStockLevelActionPerformed

    private void tfNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNameActionPerformed
        applyFilter();
    }//GEN-LAST:event_tfNameActionPerformed

    private void cbSortColActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSortColActionPerformed

        applyFilter();


    }//GEN-LAST:event_cbSortColActionPerformed

    private void cbSortOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSortOrderActionPerformed
        applyFilter();
    }//GEN-LAST:event_cbSortOrderActionPerformed

    private void tfDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDayActionPerformed

        applyFilter();

    }//GEN-LAST:event_tfDayActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LeftSide;
    private javax.swing.JPanel RightSide;
    private javax.swing.JTable UserTable;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLogout;
    private java.awt.Button btnSave2;
    private javax.swing.JComboBox<String> cbSortCol;
    private javax.swing.JComboBox<String> cbSortOrder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
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
