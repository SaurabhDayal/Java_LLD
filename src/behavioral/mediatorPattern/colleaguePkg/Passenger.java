package behavioral.mediatorPattern.colleaguePkg;

import behavioral.mediatorPattern.mediatorPkg.AirlineMediator;

public class Passenger implements Colleague {

    private AirlineMediator mediator;
    private String name;

    public Passenger(String name) {
        this.name = name;
    }

    @Override
    public void setMediator(AirlineMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void notifyMediator(String event) {
        mediator.notify(this, event);
    }

    @Override
    public void registerWithMediator() {
        mediator.addColleague(this);
    }

    public String getName() {
        return name;
    }

    public void requestBooking() {
        System.out.println(name + " is requesting a flight booking.");
        notifyMediator("bookingRequested");
    }
}

