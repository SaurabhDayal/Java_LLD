package creational.factoryPattern2.factoryPkg;

import creational.factoryPattern2.productsPkg.Burger;
import creational.factoryPattern2.productsPkg.ClassicBurger;

public class ClassicRestaurant extends Restaurant {

    @Override
    protected Burger createBurger() {
        System.out.println("Creating Classic Burger...");
        return new ClassicBurger();
    }

}