 
package main;


public abstract class User {
    protected String UserID;
    protected String Password;
    public enum Role {Admin, FinanceManager, InventoryManager, PurchaseManager, SalesManager}
    protected Role userRole;
    protected String FName;
    protected String LName;
    protected String Email;
    
    public User(){
    UserID = null;
    Password = null;
    Role userRole = null;
    FName = null;
    LName = null;
    Email = null;
        
    }
    
    public User(String userID, String password, Role role, String fName, String lName, String email) { 
        this.UserID = userID; 
        this.Password = password; 
        this.userRole = role; 
        this.FName = fName; 
        this.LName = lName; 
        this.Email = email;
    }
    
    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        this.UserID = userID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role role) {
        this.userRole = role;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String fName) {
        this.FName = fName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String lName) {
        this.LName = lName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }


    
    protected void Logout(){
        Login LoginFrame = new Login();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null);     
        return;
    }
    
    public String toString(){
        return this.UserID+";"+this.Password+";"+ this.userRole+";"+ this.FName +";"+ this.LName+";"+ this.Email + "\n";
    }
    
    
}
