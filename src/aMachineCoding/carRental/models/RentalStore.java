package aMachineCoding.carRental.models;

import aMachineCoding.carRental.factories.Vehicle;

import java.util.*;

public class RentalStore {

    private final int id;
    private final String name;
    private final Location location;
    private final Map<String, Vehicle> vehicles;  // Registration Number (Key) -> Vehicle (Value)

    // --------------------------------------
    // Constructor & Initialization
    // --------------------------------------

    public RentalStore(int id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.vehicles = new HashMap<>();
    }

    // --------------------------------------
    // Vehicle Availability & Search
    // --------------------------------------

    // Get all available vehicles between the given dates
    public List<Vehicle> getAvailableVehicles(Date startDate, Date endDate) {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles.values()) {
            if (vehicle.getStatus() == VehicleStatus.AVAILABLE) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }

    // Check if a specific vehicle is available between given dates
    public boolean isVehicleAvailable(String registrationNumber, Date startDate, Date endDate) {
        Vehicle vehicle = vehicles.get(registrationNumber);
        return vehicle != null && vehicle.getStatus() == VehicleStatus.AVAILABLE;
    }

    // --------------------------------------
    // Vehicle Management
    // --------------------------------------

    // Add a new vehicle to the rental store
    public void addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.getRegistrationNumber(), vehicle);
    }

    // Remove a vehicle from the rental store by registration number
    public void removeVehicle(String registrationNumber) {
        vehicles.remove(registrationNumber);
    }

    // Get vehicle details by registration number
    public Vehicle getVehicle(String registrationNumber) {
        return vehicles.get(registrationNumber);
    }

    // Get all vehicles in the store
    public Map<String, Vehicle> getAllVehicles() {
        return vehicles;
    }

    // --------------------------------------
    // Store Details
    // --------------------------------------

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }
}
