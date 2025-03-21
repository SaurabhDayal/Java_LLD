package creational.factoryPattern2.factoryPkg;

import creational.factoryPattern2.productsPkg.Burger;

public abstract class Restaurant {

    public void orderBurger() {
        System.out.println("Ordering Burger...");
        Burger burger = createBurger();
        burger.prepare();
    }

    // Factory method to be implemented by subclasses to create specific Burger types
    protected abstract Burger createBurger();
}