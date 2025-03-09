package creational.builderPattern2.carsPkg;

import creational.builderPattern2.buildersPkg.Builder;
import creational.builderPattern2.componentsPkg.Engine;
import creational.builderPattern2.componentsPkg.GPSNavigator;
import creational.builderPattern2.componentsPkg.Transmission;
import creational.builderPattern2.componentsPkg.TripComputer;

public class Manual {

    private final CarType carType;
    private final int seats;
    private final Engine engine;
    private final Transmission transmission;
    private final TripComputer tripComputer;
    private final GPSNavigator gpsNavigator;

    private Manual(ManualBuilder builder) {
        this.carType = builder.carType;
        this.seats = builder.seats;
        this.engine = builder.engine;
        this.transmission = builder.transmission;
        this.tripComputer = builder.tripComputer;
        this.gpsNavigator = builder.gpsNavigator;
    }

    public String print() {
        return "Type of car: " + carType + "\n" +
                "Seats: " + seats + "\n" +
                "Engine volume: " + engine.getVolume() + "\n" +
                "Transmission: " + transmission + "\n" +
                (tripComputer != null ? "Trip Computer: Functional\n" : "Trip Computer: N/A\n") +
                (gpsNavigator != null ? "GPS Navigator: Functional\n" : "GPS Navigator: N/A\n");
    }

    /*** Static Nested Builder Class ***/
    public static class ManualBuilder implements Builder {
        private CarType carType;
        private int seats;
        private Engine engine;
        private Transmission transmission;
        private TripComputer tripComputer;
        private GPSNavigator gpsNavigator;

        @Override
        public void setCarType(CarType carType) {
            this.carType = carType;
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
            return new Manual(this);
        }
    }
}
