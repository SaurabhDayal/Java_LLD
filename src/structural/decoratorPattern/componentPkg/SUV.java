package structural.decoratorPattern.componentPkg;

// Concrete Component
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