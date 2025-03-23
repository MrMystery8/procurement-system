/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;
import java.time.format.DateTimeFormatter;
import java.io.IOException;

public class FM_PO_Status extends javax.swing.JFrame {
    private FinanceManager user; 
    private DefaultTableModel tablemodel;
    /**
     * Creates new form FM_PO_Status
     */
    public FM_PO_Status(FinanceManager manager) {
        this.user = manager;
        initComponents(); 
       usernamedisplay.setText("User: " + user.FName + " " + user.LName);
        roledisplay.setText("Role: " + user.userRole);

        String[] columnNames = {"PO ID", "Sup ID", "PM ID", "Day", "Month", "Year", "Date Created", "Status", "Total Cost", "Items"};
        tablemodel = new DefaultTableModel(columnNames, 0);
        UserTable.setModel(tablemodel);

        loadPOs();
    }
    
    private void loadPOs() {
    tablemodel.setRowCount(0); // Clear existing data

    String[] poLines = user.getAllPO(); // Retrieve all POs
    System.out.println("Debug: Number of POs retrieved = " + poLines.length);

    for (String line : poLines) {
        try {
            PO po = parsePOFromString(line);
            if (po == null) continue;

            if (po.getStatus() != null && "Pending".equalsIgnoreCase(po.getStatus().trim())) {
                String itemsInfo = formatPOItems(po.getPoItems());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String formattedDate = po.getDateCreated().format(formatter);

                Object[] rowData = {
                    po.getPoID(),             
                    po.getSupplierID(),       
                    po.getPurchaseManagerID(),
                    po.getDay(),              
                    po.getMonth(),            
                    po.getYear(),             
                    formattedDate,            
                    po.getStatus(),           
                    po.getPoTotal(),          
                    itemsInfo                 
                };

                tablemodel.addRow(rowData);
            }
        } catch (Exception e) {
            System.err.println("Error processing PO: " + e.getMessage());
        }
    }

    UserTable.revalidate();
    UserTable.repaint();
}
    
    private PO parsePOFromString(String line) {
    String[] parts = line.split(";");
    String poId = parts[0];
    String supplierId = parts[1];
    String purchaseManagerId = parts[2];
    int day = Integer.parseInt(parts[3]);
    int month = Integer.parseInt(parts[4]);
    int year = Integer.parseInt(parts[5]);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate dateCreated = LocalDate.parse(parts[6], formatter);

    String status = parts[7];
    double poTotal = Double.parseDouble(parts[8]);

    ArrayList<POItem> poItems = new ArrayList<>();
    if (parts.length > 9) {
        String[] items = parts[9].split(",");
        for (String itemStr : items) {
            String[] itemParts = itemStr.split(":");
            if (itemParts.length == 3) {
                poItems.add(new POItem(itemParts[0], Integer.parseInt(itemParts[1]), Double.parseDouble(itemParts[2])));
            }
        }
    }

    return new PO(poId, supplierId, purchaseManagerId, day, month, year, dateCreated, poItems, status);
}
    
    private String formatPOItems(ArrayList<POItem> items) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            POItem item = items.get(i);
            sb.append("ID: ").append(item.getItemID())
              .append(", Qty: ").append(item.getQuantity())
              .append(", Cost: ").append(item.getUnitCost());
            if (i < items.size() - 1) sb.append("; ");
        }
        return sb.toString();
    }
    
    
    private void updatePOStatus(String newStatus) {
    int selectedRow = UserTable.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a PO to mark as " + newStatus, "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    
    String poID = tablemodel.getValueAt(selectedRow, 0).toString();
    String supplierID = tablemodel.getValueAt(selectedRow, 1).toString();
    String pmID = tablemodel.getValueAt(selectedRow, 2).toString();
    int day = Integer.parseInt(tablemodel.getValueAt(selectedRow, 3).toString());
    int month = Integer.parseInt(tablemodel.getValueAt(selectedRow, 4).toString());
    int year = Integer.parseInt(tablemodel.getValueAt(selectedRow, 5).toString());
    String dateCreated = tablemodel.getValueAt(selectedRow, 6).toString();
    double totalCost = Double.parseDouble(tablemodel.getValueAt(selectedRow, 8).toString());
    String items = tablemodel.getValueAt(selectedRow, 9).toString();

    
    tablemodel.setValueAt(newStatus, selectedRow, 7); 
    tfStatus.setText(newStatus);

    try {
        
        String filePath = "POs.txt";
        java.util.List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(filePath));
        ArrayList<String> updatedLines = new ArrayList<>();

        
        for (String line : lines) {
            if (!line.startsWith(poID + ";")) {
                updatedLines.add(line);
            }
        }

       
        String updatedPOLine = String.format("%s;%s;%s;%d;%d;%d;%s;%s;%.1f;%s",
                poID, supplierID, pmID, day, month, year, dateCreated, newStatus, totalCost, formatPOItems(items));

        updatedLines.add(updatedPOLine);

        // Write the updated list back to the file
        java.nio.file.Files.write(java.nio.file.Paths.get(filePath), updatedLines);

        JOptionPane.showMessageDialog(this, "PO ID " + poID + " has been marked as " + newStatus, "Success", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("PO ID " + poID + " updated to " + newStatus);
         loadPOs();
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error updating PO: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        System.err.println("Error updating PO: " + e.getMessage());
    }
}

