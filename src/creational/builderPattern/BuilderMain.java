package creational.builderPattern;

import creational.builderPattern.buildersPkg.CarBuilder;
import creational.builderPattern.buildersPkg.CarManualBuilder;
import creational.builderPattern.carsPkg.Car;
import creational.builderPattern.carsPkg.Manual;
import creational.builderPattern.directorPkg.Director;

public class BuilderMain {

    public static void main(String[] args) {

        Director director = new Director();
        CarBuilder builder = new CarBuilder();
        director.constructSportsCar(builder);

        Car car = builder.getResult();
        System.out.println("Car built:\n" + car.getCarType());

        CarManualBuilder manualBuilder = new CarManualBuilder();

        director.constructSportsCar(manualBuilder);
        Manual carManual = manualBuilder.getResult();
        System.out.println("\nCar manual built:\n" + carManual.print());
    }

}
