package creational.factoryPattern2.factoryPkg;

import creational.factoryPattern2.productsPkg.Burger;
import creational.factoryPattern2.productsPkg.OrientalBurger;

public class OrientalRestaurant extends Restaurant {

    @Override
    protected Burger createBurger() {
        System.out.println("Creating Oriental Burger...");
        return new OrientalBurger();
    }

}