package creational.abstractFactoryPattern.factoryPkg;

import creational.abstractFactoryPattern.productsPkg.Burger;
import creational.abstractFactoryPattern.productsPkg.Pizza;

public abstract class Restaurant {

    public void orderBurger() {
        System.out.println("Ordering Burger...");
        Burger burger = createBurger();
        burger.prepare();
    }

    public void orderPizza() {
        System.out.println("Ordering Pizza...");
        Pizza pizza = createPizza();
        pizza.bake();
    }

    // Abstract factory method to be implemented for creating specific Burger types
    public abstract Burger createBurger();

    // Abstract factory method to be implemented for creating specific Pizza types
    public abstract Pizza createPizza();
}