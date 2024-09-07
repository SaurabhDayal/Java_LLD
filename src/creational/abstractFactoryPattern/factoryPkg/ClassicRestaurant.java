package creational.abstractFactoryPattern.factoryPkg;

import creational.abstractFactoryPattern.productsPkg.*;

public class ClassicRestaurant extends Restaurant {

    @Override
    public Burger createBurger() {
        System.out.println("Creating Classic Burger...");
        return new ClassicBurger();
    }

    @Override
    public Pizza createPizza() {
        System.out.println("Creating Classic Pizza...");
        return new ClassicPizza();
    }

}