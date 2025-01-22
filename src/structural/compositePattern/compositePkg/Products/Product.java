package structural.compositePattern.compositePkg.Products;

import structural.compositePattern.compositePkg.Box;

// LEAF abstract class
public abstract class Product implements Box {

    protected final String title;
    protected final double price;

    public Product(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }
}