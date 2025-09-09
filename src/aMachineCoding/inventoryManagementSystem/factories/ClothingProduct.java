package aMachineCoding.inventoryManagementSystem.factories;

import aMachineCoding.inventoryManagementSystem.models.ProductCategory;

public class ClothingProduct extends Product {

    private String size;
    private String color;

    public ClothingProduct(String sku, String name, double price, String size, String color) {
        super();
        setSku(sku);
        setName(name);
        setPrice(price);
        setCategory(ProductCategory.CLOTHING);
        this.size = size;
        this.color = color;
    }

    // Clothing-specific attributes
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
