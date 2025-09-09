package aMachineCoding.inventoryManagementSystem.factories;

import aMachineCoding.inventoryManagementSystem.models.ProductCategory;

public abstract class Product {

    private String sku;
    private String name;
    private double price;
    private ProductCategory category;

    // Common getters and setters
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}
