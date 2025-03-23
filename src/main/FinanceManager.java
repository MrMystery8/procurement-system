package main;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FinanceManager extends User {

    private List<PO> purchaseOrders;

    public FinanceManager(String username, String password, User.Role role, String firstName, String lastName, String email) {
        super(username, password, role, firstName, lastName, email);
        this.purchaseOrders = new ArrayList<>();
    }

    public FinanceManager(FinanceManager user) {
        super();
        this.purchaseOrders = new ArrayList<>();
    }

    
    public static String[] getAllPO() {
        ArrayList<String> POs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("POs.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                POs.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading POs from file: " + e.getMessage());
        }
        return POs.toArray(new String[0]);
    }

    
    public void loadPOsFromFile() {
        purchaseOrders.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("PO's.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String poID = parts[0];
                String supplierID = parts[1];
                String purchaseManagerID = parts[2];
                int day = Integer.parseInt(parts[3]);
                int month = Integer.parseInt(parts[4]);
                int year = Integer.parseInt(parts[5]);
                LocalDate dateCreated = LocalDate.parse(parts[6], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String status = parts[7];

                ArrayList<POItem> poItems = new ArrayList<>();
                String[] items = parts[8].split(",");
                for (String itemData : items) {
                    String[] itemParts = itemData.split(":");
                    poItems.add(new POItem(itemParts[0], Integer.parseInt(itemParts[1]), Double.parseDouble(itemParts[2])));
                }
                purchaseOrders.add(new PO(poID, supplierID, purchaseManagerID, day, month, year, dateCreated, poItems, status));
            }
        } catch (IOException e) {
            System.out.println("Error loading POs from file: " + e.getMessage());
        }
    }
    
    public String processPaymentAndUpdateStock(String poID, String supplierID, double totalCost, ArrayList<POItem> items) {
       String paymentFile = "SPs.txt";
String poFile = "POs.txt";
String stockFile = "items.txt"; // File for stock updates

try {
    // Record payment transaction in SPs.txt
    try (BufferedWriter paymentWriter = new BufferedWriter(new FileWriter(paymentFile, true))) {
        String paymentRecord = String.format("POID:%s;SupplierID:%s;Amount:%.2f;Date:%s",
                poID, supplierID, totalCost, LocalDate.now());
        paymentWriter.write(paymentRecord);
        paymentWriter.newLine();
    }

    // Update PO status in POs.txt
    List<String> poLines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(poFile));
    List<String> updatedPOLines = new ArrayList<>();
    for (String line : poLines) {
        if (line.startsWith(poID + ";")) {
            String[] parts = line.split(";");
            parts[7] = "Approved(Paid)"; // Update status
            updatedPOLines.add(String.join(";", parts));
        } else {
            updatedPOLines.add(line);
        }
    }
    java.nio.file.Files.write(java.nio.file.Paths.get(poFile), updatedPOLines);

    // Update stock levels in items.txt
    List<String> stockLines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(stockFile));
    List<String> updatedStockLines = new ArrayList<>();

    for (String line : stockLines) {
        String[] parts = line.split(";");
        String itemID = parts[0];
        int stockLevel = Integer.parseInt(parts[3]); // Current stock level
        boolean updated = false;

        // Match with PO items and update stock level
        for (POItem item : items) {
            if (itemID.equals(item.getItemID())) {
                stockLevel += item.getQuantity(); // Add quantity to stock level
                updated = true;
                break; // Exit loop once matched
            }
        }

        // Rebuild the line and add to updated list
        updatedStockLines.add(String.format("%s;%s;%s;%d;%s;%s;%s",
                parts[0], parts[1], parts[2], stockLevel, parts[4], parts[5], parts[6]));
    }

    // Add any new items (not in the existing stock file)
    for (POItem item : items) {
        boolean exists = stockLines.stream().anyMatch(line -> line.startsWith(item.getItemID() + ";"));
        if (!exists) {
            updatedStockLines.add(String.format("%s;NewItem;Unknown;%d;0;%.2f;%.2f",
                    item.getItemID(), item.getQuantity(), item.getUnitCost(), item.getUnitCost()));
        }
    }

    // Write updated stock back to items.txt
    java.nio.file.Files.write(java.nio.file.Paths.get(stockFile), updatedStockLines);

    return "Payment processed, PO status updated to 'Approved, Paid', and stock levels updated successfully.";
} catch (IOException e) {
    return "Error processing payment and updating stock: " + e.getMessage();
}

    }
    
    public void updateStock(ArrayList<POItem> poItems) {
        String stockFile = "items.txt";
    try {
        List<String> stockLines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(stockFile));
        List<String> updatedStock = new ArrayList<>();
        boolean[] itemUpdated = new boolean[poItems.size()]; // To track which items are updated

        // Update existing stock lines
        for (String line : stockLines) {
            String[] parts = line.split(";");
            String itemID = parts[0];
            int stockLevel = Integer.parseInt(parts[3]); // Stock level is in index 3

            for (int i = 0; i < poItems.size(); i++) {
                POItem item = poItems.get(i);
                if (itemID.equals(item.getItemID())) {
                    stockLevel += item.getQuantity(); // Add quantity to the stock level
                    parts[3] = String.valueOf(stockLevel); // Update the stock level
                    itemUpdated[i] = true; // Mark as updated
                }
            }
            updatedStock.add(String.join(";", parts)); // Rebuild the line
        }

        // Add new items that were not found in the existing stock file
        for (int i = 0; i < poItems.size(); i++) {
            if (!itemUpdated[i]) { // Check if item was not updated
                POItem item = poItems.get(i);
                updatedStock.add(String.format("%s;NewItem;Unknown;%d;0;%.2f;%.2f",
                        item.getItemID(), item.getQuantity(), item.getUnitCost(), item.getUnitCost()));
            }
        }

        // Write updated stock back to the file
        java.nio.file.Files.write(java.nio.file.Paths.get(stockFile), updatedStock);
        System.out.println("Stock levels updated successfully.");

    } catch (IOException e) {
        System.err.println("Error updating stock: " + e.getMessage());
    } catch (NumberFormatException e) {
        System.err.println("Invalid stock data format: " + e.getMessage());
    }
    }
    
     public ArrayList<POItem> parsePOItems(String items) {
       ArrayList<POItem> poItems = new ArrayList<>();
    try {
        String[] itemEntries = items.split("; ");
        for (String entry : itemEntries) {
            try {
                String[] parts = entry.split(", ");
                String itemID = parts[0].replace("ID: ", "").trim();
                int quantity = Integer.parseInt(parts[1].replace("Qty: ", "").trim());
                double cost = Double.parseDouble(parts[2].replace("Cost: ", "").trim());
                poItems.add(new POItem(itemID, quantity, cost));
            } catch (Exception innerEx) {
                System.err.println("Invalid item entry skipped: " + entry);
            }
        }
    } catch (Exception e) {
        System.err.println("Error parsing PO items: " + e.getMessage());
        e.printStackTrace();
    }
    return poItems;

    }
     
     public ArrayList<String[]> loadPaymentHistory() {
    ArrayList<String[]> paymentHistory = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader("SPs.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(";");
            if (parts.length == 4) {
                String poID = parts[0].split(":")[1];
                String supplierID = parts[1].split(":")[1];
                String amount = parts[2].split(":")[1];
                String date = parts[3].split(":")[1];
                paymentHistory.add(new String[]{poID, supplierID, amount, date});
            }
        }
    } catch (IOException e) {
        System.err.println("Error loading payment history: " + e.getMessage());
    }
    return paymentHistory;
}
}