package creational.builderPattern2.buildersPkg;

import creational.builderPattern2.carsPkg.CarType;
import creational.builderPattern2.componentsPkg.Engine;
import creational.builderPattern2.componentsPkg.GPSNavigator;
import creational.builderPattern2.componentsPkg.Transmission;
import creational.builderPattern2.componentsPkg.TripComputer;

public interface Builder {
    Builder setCarType(CarType type);

    Builder setSeats(int seats);

    Builder setEngine(Engine engine);

    Builder setTransmission(Transmission transmission);

    Builder setTripComputer(TripComputer tripComputer);

    Builder setGPSNavigator(GPSNavigator gpsNavigator);
}
