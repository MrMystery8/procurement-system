
package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;



public class PM_EditPO extends javax.swing.JFrame {
    private PurchaseManager user;
    DefaultTableModel tablemodel;

    public PM_EditPO(PurchaseManager user) {
        initComponents();
        this.user = user;
        usernamedisplay.setText("User: " + user.FName + " " + user.LName);
        roledisplay.setText("Role: " + user.userRole);

        // Updated column names for PO table
        String[] columnNames = {"PO ID", "Sup ID", "PM ID", "Day", "Month", "Year", "Date Created", "Status", "Total Cost", "Items"};
        tablemodel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells uneditable
            }
        };
        UserTable.setModel(tablemodel);

        // Set preferred column widths
        TableColumnModel columnModel = UserTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50); // PO ID
        columnModel.getColumn(1).setPreferredWidth(50); // Sup ID
        columnModel.getColumn(2).setPreferredWidth(50); // PM ID
        columnModel.getColumn(3).setPreferredWidth(50); // Day
        columnModel.getColumn(4).setPreferredWidth(50); // Month
        columnModel.getColumn(5).setPreferredWidth(50); // Year
        columnModel.getColumn(6).setPreferredWidth(75); // Date Created
        columnModel.getColumn(7).setPreferredWidth(50); // Status
        columnModel.getColumn(8).setPreferredWidth(60); // Total Cost
        columnModel.getColumn(9).setPreferredWidth(200); // Items

        loadPOs();
    }

    private void loadPOs() {
        tablemodel.setRowCount(0);

        String[] poLines = user.getAllPO(); // Use the getAllPO method from PurchaseManager

        for (String line : poLines) {
            PO po = parsePOFromString(line);

            // Manually concatenate POItem information into a single string
            StringBuilder itemsInfo = new StringBuilder();
            for (int i = 0; i < po.getPoItems().size(); i++) {
                POItem item = po.getPoItems().get(i);
                itemsInfo.append("Item ID: ").append(item.getItemID()).append(", Qty: ").append(item.getQuantity()).append(", Cost: ").append(item.getUnitCost());
                if (i < po.getPoItems().size() - 1) {
                    itemsInfo.append("; "); // Separator between items
                }
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = po.getDateCreated().format(formatter);

            // Add a row to the table with all properties of the PO object
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
                itemsInfo.toString()
            };
            tablemodel.addRow(rowData);
        }
    }

    // Parses a PO from a line of text
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
        if (parts.length > 9) { // Check for items before parsing
            String[] items = parts[9].split(",");
            for (String itemStr : items) {
                String[] itemParts = itemStr.split(":");
                if (itemParts.length == 3) {
                    poItems.add(new POItem(itemParts[0], Integer.parseInt(itemParts[1]), Double.parseDouble(itemParts[2])));
                } else {
                    System.err.println("Skipping invalid item format: " + itemStr);
                }
            }
        }

        return new PO(poId, supplierId, purchaseManagerId, day, month, year, dateCreated, new ArrayList<>(poItems), status);
    }

    private void clearFields() {
        tfPOID.setText("");
        tfSupplierID.setText("");
        tfPMID.setText("");
        tfDay.setText("");
        tfMonth.setText("");
        tfYear.setText("");
        tfDateCreated.setText("");
        cbStatus.setSelectedIndex(-1);
        tfTotalCost.setText("");
        tfItems.setText("");
    }

    private boolean isValidDate(int day, int month, int year) {
        try {
            LocalDate.of(year, month, day);
            return true;
        } catch (Exception e) {
            return false;
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
        tfPOID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfSupplierID = new javax.swing.JTextField();
        tfDay = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfMonth = new javax.swing.JTextField();
        tfYear = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnEdit = new java.awt.Button();
        btnDelete = new java.awt.Button();
        btnClear = new java.awt.Button();
        tfPMID = new javax.swing.JTextField();
        tfItems = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tfDateCreated = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tfTotalCost = new javax.swing.JTextField();
        cbStatus = new javax.swing.JComboBox<>();

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
        jLabel1.setText("PO EDIT");

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

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Supplier ID");

        tfSupplierID.setBackground(new java.awt.Color(240, 255, 255));
        tfSupplierID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfSupplierID.setForeground(new java.awt.Color(51, 51, 51));
        tfSupplierID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfSupplierID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSupplierIDActionPerformed(evt);
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

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("PM ID");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Month");

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

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Year");

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

        tfPMID.setBackground(new java.awt.Color(240, 255, 255));
        tfPMID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfPMID.setForeground(new java.awt.Color(51, 51, 51));
        tfPMID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Date Created");

        tfDateCreated.setBackground(new java.awt.Color(240, 255, 255));
        tfDateCreated.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfDateCreated.setForeground(new java.awt.Color(51, 51, 51));
        tfDateCreated.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfDateCreated.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDateCreatedActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Status");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Total Cost");

        tfTotalCost.setBackground(new java.awt.Color(240, 255, 255));
        tfTotalCost.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfTotalCost.setForeground(new java.awt.Color(51, 51, 51));
        tfTotalCost.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfTotalCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTotalCostActionPerformed(evt);
            }
        });

        cbStatus.setBackground(new java.awt.Color(240, 255, 255));
        cbStatus.setForeground(new java.awt.Color(50, 50, 50));
        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "Rejected", "Approved(NotPaid)", "Approved(Paid)" }));
        cbStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RightSideLayout = new javax.swing.GroupLayout(RightSide);
        RightSide.setLayout(RightSideLayout);
        RightSideLayout.setHorizontalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(261, 261, 261))
            .addGroup(RightSideLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(RightSideLayout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightSideLayout.createSequentialGroup()
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(tfItems, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, RightSideLayout.createSequentialGroup()
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(RightSideLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(RightSideLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfPOID, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(RightSideLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfSupplierID, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(RightSideLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(RightSideLayout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(RightSideLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tfDateCreated, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(42, 42, 42)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(RightSideLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(cbStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(RightSideLayout.createSequentialGroup()
                                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tfDay, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfPMID, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfYear, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        RightSideLayout.setVerticalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfPOID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfSupplierID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(7, 7, 7)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(tfMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfDateCreated, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addGroup(RightSideLayout.createSequentialGroup()
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tfPMID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(tfDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(tfYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(tfTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        PM_Dashboard pageFrame = new PM_Dashboard(user);
        pageFrame.setVisible(true);
        pageFrame.pack();
        pageFrame.setLocationRelativeTo(null);
        this.dispose();   
        return;
               
               
    }//GEN-LAST:event_btnBackActionPerformed

    private void tfDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDayActionPerformed


    }//GEN-LAST:event_tfDayActionPerformed

    private void tfSupplierIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSupplierIDActionPerformed


    }//GEN-LAST:event_tfSupplierIDActionPerformed

    private void tfYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfYearActionPerformed

    private void tfMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfMonthActionPerformed

    private void tfPOIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPOIDActionPerformed


    }//GEN-LAST:event_tfPOIDActionPerformed

    private void UserTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserTableMouseReleased

        int row = UserTable.rowAtPoint(evt.getPoint()); 
        
        if (row >= 0) {
            tfPOID.setText(UserTable.getValueAt(row, 0).toString());
            tfSupplierID.setText(UserTable.getValueAt(row, 1).toString());
            tfPMID.setText(UserTable.getValueAt(row, 2).toString());
            tfDay.setText(UserTable.getValueAt(row, 3).toString());
            tfMonth.setText(UserTable.getValueAt(row, 4).toString());
            tfYear.setText(UserTable.getValueAt(row, 5).toString());
            tfDateCreated.setText(UserTable.getValueAt(row, 6).toString());
            cbStatus.setSelectedItem(UserTable.getValueAt(row, 7).toString());
            tfTotalCost.setText(UserTable.getValueAt(row, 8).toString());
            tfItems.setText(UserTable.getValueAt(row, 9).toString());

            tfPOID.setEditable(false);
        }




    }//GEN-LAST:event_UserTableMouseReleased

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        String delPOID = tfPOID.getText().trim();

        if (delPOID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a PO to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete PO: " + delPOID + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            String message = user.deletePO(delPOID); // Assuming you have a deletePO method in PurchaseManager
            JOptionPane.showMessageDialog(this, message, "Delete PO Result", JOptionPane.INFORMATION_MESSAGE);

            tfPOID.setEditable(true);
            clearFields();
            loadPOs();
        }

    }//GEN-LAST:event_btnDeleteActionPerformed


    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed

        
        String poID = tfPOID.getText().trim();
        String supplierID = tfSupplierID.getText().trim();
        String pmID = tfPMID.getText().trim();
        String dayStr = tfDay.getText().trim();
        String monthStr = tfMonth.getText().trim();
        String yearStr = tfYear.getText().trim();
        String dateCreatedStr = tfDateCreated.getText().trim();
        String status = cbStatus.getSelectedItem().toString().trim();
        String totalCostStr = tfTotalCost.getText().trim();
        String itemsStr = tfItems.getText().trim();

        // Check if any of the required fields are empty
        if (poID.isEmpty() || supplierID.isEmpty() || pmID.isEmpty() || dayStr.isEmpty() || 
            monthStr.isEmpty() || yearStr.isEmpty() || dateCreatedStr.isEmpty() || 
            status.isEmpty() || itemsStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
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
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Day, month, and year must be valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Invalid date entered.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validate total cost
        double totalCost;
        try {
            totalCost = Double.parseDouble(totalCostStr);
            if (totalCost < 0) {
                throw new IllegalArgumentException("Total cost cannot be negative");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Total cost must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        ArrayList<POItem> poItems = new ArrayList<>();
        String[] itemStrings = itemsStr.split(";");
        for (String itemStr : itemStrings) {
            itemStr = itemStr.trim();
            if (!itemStr.isEmpty()) {
                String[] parts = itemStr.replaceAll("Item ID: |Qty: |Cost: ", "").split(",");
                if (parts.length == 3) {
                    try {
                        String itemID = parts[0].trim();
                        int quantity = Integer.parseInt(parts[1].trim());
                        double cost = Double.parseDouble(parts[2].trim());
                        poItems.add(new POItem(itemID, quantity, cost));
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

        // Create the PO object and edit
        PO editedPO = new PO(poID, supplierID, pmID, day, month, year, dateCreated, poItems, status);
        editedPO.setPoTotal(totalCost);
        String result = user.editPO(editedPO); // Assuming you have an editPO method in PurchaseManager
        JOptionPane.showMessageDialog(this, result, "Edit PO Result", JOptionPane.INFORMATION_MESSAGE);

        tfPOID.setEditable(true);
        clearFields();
        loadPOs();



    }//GEN-LAST:event_btnEditActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed

        tfPOID.setEditable(true);

        clearFields();

    }//GEN-LAST:event_btnClearActionPerformed

    private void tfDateCreatedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDateCreatedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDateCreatedActionPerformed

    private void tfTotalCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTotalCostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTotalCostActionPerformed

    private void tfItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfItemsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfItemsActionPerformed

    private void cbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbStatusActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LeftSide;
    private javax.swing.JPanel RightSide;
    private javax.swing.JTable UserTable;
    private javax.swing.JButton btnBack;
    private java.awt.Button btnClear;
    private java.awt.Button btnDelete;
    private java.awt.Button btnEdit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JTextField tfPMID;
    private javax.swing.JTextField tfPOID;
    private javax.swing.JTextField tfSupplierID;
    private javax.swing.JTextField tfTotalCost;
    private javax.swing.JTextField tfYear;
    private javax.swing.JLabel usernamedisplay;
    // End of variables declaration//GEN-END:variables
}
