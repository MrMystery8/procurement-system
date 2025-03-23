
package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Supplier {
    private String supplierID;
    private String name;
    private String contact;
    private String location;
    
    private static final String SUPPLIER_FILE = "suppliers.txt";
    
    public Supplier(String supplierID, String name, String contact, String location) {
        this.supplierID = supplierID;
        this.name = name;
        this.contact = contact;
        this.location = location;
    }

    // Getters and Setters
    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @Override
    public String toString() {
        return supplierID + " - " + name + " (" + location + ")";
    }
    
    public static List<Supplier> loadSuppliersFromFile() {
        List<Supplier> suppliers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(SUPPLIER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    String supplierID = parts[0];
                    String name = parts[1];
                    String contact = parts[2];
                    String location = parts[3];
                    suppliers.add(new Supplier(supplierID, name, contact, location));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading suppliers file: " + e.getMessage());
        }
        return suppliers;
    }

    // Save suppliers to the file
    public static void saveSuppliersToFile(List<Supplier> suppliers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SUPPLIER_FILE))) {
            for (Supplier supplier : suppliers) {
                writer.write(supplier.getSupplierID() + ";" +
                             supplier.getName() + ";" +
                             supplier.getContact() + ";" +
                             supplier.getLocation() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing suppliers file: " + e.getMessage());
        }
    }
}
    

