package aMachineCoding.inventoryManagementSystem.factories;

import aMachineCoding.inventoryManagementSystem.models.ProductCategory;

import java.util.Date;

public class ProductFactory {

    public Product createProduct(ProductCategory category,
                                 String sku,
                                 String name,
                                 double price,
                                 Object... extras) {
        return switch (category) {
            case ELECTRONICS -> new ElectronicsProduct(
                    sku,
                    name,
                    price,
                    (String) extras[0],       // brand
                    (int) extras[1]           // warrantyPeriod
            );
            case CLOTHING -> new ClothingProduct(
                    sku,
                    name,
                    price,
                    (String) extras[0],       // size
                    (String) extras[1]        // color
            );
            case GROCERY -> new GroceryProduct(
                    sku,
                    name,
                    price,
                    (Date) extras[0],         // expiryDate
                    (boolean) extras[1]       // refrigerated
            );
            default -> throw new IllegalArgumentException(
                    "Unsupported product category: " + category);
        };
    }
}
