package structural.compositePattern;

import structural.compositePattern.Products.Book;
import structural.compositePattern.Products.VideoGame;
import structural.compositePattern.compositePkg.CompositeBox;
import structural.compositePattern.compositePkg.DeliveryService;

public class CompositeMain {

    public static void main(String[] args) {

        DeliveryService deliveryService = new DeliveryService();
        deliveryService.setupOrder(
                new CompositeBox(
                        new VideoGame("1", 100)
                ),
                new CompositeBox(
                        new CompositeBox(
                                new Book("2", 200),
                                new Book("3", 300)
                        ),
                        new VideoGame("4", 400),
                        new VideoGame("5", 500)
                )
        );
        System.out.println(deliveryService.calculateOrderPrice());

    }
}
