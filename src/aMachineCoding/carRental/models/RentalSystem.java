package aMachineCoding.carRental.models;

import aMachineCoding.carRental.factories.Vehicle;
import aMachineCoding.carRental.strategies.PaymentProcessor;
import aMachineCoding.carRental.strategies.PaymentStrategy;

import java.util.*;

public class RentalSystem {

    private static RentalSystem instance;

    private final List<RentalStore> stores;
    private final Map<Integer, User> users;
    private final ReservationManager reservationManager;
    private final PaymentProcessor paymentProcessor;

    // --------------------------------------
    // Constructor & Singleton Access
    // --------------------------------------

    private RentalSystem() {
        this.stores = new ArrayList<>();
        this.users = new HashMap<>();
        this.reservationManager = new ReservationManager();
        this.paymentProcessor = new PaymentProcessor();
    }

    public static synchronized RentalSystem getInstance() {
        if (instance == null) {
            instance = new RentalSystem();
        }
        return instance;
    }

    // --------------------------------------
    // Store Management
    // --------------------------------------

    public void addStore(RentalStore store) {
        stores.add(store);
    }

    public RentalStore getStore(int storeId) {
        for (RentalStore store : stores) {
            if (store.getId() == storeId) {
                return store;
            }
        }
        return null;
    }

    public List<RentalStore> getStores() {
        return stores;
    }

    // --------------------------------------
    // User Management
    // --------------------------------------

    public void registerUser(User user) {
        int userId = user.getId();
        if (users.containsKey(userId)) {
            System.out.println("User with id: " + userId + " already exists in the system");
            return;
        }
        users.put(userId, user);
    }

    public User getUser(int userId) {
        return users.get(userId);
    }

    // --------------------------------------
    // Reservation Management
    // --------------------------------------

    public Reservation createReservation(int userId,
                                         String vehicleRegistration,
                                         int pickupStoreId,
                                         int returnStoreId,
                                         Date startDate,
                                         Date endDate) {
        User user = users.get(userId);
        RentalStore pickupStore = getStore(pickupStoreId);
        RentalStore returnStore = getStore(returnStoreId);

        if (user == null || pickupStore == null || returnStore == null) {
            return null;
        }

        // Check vehicle availability with date range
        if (!pickupStore.isVehicleAvailable(vehicleRegistration, startDate, endDate)) {
            System.out.println("Vehicle " + vehicleRegistration + " is not available for the selected dates.");
            return null;
        }

        Vehicle vehicle = pickupStore.getVehicle(vehicleRegistration);

        if (vehicle != null) {
            return reservationManager.createReservation(
                    user,
                    vehicle,
                    pickupStore,
                    returnStore,
                    startDate,
                    endDate
            );
        }

        return null;
    }

    public void cancelReservation(int reservationId) {
        reservationManager.cancelReservation(reservationId);
    }

    // --------------------------------------
    // Payment & Rental Flow
    // --------------------------------------

    public boolean processPayment(int reservationId, PaymentStrategy paymentStrategy) {
        Reservation reservation = reservationManager.getReservation(reservationId);
        if (reservation != null) {
            boolean result = paymentProcessor.processPayment(
                    reservation.getTotalAmount(),
                    paymentStrategy
            );
            if (result) {
                reservationManager.confirmReservation(reservationId);
                return true;
            }
        }
        return false;
    }

    public void startRental(int reservationId) {
        reservationManager.startRental(reservationId);
    }

    public void completeRental(int reservationId) {
        reservationManager.completeRental(reservationId);
    }
}
