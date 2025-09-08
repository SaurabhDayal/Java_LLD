package aMachineCoding.carRental.factories;

import aMachineCoding.carRental.factories.concreteVehicles.EconomyVehicle;
import aMachineCoding.carRental.factories.concreteVehicles.LuxuryVehicle;
import aMachineCoding.carRental.factories.concreteVehicles.SUVVehicle;
import aMachineCoding.carRental.models.VehicleType;

public class VehicleFactory {
    public static Vehicle createVehicle(VehicleType vehicleType, String registrationNumber, String model, double baseRentalPrice) {
        return switch (vehicleType) {
            case ECONOMY -> new EconomyVehicle(registrationNumber, model, vehicleType, baseRentalPrice);
            case LUXURY -> new LuxuryVehicle(registrationNumber, model, vehicleType, baseRentalPrice);
            case SUV -> new SUVVehicle(registrationNumber, model, vehicleType, baseRentalPrice);
            default -> throw new IllegalArgumentException("Unsupported vehicle type: " + vehicleType);
        };
    }
}
