package aMachineCoding.inventoryManagementSystem.strategies;

import aMachineCoding.inventoryManagementSystem.factories.Product;
import aMachineCoding.inventoryManagementSystem.models.InventoryItem;

public class JustInTimeStrategy implements ReplenishmentStrategy {

    @Override
    public void replenish(InventoryItem item) {
        Product product = item.getProduct();
        System.out.println("Applying Just-In-Time replenishment for " + product.getName());

        // replenish only up to the threshold
        int replenishmentQty = item.getThreshold() - item.getQuantity();
        if (replenishmentQty > 0) {
            item.setQuantity(item.getQuantity() + replenishmentQty);
            System.out.println("JIT order placed. New quantity: " + item.getQuantity());
        } else {
            System.out.println("No replenishment needed. Quantity already at or above threshold.");
        }
    }
}
