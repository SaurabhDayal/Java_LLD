package structural.decoratorPattern.decoratorPkg;

import structural.decoratorPattern.componentPkg.Car;

public class LeatherSeatsDecorator extends CarDecorator {
    public LeatherSeatsDecorator(Car decoratedCar) {
        super(decoratedCar);
    }

    @Override
    public String getDescription() {
        return decoratedCar.getDescription() + ", Leather Seats";
    }

    @Override
    public double getCost() {
        return decoratedCar.getCost() + 1500; // Additional cost for leather seats
    }
}
