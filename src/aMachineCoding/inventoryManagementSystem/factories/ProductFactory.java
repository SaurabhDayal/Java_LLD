package aMachineCoding.inventoryManagementSystem.factories;

import aMachineCoding.inventoryManagementSystem.models.ProductCategory;

// Product Factory class implementing Factory Pattern
public class ProductFactory {
    public Product createProduct(ProductCategory category, String sku, String name, double price, int quantity, int threshold) {
        return switch (category) {
            case ELECTRONICS -> new ElectronicsProduct(sku, name, price, quantity, threshold);
            case CLOTHING -> new ClothingProduct(sku, name, price, quantity, threshold);
            case GROCERY -> new GroceryProduct(sku, name, price, quantity, threshold);
            default -> throw new IllegalArgumentException("Unsupported product category: " + category);
        };
    }
}
