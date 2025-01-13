package creational.prototypePattern.prototypePkg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleCache {

    private final Map<String, Vehicle> cache;

    public VehicleCache() {

        cache = new HashMap<>();
        // Prepopulate the cache with default vehicles
        cache.put("Bugatti Chiron", new Car("Bugatti", "Chiron", "Blue", 261));
        cache.put("Mercedes Setra", new Bus("Mercedes", "Setra", "White", 48));
    }

    // Get retrieves a clone of the vehicle corresponding to the given key.
    public Vehicle get(String key) {
        Vehicle vehicle = cache.get(key);
        if (vehicle == null) {
            return null;
        }
        return vehicle.clone();
    }

    // Put adds a list of vehicles to the cache.
    public void put(List<Vehicle> vehicles) {
        vehicles.forEach(vehicle -> cache.put(vehicle.getBrand() + " " + vehicle.getModel(), vehicle));
    }
}
