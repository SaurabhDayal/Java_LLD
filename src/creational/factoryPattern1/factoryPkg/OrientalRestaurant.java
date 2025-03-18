package creational.factoryPattern1.factoryPkg;

import creational.factoryPattern1.productsPkg.Burger;
import creational.factoryPattern1.productsPkg.OrientalBurger;

public class OrientalRestaurant extends Restaurant {

    @Override
    protected Burger createBurger() {
        System.out.println("Creating Oriental Burger...");
        return new OrientalBurger();
    }

}