package aMachineCoding.carRental.factories;

import aMachineCoding.carRental.models.VehicleStatus;
import aMachineCoding.carRental.models.VehicleType;

public abstract class Vehicle {

    private final String registrationNumber;
    private final String model;
    private final VehicleType type;
    private VehicleStatus status;
    private final double baseRentalPrice;

    // Constructor
    public Vehicle(String registrationNumber, String model, VehicleType type,
                   double baseRentalPrice) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.type = type;
        this.status = VehicleStatus.AVAILABLE;
        this.baseRentalPrice = baseRentalPrice;
    }

    // Abstract method for calculating rental fee
    public abstract double calculateRentalFee(int days);

    // Getters and setters
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getModel() {
        return model;
    }

    public VehicleType getType() {
        return type;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    public double getBaseRentalPrice() {
        return baseRentalPrice;
    }
}
