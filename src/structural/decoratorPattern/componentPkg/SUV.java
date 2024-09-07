package structural.decoratorPattern.componentPkg;

public class SUV implements Car {
    @Override
    public String getDescription() {
        return "SUV";
    }

    @Override
    public double getCost() {
        return 20000; // Base cost for SUV
    }
}