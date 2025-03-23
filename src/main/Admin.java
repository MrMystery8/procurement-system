package main;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.*;

public class Admin extends User{
    
    public Admin(){
        super();
    }
    
    public Admin(String userID, String password, User.Role role, String fName, String lName, String email){
        super(userID, password, role, fName, lName, email);
    }
    
    protected String registerUser(Admin newentry){
        if (useridExists(UserID)){
            return "Username already exists. Please choose a different Username";
        }else if(UserID.isBlank() || Password.isBlank()|| FName.isBlank()|| LName.isBlank() ||Email.isBlank()){
            return "Please fill in all the fields";
        }
        else{
        
        
            try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true));
            writer.write(newentry.toString());
            writer.close();

            } catch (IOException e){
                e.printStackTrace();
            }
        return "Registration Successful";
        }
    }
    
    private boolean useridExists(String userid){
        try{
        BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
        String line;
        while ((line = reader.readLine())!= null){
            String[] sepline = line.split(";");
            if (userid.equals(sepline[0])){
                return true;
            } 
        }
        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
    
    protected String[] getAllUsers(){
        ArrayList<String> users = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
            String line;
            while ((line = reader.readLine())!=null){
                users.add(line);
            }
            
            return users.toArray(new String [0]);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return users.toArray(new String [0]);
    }
    
    protected String editUser(Admin edits){
        if (!useridExists(edits.UserID)){
            return "Username not found";
        } 
        else if(UserID.isBlank() || Password.isBlank()|| FName.isBlank()|| LName.isBlank() ||Email.isBlank()){
            return "Please fill in all the fields";
        } 
        else{
        edits.deleteUser(edits);
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true));
            writer.write(edits.toString());
            writer.close();

            } catch (IOException e){
                e.printStackTrace();
            }
        
        return "User successfully edited";
        
        }
    }

    
    protected String deleteUser(Admin delete, Admin user){
        if (!useridExists(delete.UserID)){
            return "Username not found. Please enter a valid Username";
        }
        else if(delete.UserID.equals(user.UserID)){
            return("Why dya wanna delete yourself lol");
        }
        else if(delete.UserID.isBlank()){
            return "Please fill in the Username field";
        } 
        else{
            ArrayList<String> users = new ArrayList<>(Arrays.asList(delete.getAllUsers()));
            
            String removeline = "";
            for (String userline : users){
                if (userline.startsWith(UserID + ";")){
                    removeline = userline;
                }
            }
            users.remove(removeline);
            
            try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"));
            for (String userline : users){
                writer.write(userline + "\n");
            }
            writer.close();

            } catch (IOException e){
                e.printStackTrace();
            }
            
            
            return "User Deleted Successfully";
        }    }
    
        protected String deleteUser(Admin delete){
        if (!useridExists(delete.UserID)){
            return "Username not found. Please enter a valid Username";
        }
        else if(delete.UserID.isBlank()){
            return "Please fill in the Username field";
        } 
        else{
            ArrayList<String> users = new ArrayList<>(Arrays.asList(delete.getAllUsers()));
            
            String removeline = "";
            for (String userline : users){
                if (userline.startsWith(UserID + ";")){
                    removeline = userline;
                }
            }
            users.remove(removeline);
            
            try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"));
            for (String userline : users){
                writer.write(userline + "\n");
            }
            writer.close();

            } catch (IOException e){
                e.printStackTrace();
            }
            
            
            return "User Deleted Successfully";
        }    }
    
    
    
    
    
    
}