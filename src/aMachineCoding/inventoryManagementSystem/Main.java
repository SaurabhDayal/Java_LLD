package aMachineCoding.inventoryManagementSystem;

import aMachineCoding.inventoryManagementSystem.factories.Product;
import aMachineCoding.inventoryManagementSystem.factories.ProductFactory;
import aMachineCoding.inventoryManagementSystem.models.InventoryManager;
import aMachineCoding.inventoryManagementSystem.models.ProductCategory;
import aMachineCoding.inventoryManagementSystem.models.Warehouse;
import aMachineCoding.inventoryManagementSystem.strategies.BulkOrderStrategy;
import aMachineCoding.inventoryManagementSystem.strategies.JustInTimeStrategy;
import aMachineCoding.inventoryManagementSystem.strategies.ReplenishmentStrategy;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        // Initialize with Just-In-Time replenishment
        ReplenishmentStrategy replenishmentStrategy = new JustInTimeStrategy();
        InventoryManager inventoryManager = InventoryManager.getInstance(replenishmentStrategy);

        // -----------------------------------
        // Create and Add Warehouses
        // -----------------------------------
        Warehouse warehouse1 = new Warehouse("Warehouse 1");
        Warehouse warehouse2 = new Warehouse("Warehouse 2");

        inventoryManager.addWarehouse(warehouse1);
        inventoryManager.addWarehouse(warehouse2);

        // -----------------------------------
        // Create Products using Factory (no stock info here)
        // -----------------------------------
        ProductFactory productFactory = new ProductFactory();

        Product laptop = productFactory.createProduct(
                ProductCategory.ELECTRONICS,
                "SKU123",
                "Laptop",
                1000.0,
                "Dell",   // brand
                24        // warrantyPeriod (months)
        );

        Product tShirt = productFactory.createProduct(
                ProductCategory.CLOTHING,
                "SKU456",
                "T-Shirt",
                20.0,
                "L",      // size
                "Blue"    // color
        );

        Product apple = productFactory.createProduct(
                ProductCategory.GROCERY,
                "SKU789",
                "Apple",
                1.0,
                new Date(), // expiryDate
                true                // refrigerated
        );

        // -----------------------------------
        // Add Products to Warehouses with stock info
        // -----------------------------------
        System.out.println();
        warehouse1.addProduct(laptop, 15, 25);   // quantity = 15, threshold = 25
        warehouse1.addProduct(tShirt, 20, 100);  // quantity = 20, threshold = 100
        warehouse2.addProduct(apple, 50, 200);   // quantity = 50, threshold = 200

        // -----------------------------------
        // Perform Inventory Check (JIT)
        // -----------------------------------
        System.out.println();
        inventoryManager.performInventoryCheck();

        // -----------------------------------
        // Switch to Bulk Order Strategy
        // -----------------------------------

        System.out.println();
        warehouse1.removeProduct("SKU123", 2);
        inventoryManager.setReplenishmentStrategy(new BulkOrderStrategy());

        // Replenish a specific product (Laptop)
        System.out.println();
        inventoryManager.checkAndReplenish("SKU123");
    }
}
