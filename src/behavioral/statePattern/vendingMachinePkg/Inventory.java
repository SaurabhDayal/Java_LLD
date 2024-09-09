package behavioral.statePattern.vendingMachinePkg;


import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
        // Adding initial items to the inventory (itemCode, price, quantity)
        items.add(new Item("C1", ItemType.COKE, 25, 5));
        items.add(new Item("J1", ItemType.JUICE, 35, 3));
        items.add(new Item("P1", ItemType.PEPSI, 45, 4));
    }

    public Item findItemByCode(String itemCode) {
        for (Item item : items) {
            if (item.getItemCode().equals(itemCode)) {
                return item;
            }
        }
        return null;
    }

    public boolean isProductAvailable(String itemCode) {
        Item item = findItemByCode(itemCode);
        return item != null && item.isAvailable();
    }

    public void reduceQuantity(String itemCode, int quantity) {
        Item item = findItemByCode(itemCode);
        if (item != null) {
            item.reduceQuantity(quantity);
        }
    }


    public boolean isEmpty() {
        return items.stream().allMatch(item -> item.getQuantity() <= 0);
    }

    public void displayInventory() {
        System.out.println("Inventory:");
        for (Item item : items) {
            System.out.println("Item Code: " + item.getItemCode() +
                    ", Type: " + item.getType() +
                    ", Price: " + item.getPrice() +
                    ", Quantity: " + item.getQuantity());
        }
    }
}
