package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Item {

    private String itemID;
    private String name;
    private String supplierID;
    private int stockLevel;
    private int reorderLevel;
    private double price;
    private double cost; // New variable to store the cost of the item

    public Item(String itemID, String name, String supplierID, int stockLevel, int reorderLevel, double price, double cost) {
        this.itemID = itemID;
        this.name = name;
        this.supplierID = supplierID;
        this.stockLevel = stockLevel;
        this.reorderLevel = reorderLevel;
        this.price = price;
        this.cost = cost; // Initialize cost in the constructor
    }

    public Item() {
        this.itemID = null;
        this.name = null;
        this.supplierID = null;
        this.stockLevel = 0;
        this.reorderLevel = 0;
        this.price = 0;
        this.cost = 0; // Initialize cost in the default constructor
    }

    private static final String ITEM_FILE = "items.txt";

    // Getters and setters for the new cost variable
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    
    // Existing getters and setters for other variables
    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    

    // Method to check if the item needs reordering
    public boolean needsReorder() {
        return stockLevel <= reorderLevel;
    }

    public void updateStock(int quantity) {
        this.stockLevel += quantity; // Adjust stock level
        if (this.stockLevel < 0) {
            this.stockLevel = 0; // Prevent stock level from being negative
        }
    }

    @Override
    public String toString() {
        return "Item ID: " + itemID +
               ", Name: " + name +
               ", Supplier ID: " + supplierID +
               ", Stock Level: " + stockLevel +
               ", Reorder Level: " + reorderLevel +
               ", Price: " + price +
               ", Cost: " + cost; // Include cost in toString()
    }

    public static List<Item> loadItemsFromFile() {
        List<Item> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ITEM_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 7) { // Increased to 7 to account for cost
                    String itemID = parts[0];
                    String name = parts[1];
                    String supplierID = parts[2];
                    int stockLevel = Integer.parseInt(parts[3]);
                    int reorderLevel = Integer.parseInt(parts[4]);
                    double price = Double.parseDouble(parts[5]);
                    double cost = Double.parseDouble(parts[6]); // Parse cost from file
                    items.add(new Item(itemID, name, supplierID, stockLevel, reorderLevel, price, cost)); // Include cost
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading items file: " + e.getMessage());
        }
        return items;
    }

    // Save items to the file, including the cost
    public static void saveItemsToFile(List<Item> items) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ITEM_FILE))) {
            for (Item item : items) {
                writer.write(item.getItemID() + ";" +
                             item.getName() + ";" +
                             item.getSupplierID() + ";" +
                             item.getStockLevel() + ";" +
                             item.getReorderLevel() + ";" +
                             item.getPrice() + ";" +
                             item.getCost() + "\n"); // Include cost when saving
            }
        } catch (IOException e) {
            System.out.println("Error writing items file: " + e.getMessage());
        }
    }

    public static String[] getAllItems() {
        ArrayList<String> items = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("items.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                items.add(line);
            }

            return items.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items.toArray(new String[0]);
    }

    public static Item getItem(String itemID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ITEM_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 7 && parts[0].equals(itemID)) { // Increased to 7 to account for cost
                    String name = parts[1];
                    String supplierID = parts[2];
                    int stockLevel = Integer.parseInt(parts[3]);
                    int reorderLevel = Integer.parseInt(parts[4]);
                    double price = Double.parseDouble(parts[5]);
                    double cost = Double.parseDouble(parts[6]);// Parse cost from file
                    return new Item(itemID, name, supplierID, stockLevel, reorderLevel, price, cost); // Include cost
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading items file: " + e.getMessage());
        }
        return null;
    }

    public boolean itemidExists(String itemid) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("items.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] sepline = line.split(";");
                if (itemid.equals(sepline[0])) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}