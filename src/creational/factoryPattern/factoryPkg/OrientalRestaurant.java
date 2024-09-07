package creational.factoryPattern.factoryPkg;

import creational.factoryPattern.productsPkg.Burger;
import creational.factoryPattern.productsPkg.OrientalBurger;

public class OrientalRestaurant extends Restaurant {

    @Override
    protected Burger createBurger() {
        System.out.println("Creating Oriental Burger...");
        return new OrientalBurger();
    }

}