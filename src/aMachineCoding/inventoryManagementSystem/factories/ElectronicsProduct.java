package aMachineCoding.inventoryManagementSystem.factories;

import aMachineCoding.inventoryManagementSystem.models.ProductCategory;

public class ElectronicsProduct extends Product {

    private String brand;
    private int warrantyPeriod; // months

    public ElectronicsProduct(String sku, String name, double price, String brand, int warrantyPeriod) {
        super();
        setSku(sku);
        setName(name);
        setPrice(price);
        setCategory(ProductCategory.ELECTRONICS);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    // Electronics-specific attributes
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
}
