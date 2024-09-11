package behavioral.mediatorPattern;

import behavioral.mediatorPattern.colleaguePkg.BookingSystem;
import behavioral.mediatorPattern.colleaguePkg.Flight;
import behavioral.mediatorPattern.colleaguePkg.Passenger;
import behavioral.mediatorPattern.mediatorPkg.ConcreteAirlineMediator;

public class MediatorMain {

    public static void main(String[] args) {

        // Create the mediator
        ConcreteAirlineMediator mediator = new ConcreteAirlineMediator();

        // Create instances of Colleague classes
        Flight flight1 = new Flight("AA123");
        Passenger passenger1 = new Passenger("John Doe");
        BookingSystem bookingSystem = new BookingSystem();

        // Set mediator for each colleague
        flight1.setMediator(mediator);
        passenger1.setMediator(mediator);
        bookingSystem.setMediator(mediator);

        // Register colleagues with the mediator
        flight1.registerWithMediator();
        passenger1.registerWithMediator();
        bookingSystem.registerWithMediator();

        // Simulate interactions
        passenger1.requestBooking();
        flight1.cancelFlight();
    }
}

