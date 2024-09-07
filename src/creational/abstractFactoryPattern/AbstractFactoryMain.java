package creational.abstractFactoryPattern;

import creational.abstractFactoryPattern.factoryPkg.ClassicRestaurant;
import creational.abstractFactoryPattern.factoryPkg.OrientalRestaurant;
import creational.abstractFactoryPattern.factoryPkg.Restaurant;
import creational.abstractFactoryPattern.productsPkg.Burger;
import creational.abstractFactoryPattern.productsPkg.Pizza;

public class AbstractFactoryMain {

        public static void main(String[] args) {

            Restaurant orientalRestaurant = new OrientalRestaurant();
            Restaurant classicRestaurant = new ClassicRestaurant();

            Pizza classicPizza = classicRestaurant.createPizza();
            Pizza orientalPizza = orientalRestaurant.createPizza();

            System.out.println("==========================================");

            Burger classicBurger = classicRestaurant.createBurger();
            Burger orientalBurger = orientalRestaurant.createBurger();

        }
}
