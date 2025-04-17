package aExercise.parkingLot.services;

import aExercise.parkingLot.exceptions.GateNotFoundException;
import aExercise.parkingLot.exceptions.OperatorNotFoundException;
import aExercise.parkingLot.exceptions.TicketNotFoundException;
import aExercise.parkingLot.models.*;
import aExercise.parkingLot.repositories.BillRepository;
import aExercise.parkingLot.repositories.GateRepository;
import aExercise.parkingLot.repositories.OperatorRepository;
import aExercise.parkingLot.repositories.TicketRepository;

import java.util.Optional;

public class BillService {
    private BillRepository billRepository;
    private TicketRepository ticketRepository;
    private GateRepository gateRepository;
    private OperatorRepository operatorRepository;  // Add the OperatorRepository

    public BillService(BillRepository billRepository, TicketRepository ticketRepository, GateRepository gateRepository, OperatorRepository operatorRepository) {
        this.billRepository = billRepository;
        this.ticketRepository = ticketRepository;
        this.gateRepository = gateRepository;
        this.operatorRepository = operatorRepository;  // Initialize OperatorRepository
    }

    // Issue Bill for a Ticket
    public Bill issueBill(Long ticketId, int amount, String paymentStatus, String paymentMode, String referenceNumber, Long gateId, Long operatorId) {
        try {
            // Find the ticket by its ID
            Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
            if (optionalTicket.isEmpty()) {
                throw new TicketNotFoundException("Ticket not found");
            }
            Ticket ticket = optionalTicket.get();  // Get the Ticket object from the Optional

            // Get the Gate associated with the ticket (Assuming the gate is linked to the ticket)
            Optional<Gate> optionalGate = gateRepository.findByGateId(gateId);
            if (optionalGate.isEmpty()) {
                throw new GateNotFoundException("Invalid gate id passed");
            }
            Gate gate = optionalGate.get();  // Get the Gate object from the Optional

            // Ensure the gate type is EXIT (to issue a bill at exit gate only)
            if (gate.getGateType() != GateType.EXIT) {
                throw new Exception("Bill can only be issued at the exit gate.");
            }

            // Get the Operator associated with the provided operatorId
            Optional<Operator> optionalOperator = operatorRepository.findById(operatorId);
            if (optionalOperator.isEmpty()) {
                throw new OperatorNotFoundException("Operator not found");
            }
            Operator operator = optionalOperator.get();  // Get the Operator object from the Optional

            // Create a new Bill
            Bill bill = new Bill();
            bill.setAmount(amount);
            bill.setBillStatus(BillStatus.PENDING);  // Bill status is initially pending
            bill.setTicket(ticket);
            bill.setOperator(operator);  // Set the operator for the bill
            bill.setExitTime(new java.util.Date()); // Set the exit time when the bill is issued
            bill.setGate(gate);
            bill.setOperator(operator);

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

            // Return the saved bill
            return savedBill;

        } catch (Exception e) {
            // Handle any exceptions that occur
            System.out.println("Error while issuing bill: " + e.getMessage());
            return null;  // Return null or throw a custom exception based on your design
        }
    }
}
