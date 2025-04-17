package aExercise.parkingLot;

import aExercise.parkingLot.controllers.BillController;
import aExercise.parkingLot.controllers.TicketController;
import aExercise.parkingLot.dtos.IssueBillRequestDto;
import aExercise.parkingLot.dtos.IssueBillResponseDto;
import aExercise.parkingLot.dtos.IssueTicketRequestDto;
import aExercise.parkingLot.dtos.IssueTicketResponseDto;
import aExercise.parkingLot.factories.ParkingSpotAssignmentStrategyFactory;
import aExercise.parkingLot.models.*;
import aExercise.parkingLot.repositories.BillRepository;
import aExercise.parkingLot.repositories.GateRepository;
import aExercise.parkingLot.repositories.TicketRepository;
import aExercise.parkingLot.repositories.VehicleRepository;
import aExercise.parkingLot.services.BillService;
import aExercise.parkingLot.services.TicketService;
import aExercise.parkingLot.strategies.ParkingSpotAssignmentStrategy;

public class ParkingLotApplication {

    private static TicketController ticketController;
    private static BillController billController;
    private static GateRepository gateRepository;
    private static VehicleRepository vehicleRepository;
    private static TicketRepository ticketRepository;
    private static BillRepository billRepository;

    public static void main(String[] args) {
        init();

        // Car Entry Simulation
        IssueTicketRequestDto ticketRequestDto = new IssueTicketRequestDto();
        ticketRequestDto.setGateId(123L);
        ticketRequestDto.setOperatorId(345L);
        ticketRequestDto.setOwnerName("Shivam");
        ticketRequestDto.setVehicleNumber("KA01X1111");

        IssueTicketResponseDto ticketResponseDto = ticketController.issueTicket(ticketRequestDto);
        Ticket ticket = ticketResponseDto.getTicket();

        if (ticket != null) {
            System.out.println("Ticket issued:");
            System.out.println("- Ticket ID: " + ticket.getId());
            System.out.println("- Vehicle Number: " + ticket.getVehicle().getNumber());
            System.out.println("- Gate ID: " + ticket.getGate().getId());
            System.out.println("- Entry Time: " + ticket.getEntryTime());
        } else {
            System.out.println("Ticket issuing failed.");
            return;
        }

        // Simulate wait
        try {
            Thread.sleep(1000); // Wait 1 second to simulate parking time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Car Exit Simulation (Billing)
        IssueBillRequestDto billRequestDto = new IssueBillRequestDto();
        billRequestDto.setTicketId(ticket.getId());
        billRequestDto.setAmount(100); // dummy fixed amount
        billRequestDto.setPaymentMode(String.valueOf(PaymentMode.UPI));
        billRequestDto.setPaymentStatus(String.valueOf(PaymentStatus.SUCCESS));
        billRequestDto.setReferenceNumber("TXN-UPI-98765");

        IssueBillResponseDto billResponseDto = billController.issueBill(billRequestDto);

        if (billResponseDto.getResponseStatus().name().equals("SUCCESS")) {
            Bill bill = billResponseDto.getBill();
            System.out.println("Bill issued:");
            System.out.println("- Bill ID: " + bill.getId());
            System.out.println("- Ticket ID: " + bill.getTicket().getId());
            System.out.println("- Amount: " + bill.getAmount());
            System.out.println("- Exit Time: " + bill.getExitTime());
        } else {
            System.out.println("Bill issuing failed.");
        }
    }

    private static void init() {
        gateRepository = new GateRepository();
        vehicleRepository = new VehicleRepository();
        ticketRepository = new TicketRepository();
        billRepository = new BillRepository();

        Operator operator = new Operator();
        operator.setId(345L);
        operator.setName("Ramesh");

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(1L);
        parkingLot.setName("Mall Parking");

        Gate gate = new Gate();
        gate.setId(123L);
        gate.setOperator(operator);
        gate.setParkingLot(parkingLot);

        if (gateRepository.findByGateId(123L).isEmpty()) {
            gateRepository.addGate(gate);
        }

        ParkingSpotAssignmentStrategy strategy =
                ParkingSpotAssignmentStrategyFactory.getParkingLotStrategy(ParkingSpotStrategyType.NEAREST);

        TicketService ticketService = new TicketService(gateRepository, vehicleRepository, strategy, ticketRepository);
        BillService billService = new BillService(billRepository, ticketRepository);
        ticketController = new TicketController(ticketService);
        billController = new BillController(billService);
    }
}
