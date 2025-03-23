
package main;

import java.io.*;
import java.util.*;

public class SalesManager extends User{
    
    public SalesManager(){
        super();
    }
    
    public SalesManager(String userID, String password, User.Role role, String fName, String lName, String email){
        super(userID, password, role, fName, lName, email);
    }
    
    
    protected String[] getAllItems(){
        ArrayList<String> items = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("items.txt"));
            String line;
            while ((line = reader.readLine())!=null){
                items.add(line);
            }
            
            return items.toArray(new String [0]);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return items.toArray(new String [0]);
    }
    
    public static String[] getAllPR(){
        ArrayList<String> PRs = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("PRs.txt"));
            String line;
            while ((line = reader.readLine())!=null){
                PRs.add(line);
            }
            
            return PRs.toArray(new String [0]);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return PRs.toArray(new String [0]);
    }
    
    
    
    
    public String StockUpdate(Sales newentry, int qty){
        
        Item item = Item.getItem(newentry.getItemID());

        if (qty <= 0 ){
            return "Enter a valid Quantity";
        }
        else if(!item.itemidExists(item.getItemID())){
            return "Item ID doesnt exist";
        }
        else {
        
        if (item.getStockLevel()- qty >= 0 ){
            item.setStockLevel(item.getStockLevel()-qty);
            newentry.setStocklevel(item.getStockLevel());
            
            InventoryManager IM = new InventoryManager();
            if (IM.editItem(item.getItemID(), item.getName(), item.getSupplierID(), item.getStockLevel(), item.getReorderLevel(), item.getPrice(), item.getCost())){
                
                try{
                    BufferedWriter writer = new BufferedWriter(new FileWriter("sales.txt", true));
                    writer.write(newentry.toString());
                    writer.close();

                    } catch (IOException e){
                        e.printStackTrace();
                    }
                
                return "Sale Registered";
            }
            else{
                return "Sale not registered";
            }
        }
        else {
            return "Item Stock insufficient";
        }
    }  
        
        
    }
    
    public static boolean prExists(String prId) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("PRs.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length > 0 && parts[0].equals(prId)) {
                    reader.close(); // Close the reader when PR ID is found
                    return true; // PR ID found
                }
            }
            reader.close(); // Close the reader if PR ID is not found
        } catch (IOException e) {
            System.err.println("Error reading PRs file: " + e.getMessage());
        }
        return false; // PR ID not found or error occurred
    }
    
    
    public String createPR(PR newPR) {
        if (newPR == null) {
            return "Error: PR object is null.";
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("PRs.txt", true));
            writer.write(newPR.toString());
            writer.close();
            return "Purchase Requisition created and saved successfully.";
        } catch (IOException e) {
            return "Error saving Purchase Requisition: " + e.getMessage();
        }
    }
    
    
     protected String editPR(PR editedPR) {
        String prIDToEdit = editedPR.getPrID();
        ArrayList<String> updatedPRs = new ArrayList<>();
        boolean prFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("PRs.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length > 0 && parts[0].equals(prIDToEdit)) {
                    // Found the PR to edit, replace with the edited PR's string representation
                    updatedPRs.add(editedPR.toString().trim());
                    prFound = true;
                } else {
                    // Keep other PRs as they are
                    updatedPRs.add(line);
                }
            }
        } catch (IOException e) {
            return "Error reading PRs file: " + e.getMessage();
        }

        if (!prFound) {
            return "PR with ID " + prIDToEdit + " not found.";
        }

        // Write the updated PRs back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("PRs.txt"))) {
            for (String prLine : updatedPRs) {
                writer.write(prLine + "\n");
            }
        } catch (IOException e) {
            return "Error writing to PRs file: " + e.getMessage();
        }

        return "PR successfully updated.";
    }
     
     protected String deletePR(String prIDToDelete) {
        ArrayList<String> updatedPRs = new ArrayList<>();
        boolean prFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("PRs.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length > 0 && parts[0].equals(prIDToDelete)) {
                    // Found the PR to delete, skip adding it to updatedPRs
                    prFound = true;
                } else {
                    // Keep other PRs
                    updatedPRs.add(line);
                }
            }
        } catch (IOException e) {
            return "Error reading PRs file: " + e.getMessage();
        }

        if (!prFound) {
            return "PR with ID " + prIDToDelete + " not found.";
        }

        // Write the updated PRs (without the deleted one) back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("PRs.txt"))) {
            for (String prLine : updatedPRs) {
                writer.write(prLine + "\n");
            }
        } catch (IOException e) {
            return "Error writing to PRs file: " + e.getMessage();
        }

        return "PR successfully deleted.";
    }
     
     
    public String[] getAllSales() {
        ArrayList<String> sales = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("sales.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sales.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sales.toArray(new String[0]);
    }
    
    
    public boolean editSale(Sales updatedSale) {
        String saleIDToEdit = updatedSale.getSaleID();
        ArrayList<String> updatedSales = new ArrayList<>();
        boolean saleFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("sales.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length > 0 && parts[0].equals(saleIDToEdit)) {
                    // Found the sale to edit
                    updatedSales.add(updatedSale.toString().trim());
                    saleFound = true;
                } else {
                    // Keep other sales as they are
                    updatedSales.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading sales file: " + e.getMessage());
            return false;
        }

        if (!saleFound) {
            System.err.println("Sale with ID " + saleIDToEdit + " not found.");
            return false;
        }

        // Write the updated sales back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sales.txt"))) {
            for (String saleLine : updatedSales) {
                writer.write(saleLine + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to sales file: " + e.getMessage());
            return false;
        }

        return true;
    }

    public boolean deleteSale(String saleIDToDelete) {
        ArrayList<String> updatedSales = new ArrayList<>();
        boolean saleFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("sales.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length > 0 && parts[0].equals(saleIDToDelete)) {
                    // Found the sale to delete, skip adding it to updatedSales
                    saleFound = true;
                } else {
                    // Keep other sales
                    updatedSales.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading sales file: " + e.getMessage());
            return false;
        }

        if (!saleFound) {
            System.err.println("Sale with ID " + saleIDToDelete + " not found.");
            return false;
        }

        // Write the updated sales (without the deleted one) back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sales.txt"))) {
            for (String saleLine : updatedSales) {
                writer.write(saleLine + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to sales file: " + e.getMessage());
            return false;
        }

        return true;
    }
}