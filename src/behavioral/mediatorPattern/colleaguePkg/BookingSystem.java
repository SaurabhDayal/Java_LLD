package behavioral.mediatorPattern.colleaguePkg;

import behavioral.mediatorPattern.mediatorPkg.AirlineMediator;

public class BookingSystem implements Colleague {

    private AirlineMediator mediator;

    @Override
    public void setMediator(AirlineMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void notifyMediator(String event) {
        // BookingSystem does not notify mediator in this example.
    }

    @Override
    public void registerWithMediator() {
        mediator.addColleague(this);
    }

    public void handleFlightCancellation(Flight flight) {
        System.out.println("BookingSystem: Handling cancellation for flight " + flight.getFlightNumber());
        // Inform other passengers about flight cancellation
    }

    public void handleNewBooking(Passenger passenger) {
        System.out.println("BookingSystem: Processing new booking request from " + passenger.getName());
        // Process new request by updating data of flight seats
    }
}
