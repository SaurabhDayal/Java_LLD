package aExercise.parkingLot.services;

import aExercise.parkingLot.models.*;
import aExercise.parkingLot.repositories.BillRepository;
import aExercise.parkingLot.repositories.TicketRepository;

public class BillService {
    private BillRepository billRepository;
    private TicketRepository ticketRepository;

    public BillService(BillRepository billRepository, TicketRepository ticketRepository) {
        this.billRepository = billRepository;
        this.ticketRepository = ticketRepository;
    }

    // Issue Bill for a Ticket
    public Bill issueBill(Long ticketId, int amount, String paymentStatus, String paymentMode, String referenceNumber) {
        try {
            // Find the ticket by its ID
            Ticket ticket = ticketRepository.findById(ticketId)
                    .orElseThrow(() -> new Exception("Ticket not found"));

            // Create a new Bill
            Bill bill = new Bill();
            bill.setAmount(amount);
            bill.setBillStatus(BillStatus.PENDING);  // Bill status is initially pending
            bill.setTicket(ticket);
            bill.setOperator(ticket.getOperator()); // Assuming the operator is associated with the ticket
            bill.setExitTime(new java.util.Date()); // Set the exit time when the bill is issued

            // Create a payment object and set its details
            Payment payment = new Payment();
            payment.setAmount(amount); // Payment amount equals bill amount initially
            payment.setPaymentStatus(PaymentStatus.PENDING); // Payment is pending until completed
            payment.setPaymentMode(PaymentMode.valueOf(paymentMode)); // Enum conversion for payment mode
            payment.setReferenceNumber(referenceNumber);
            payment.setTime(new java.util.Date()); // Set current time for payment

            // Add the payment to the bill
            bill.addPayment(payment);

            // Save the bill in the repository
            Bill savedBill = billRepository.save(bill);

            // After saving the bill, update the bill status based on payment
            savedBill.updateBillStatus();

            // Return the saved bill
            return savedBill;

        } catch (Exception e) {
            // Handle any exceptions that occur
            System.out.println("Error while issuing bill: " + e.getMessage());
            return null;  // Return null or throw a custom exception based on your design
        }
    }
}
