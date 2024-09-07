package creational.abstractFactoryPattern.productsPkg;

public class ClassicPizza implements Pizza {

    @Override
    public void bake() {
        // Logic relevant to Classic Pizzas
        System.out.println("Baking Classic Pizza...");
    }

}