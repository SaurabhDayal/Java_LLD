package structural.decoratorPattern.componentPkg;


public class BasicCar implements Car {
    @Override
    public String getDescription() {
        return "Basic Car";
    }

    @Override
    public double getCost() {
        return 10000; // Base cost
    }
}
