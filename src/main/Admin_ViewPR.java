
package main;

import java.io.BufferedWriter;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.JFileChooser;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.table.TableModel;



public class Admin_ViewPR extends javax.swing.JFrame {
    private Admin user;

    DefaultTableModel tablemodel;

    public Admin_ViewPR(Admin user) {
        initComponents();
        this.user = user;
        usernamedisplay.setText("User: " + user.FName + " " + user.LName);
        roledisplay.setText("Role: " + user.userRole);

        // Update column names for PR table
        String[] columnNames = {"PR ID", "Day", "Month", "Year", "Date Created","SM ID", "Items"};
        tablemodel = new DefaultTableModel(columnNames, 0);
        UserTable.setModel(tablemodel);
        
        TableColumn itemsColumn = UserTable.getColumnModel().getColumn(tablemodel.getColumnCount() - 1);
        itemsColumn.setPreferredWidth(200);
        
        TableColumn dayColumn = UserTable.getColumnModel().getColumn(1); // Day column (index 1)
        dayColumn.setPreferredWidth(40); 
        dayColumn.setMaxWidth(40);      

        TableColumn monthColumn = UserTable.getColumnModel().getColumn(2); // Month column (index 2)
        monthColumn.setPreferredWidth(45); 
        monthColumn.setMaxWidth(45);      

        TableColumn yearColumn = UserTable.getColumnModel().getColumn(3); // Year column (index 3)
        yearColumn.setPreferredWidth(40); 
        yearColumn.setMaxWidth(40);
        
        TableColumn SMIDColumn = UserTable.getColumnModel().getColumn(5); // SM ID column (index 5)
        SMIDColumn.setPreferredWidth(50); 
        SMIDColumn.setMaxWidth(50);

        loadPRs();
    }

    private void loadPRs() {
        tablemodel.setRowCount(0); // Clear existing data

        String[] prLines = SalesManager.getAllPR(); // Use the getAllPR() method from SalesManager

        for (String line : prLines) {
            PR pr = parsePRFromString(line);

            // Manually concatenate PRItem information into a single string
            String itemsInfo = "";
            for (int i = 0; i < pr.getPrItems().size(); i++) {
                PRItem item = pr.getPrItems().get(i);
                itemsInfo = itemsInfo + "Item ID: " + item.getItemID() + ", Qty: " + item.getQuantity() + ", Supplier: " + item.getSupplierID();
                if (i < pr.getPrItems().size() - 1) {
                    itemsInfo = itemsInfo + "; "; // Separator between items
                }
            }
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = pr.getDateCreated().format(formatter);

            // Add a row to the table
            Object[] rowData = {
                pr.getPrID(),
                pr.getDay(),
                pr.getMonth(),
                pr.getYear(),
                formattedDate,
                pr.getSalesmanagerID(),
                itemsInfo
            };
            tablemodel.addRow(rowData);
        }
    }

    // Parses a PR from a line of text
    private PR parsePRFromString(String line) {
        String[] parts = line.split(";");
        String prId = parts[0];
        ArrayList<PRItem> prItems = new ArrayList<>();
        String[] items = parts[1].split(",");
        for (String itemStr : items) {
            String[] itemParts = itemStr.split(":");
            if (itemParts.length == 3) {
                prItems.add(new PRItem(itemParts[0], Integer.parseInt(itemParts[1]), itemParts[2]));
            } else {
                System.err.println("Skipping invalid item format: " + itemStr);
            }
        }
            int day = Integer.parseInt(parts[2]);
            int month = Integer.parseInt(parts[3]);
            int year = Integer.parseInt(parts[4]);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateCreated = LocalDate.parse(parts[5], formatter);
            
        String salesManagerId = parts[6];

        return new PR(prId, prItems, day, month, year, dateCreated, salesManagerId);
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
        
        
        String prID = tfPRID.getText();
        String day = tfDay.getText();
        String month = tfMonth.getText();
        String year = tfYear.getText();
        String smID = tfSMID.getText();
        String dateCreated = tfDateCreated.getText();
        String item = tfItem.getText();
        
        

        ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>();

        if (prID.trim().length() > 0) {
            filters.add(RowFilter.regexFilter("(?i)" + prID, 0));
        }
        if (day.trim().length() > 0) {
            filters.add(RowFilter.regexFilter("(?i)" + day, 1));
        }
        if (month.trim().length() > 0) {
            filters.add(RowFilter.regexFilter("(?i)" + month, 2));
        }
        if (year.trim().length() > 0) {
            filters.add(RowFilter.regexFilter("(?i)" + year, 3));
        }
        if (smID.trim().length() > 0) {
            filters.add(RowFilter.regexFilter("(?i)" + smID, 5));
        }
        if (dateCreated.trim().length() > 0) {
            filters.add(RowFilter.regexFilter("(?i)" + dateCreated, 4));
        }
        if (item.trim().length() > 0) {
            filters.add(RowFilter.regexFilter("(?i)" + item, 6));
        }

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tablemodel);
        UserTable.setRowSorter(sorter);

