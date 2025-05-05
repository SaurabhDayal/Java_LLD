package aMachineCoding.vendingMachine.models;

public class Item {

    private final String itemCode;
    private final ItemType type;
    private final int price;
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
