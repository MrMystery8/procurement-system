/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ayaan
 */
public class InventoryManager extends User {

    private List<Item> inventory;
    private List<Supplier> suppliers;

    public InventoryManager(String username, String password, Role role, String firstName, String lastName, String email) {
        super(username, password, role, firstName, lastName, email);
        this.inventory = Item.loadItemsFromFile();
        this.suppliers = Supplier.loadSuppliersFromFile();
    }

    public InventoryManager() {
        super();
        this.inventory = Item.loadItemsFromFile();
        this.suppliers = Supplier.loadSuppliersFromFile();
    }

    public boolean addItem(String itemID, String name, String supplierID, int stockLevel, int reorderLevel, double price, double cost) {
        // Check for duplicate item ID
        for (Item item : inventory) {
            if (item.getItemID().equals(itemID)) {
                return false; // Duplicate item ID
            }
        }

        Supplier supplier = getSupplier(supplierID);
        if (supplier == null) {
            return false;
        }

        Item newItem = new Item(itemID, name, supplierID, stockLevel, reorderLevel, price, cost); // Include cost
        inventory.add(newItem);

        Item.saveItemsToFile(inventory);
        return true;
    }

    public boolean editItem(String itemID, String name, String supplierID, int stockLevel, int reorderLevel, double price, double cost) {
        for (Item item : inventory) {
            if (item.getItemID().equals(itemID)) {
                item.setName(name);
                item.setSupplierID(supplierID);
                item.setStockLevel(stockLevel);
                item.setReorderLevel(reorderLevel);
                item.setPrice(price);
                item.setCost(cost); // Set the cost

                Item.saveItemsToFile(inventory);
                return true;
            }
        }
        return false;
    }

    public boolean deleteItem(String itemID) {
        // Find the item and remove it from the inventory
        for (Item item : inventory) {
            if (item.getItemID().equals(itemID)) {
                inventory.remove(item);

                Item.saveItemsToFile(inventory);
                return true;
            }
        }
        return false;
    }

    // View all items
    public List<Item> getAllItems() {
        return inventory;
    }

    // View item by id
    public Item getItem(String itemID) {
        for (Item item : inventory) {
            if (item.getItemID().equals(itemID)) {
                return item; // Found
            }
        }
        return null;
    }

    // Check which items need reordering
    public List<Item> getItemsToReorder() {
        List<Item> reorderList = new ArrayList<>();
        for (Item item : inventory) {
            if (item.needsReorder()) {
                reorderList.add(item);
            }
        }
        return reorderList;

    }

    // One more function for View Stock Levels
    public boolean updateStock(String itemID, int quantity) {
        for (Item item : inventory) {
            if (item.getItemID().equals(itemID)) {
                item.updateStock(quantity);
                Item.saveItemsToFile(inventory);
                return true;
            }
        }
        return false;
    }

    public boolean addSupplier(String supplierID, String name, String contact, String location) {
        // Check for duplicate supplier ID
        for (Supplier supplier : suppliers) {
            if (supplier.getSupplierID().equals(supplierID)) {
                return false;
            }
        }
        // Add new supplier
        Supplier newSupplier = new Supplier(supplierID, name, contact, location);
        suppliers.add(newSupplier);
        Supplier.saveSuppliersToFile(suppliers);
        return true;
    }

    // Edit an existing supplier
    public boolean editSupplier(String supplierID, String name, String contact, String location) {
        for (Supplier supplier : suppliers) {
            if (supplier.getSupplierID().equals(supplierID)) {
                supplier.setName(name);
                supplier.setContact(contact);
                supplier.setLocation(location);
                Supplier.saveSuppliersToFile(suppliers);
                return true;
            }
        }
        return false;
    }

    public boolean deleteSupplier(String supplierID) {
        for (Supplier supplier : suppliers) {
            if (supplier.getSupplierID().equals(supplierID)) {
                suppliers.remove(supplier);
                Supplier.saveSuppliersToFile(suppliers); // Save to file
                return true;
            }
        }
        return false;
    }

    // Get all suppliers
    public List<Supplier> getAllSuppliers() {
        return suppliers;
    }

    // Get a single supplier by its ID
    public Supplier getSupplier(String supplierID) {
        for (Supplier supplier : suppliers) {
            if (supplier.getSupplierID().equals(supplierID)) {
                return supplier;
            }
        }
        return null;
    }
}