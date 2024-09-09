package creational.builderPattern.buildersPkg;

import creational.builderPattern.carsPkg.CarType;
import creational.builderPattern.componentsPkg.Engine;
import creational.builderPattern.componentsPkg.GPSNavigator;
import creational.builderPattern.componentsPkg.Transmission;
import creational.builderPattern.componentsPkg.TripComputer;

public interface Builder {
    void setCarType(CarType type);

    void setSeats(int seats);

    void setEngine(Engine engine);

    void setTransmission(Transmission transmission);

    void setTripComputer(TripComputer tripComputer);

    void setGPSNavigator(GPSNavigator gpsNavigator);
}