package creational.factoryPattern2;

import creational.factoryPattern2.factoryPkg.ClassicRestaurant;
import creational.factoryPattern2.factoryPkg.OrientalRestaurant;
import creational.factoryPattern2.factoryPkg.Restaurant;

public class FactoryMain {
    public static void main(String[] args) {

        Restaurant orientalRestaurant = new OrientalRestaurant();
        orientalRestaurant.orderBurger();

        System.out.println("==========================================");

        Restaurant classicRestaurant = new ClassicRestaurant();
        classicRestaurant.orderBurger();
    }
}

