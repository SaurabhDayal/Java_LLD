package aMachineCoding.inventoryManagementSystem.strategies;

import aMachineCoding.inventoryManagementSystem.factories.Product;

// Interface for different replenishment strategies
public interface ReplenishmentStrategy {
    // Method to replenish stock for a given product
    void replenish(Product product);
}
