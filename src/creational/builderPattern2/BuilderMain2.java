package creational.builderPattern2;

import creational.builderPattern2.carsPkg.Car;
import creational.builderPattern2.carsPkg.Manual;
import creational.builderPattern2.directorPkg.Director;

public class BuilderMain2 {

    public static void main(String[] args) {

        Director director = new Director();

        // Use the static nested class inside Car
        Car.CarBuilder carBuilder = new Car.CarBuilder();
        director.constructSportsCar(carBuilder);
        Car car = carBuilder.build();
        System.out.println("Car built:\n" + car.getCarType());

        // Use the static nested class inside Manual
        Manual.ManualBuilder manualBuilder = new Manual.ManualBuilder();
        director.constructSportsCar(manualBuilder);
        Manual carManual = manualBuilder.build();
        System.out.println("Car manual built:\n" + carManual.print());
    }
}
