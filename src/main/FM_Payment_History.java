
package main;

import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import main.Admin;
import main.FinanceManager;
import main.Item;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JFileChooser;
import javax.swing.table.TableModel;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import javax.swing.JTable;


public class FM_Payment_History extends javax.swing.JFrame {
private FinanceManager manager;
    DefaultTableModel tablemodel;
    /**
     * Creates new form FM_Payment_History
     */
    public FM_Payment_History(FinanceManager manager) {
        this.manager = manager;
        initComponents();

        usernamedisplay.setText("User: " + manager.FName + " " + manager.LName);
        roledisplay.setText("Role: " + manager.userRole.name());

        // Initialize table model
        String[] columnNames = {"PO ID", "Supplier ID", "Amount", "Date"};
        tablemodel = new DefaultTableModel(columnNames, 0);
        UserTable.setModel(tablemodel);

        loadPaymentHistory();
        
    }
    
    private void loadPaymentHistory() {
    tablemodel.setRowCount(0); // Clear the table
    ArrayList<String[]> paymentHistory = manager.loadPaymentHistory();

    for (String[] record : paymentHistory) {
        tablemodel.addRow(record); // Add each record as a row in the table
    }
}

    private void applyFilter() {
     String poID = tfPoID.getText();
    String supplierID = tfSupplierID.getText();
    String amount = tfAmount.getText();
    String date = tfDare.getText(); // Date input field
    String sortcol = cbSortCol.getSelectedItem().toString();
    String sortorder = cbSortOrder.getSelectedItem().toString();

    // Create a list of filters
    ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>();

    // Add filters based on non-empty input fields
    if (poID != null && !poID.trim().isEmpty()) {
        filters.add(RowFilter.regexFilter("(?i)" + poID.trim(), 0)); // Column 0 for PO ID
    }
    if (supplierID != null && !supplierID.trim().isEmpty()) {
        filters.add(RowFilter.regexFilter("(?i)" + supplierID.trim(), 1)); // Column 1 for Supplier ID
    }
    if (amount != null && !amount.trim().isEmpty()) {
        filters.add(RowFilter.regexFilter("(?i)" + amount.trim(), 2)); // Column 2 for Amount
    }
    if (date != null && !date.trim().isEmpty()) {
        filters.add(RowFilter.regexFilter("(?i)" + date.trim(), 3)); // Column 3 for Date
    }

    // Set up a TableRowSorter with the table's model
    TableRowSorter<TableModel> sorter = new TableRowSorter<>(tablemodel);
    UserTable.setRowSorter(sorter);

    // Combine all filters and apply them
    RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);
    sorter.setRowFilter(combinedFilter);

    // Sorting logic
    ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
    int colnum;

    if (sortcol.equals("Stock")) {
        colnum = 3;
    } else if (sortcol.equals("Reorder")) {
        colnum = 4;
    } else if (sortcol.equals("Price")) {
        colnum = 5;
    } else {
        colnum = 6;
    }

    if (sortorder.equals("Ascending")) {
        sortKeys.add(new RowSorter.SortKey(colnum, SortOrder.ASCENDING));
    } else {
        sortKeys.add(new RowSorter.SortKey(colnum, SortOrder.DESCENDING));
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

        btnSave5 = new java.awt.Button();
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
        tfPoID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfDare = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfSupplierID = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfAmount = new javax.swing.JTextField();
        cbSortCol = new javax.swing.JComboBox<>();
        cbSortOrder = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        btnSave6 = new java.awt.Button();

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
        jLabel1.setText("SUPPLIER PAYMENT HISTORY");

        UserTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Username", "Password", "Role", "First Name", "Last Name", "Price", "Cost"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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

        tfPoID.setBackground(new java.awt.Color(240, 255, 255));
        tfPoID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfPoID.setForeground(new java.awt.Color(51, 51, 51));
        tfPoID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfPoID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPoIDActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("PO ID");

        tfDare.setBackground(new java.awt.Color(240, 255, 255));
        tfDare.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfDare.setForeground(new java.awt.Color(51, 51, 51));
        tfDare.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfDare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDareActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Date");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Amount");

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

        tfAmount.setBackground(new java.awt.Color(240, 255, 255));
        tfAmount.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfAmount.setForeground(new java.awt.Color(51, 51, 51));
        tfAmount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfAmountActionPerformed(evt);
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

        btnSave6.setActionCommand("Save");
        btnSave6.setBackground(new java.awt.Color(0, 0, 153));
        btnSave6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSave6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnSave6.setForeground(new java.awt.Color(255, 255, 255));
        btnSave6.setLabel("Save");
        btnSave6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RightSideLayout = new javax.swing.GroupLayout(RightSide);
        RightSide.setLayout(RightSideLayout);
        RightSideLayout.setHorizontalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSave6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(RightSideLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(RightSideLayout.createSequentialGroup()
                                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfSupplierID, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfPoID, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(26, 26, 26)
                                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel9))
                                        .addGap(6, 6, 6)
                                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfDare, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(RightSideLayout.createSequentialGroup()
                                                .addComponent(cbSortCol, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cbSortOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        RightSideLayout.setVerticalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPoID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tfSupplierID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(tfDare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSortCol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSortOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnSave6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        FM_DashBoard pageFrame = new FM_DashBoard(manager);
        pageFrame.setVisible(true);
        pageFrame.pack();
        pageFrame.setLocationRelativeTo(null);
        this.dispose();
        return;

    }//GEN-LAST:event_btnBackActionPerformed

    private void tfPoIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPoIDActionPerformed

        applyFilter();
    }//GEN-LAST:event_tfPoIDActionPerformed

    private void tfDareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDareActionPerformed

        applyFilter();
    }//GEN-LAST:event_tfDareActionPerformed

    private void tfSupplierIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSupplierIDActionPerformed

        applyFilter();
    }//GEN-LAST:event_tfSupplierIDActionPerformed

    private void tfAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfAmountActionPerformed

        applyFilter();
    }//GEN-LAST:event_tfAmountActionPerformed

    private void cbSortColActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSortColActionPerformed

        applyFilter();
    }//GEN-LAST:event_cbSortColActionPerformed

    private void cbSortOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSortOrderActionPerformed

        applyFilter();
    }//GEN-LAST:event_cbSortOrderActionPerformed

    private void UserTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserTableMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_UserTableMouseReleased

    private void btnSave5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave5ActionPerformed
        saveTableAsCSV(UserTable);
    }//GEN-LAST:event_btnSave5ActionPerformed

    private void btnSave6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave6ActionPerformed
        saveTableAsCSV(UserTable);
    }//GEN-LAST:event_btnSave6ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LeftSide;
    private javax.swing.JPanel RightSide;
    private javax.swing.JTable UserTable;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLogout;
    private java.awt.Button btnSave5;
    private java.awt.Button btnSave6;
    private javax.swing.JComboBox<String> cbSortCol;
    private javax.swing.JComboBox<String> cbSortOrder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel roledisplay;
    private javax.swing.JTextField tfAmount;
    private javax.swing.JTextField tfDare;
    private javax.swing.JTextField tfPoID;
    private javax.swing.JTextField tfSupplierID;
    private javax.swing.JLabel usernamedisplay;
    // End of variables declaration//GEN-END:variables
}
