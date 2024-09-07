package structural.decoratorPattern;

import structural.decoratorPattern.componentPkg.*;
import structural.decoratorPattern.decoratorPkg.LeatherSeatsDecorator;
import structural.decoratorPattern.decoratorPkg.SunroofDecorator;

public class DecoratorMain {

        public static void main(String[] args) {

            // Create a Sedan
            Car mySedan = new Sedan();
            System.out.println("Description: " + mySedan.getDescription());
            System.out.println("Cost: $" + mySedan.getCost());

            // Add sunroof to the Sedan
            mySedan = new SunroofDecorator(mySedan);
            System.out.println("Description: " + mySedan.getDescription());
            System.out.println("Cost: $" + mySedan.getCost());

            // Add leather seats to the Sedan
            mySedan = new LeatherSeatsDecorator(mySedan);
            System.out.println("Description: " + mySedan.getDescription());
            System.out.println("Cost: $" + mySedan.getCost());

            System.out.println();

            // Create an SUV
            Car mySUV = new SUV();
            System.out.println("Description: " + mySUV.getDescription());
            System.out.println("Cost: $" + mySUV.getCost());

            // Add sunroof to the SUV
            mySUV = new SunroofDecorator(mySUV);
            System.out.println("Description: " + mySUV.getDescription());
            System.out.println("Cost: $" + mySUV.getCost());

            // Add leather seats to the SUV
            mySUV = new LeatherSeatsDecorator(mySUV);
            System.out.println("Description: " + mySUV.getDescription());
            System.out.println("Cost: $" + mySUV.getCost());
        }

}