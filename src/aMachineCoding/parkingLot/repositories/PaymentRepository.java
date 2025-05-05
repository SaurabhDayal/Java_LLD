package aMachineCoding.parkingLot.repositories;

import aMachineCoding.parkingLot.models.Payment;

import java.util.HashMap;
import java.util.Optional;

public class PaymentRepository {
    // In-memory storage for payments by ID
    private HashMap<Long, Payment> paymentsById = new HashMap<>();
    private Long idCounter = 1L;

    // Save a Payment
    public Payment save(Payment payment) {
        // Assign an ID to the payment before saving it
        payment.setId(idCounter++);
        paymentsById.put(payment.getId(), payment);
        return payment;
    }

    // Find a Payment by its ID
    public Optional<Payment> findById(Long id) {
        return Optional.ofNullable(paymentsById.get(id));
    }

    // Find a Payment by its reference number
    public Optional<Payment> findByReferenceNumber(String referenceNumber) {
        return paymentsById.values().stream()
                .filter(payment -> payment.getReferenceNumber().equals(referenceNumber))
                .findFirst();
    }

    // Delete a Payment by its ID
    public boolean delete(Long paymentId) {
        if (paymentsById.containsKey(paymentId)) {
            paymentsById.remove(paymentId);
            return true;
        }
        return false;
    }
}
