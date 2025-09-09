package aMachineCoding.inventoryManagementSystem.models;

import aMachineCoding.inventoryManagementSystem.factories.Product;

public class InventoryItem {

    private final Product product;
    private int quantity;
    private int threshold;

    public InventoryItem(Product product, int quantity, int threshold) {
        this.product = product;
        this.quantity = quantity;
        this.threshold = threshold;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    // helper method
    public boolean needsRestock() {
        return quantity <= threshold;
    }
}
