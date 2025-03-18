package creational.factoryPattern1;

import creational.factoryPattern1.factoryPkg.ClassicRestaurant;
import creational.factoryPattern1.factoryPkg.OrientalRestaurant;
import creational.factoryPattern1.factoryPkg.Restaurant;

public class FactoryMain {
    public static void main(String[] args) {

        Restaurant orientalRestaurant = new OrientalRestaurant();
        orientalRestaurant.orderBurger();

        System.out.println("==========================================");

        Restaurant classicRestaurant = new ClassicRestaurant();
        classicRestaurant.orderBurger();
    }
}

