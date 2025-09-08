package aMachineCoding.carRental.models;

import aMachineCoding.carRental.factories.Vehicle;

import java.util.Date;

public class Reservation {

    private final int id;
    private final User user;
    private final Vehicle vehicle;
    private final RentalStore pickupStore;
    private final RentalStore returnStore;
    private final Date startDate;
    private final Date endDate;

    private ReservationStatus status;
    private final double totalAmount;

    public Reservation(int id,
                       User user,
                       Vehicle vehicle,
                       RentalStore pickupStore,
                       RentalStore returnStore,
                       Date startDate,
                       Date endDate) {
        this.id = id;
        this.user = user;
        this.vehicle = vehicle;
        this.pickupStore = pickupStore;
        this.returnStore = returnStore;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = ReservationStatus.PENDING;
        // Calculate days between start and end dates
        long diffInMillis = endDate.getTime() - startDate.getTime();
        int days = (int) (diffInMillis / (1000 * 60 * 60 * 24)) + 1;
        this.totalAmount = vehicle.calculateRentalFee(days);
    }

    public void confirmReservation() {
        if (status == ReservationStatus.PENDING) {
            status = ReservationStatus.CONFIRMED;
            vehicle.setStatus(VehicleStatus.RESERVED);
        }
    }

    public void startRental() {
        if (status == ReservationStatus.CONFIRMED) {
            status = ReservationStatus.IN_PROGRESS;
            vehicle.setStatus(VehicleStatus.RENTED);
        }
    }

    public void completeRental() {
        if (status == ReservationStatus.IN_PROGRESS) {
            status = ReservationStatus.COMPLETED;
            vehicle.setStatus(VehicleStatus.AVAILABLE);
        }
    }

    public void cancelReservation() {
        if (status == ReservationStatus.PENDING
                || status == ReservationStatus.CONFIRMED) {
            status = ReservationStatus.CANCELED;
            vehicle.setStatus(VehicleStatus.AVAILABLE);
        }
    }

    // Getters and setters can be defined here
    public User getUser() {
        return user;
    }

    public Integer getId() {
        return id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public RentalStore getPickupStore() {
        return pickupStore;
    }

    public RentalStore getReturnStore() {
        return returnStore;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