        RowFilter<Object, Object> Filter = RowFilter.andFilter(filters);
        sorter.setRowFilter(Filter);
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSave1 = new java.awt.Button();
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
        tfPRID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfDay = new javax.swing.JTextField();
        tfSMID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfItem = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfMonth = new javax.swing.JTextField();
        tfYear = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfDateCreated = new javax.swing.JTextField();
        btnSave2 = new java.awt.Button();

        btnSave1.setActionCommand("Save");
        btnSave1.setBackground(new java.awt.Color(0, 0, 153));
        btnSave1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSave1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnSave1.setForeground(new java.awt.Color(255, 255, 255));
        btnSave1.setLabel("Save");
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
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
        jLabel1.setText("PR VIEW");

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

        tfPRID.setBackground(new java.awt.Color(240, 255, 255));
        tfPRID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfPRID.setForeground(new java.awt.Color(51, 51, 51));
        tfPRID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfPRID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPRIDActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("PR ID");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Day");

        tfDay.setBackground(new java.awt.Color(240, 255, 255));
        tfDay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfDay.setForeground(new java.awt.Color(51, 51, 51));
        tfDay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDayActionPerformed(evt);
            }
        });

        tfSMID.setBackground(new java.awt.Color(240, 255, 255));
        tfSMID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfSMID.setForeground(new java.awt.Color(51, 51, 51));
        tfSMID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfSMID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSMIDActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Sales Manager ID");

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
        jLabel8.setText("Item/Qty/Sup ID");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Month");

        tfMonth.setBackground(new java.awt.Color(240, 255, 255));
        tfMonth.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfMonth.setForeground(new java.awt.Color(51, 51, 51));
        tfMonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMonthActionPerformed(evt);
            }
        });

        tfYear.setBackground(new java.awt.Color(240, 255, 255));
        tfYear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfYear.setForeground(new java.awt.Color(51, 51, 51));
        tfYear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfYearActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Year");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Date Created");

        tfDateCreated.setBackground(new java.awt.Color(240, 255, 255));
        tfDateCreated.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfDateCreated.setForeground(new java.awt.Color(51, 51, 51));
        tfDateCreated.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfDateCreated.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDateCreatedActionPerformed(evt);
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

        javax.swing.GroupLayout RightSideLayout = new javax.swing.GroupLayout(RightSide);
        RightSide.setLayout(RightSideLayout);
        RightSideLayout.setHorizontalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel6))
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfMonth, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfDay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfPRID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(RightSideLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfItem, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(RightSideLayout.createSequentialGroup()
                                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel7))
                                    .addGroup(RightSideLayout.createSequentialGroup()
                                        .addGap(63, 63, 63)
                                        .addComponent(jLabel10)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfYear, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfDateCreated, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfSMID, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 20, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(188, 188, 188))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                                .addComponent(btnSave2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        RightSideLayout.setVerticalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPRID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8)
                    .addComponent(tfItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(tfDay, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfSMID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfDateCreated, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnSave2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
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
        Admin_Dashboard pageFrame = new Admin_Dashboard(user);
        pageFrame.setVisible(true);
        pageFrame.pack();
        pageFrame.setLocationRelativeTo(null);
        this.dispose();   
               
               
    }//GEN-LAST:event_btnBackActionPerformed

    private void tfYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfYearActionPerformed

        applyFilter();


    }//GEN-LAST:event_tfYearActionPerformed

    private void tfMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMonthActionPerformed

        
        applyFilter();


    }//GEN-LAST:event_tfMonthActionPerformed

    private void tfItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfItemActionPerformed

        applyFilter();


    }//GEN-LAST:event_tfItemActionPerformed

    private void tfSMIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSMIDActionPerformed

        applyFilter();
    }//GEN-LAST:event_tfSMIDActionPerformed

    private void tfDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDayActionPerformed

        applyFilter();
    }//GEN-LAST:event_tfDayActionPerformed

    private void tfPRIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPRIDActionPerformed

        applyFilter();
    }//GEN-LAST:event_tfPRIDActionPerformed

    private void tfDateCreatedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDateCreatedActionPerformed


        applyFilter();

    }//GEN-LAST:event_tfDateCreatedActionPerformed

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed

        saveTableAsCSV(UserTable);
    }//GEN-LAST:event_btnSave1ActionPerformed

    private void btnSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave2ActionPerformed

        saveTableAsCSV(UserTable);
    }//GEN-LAST:event_btnSave2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LeftSide;
    private javax.swing.JPanel RightSide;
    private javax.swing.JTable UserTable;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLogout;
    private java.awt.Button btnSave1;
    private java.awt.Button btnSave2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel roledisplay;
    private javax.swing.JTextField tfDateCreated;
    private javax.swing.JTextField tfDay;
    private javax.swing.JTextField tfItem;
    private javax.swing.JTextField tfMonth;
    private javax.swing.JTextField tfPRID;
    private javax.swing.JTextField tfSMID;
    private javax.swing.JTextField tfYear;
    private javax.swing.JLabel usernamedisplay;
    // End of variables declaration//GEN-END:variables
}
