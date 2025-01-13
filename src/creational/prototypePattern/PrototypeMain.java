package creational.prototypePattern;

import creational.prototypePattern.prototypePkg.Bus;
import creational.prototypePattern.prototypePkg.Car;
import creational.prototypePattern.prototypePkg.Vehicle;
import creational.prototypePattern.prototypePkg.VehicleCache;

import java.util.ArrayList;
import java.util.List;

public class PrototypeMain {

    public static void main(String[] args) {

        List<Vehicle> vehicles = List.of(
                new Car("car_brand", "car_model", "car_color", 300),
                new Bus("bus_brand", "bus_model", "bus_color", 8)
        );
        List<Vehicle> copyList = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            copyList.add(vehicle.clone());
        }
        copyList.forEach(System.out::println);
        System.out.println("==========================================");

        VehicleCache registry = new VehicleCache(); // prototype registry
        registry.put(vehicles);

        // Use the utility method to check and print
        printClonedVehicle(registry, "car_brand car_model");
        printClonedVehicle(registry, "Mercedes Setra");
    }

    private static void printClonedVehicle(VehicleCache registry, String key) {
        Vehicle clonedVehicle = registry.get(key);
        if (clonedVehicle != null) {
            System.out.println(clonedVehicle);
        } else {
            System.out.println("Vehicle not found in the registry for key: " + key);
        }
    }
}
