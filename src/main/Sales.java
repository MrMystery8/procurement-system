
package main;

import java.util.*;

public class Sales {
    
        private String saleID;
        private int day;
        private int month;
        private int year;
        private int hour;
        private int minute;
        
        private String itemID;
        private String name;
        private int qtysold;
        private double price;
        private double totalamount;
        private String salesmanagerid;
        private int stocklevel;               

    public String getSaleID() {
        return saleID;
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

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public int getQtysold() {
        return qtysold;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalamount() {
        return totalamount;
    }

    public String getSalesmanagerid() {
        return salesmanagerid;
    }

    public int getStocklevel() {
        return stocklevel;
    }

    
    public void setSaleID(String saleID) {
        this.saleID = saleID;
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

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQtysold(int qtysold) {
        this.qtysold = qtysold;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTotalamount(double totalamount) {
        this.totalamount = totalamount;
    }

    public void setSalesmanagerid(String salesmanagerid) {
        this.salesmanagerid = salesmanagerid;
    }

    public void setStocklevel(int stocklevel) {
        this.stocklevel = stocklevel;
    }
                
        
        
    public Sales(){
        
        GregorianCalendar cal = new GregorianCalendar();
        
        this.year = cal.get(GregorianCalendar.YEAR); 
        this.month = cal.get(GregorianCalendar.MONTH) + 1;
        this.day = cal.get(GregorianCalendar.DAY_OF_MONTH); 
        this.hour = cal.get(GregorianCalendar.HOUR_OF_DAY); 
        this.minute = cal.get(GregorianCalendar.MINUTE); 
    }
    
    public Sales(String saleID, Item item, int qty, SalesManager sm){
        
        GregorianCalendar cal = new GregorianCalendar();
        
        this.year = cal.get(GregorianCalendar.YEAR); 
        this.month = cal.get(GregorianCalendar.MONTH) + 1;
        this.day = cal.get(GregorianCalendar.DAY_OF_MONTH); 
        this.hour = cal.get(GregorianCalendar.HOUR_OF_DAY); 
        this.minute = cal.get(GregorianCalendar.MINUTE); 
        
        this.saleID = saleID;
        this.itemID = item.getItemID();
        this.name = item.getName();
        this.qtysold = qty;
        this.price = item.getPrice();
        this.totalamount = price * qtysold;
        this.salesmanagerid = sm.getUserID();
        this.stocklevel = item.getStockLevel();
    }

    public Sales(String saleID, int day, int month, int year, int hour, int minute, String itemID, String name, int qtysold, String salesmanagerid, int stocklevel) {
        this.saleID = saleID;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.itemID = itemID;
        this.name = name;
        this.qtysold = qtysold;
        this.salesmanagerid = salesmanagerid;
        this.stocklevel = stocklevel;
        
        this.price = Item.getItem(itemID).getPrice();
        this.totalamount = price * qtysold;

    }
    
    
    
    public String toString(){
        return saleID + ";" + day + ";" + month + ";" + year + ";" + hour + ";" + minute +
                ";" + itemID + ";" + name + ";" + qtysold + ";" + price + ";" +
                totalamount + ";" + salesmanagerid + ";" + stocklevel + "\n"; 
    }
    
}
   

