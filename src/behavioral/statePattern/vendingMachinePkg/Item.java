package behavioral.statePattern.vendingMachinePkg;

// Item Class representing a product in the vending machine with itemCode
public class Item {
    
    private String itemCode;
    private ItemType type;
    private int price;
    private int quantity;

    public Item(String itemCode, ItemType type, int price, int quantity) {
        this.itemCode = itemCode;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItemCode() {
        return itemCode;
    }

    public ItemType getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int amount) {
        this.quantity -= amount;
    }

    public boolean isAvailable() {
        return quantity > 0;
    }
}
