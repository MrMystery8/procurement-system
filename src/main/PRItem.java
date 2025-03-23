/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Ayaan
 */
public class PRItem {
    private String itemID;
    private int quantity;
    private String supplierID;

    // Constructor
    public PRItem(String itemID, int quantity, String supplierID) {
        this.itemID = itemID;
        this.quantity = quantity;
        this.supplierID = supplierID;
    }

    // Getters
    public String getItemID() {
        return itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSupplierID() {
        return supplierID;
    }

    // Setters (if needed)
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    @Override
    public String toString() {
        return itemID + ":" + quantity + ":" + supplierID;
    }
}
