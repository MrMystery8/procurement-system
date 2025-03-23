
package main;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.Arrays;

public class Login extends javax.swing.JFrame {
    public Login() {
        initComponents();
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        LeftSide = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        RightSide = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        pfPassword = new javax.swing.JPasswordField();
        btnLogin = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(null);

        LeftSide.setBackground(new java.awt.Color(0, 0, 153));
        LeftSide.setPreferredSize(new java.awt.Dimension(400, 500));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/image (2).png"))); // NOI18N

        javax.swing.GroupLayout LeftSideLayout = new javax.swing.GroupLayout(LeftSide);
        LeftSide.setLayout(LeftSideLayout);
        LeftSideLayout.setHorizontalGroup(
            LeftSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftSideLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 219, Short.MAX_VALUE)
                .addGap(93, 93, 93))
        );
        LeftSideLayout.setVerticalGroup(
            LeftSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftSideLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(191, Short.MAX_VALUE))
        );

        jPanel1.add(LeftSide);
        LeftSide.setBounds(0, 0, 400, 500);

        RightSide.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("LOGIN");

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Username");

        tfUsername.setBackground(new java.awt.Color(240, 255, 255));
        tfUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfUsername.setForeground(new java.awt.Color(51, 51, 51));
        tfUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfUsernameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Password");

        pfPassword.setBackground(new java.awt.Color(240, 255, 255));
        pfPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pfPassword.setMinimumSize(new java.awt.Dimension(64, 22));
        pfPassword.setPreferredSize(new java.awt.Dimension(64, 22));
        pfPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pfPasswordActionPerformed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(0, 0, 153));
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLogin.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setLabel("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RightSideLayout = new javax.swing.GroupLayout(RightSide);
        RightSide.setLayout(RightSideLayout);
        RightSideLayout.setHorizontalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addComponent(tfUsername)
                        .addComponent(jLabel3)
                        .addComponent(pfPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        RightSideLayout.setVerticalGroup(
            RightSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightSideLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );

        jPanel1.add(RightSide);
        RightSide.setBounds(400, 0, 400, 500);

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

    private void tfUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfUsernameActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed

        String filepath = "users.txt";
//        try{
//        BufferedWriter likh = new BufferedWriter(new FileWriter("users.txt", true));
//        likh.write(tfUsername.getText()+"\n");
//        likh.close();
//        
//        } catch (IOException e){
//            e.printStackTrace();
//        }
            
            try{
                BufferedReader parh = new BufferedReader(new FileReader(filepath));
                String line;
                while ((line = parh.readLine())!= null){
                    String[] sepline = line.split(";");
                    if (tfUsername.getText().equals(sepline[0]) && new String(pfPassword.getPassword()).equals(sepline[1])){
                        JOptionPane.showMessageDialog(null, "Login Successful!", "Information", JOptionPane.INFORMATION_MESSAGE);
                        switch (sepline[2]){
                            case "Admin":
                                Admin user = new Admin(sepline[0],sepline[1], User.Role.valueOf(sepline[2]),sepline[3],sepline[4],sepline[5]);
    
                                Admin_Dashboard adminDashboard = new Admin_Dashboard(user);
                                adminDashboard.setVisible(true);
                                adminDashboard.pack();
                                adminDashboard.setLocationRelativeTo(null);
                                this.dispose();     
                                return;
                                
                            case "InventoryManager":
                            InventoryManager inventoryManager = new InventoryManager(sepline[0], sepline[1], User.Role.valueOf(sepline[2]), sepline[3], sepline[4], sepline[5]);
                            IM_Dashboard inventoryDashboard = new IM_Dashboard(inventoryManager);
                            inventoryDashboard.setVisible(true);
                            this.dispose();
                            return;
                                
                            case "SalesManager":
                                SalesManager salesManager = new SalesManager(sepline[0],sepline[1], User.Role.valueOf(sepline[2]),sepline[3],sepline[4],sepline[5]);
    
                                SM_Dashboard smDashboard = new SM_Dashboard(salesManager);
                                smDashboard.setVisible(true);
                                smDashboard.pack();
                                smDashboard.setLocationRelativeTo(null);
                                this.dispose();     
                                return;
                                
                            case "PurchaseManager":
                                PurchaseManager purchaseManager = new PurchaseManager(sepline[0],sepline[1], User.Role.valueOf(sepline[2]),sepline[3],sepline[4],sepline[5]);
    
                                PM_Dashboard pmDashboard = new PM_Dashboard(purchaseManager);
                                pmDashboard.setVisible(true);
                                pmDashboard.pack();
                                pmDashboard.setLocationRelativeTo(null);
                                this.dispose();     
                                return;
                                
                            case "FinanceManager":
                        FinanceManager financeManager = new FinanceManager(sepline[0], sepline[1], User.Role.valueOf(sepline[2]), sepline[3], sepline[4], sepline[5]);
                        FM_DashBoard fmDashboard = new FM_DashBoard(financeManager);
                        fmDashboard.setVisible(true);
                        fmDashboard.pack();
                        fmDashboard.setLocationRelativeTo(null);
                        this.dispose();
                        return;
                                
       
                        
                        default:
                            JOptionPane.showMessageDialog(this, "Role not supported!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        
                        }
                    }
                    
                    
                }
                JOptionPane.showMessageDialog(null, "Login Unsuccessful! Try again!", "Information", JOptionPane.INFORMATION_MESSAGE);

            }
            catch (IOException e){
                e.printStackTrace();
            }
        
        
        


//        NextPage NextPageFrame = new NextPage();
//        NextPageFrame.setVisible(true);
//        NextPageFrame.pack();
//        NextPageFrame.setLocationRelativeTo(null);
//        this.dispose();

        
    }//GEN-LAST:event_btnLoginActionPerformed

    private void pfPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pfPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pfPasswordActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LeftSide;
    private javax.swing.JPanel RightSide;
    private java.awt.Button btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField pfPassword;
    private javax.swing.JTextField tfUsername;
    // End of variables declaration//GEN-END:variables
}
