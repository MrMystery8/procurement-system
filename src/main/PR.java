package main;


/**
 * Purchase Requisition (PR) Class
 * Handles multiple item IDs and their quantities.
 * Uses normal string concatenation for toString().
 * 
 * @author Ayaan
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PR {
    private String prID;
    private List<PRItem> prItems; // List of PRItem objects
    private int day;
    private int month;
    private int year;
    private LocalDate dateCreated;
    private String salesmanagerID;

    // Constructor
    public PR(String prID, List<PRItem> prItems, int day, int month, int year, String salesmanagerID) {
        this.prID = prID;
        this.prItems = prItems;
        this.day = day;
        this.month = month;
        this.year = year;
        this.dateCreated = LocalDate.now();
        this.salesmanagerID = salesmanagerID;
    }
    public PR(String prID, List<PRItem> prItems, int day, int month, int year, LocalDate dateCreated, String salesmanagerID) {
        this.prID = prID;
        this.prItems = prItems;
        this.day = day;
        this.month = month;
        this.year = year;
        this.dateCreated = dateCreated;
        this.salesmanagerID = salesmanagerID;
    }
    
      public static PR getPR(String prID) {
        try (BufferedReader reader = new BufferedReader(new FileReader("PRs.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 6 && parts[0].equals(prID)) {
                   String prId = parts[0];
                   List<PRItem> prItems = new ArrayList<>();
                    String[] items = parts[1].split(",");
                    for (String itemStr : items) {
                       String[] itemParts = itemStr.split(":");
                       if (itemParts.length == 3) {
                            prItems.add(new PRItem(itemParts[0], Integer.parseInt(itemParts[1]),itemParts[2]));
                           } else {
                          System.err.println("Skipping invalid item format: " + itemStr);
                            }
                    }
                    
                    int day = Integer.parseInt(parts[2]);
                    int month = Integer.parseInt(parts[3]);
                    int year = Integer.parseInt(parts[4]);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                      LocalDate dateCreated = LocalDate.parse(parts[5], formatter);

                    String salesManagerId = parts[6];
                    return new PR(prId, prItems, day, month, year, dateCreated, salesManagerId);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading PRs file: " + e.getMessage());
        }
        return null; // Return null if not found
    }

    // Getters
    public String getPrID() {
        return prID;
    }

    public List<PRItem> getPrItems() {
        return prItems;
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
    
    

    public String getSalesmanagerID() {
        return salesmanagerID;
    }
    

    // Setters (if needed)
    public void setPrID(String prID) {
        this.prID = prID;
    }

    public void setPrItems(List<PRItem> prItems) {
        this.prItems = prItems;
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
    
    

    public void setSalesmanagerID(String salesmanagerID) {
        this.salesmanagerID = salesmanagerID;
    }

    // toString method
    @Override
    public String toString() {
        String output = this.prID + ";";
        for (int i = 0; i < this.prItems.size(); i++) {
            output += this.prItems.get(i).toString();
            if (i < this.prItems.size() - 1) {
                output += ",";
            }
        }
      
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = this.dateCreated.format(formatter);
        

        output +=  ";" + this.day + ";" + this.month + ";" + this.year + ";" + formattedDate + ";"
            + this.salesmanagerID + "\n";
        return output;
    }
}
