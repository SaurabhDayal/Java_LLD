package structural.decoratorPattern.componentPkg;


public class Sedan implements Car {
    @Override
    public String getDescription() {
        return "Sedan";
    }

    @Override
    public double getCost() {
        return 15000; // Base cost for Sedan
    }
}