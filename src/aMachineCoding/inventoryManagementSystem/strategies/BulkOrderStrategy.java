package aMachineCoding.inventoryManagementSystem.strategies;

import aMachineCoding.inventoryManagementSystem.factories.Product;
import aMachineCoding.inventoryManagementSystem.models.InventoryItem;

public class BulkOrderStrategy implements ReplenishmentStrategy {

    @Override
    public void replenish(InventoryItem item) {
        Product product = item.getProduct();
        System.out.println("Applying Bulk Order replenishment for " + product.getName());

        // order fixed bulk (example: 100 units)
        item.setQuantity(item.getQuantity() + 100);
        System.out.println("Bulk order placed. New quantity: " + item.getQuantity());
    }
}
