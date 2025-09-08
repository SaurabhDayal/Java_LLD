package aMachineCoding.carRental;

import aMachineCoding.carRental.factories.Vehicle;
import aMachineCoding.carRental.factories.VehicleFactory;
import aMachineCoding.carRental.models.*;
import aMachineCoding.carRental.strategies.PaymentStrategy;
import aMachineCoding.carRental.strategies.concretePaymentStrategies.CashPayment;
import aMachineCoding.carRental.strategies.concretePaymentStrategies.CreditCardPayment;
import aMachineCoding.carRental.strategies.concretePaymentStrategies.PaypalPayment;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        RentalSystem rentalSystem = RentalSystem.getInstance();
        initializeStoresAndVehicles(rentalSystem);                  // Initialize stores and vehicles
        initializeUsers(rentalSystem);                              // Register users

        // ------------------ Display available cars in store1 ------------------
        RentalStore store1 = rentalSystem.getStore(1);
        Date startDate = new GregorianCalendar(2025, Calendar.MAY, 1).getTime();
        Date endDate = new GregorianCalendar(2025, Calendar.JUNE, 15).getTime();

        System.out.println("Available vehicles in " + store1.getName() + ":");
        displayAvailableVehicles(store1, startDate, endDate);


        Reservation reservation = createReservation(rentalSystem);  // Create reservation
        processPayment(rentalSystem, reservation);                  // Process payment
    }

    // ------------------------- Initialization -------------------------

    private static void initializeStoresAndVehicles(RentalSystem rentalSystem) {
        // Create rental stores
        RentalStore store1 = new RentalStore(
                1, "Downtown Rentals",
                new Location("123 Main St", "New York", "NY", "10001")
        );
        RentalStore store2 = new RentalStore(
                2, "Airport Rentals",
                new Location("456 Airport Rd", "Los Angeles", "CA", "90045")
        );
        rentalSystem.addStore(store1);
        rentalSystem.addStore(store2);

        // Create vehicles
        Vehicle economyCar = VehicleFactory.createVehicle(
                VehicleType.ECONOMY, "EC001", "Toyota", 50.0
        );
        Vehicle luxuryCar = VehicleFactory.createVehicle(
                VehicleType.LUXURY, "LX001", "Mercedes", 200.0
        );
        Vehicle suvCar = VehicleFactory.createVehicle(
                VehicleType.SUV, "SV001", "Honda", 75.0
        );

        // Add vehicles to stores
        store1.addVehicle(economyCar);
        store1.addVehicle(luxuryCar);
        store2.addVehicle(suvCar);
    }

    private static void initializeUsers(RentalSystem rentalSystem) {
        User user1 = new User(123, "ABC", "abc@gmail.com");
        User user2 = new User(345, "BCD", "bcd@yahoo.com");

        rentalSystem.registerUser(user1);
        rentalSystem.registerUser(user2);
    }

    // ------------------------- Reservation -------------------------

    private static Reservation createReservation(RentalSystem rentalSystem) {
        User user = rentalSystem.getUser(123); // demo: picking user1
        Vehicle vehicle = rentalSystem.getStore(1).getVehicle("EC001"); // demo: economy car

        return rentalSystem.createReservation(
                user.getId(),
                vehicle.getRegistrationNumber(),
                1, // pickupStoreId
                1, // returnStoreId
                new GregorianCalendar(2025, Calendar.MAY, 1).getTime(),
                new GregorianCalendar(2025, Calendar.JUNE, 15).getTime()
        );
    }

    // ------------------------- Payment -------------------------

    private static void processPayment(RentalSystem rentalSystem, Reservation reservation) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nProcessing payment for reservation #" + reservation.getId());
        System.out.println("Total amount: $" + reservation.getTotalAmount());
        System.out.println("Select payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. Cash");
        System.out.println("3. PayPal");

        int choice = 1; // default to credit card for demo
        // int choice = scanner.nextInt(); // real input

        PaymentStrategy paymentStrategy = switch (choice) {
            case 1 -> new CreditCardPayment();
            case 2 -> new CashPayment();
            case 3 -> new PaypalPayment();
            default -> {
                System.out.println("Invalid choice! Defaulting to credit card payment.");
                yield new CreditCardPayment();
            }
        };

        boolean paymentSuccess = rentalSystem.processPayment(
                reservation.getId(), paymentStrategy
        );

        if (paymentSuccess) {
            System.out.println("Payment successful!");
            rentalSystem.startRental(reservation.getId());

            System.out.println("\nSimulating rental period...");

            rentalSystem.completeRental(reservation.getId());
        } else {
            System.out.println("Payment failed!");
        }
    }

    // ------------------------- Utility -------------------------

    private static void displayAvailableVehicles(RentalStore store, Date startDate, Date endDate) {
        for (Vehicle vehicle : store.getAvailableVehicles(startDate, endDate)) {
            System.out.println(" - " + vehicle.getType() + " | " + vehicle.getRegistrationNumber() + " | $" + vehicle.getBaseRentalPrice());
        }
    }
}
