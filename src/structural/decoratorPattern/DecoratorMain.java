package structural.decoratorPattern;

import structural.decoratorPattern.componentPkg.*;
import structural.decoratorPattern.decoratorPkg.LeatherSeatsDecorator;
import structural.decoratorPattern.decoratorPkg.SunroofDecorator;

public class DecoratorMain {
    public static void main(String[] args) {
        // Create a basic car
        Car myCar = new BasicCar();
        System.out.println("Description: " + myCar.getDescription());
        System.out.println("Cost: $" + myCar.getCost());

        // Add sunroof to the car
        myCar = new SunroofDecorator(myCar);
        System.out.println("Description: " + myCar.getDescription());
        System.out.println("Cost: $" + myCar.getCost());

        // Add leather seats to the car
        myCar = new LeatherSeatsDecorator(myCar);
        System.out.println("Description: " + myCar.getDescription());
        System.out.println("Cost: $" + myCar.getCost());
    }
}