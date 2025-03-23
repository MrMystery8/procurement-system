
package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;



public class SM_EditPR extends javax.swing.JFrame {
    private SalesManager user;
    
    DefaultTableModel tablemodel;
    public SM_EditPR(SalesManager user) {        
        initComponents();
        this.user = user;
        usernamedisplay.setText("User: "+ user.FName +" "+ user.LName);
        roledisplay.setText("Role: " + user.userRole);
        loadtable();

        
       

    }

    private void loadtable(){
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

        String[] prLines = user.getAllPR(); // Use the getAllPR() method from SalesManager

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
    
    private void clearFields(){
        tfPRID.setText(""); 
        tfDateCreated.setText(""); 
        tfDay.setText("");
        tfMonth.setText(""); 
        tfYear.setText(""); 
        tfSMID.setText("");
        tfItems.setText("");
    }
    
    private boolean validateFields() {
    String username = tfPRID.getText().trim();
    String password = tfDateCreated.getText().trim();
    String email = tfSMID.getText().trim();

    if (username.isEmpty() || username.length() < 1) {
        JOptionPane.showMessageDialog(this, "Username must be at least 4 characters long!", "Validation Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    if (password.isEmpty() || password.length() < 6 || 
        !password.matches(".*[0-9].*") || !password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
        JOptionPane.showMessageDialog(this, "Password must be at least 6 characters long and include a number and a special character!", "Validation Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
        JOptionPane.showMessageDialog(this, "Invalid email format!", "Validation Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    return true;
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
        tfPRID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfMonth = new javax.swing.JTextField();
        tfYear = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfDateCreated = new javax.swing.JTextField();
        tfSMID = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnEdit = new java.awt.Button();
        btnDelete = new java.awt.Button();
        btnClear = new java.awt.Button();
        tfDay = new javax.swing.JTextField();
        tfItems = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

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
        jLabel1.setText("PR EDIT");

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
        UserTable.setEnabled(false);
        UserTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                UserTableMouseReleased(evt);
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
        jLabel6.setText("Month");

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

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Year");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Day");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Date Created");

        tfDateCreated.setBackground(new java.awt.Color(240, 255, 255));
        tfDateCreated.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfDateCreated.setForeground(new java.awt.Color(51, 51, 51));
        tfDateCreated.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfDateCreated.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDateCreatedActionPerformed(evt);
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

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("SM ID");

        btnEdit.setBackground(new java.awt.Color(0, 0, 153));
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEdit.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setLabel("Edit");
        btnEdit.setMinimumSize(new java.awt.Dimension(70, 33));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(0, 0, 153));
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDelete.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setLabel("Delete");
        btnDelete.setMinimumSize(new java.awt.Dimension(70, 33));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(0, 0, 153));
        btnClear.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnClear.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setLabel("Clear");
        btnClear.setMinimumSize(new java.awt.Dimension(70, 33));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        tfDay.setBackground(new java.awt.Color(240, 255, 255));
        tfDay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfDay.setForeground(new java.awt.Color(51, 51, 51));
        tfDay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tfItems.setBackground(new java.awt.Color(240, 255, 255));
        tfItems.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfItems.setForeground(new java.awt.Color(51, 51, 51));
        tfItems.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfItemsActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Items");

        javax.swing.GroupLayout RightSideLayout = new javax.swing.GroupLayout(RightSide);
        RightSide.setLayout(RightSideLayout);
        RightSideLayout.setHorizontalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(RightSideLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfItems, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(RightSideLayout.createSequentialGroup()
                                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(RightSideLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(tfPRID, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(RightSideLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfDateCreated, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(RightSideLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(tfMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(RightSideLayout.createSequentialGroup()
                                            .addGap(11, 11, 11)
                                            .addComponent(jLabel7))
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfSMID)
                                    .addComponent(tfYear)
                                    .addComponent(tfDay, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(42, 42, 42))))
            .addGroup(RightSideLayout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(261, 261, 261))
        );
        RightSideLayout.setVerticalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(tfYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfPRID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(tfDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addGap(18, 18, 18)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(tfSMID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(tfDateCreated, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void tfYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfYearActionPerformed


    }//GEN-LAST:event_tfYearActionPerformed

    private void tfMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMonthActionPerformed


    }//GEN-LAST:event_tfMonthActionPerformed

    private void tfSMIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSMIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSMIDActionPerformed

    private void tfDateCreatedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDateCreatedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDateCreatedActionPerformed

    private void tfPRIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPRIDActionPerformed


    }//GEN-LAST:event_tfPRIDActionPerformed

    private void UserTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserTableMouseReleased

        int row = UserTable.rowAtPoint(evt.getPoint()); 
        
        if (row >= 0) { 
            tfPRID.setText(UserTable.getValueAt(row, 0).toString()); 
            tfDay.setText(UserTable.getValueAt(row, 1).toString()); 
            tfMonth.setText(UserTable.getValueAt(row, 2).toString());
            tfYear.setText(UserTable.getValueAt(row, 3).toString()); 
            tfDateCreated.setText(UserTable.getValueAt(row, 4).toString());
            tfSMID.setText(UserTable.getValueAt(row, 5).toString());
            tfItems.setText(UserTable.getValueAt(row, 6).toString());
            
            tfPRID.setEditable(false);
        }



    }//GEN-LAST:event_UserTableMouseReleased

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        String delPRID= tfPRID.getText().trim();

        String message = user.deletePR(delPRID);
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);

        tfPRID.setEditable(true);
        clearFields();
        loadtable();

    }//GEN-LAST:event_btnDeleteActionPerformed


    private boolean isValidDate(int day, int month, int year) {
        try {
            // Create a date object using the given day, month, and year.
            // This will throw a DateTimeException if the date is invalid.
            LocalDate.of(year, month, day);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed

        
        String prID = tfPRID.getText().trim();
        String dateCreatedStr = tfDateCreated.getText().trim();
        String dayStr = tfDay.getText().trim();
        String monthStr = tfMonth.getText().trim();
        String yearStr = tfYear.getText().trim();
        String smID = tfSMID.getText().trim();
        String itemsStr = tfItems.getText().trim();

        // Check if any of the required fields are empty
        if (prID.isEmpty() || dayStr.isEmpty() || monthStr.isEmpty() || yearStr.isEmpty() || 
            dateCreatedStr.isEmpty() || smID.isEmpty() || itemsStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate date fields
         int day, month, year;
         try {
        day = Integer.parseInt(dayStr);
        month = Integer.parseInt(monthStr);
        year = Integer.parseInt(yearStr);

        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date");
        }

        // Check if the year is not in the past
        int currentYear = LocalDate.now().getYear();
        if (year < currentYear) {
            JOptionPane.showMessageDialog(this, "Year cannot be in the past.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Day, month, and year must be valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(this, "Invalid date entered.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

        // Parse date created
        LocalDate dateCreated;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            dateCreated = LocalDate.parse(dateCreatedStr, formatter);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Parse items
        ArrayList<PRItem> prItems = new ArrayList<>();
        String[] itemStrings = itemsStr.split(";");
        for (String itemStr : itemStrings) {
            itemStr = itemStr.trim();
            if (!itemStr.isEmpty()) {
                String[] parts = itemStr.replaceAll("Item ID: |Qty: |Supplier: ", "").split(",");
                if (parts.length == 3) {
                    try {
                        String itemID = parts[0].trim();
                        int quantity = Integer.parseInt(parts[1].trim());
                        String supplierID = parts[2].trim();
                        prItems.add(new PRItem(itemID, quantity, supplierID));
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Invalid number format in item details.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid item format: " + itemStr, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }

        // Create the PR object and edit
        PR editedPR = new PR(prID, prItems, day, month, year, dateCreated, smID);
        String result = user.editPR(editedPR);
        JOptionPane.showMessageDialog(this, result, "Edit PR Result", JOptionPane.INFORMATION_MESSAGE);

        tfPRID.setEditable(true);
        clearFields();
        loadtable();



    }//GEN-LAST:event_btnEditActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed

        tfPRID.setEditable(true);

        clearFields();

    }//GEN-LAST:event_btnClearActionPerformed

    private void tfItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfItemsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfItemsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LeftSide;
    private javax.swing.JPanel RightSide;
    private javax.swing.JTable UserTable;
    private javax.swing.JButton btnBack;
    private java.awt.Button btnClear;
    private java.awt.Button btnDelete;
    private java.awt.Button btnEdit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JTextField tfItems;
    private javax.swing.JTextField tfMonth;
    private javax.swing.JTextField tfPRID;
    private javax.swing.JTextField tfSMID;
    private javax.swing.JTextField tfYear;
    private javax.swing.JLabel usernamedisplay;
    // End of variables declaration//GEN-END:variables
}
