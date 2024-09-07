package creational.abstractFactoryPattern.factoryPkg;

import creational.abstractFactoryPattern.productsPkg.Burger;
import creational.abstractFactoryPattern.productsPkg.OrientalBurger;
import creational.abstractFactoryPattern.productsPkg.OrientalPizza;
import creational.abstractFactoryPattern.productsPkg.Pizza;

public class OrientalRestaurant extends Restaurant {

    @Override
    public Burger createBurger() {
        System.out.println("Creating Oriental Burger...");
        return new OrientalBurger();
    }

    @Override
    public Pizza createPizza() {
        System.out.println("Creating Oriental Pizza...");
        return new OrientalPizza();
    }
}