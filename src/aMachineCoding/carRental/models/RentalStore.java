package aMachineCoding.carRental.models;


import aMachineCoding.carRental.factories.Vehicle;

import java.util.*;

public class RentalStore {
    private final int id;
    private final String name;
    private final Location location;
    private final Map<String, Vehicle> vehicles; // Registration Number (Key) -> Vehicle (Value)

    public RentalStore(int id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.vehicles = new HashMap<>();
    }

    public List<Vehicle> getAvailableVehicles(Date startDate, Date endDate) {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles.values()) {
            if (vehicle.getStatus() == VehicleStatus.AVAILABLE) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.getRegistrationNumber(), vehicle);
    }

    public void removeVehicle(String registrationNumber) {
        vehicles.remove(registrationNumber);
    }

    public boolean isVehicleAvailable(
            String registrationNumber, Date startDate, Date endDate) {
        Vehicle vehicle = vehicles.get(registrationNumber);
        return vehicle != null
                && vehicle.getStatus() == VehicleStatus.AVAILABLE;
    }

    public Vehicle getVehicle(String registrationNumber) {
        return vehicles.get(registrationNumber);
    }

    public Map<String, Vehicle> getAllVehicles() {
        return vehicles;
    }

    public int getId() {
        return id;
    }

    // Getters and setters can be defined here
    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public Map<String, Vehicle> getVehicles() {
        return vehicles;
    }
}
