package aMachineCoding.parkingLot.services;

import aMachineCoding.parkingLot.exceptions.BillEntryGateException;
import aMachineCoding.parkingLot.exceptions.GateNotFoundException;
import aMachineCoding.parkingLot.exceptions.OperatorNotFoundException;
import aMachineCoding.parkingLot.exceptions.TicketNotFoundException;
import aMachineCoding.parkingLot.models.*;
import aMachineCoding.parkingLot.repositories.BillRepository;
import aMachineCoding.parkingLot.repositories.GateRepository;
import aMachineCoding.parkingLot.repositories.OperatorRepository;
import aMachineCoding.parkingLot.repositories.TicketRepository;

import java.util.Optional;

public class BillService {
    private BillRepository billRepository;
    private TicketRepository ticketRepository;
    private GateRepository gateRepository;
    private OperatorRepository operatorRepository;

    public BillService(BillRepository billRepository,
                       TicketRepository ticketRepository,
                       GateRepository gateRepository,
                       OperatorRepository operatorRepository) {
        this.billRepository = billRepository;
        this.ticketRepository = ticketRepository;
        this.gateRepository = gateRepository;
        this.operatorRepository = operatorRepository;
    }

    public Bill issueBill(Long ticketId,
                          int amount,
                          String paymentStatus,
                          String paymentMode,
                          String referenceNumber,
                          Long gateId,
                          Long operatorId) throws
            TicketNotFoundException,
            GateNotFoundException,
            OperatorNotFoundException,
            BillEntryGateException {

        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isEmpty()) {
            throw new TicketNotFoundException("Ticket not found");
        }
        Ticket ticket = optionalTicket.get();

        Optional<Gate> optionalGate = gateRepository.findByGateId(gateId);
        if (optionalGate.isEmpty()) {
            throw new GateNotFoundException("Invalid gate id passed");
        }
        Gate gate = optionalGate.get();

        if (gate.getGateType() != GateType.EXIT) {
            throw new BillEntryGateException("Bill can only be issued at the exit gate.");
        }

        Optional<Operator> optionalOperator = operatorRepository.findById(operatorId);
        if (optionalOperator.isEmpty()) {
            throw new OperatorNotFoundException("Operator not found");
        }
        Operator operator = optionalOperator.get();

        Bill bill = new Bill();

        // TODO: Use Strategy Pattern here to calculate amount dynamically instead of passing it from DTO
        bill.setAmount(amount);
        bill.setBillStatus(BillStatus.PENDING);
        bill.setTicket(ticket);
        bill.setOperator(operator);
        bill.setExitTime(new java.util.Date());
        bill.setGate(gate);

        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setPaymentMode(PaymentMode.valueOf(paymentMode));
        payment.setReferenceNumber(referenceNumber);
        payment.setTime(new java.util.Date());

        bill.addPayment(payment);

        Bill savedBill = billRepository.save(bill);

        return savedBill;
    }
}
