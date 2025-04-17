package aExercise.parkingLot;

import aExercise.parkingLot.controllers.TicketController;
import aExercise.parkingLot.dtos.IssueTicketRequestDto;
import aExercise.parkingLot.dtos.IssueTicketResponseDto;
import aExercise.parkingLot.factories.ParkingSpotAssignmentStrategyFactory;
import aExercise.parkingLot.models.*;
import aExercise.parkingLot.repositories.GateRepository;
import aExercise.parkingLot.repositories.VehicleRepository;
import aExercise.parkingLot.services.TicketService;
import aExercise.parkingLot.strategies.ParkingSpotAssignmentStrategy;

public class ParkingLotApplication {

    private static TicketController ticketController;
    private static GateRepository gateRepository;
    private static VehicleRepository vehicleRepository;

    public static void main(String[] args) {
        init();

        IssueTicketRequestDto requestDto = new IssueTicketRequestDto();
        requestDto.setGateId(123L);
        requestDto.setOperatorId(345L);
        requestDto.setOwnerName("Shivam");
        requestDto.setVehicleNumber("KA01X1111");

        IssueTicketResponseDto responseDto = ticketController.issueTicket(requestDto);
        Ticket ticket = responseDto.getTicket();

        if (ticket != null) {
            System.out.println("Ticket issued with ID: " + ticket.getId());
            System.out.println("Vehicle Number: " + ticket.getVehicle().getNumber());
            System.out.println("Assigned Gate ID: " + ticket.getGate().getId());
        } else {
            System.out.println("Ticket issue failed.");
        }
    }

    private static void init() {
        // Initialize repositories
        gateRepository = new GateRepository();
        vehicleRepository = new VehicleRepository();

        // Create Operator
        Operator operator = new Operator();
        operator.setId(345L);
        operator.setName("Ramesh");

        // Create ParkingLot
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(1L);
        parkingLot.setName("Mall Parking");

        // Create Gate and set operator + parking lot
        Gate gate = new Gate();
        gate.setId(123L);
        gate.setOperator(operator);
        gate.setParkingLot(parkingLot);

        // Check if gate already exists in the repository
        Gate existingGate = gateRepository.findByGateId(123L).orElse(null);

        // Only add if not already present
        if (existingGate == null) {
            gateRepository.addGate(gate);
        }

        // Create strategy
        ParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy =
                ParkingSpotAssignmentStrategyFactory.getParkingLotStrategy(ParkingSpotStrategyType.NEAREST);

        // Create service and controller
        TicketService ticketService = new TicketService(gateRepository, vehicleRepository, parkingSpotAssignmentStrategy);
        ticketController = new TicketController(ticketService);
    }
}
