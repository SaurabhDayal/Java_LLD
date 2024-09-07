package structural.decoratorPattern.decoratorPkg;

import structural.decoratorPattern.componentPkg.Car;

public class SunroofDecorator extends CarDecorator {
    public SunroofDecorator(Car decoratedCar) {
        super(decoratedCar);
    }

    @Override
    public String getDescription() {
        return decoratedCar.getDescription() + ", Sunroof";
    }

    @Override
    public double getCost() {
        return decoratedCar.getCost() + 2000; // Additional cost for sunroof
    }
}
