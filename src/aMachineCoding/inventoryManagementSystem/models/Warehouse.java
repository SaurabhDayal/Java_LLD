package aMachineCoding.inventoryManagementSystem.models;

import aMachineCoding.inventoryManagementSystem.factories.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Warehouse {

    private int id;
    private final String name;
    private String location;

    // SKU -> InventoryItem
    private final Map<String, InventoryItem> inventory;

    // -----------------------------------
    // Constructor
    // -----------------------------------
    public Warehouse(String name) {
        this.name = name;
        this.inventory = new HashMap<>();
    }

    // -----------------------------------
    // Inventory Operations
    // -----------------------------------

    // Add product with quantity and threshold
    public void addProduct(Product product, int quantity, int threshold) {
        String sku = product.getSku();

        InventoryItem item = inventory.get(sku);
        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            item = new InventoryItem(product, quantity, threshold);
            inventory.put(sku, item);
        }

        logOperation(InventoryOperation.ADD, product, quantity, item.getQuantity());
    }

    // Remove product from warehouse
    public boolean removeProduct(String sku, int quantity) {
        if (!inventory.containsKey(sku)) {
            System.out.println("Error: Product with SKU " + sku + " not found in " + name);
            return false;
        }

        InventoryItem item = inventory.get(sku);
        if (item.getQuantity() < quantity) {
            System.out.println("Error: Insufficient inventory. Requested: " + quantity + ", Available: " + item.getQuantity());
            return false;
        }

        item.setQuantity(item.getQuantity() - quantity);
        logOperation(InventoryOperation.REMOVE, item.getProduct(), quantity, item.getQuantity());

        if (item.getQuantity() == 0) {
            inventory.remove(sku);
            System.out.println("Product " + item.getProduct().getName() + " removed from inventory as quantity is now zero.");
        }
        
        return true;
    }

    // Get available quantity
    public int getAvailableQuantity(String sku) {
        return inventory.containsKey(sku) ? inventory.get(sku).getQuantity() : 0;
    }

    // Get item by SKU
    public InventoryItem getItemBySku(String sku) {
        return inventory.get(sku);
    }

    // Get all inventory items
    public Collection<InventoryItem> getAllItems() {
        return inventory.values();
    }

    // -----------------------------------
    // Helpers
    // -----------------------------------
    private void logOperation(InventoryOperation operation, Product product, int quantity, int finalQuantity) {
        System.out.println(quantity + " units of " + product.getName()
                + " (SKU: " + product.getSku() + ") "
                + operation.name().toLowerCase() + " in " + name
                + ". Current updated quantity: " + finalQuantity
        );
    }

    // -----------------------------------
    // Getters & Setters
    // -----------------------------------
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, InventoryItem> getInventory() {
        return inventory;
    }
}
