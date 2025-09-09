package aMachineCoding.inventoryManagementSystem.factories;

import aMachineCoding.inventoryManagementSystem.models.ProductCategory;

import java.util.Date;

public class GroceryProduct extends Product {

    private Date expiryDate;
    private boolean refrigerated;

    public GroceryProduct(String sku, String name, double price, Date expiryDate, boolean refrigerated) {
        super();
        setSku(sku);
        setName(name);
        setPrice(price);
        setCategory(ProductCategory.GROCERY);
        this.expiryDate = expiryDate;
        this.refrigerated = refrigerated;
    }

    // Getters and setters for grocery-specific attributes
    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isRefrigerated() {
        return refrigerated;
    }

    public void setRefrigerated(boolean refrigerated) {
        this.refrigerated = refrigerated;
    }
}
