package behavioral.mediatorPattern.colleaguePkg;

import behavioral.mediatorPattern.mediatorPkg.AirlineMediator;

public class Flight implements Colleague {

    private AirlineMediator mediator;
    private String flightNumber;
    private boolean isCancelled;

    public Flight(String flightNumber) {
        this.flightNumber = flightNumber;
        this.isCancelled = false;
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

    public String getFlightNumber() {
        return flightNumber;
    }

    public void cancelFlight() {
        this.isCancelled = true;
        System.out.println("Flight " + flightNumber + " is cancelled.");
        notifyMediator("flightCancelled");
    }
}

