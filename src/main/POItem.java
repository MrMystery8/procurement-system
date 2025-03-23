package main;

public class POItem {
    
    private String itemID;
    private int quantity;
    private double unitCost;


    public POItem(String itemID, int quantity) {
        this.itemID = itemID;
        this.quantity = quantity;
        this.unitCost = getItemCost(itemID);

    }
      
    public POItem(String itemID, int quantity, double unitCost){
        this.itemID = itemID;
        this.quantity = quantity;
        this.unitCost = unitCost;

    }
    
    private double getItemCost(String itemID) {
      Item item = Item.getItem(itemID);
        if (item != null) {
                return item.getCost();
            } else {
                return 0.0; // Return 0.0 if the item is not found
        }
    }
   // Getters and Setters

    public String getItemID() {
        return itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitCost() {
        return unitCost;
    }
    
    public void setunitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

      // Helper method
    public double getLineTotal() {
        return unitCost * quantity;
    }
    @Override
    public String toString() {
        return this.itemID + ":" + this.quantity + ":" + this.unitCost;
    }
}