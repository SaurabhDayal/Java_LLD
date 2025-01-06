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

        VehicleCache registry = new VehicleCache();
        registry.put(vehicles);
        System.out.println(registry.get("car_brand car_model"));

    }
}
