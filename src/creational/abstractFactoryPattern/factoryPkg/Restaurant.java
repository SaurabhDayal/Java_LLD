package creational.abstractFactoryPattern.factoryPkg;

import creational.abstractFactoryPattern.productsPkg.Burger;
import creational.abstractFactoryPattern.productsPkg.Pizza;

/***
 * now A abstract factory
 */
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

    public abstract Burger createBurger();

    public abstract Pizza createPizza();
}