package creational.prototypePattern;

import creational.prototypePattern.prototypePkg.Bus;
import creational.prototypePattern.prototypePkg.Car;
import creational.prototypePattern.prototypePkg.Vehicle;
import creational.prototypePattern.prototypePkg.VehicleCache;

import java.util.ArrayList;
import java.util.List;

public class PrototypeMain {

    public static void main(String[] args) {
        // Create a list of vehicles
        List<Vehicle> vehicles = List.of(
                new Car("car_brand", "car_model", "car_color", 300),
                new Bus("bus_brand", "bus_model", "bus_color", 8)
        );
        // Clone each vehicle and store in a separate list
        List<Vehicle> copyList = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            copyList.add(vehicle.clone());
        }
        // Print all cloned vehicles
        copyList.forEach(System.out::println);
        System.out.println("==========================================");

        // Create a registry (VehicleCache) to store prototypes of vehicles
        VehicleCache registry = new VehicleCache();
        // Add the original list of vehicles to the registry
        registry.put(vehicles);
        // Retrieve a vehicle by its key from the registry and print
        printClonedVehicle(registry, "car_brand car_model"); // Key exists
        printClonedVehicle(registry, "Mercedes Setra"); // Key does not exist
    }

    // Utility method to retrieve and print a cloned vehicle from the registry
    private static void printClonedVehicle(VehicleCache registry, String key) {
        Vehicle clonedVehicle = registry.get(key);
        if (clonedVehicle != null) {
            System.out.println(clonedVehicle); // Vehicle found, print details
        } else {
            System.out.println("Vehicle not found in the registry for key: " + key); // Key not found
        }
    }
}
