package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PO {
    
    private String poID;
    private String supplierID;
    private String purchaseManagerID;
    private int day;
    private int month;
    private int year;
    private LocalDate dateCreated;
    private ArrayList<POItem> poItems;
    private double poTotal;
    private String status;
    

    public PO(String poID, String supplierID, String purchaseManagerID, int day, int month, int year, ArrayList<POItem> poItems) {
        this.poID = poID;
        this.supplierID = supplierID;
        this.purchaseManagerID = purchaseManagerID;
        this.day = day;
        this.month = month;
        this.year = year;
        this.dateCreated = LocalDate.now();
        this.poItems = poItems;
        this.poTotal = getPOTotal();
        this.status = "Pending";
    }
      
    public PO(String poID, String supplierID, String purchaseManagerID, int day, int month, int year, LocalDate dateCreated, ArrayList<POItem> poItems, String status) {
        this.poID = poID;
        this.supplierID = supplierID;
        this.purchaseManagerID = purchaseManagerID;
        this.day = day;
        this.month = month;
        this.year = year;
        this.dateCreated = dateCreated;
        this.poItems = poItems;
        this.poTotal = getPOTotal();
        this.status = status;
    }
   
    public double getPOTotal() {
        double total = 0.0;
        for (POItem item : poItems) {
            total += item.getLineTotal();
        }
        return total;
    }

    public String getPoID() {
        return poID;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public String getPurchaseManagerID() {
        return purchaseManagerID;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public ArrayList<POItem> getPoItems() {
        return poItems;
    }

    public double getPoTotal() {
        return poTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setPoID(String poID) {
        this.poID = poID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public void setPurchaseManagerID(String purchaseManagerID) {
        this.purchaseManagerID = purchaseManagerID;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setPoItems(ArrayList<POItem> poItems) {
        this.poItems = poItems;
    }

    public void setPoTotal(double poTotal) {
        this.poTotal = poTotal;
    }

    public void setStatus(String status) {
        this.status = status;
    }    
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String output = this.poID + ";" + this.supplierID + ";" + this.purchaseManagerID + ";" + this.day + ";" + this.month + ";" + this.year + ";"
             + this.dateCreated.format(formatter) + ";" + this.status + ";"
             + this.poTotal + ";";
       for (int i = 0; i < this.poItems.size(); i++) {
           output += this.poItems.get(i).toString();
            if (i < this.poItems.size() - 1) {
                output += ",";
            }
        }
       output += "\n";
       return output;
    }
}