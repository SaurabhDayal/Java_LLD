package aMachineCoding.inventoryManagementSystem.models;

import aMachineCoding.inventoryManagementSystem.strategies.ReplenishmentStrategy;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    private static InventoryManager instance;

    private final List<Warehouse> warehouses;
    private ReplenishmentStrategy replenishmentStrategy;

    private InventoryManager(ReplenishmentStrategy replenishmentStrategy) {
        warehouses = new ArrayList<>();
        this.replenishmentStrategy = replenishmentStrategy;
    }

    public static synchronized InventoryManager getInstance(ReplenishmentStrategy replenishmentStrategy) {
        if (instance == null) {
            instance = new InventoryManager(replenishmentStrategy);
        }
        return instance;
    }

    // -----------------------------------
    // Strategy Pattern
    // -----------------------------------
    public void setReplenishmentStrategy(ReplenishmentStrategy replenishmentStrategy) {
        this.replenishmentStrategy = replenishmentStrategy;
    }

    // -----------------------------------
    // Warehouse Management
    // -----------------------------------
    public void addWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
    }

    public void removeWarehouse(Warehouse warehouse) {
        warehouses.remove(warehouse);
    }

    // -----------------------------------
    // Product Inventory Operations
    // -----------------------------------
    public InventoryItem getItemBySku(String sku) {
        for (Warehouse warehouse : warehouses) {
            InventoryItem item = warehouse.getItemBySku(sku);
            if (item != null) {
                return item;
            }
        }
        return null;
    }

    public void checkAndReplenish(String sku) {
        InventoryItem item = getItemBySku(sku);
        if (item != null && item.getQuantity() < item.getThreshold()) {
            if (replenishmentStrategy != null) {
                replenishmentStrategy.replenish(item);
            }
        }
    }

    public void performInventoryCheck() {
        for (Warehouse warehouse : warehouses) {
            for (InventoryItem item : warehouse.getAllItems()) {
                if (item.getQuantity() < item.getThreshold()) {
                    if (replenishmentStrategy != null) {
                        replenishmentStrategy.replenish(item);
                    }
                }
            }
        }
    }
}
