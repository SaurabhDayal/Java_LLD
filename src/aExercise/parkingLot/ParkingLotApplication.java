package aExercise.parkingLot;

import aExercise.parkingLot.controllers.TicketController;
import aExercise.parkingLot.dtos.IssueTicketRequestDto;
import aExercise.parkingLot.dtos.IssueTicketResponseDto;
import aExercise.parkingLot.factories.ParkingSpotAssignmentStrategyFactory;
import aExercise.parkingLot.models.ParkingSpotStrategyType;
import aExercise.parkingLot.models.Ticket;
import aExercise.parkingLot.repositories.GateRepository;
import aExercise.parkingLot.repositories.VehicleRepository;
import aExercise.parkingLot.services.TicketService;
import aExercise.parkingLot.strategies.ParkingSpotAssignmentStrategy;

public class ParkingLotApplication {
    public static void main(String[] args) {

        GateRepository gateRepository = new GateRepository();

        VehicleRepository vehicleRepository = new VehicleRepository();

        ParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy =
                ParkingSpotAssignmentStrategyFactory.getParkingLotStrategy(ParkingSpotStrategyType.NEAREST);

        TicketService ticketService = new TicketService(gateRepository, vehicleRepository, parkingSpotAssignmentStrategy);

        TicketController ticketController = new TicketController(ticketService);

        IssueTicketRequestDto requestDto = new IssueTicketRequestDto();
        requestDto.setGateId(123L);
        requestDto.setOperatorId(345L);
        requestDto.setOwnerName("Shivam");
        requestDto.setVehicleNumber("KA01X1111");

        IssueTicketResponseDto responseDto = ticketController.issueTicket(requestDto);
        Ticket ticket = responseDto.getTicket();
    }
}
