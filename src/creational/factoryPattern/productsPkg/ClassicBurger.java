package creational.factoryPattern.productsPkg;

public class ClassicBurger implements Burger {

    @Override
    public void prepare() {
        // Prepare Classic Burger
        System.out.println("Preparing Classic Burger...");
    }
}