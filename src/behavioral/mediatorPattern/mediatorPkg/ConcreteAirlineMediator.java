package behavioral.mediatorPattern.mediatorPkg;

import behavioral.mediatorPattern.colleaguePkg.BookingSystem;
import behavioral.mediatorPattern.colleaguePkg.Colleague;
import behavioral.mediatorPattern.colleaguePkg.Flight;
import behavioral.mediatorPattern.colleaguePkg.Passenger;

import java.util.ArrayList;
import java.util.List;

public class ConcreteAirlineMediator implements AirlineMediator {

    private List<Colleague> colleagues = new ArrayList<>();  // Store all colleagues
    private BookingSystem bookingSystem; // Reference for booking system

    @Override
    public void addColleague(Colleague colleague) {
        colleagues.add(colleague);

        // If the colleague is a BookingSystem, store it separately
        if (colleague instanceof BookingSystem) {
            this.bookingSystem = (BookingSystem) colleague;
        }
    }

    public void setBookingSystem(BookingSystem bookingSystem) {
        this.bookingSystem = bookingSystem;
    }

    @Override
    public void notify(Colleague sender, String event) {
        if (sender instanceof Flight && "flightCancelled".equals(event)) {
            // Handle flight cancellation
            bookingSystem.handleFlightCancellation((Flight) sender);
        } else if (sender instanceof Passenger && "bookingRequested".equals(event)) {
            // Handle new booking request
            bookingSystem.handleNewBooking((Passenger) sender);
        }
    }
}
