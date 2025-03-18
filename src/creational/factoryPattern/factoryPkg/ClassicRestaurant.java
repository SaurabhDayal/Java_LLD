package creational.factoryPattern.factoryPkg;

import creational.factoryPattern.productsPkg.Burger;
import creational.factoryPattern.productsPkg.ClassicBurger;

public class ClassicRestaurant extends Restaurant {

    @Override
    protected Burger createBurger() {
        System.out.println("Creating Classic Burger...");
        return new ClassicBurger();
    }

}