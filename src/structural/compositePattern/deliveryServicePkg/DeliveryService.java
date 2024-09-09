package structural.compositePattern.deliveryServicePkg;

import structural.compositePattern.compositePkg.Box;
import structural.compositePattern.compositePkg.CompositeBox;

public class DeliveryService {

    private Box box;

    public DeliveryService() {
    }

    public void setupOrder(Box... boxes) {
        this.box = new CompositeBox(boxes);
    }

    public double calculateOrderPrice() {
        return box.calculatePrice();
    }

}
