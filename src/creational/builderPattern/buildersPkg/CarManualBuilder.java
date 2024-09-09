package creational.builderPattern.buildersPkg;


import creational.builderPattern.carsPkg.CarType;
import creational.builderPattern.carsPkg.Manual;
import creational.builderPattern.componentsPkg.Engine;
import creational.builderPattern.componentsPkg.GPSNavigator;
import creational.builderPattern.componentsPkg.Transmission;
import creational.builderPattern.componentsPkg.TripComputer;

public class CarManualBuilder implements Builder {
    private CarType type;
    private int seats;
    private Engine engine;
    private Transmission transmission;
    private TripComputer tripComputer;
    private GPSNavigator gpsNavigator;

    @Override
    public void setCarType(CarType type) {
        this.type = type;
    }

    @Override
    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public void setTripComputer(TripComputer tripComputer) {
        this.tripComputer = tripComputer;
    }

    @Override
    public void setGPSNavigator(GPSNavigator gpsNavigator) {
        this.gpsNavigator = gpsNavigator;
    }

    public Manual build() {
        return new Manual(type, seats, engine, transmission, tripComputer, gpsNavigator);
    }
}