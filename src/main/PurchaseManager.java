package main;

import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class PurchaseManager extends User {

    public PurchaseManager() {
        super();
    }

    public PurchaseManager(String userID, String password, User.Role role, String fName, String lName, String email) {
        super(userID, password, role, fName, lName, email);
    }

    protected String[] getAllPR() {
        ArrayList<String> PRs = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("PRs.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                PRs.add(line);
            }

            return PRs.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return PRs.toArray(new String[0]);
    }
    public static String[] getAllPO() {
        ArrayList<String> POs = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("POs.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                POs.add(line);
            }

            return POs.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return POs.toArray(new String[0]);
    }


    // Method to create POs from a PR
    public String createPO(PR pr) {
        if (pr == null) {
            return "Error: PR object is null.";
        }

        List<POItem> currentSupplierItems = new ArrayList<>(); // Hold items for the current supplier
        String currentSupplier = null;

        String results = "";
        for (PRItem prItem : pr.getPrItems()) {
            Item item = Item.getItem(prItem.getItemID());
            if (item != null) {
                if (currentSupplier == null) {
                    currentSupplier = item.getSupplierID();
                    POItem poItem = new POItem(prItem.getItemID(), prItem.getQuantity(), item.getCost());
                    currentSupplierItems.add(poItem);
                } else if (item.getSupplierID().equals(currentSupplier)) {
                     POItem poItem = new POItem(prItem.getItemID(),prItem.getQuantity(), item.getCost());
                     currentSupplierItems.add(poItem);
                } else {
                    String newPoId = generateUniquePoID();
                    PO newPO = new PO(newPoId, currentSupplier, this.getUserID(), pr.getDay(), pr.getMonth(), pr.getYear(), new ArrayList<>(currentSupplierItems)); // Pass Pending status
                   
                     String result = createPO(newPO);
                    results = results + result + "\n";

                    //resetting
                     currentSupplier = item.getSupplierID();
                     currentSupplierItems = new ArrayList<>();
                     POItem poItem = new POItem(prItem.getItemID(),prItem.getQuantity(), item.getCost());
                      currentSupplierItems.add(poItem);
                }
            } else {
                results = results + "Error: Item with ID '" + prItem.getItemID() + "' not found in items.\n";
            }
        }
        if (currentSupplier != null) {
            String newPoId = generateUniquePoID();
            PO newPO = new PO(newPoId, currentSupplier, this.getUserID(), pr.getDay(), pr.getMonth(), pr.getYear(), new ArrayList<>(currentSupplierItems));
            String result = createPO(newPO);
            results = results + result + "\n";
        }
        return results;
    }


     public String createPO(String poID, String supplierID, String purchaseManagerID, int day, int month, int year, ArrayList<POItem> poItems, String status) {
    
        if (poExists(poID)) {
            return "Error: A PO with ID '" + poID + "' already exists.";
        }

            PO newPO = new PO(poID, supplierID, purchaseManagerID, day, month, year, LocalDate.now(), new ArrayList<>(poItems), status);
        return createPO(newPO);
       
    }
    
     // Method to create a PO (used internally)
        public String createPO(PO newPO) {
         if (newPO == null) {
            return "Error: PO object is null.";
        }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("POs.txt", true))) {
                writer.write(newPO.toString());
                return "Purchase Order created and saved successfully.";
            } catch (IOException e) {
                return "Error saving Purchase Order: " + e.getMessage();
            }
        }

      private String generateUniquePoID() {
            String poID;
            do {
                poID = "PO-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            } while (poExists(poID));
            return poID;
        }
        
      
     // Method to check if PO ID already exists
    public boolean poExists(String poId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("POs.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length > 0 && parts[0].equals(poId)) {
                    return true; // PO ID found
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading POs file: " + e.getMessage());
        }
        return false; // PO ID not found or error occurred
    }
    
    
    protected String editPO(PO editedPO) {
        String poIDToEdit = editedPO.getPoID();
        ArrayList<String> updatedPOs = new ArrayList<>();
        boolean poFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("POs.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length > 0 && parts[0].equals(poIDToEdit)) {
                    // Found the PO to edit, replace with the edited PO's string representation
                    updatedPOs.add(editedPO.toString().trim());
                    poFound = true;
                } else {
                    // Keep other POs as they are
                    updatedPOs.add(line);
                }
            }
        } catch (IOException e) {
            return "Error reading POs file: " + e.getMessage();
        }

        if (!poFound) {
            return "PO with ID " + poIDToEdit + " not found.";
        }

        // Write the updated POs back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("POs.txt"))) {
            for (String poLine : updatedPOs) {
                writer.write(poLine + "\n");
            }
        } catch (IOException e) {
            return "Error writing to POs file: " + e.getMessage();
        }

        return "PO successfully updated.";
    }

    protected String deletePO(String poIDToDelete) {
        ArrayList<String> updatedPOs = new ArrayList<>();
        boolean poFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("POs.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length > 0 && parts[0].equals(poIDToDelete)) {
                    // Found the PO to delete, skip adding it to updatedPOs
                    poFound = true;
                } else {
                    // Keep other POs
                    updatedPOs.add(line);
                }
            }
        } catch (IOException e) {
            return "Error reading POs file: " + e.getMessage();
        }

        if (!poFound) {
            return "PO with ID " + poIDToDelete + " not found.";
        }

        // Write the updated POs (without the deleted one) back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("POs.txt"))) {
            for (String poLine : updatedPOs) {
                writer.write(poLine + "\n");
            }
        } catch (IOException e) {
            return "Error writing to POs file: " + e.getMessage();
        }

        return "PO successfully deleted.";
    }

    
}