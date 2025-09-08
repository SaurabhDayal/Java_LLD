package aMachineCoding.carRental.strategies.concretePaymentStrategies;

import aMachineCoding.carRental.strategies.PaymentStrategy;

public class CreditCardPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
    }
}
