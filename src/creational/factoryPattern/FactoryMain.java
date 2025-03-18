package creational.factoryPattern;

import creational.factoryPattern.factoryPkg.ClassicRestaurant;
import creational.factoryPattern.factoryPkg.OrientalRestaurant;
import creational.factoryPattern.factoryPkg.Restaurant;

public class FactoryMain {
    public static void main(String[] args) {

        Restaurant orientalRestaurant = new OrientalRestaurant();
        orientalRestaurant.orderBurger();

        System.out.println("==========================================");

        Restaurant classicRestaurant = new ClassicRestaurant();
        classicRestaurant.orderBurger();
    }
}

