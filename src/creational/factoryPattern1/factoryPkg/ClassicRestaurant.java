package creational.factoryPattern1.factoryPkg;

import creational.factoryPattern1.productsPkg.Burger;
import creational.factoryPattern1.productsPkg.ClassicBurger;

public class ClassicRestaurant extends Restaurant {

    @Override
    protected Burger createBurger() {
        System.out.println("Creating Classic Burger...");
        return new ClassicBurger();
    }

}