package creational.abstractFactoryPattern.factoryPkg;

import creational.abstractFactoryPattern.productsPkg.Burger;
import creational.abstractFactoryPattern.productsPkg.Pizza;

public abstract class Restaurant {

    public void orderBurger() {
        System.out.println("Ordering Burger...");
        Burger burger = createBurger();
        burger.prepare();
    }

    public abstract Burger createBurger();

    public abstract Pizza createPizza();
}