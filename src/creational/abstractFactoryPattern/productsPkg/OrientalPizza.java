package creational.abstractFactoryPattern.productsPkg;

public class OrientalPizza implements Pizza {

    @Override
    public void bake() {
        // Logic relevant to Oriental Pizzas
        System.out.println("Baking Oriental Pizza...");
    }

}