package aMachineCoding.inventoryManagementSystem.strategies;

import aMachineCoding.inventoryManagementSystem.models.InventoryItem;

public interface ReplenishmentStrategy {
    void replenish(InventoryItem item);
}
