package creational.abstractFactoryPattern;

import creational.abstractFactoryPattern.factoryPkg.ClassicRestaurant;
import creational.abstractFactoryPattern.factoryPkg.OrientalRestaurant;
import creational.abstractFactoryPattern.factoryPkg.Restaurant;

public class AbstractFactoryMain {

    public static void main(String[] args) {

        Restaurant orientalRestaurant = new OrientalRestaurant();
        Restaurant classicRestaurant = new ClassicRestaurant();

        classicRestaurant.orderPizza();
        System.out.println();
        orientalRestaurant.orderPizza();

        System.out.println("==========================================");

        classicRestaurant.orderBurger();
        System.out.println();
        orientalRestaurant.orderBurger();

    }
}