private String formatPOItems(String items) {
   StringBuilder formattedItems = new StringBuilder();
    try {
        // Split items by "; " to extract each item's details
        String[] itemParts = items.split("; ");
        for (String part : itemParts) {
            // Extract values for ID, Qty, and Cost
            String[] details = part.split(", ");
            String itemID = details[0].split(": ")[1];     // Extract ID value
            String quantity = details[1].split(": ")[1];   // Extract Qty value
            String cost = details[2].split(": ")[1];       // Extract Cost value
            
            // Append formatted string
            formattedItems.append(String.format("%s:%s:%.1f", itemID, quantity, Double.parseDouble(cost)));
            formattedItems.append(","); // Add comma as separator
        }
        // Remove trailing comma
        if (formattedItems.length() > 0) {
            formattedItems.setLength(formattedItems.length() - 1);
        }
    } catch (Exception e) {
        System.err.println("Error formatting items: " + e.getMessage());
    }
    return formattedItems.toString();
}


    private void clearFields() {
        tfPOID.setText("");
        tfItem.setText("");
        tfStatus.setText("");
        tfTotalCost.setText("");
    }
    
     private void applyFilter() {
        String poID = tfPOID.getText();
        String status = tfStatus.getText();
        String totalCost = tfTotalCost.getText();
        String items = tfItem.getText();

        ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>();

        if (poID.trim().length() > 0) {
            filters.add(RowFilter.regexFilter("(?i)" + poID, 0)); 
        }
        if (status.trim().length() > 0) {
            filters.add(RowFilter.regexFilter("(?i)" + status, 3));
        }
        if (totalCost.trim().length() > 0) {
            filters.add(RowFilter.regexFilter("(?i)" + totalCost, 4));
        }
        if (items.trim().length() > 0) {
            filters.add(RowFilter.regexFilter("(?i)" + items, 5)); 
        }

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tablemodel);
        UserTable.setRowSorter(sorter);

        RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);
        sorter.setRowFilter(combinedFilter);
    }
    
     

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCreatePR = new java.awt.Button();
        btnCreatePR1 = new java.awt.Button();
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
        tfPOID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfItem = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tfStatus = new javax.swing.JTextField();
        tfTotalCost = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnReject_PO = new java.awt.Button();
        btnApprove_PO = new java.awt.Button();
        btnClear = new java.awt.Button();

        btnCreatePR.setBackground(new java.awt.Color(0, 0, 153));
        btnCreatePR.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCreatePR.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnCreatePR.setForeground(new java.awt.Color(255, 255, 255));
        btnCreatePR.setLabel("Create PO");
        btnCreatePR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreatePRActionPerformed(evt);
            }
        });

        btnCreatePR1.setBackground(new java.awt.Color(0, 0, 153));
        btnCreatePR1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCreatePR1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnCreatePR1.setForeground(new java.awt.Color(255, 255, 255));
        btnCreatePR1.setLabel("Create PO");

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
        jLabel1.setText("MANAGE POs");

        UserTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "PO ID", "Supplier ID", "Date Created", "First Name", "Last Name", "Email"
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
        UserTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                UserTableMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(UserTable);
        if (UserTable.getColumnModel().getColumnCount() > 0) {
            UserTable.getColumnModel().getColumn(0).setResizable(false);
            UserTable.getColumnModel().getColumn(1).setResizable(false);
            UserTable.getColumnModel().getColumn(2).setResizable(false);
            UserTable.getColumnModel().getColumn(3).setResizable(false);
            UserTable.getColumnModel().getColumn(4).setResizable(false);
            UserTable.getColumnModel().getColumn(5).setResizable(false);
        }

        tfPOID.setBackground(new java.awt.Color(240, 255, 255));
        tfPOID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfPOID.setForeground(new java.awt.Color(51, 51, 51));
        tfPOID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfPOID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPOIDActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("PO ID");

        tfItem.setBackground(new java.awt.Color(240, 255, 255));
        tfItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfItem.setForeground(new java.awt.Color(51, 51, 51));
        tfItem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfItemActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Item/Qty/Cost");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Status");

        tfStatus.setBackground(new java.awt.Color(240, 255, 255));
        tfStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfStatus.setForeground(new java.awt.Color(51, 51, 51));
        tfStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfStatusActionPerformed(evt);
            }
        });

        tfTotalCost.setBackground(new java.awt.Color(240, 255, 255));
        tfTotalCost.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfTotalCost.setForeground(new java.awt.Color(51, 51, 51));
        tfTotalCost.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfTotalCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTotalCostActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Total Cost");

        btnReject_PO.setActionCommand("Reject PO");
        btnReject_PO.setBackground(new java.awt.Color(0, 0, 153));
        btnReject_PO.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnReject_PO.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnReject_PO.setForeground(new java.awt.Color(255, 255, 255));
        btnReject_PO.setLabel("Reject PO");
        btnReject_PO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReject_POActionPerformed(evt);
            }
        });

        btnApprove_PO.setActionCommand("Approve PO");
        btnApprove_PO.setBackground(new java.awt.Color(0, 0, 153));
        btnApprove_PO.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnApprove_PO.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnApprove_PO.setForeground(new java.awt.Color(255, 255, 255));
        btnApprove_PO.setLabel("Approve PO");
        btnApprove_PO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApprove_POActionPerformed(evt);
            }
        });

        btnClear.setActionCommand("Clear");
        btnClear.setBackground(new java.awt.Color(0, 0, 153));
        btnClear.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnClear.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setLabel("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RightSideLayout = new javax.swing.GroupLayout(RightSide);
        RightSide.setLayout(RightSideLayout);
        RightSideLayout.setHorizontalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(RightSideLayout.createSequentialGroup()
                                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfPOID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                                .addComponent(btnReject_PO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)))
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(RightSideLayout.createSequentialGroup()
                                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(RightSideLayout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addComponent(jLabel8))
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfItem, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(RightSideLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(btnApprove_PO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(RightSideLayout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        RightSideLayout.setVerticalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfPOID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(tfItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(tfTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(tfStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReject_PO, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnApprove_PO, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
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
        user.Logout();
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
          FM_DashBoard dashboard = new FM_DashBoard(user);
           dashboard.setVisible(true);
            this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void tfPOIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPOIDActionPerformed

        applyFilter();
    }//GEN-LAST:event_tfPOIDActionPerformed

    private void tfItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfItemActionPerformed

        applyFilter();

    }//GEN-LAST:event_tfItemActionPerformed

    private void tfStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfStatusActionPerformed

        applyFilter();
    }//GEN-LAST:event_tfStatusActionPerformed

    private void tfTotalCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTotalCostActionPerformed

        applyFilter();
    }//GEN-LAST:event_tfTotalCostActionPerformed

    private void btnCreatePRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreatePRActionPerformed

        

    }//GEN-LAST:event_btnCreatePRActionPerformed

    private void btnReject_POActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReject_POActionPerformed
    updatePOStatus("Rejected");


    }//GEN-LAST:event_btnReject_POActionPerformed

    private void btnApprove_POActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApprove_POActionPerformed
    updatePOStatus("Approved,(NotPaid)");
    }//GEN-LAST:event_btnApprove_POActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        
        tfPOID.setText("");
        tfItem.setText("");
        tfStatus.setText("");
        tfTotalCost.setText("");
        
        
        
    }//GEN-LAST:event_btnClearActionPerformed

    private void UserTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserTableMouseReleased
       int selectedRow = UserTable.getSelectedRow();
    if (selectedRow != -1) {
        String poID = tablemodel.getValueAt(selectedRow, 0).toString();
        String items = tablemodel.getValueAt(selectedRow, 9).toString(); // Items column
        String status = tablemodel.getValueAt(selectedRow, 7).toString();
        String totalCost = tablemodel.getValueAt(selectedRow, 8).toString(); // Total cost column

        // Set values to the text fields
        tfPOID.setText(poID);
        tfItem.setText(items);
        tfStatus.setText(status);
        tfTotalCost.setText(totalCost);

        System.out.println("Selected PO ID: " + poID); 
    }

    }//GEN-LAST:event_UserTableMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LeftSide;
    private javax.swing.JPanel RightSide;
    private javax.swing.JTable UserTable;
    private java.awt.Button btnApprove_PO;
    private javax.swing.JButton btnBack;
    private java.awt.Button btnClear;
    private java.awt.Button btnCreatePR;
    private java.awt.Button btnCreatePR1;
    private javax.swing.JButton btnLogout;
    private java.awt.Button btnReject_PO;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel roledisplay;
    private javax.swing.JTextField tfItem;
    private javax.swing.JTextField tfPOID;
    private javax.swing.JTextField tfStatus;
    private javax.swing.JTextField tfTotalCost;
    private javax.swing.JLabel usernamedisplay;
    // End of variables declaration//GEN-END:variables
}
