package creational.factoryPattern1.productsPkg;

public class OrientalBurger implements Burger {

    @Override
    public void prepare() {
        // Prepare Oriental Burger
        System.out.println("Preparing Oriental Burger...");
    }
}