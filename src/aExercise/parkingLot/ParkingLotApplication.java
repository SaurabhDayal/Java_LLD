package aExercise.parkingLot;

import aExercise.parkingLot.controllers.BillController;
import aExercise.parkingLot.controllers.TicketController;
import aExercise.parkingLot.dtos.IssueBillRequestDto;
import aExercise.parkingLot.dtos.IssueBillResponseDto;
import aExercise.parkingLot.dtos.IssueTicketRequestDto;
import aExercise.parkingLot.dtos.IssueTicketResponseDto;
import aExercise.parkingLot.factories.ParkingSpotAssignmentStrategyFactory;
import aExercise.parkingLot.models.*;
import aExercise.parkingLot.repositories.*;
import aExercise.parkingLot.services.BillService;
import aExercise.parkingLot.services.TicketService;
import aExercise.parkingLot.strategies.ParkingSpotAssignmentStrategy;

import java.util.ArrayList;
import java.util.List;

/*
In Memory Database using repo layer to store data
 */
public class ParkingLotApplication {

    private static TicketController ticketController;
    private static BillController billController;

    private static VehicleRepository vehicleRepository;
    private static GateRepository gateRepository;
    private static TicketRepository ticketRepository;
    private static BillRepository billRepository;
    private static OperatorRepository operatorRepository;

    public static void main(String[] args) {
        init();

        // Car Entry Simulation
        IssueTicketRequestDto ticketRequestDto = new IssueTicketRequestDto();
        ticketRequestDto.setGateId(1L);
        ticketRequestDto.setOperatorId(1L);
        ticketRequestDto.setOwnerName("Shivam");
        ticketRequestDto.setVehicleNumber("KA01X1111");
//        ticketRequestDto.setVehicleType(String.valueOf(VehicleType.SUV)); // todo: change to SEDAN for working case
        ticketRequestDto.setVehicleType(String.valueOf(VehicleType.SEDAN));

        IssueTicketResponseDto ticketResponseDto = ticketController.issueTicket(ticketRequestDto);
        Ticket ticket = ticketResponseDto.getTicket();

        if (ticket != null) {
            System.out.println("Ticket issued:");
            System.out.println("- Ticket ID: " + ticket.getId());
            System.out.println("- Vehicle Number: " + ticket.getVehicle().getNumber());
            System.out.println("- Gate ID: " + ticket.getGate().getId());
            System.out.println("- Operator: " + ticket.getOperator().getName());  // Corrected: Print ticket operator name
            System.out.println("- Entry Time: " + ticket.getEntryTime());
            System.out.println("- ParkingSpot: " + ticket.getParkingSpot().getSpotNumber());
        } else {
            System.out.println("Ticket issuing failed.");
            return;
        }

        try {
            Thread.sleep(1000); // Simulating a delay for car exit
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Car Exit Simulation
        IssueBillRequestDto billRequestDto = new IssueBillRequestDto();
        billRequestDto.setTicketId(ticket.getId());
        billRequestDto.setAmount(100);
        billRequestDto.setPaymentMode(String.valueOf(PaymentMode.UPI));
        billRequestDto.setPaymentStatus(String.valueOf(PaymentStatus.SUCCESS));
        billRequestDto.setReferenceNumber("TXN-UPI-98765");
        billRequestDto.setGateId(2L);
        billRequestDto.setOperatorId(2L);

        IssueBillResponseDto billResponseDto = billController.issueBill(billRequestDto);

        if (billResponseDto.getResponseStatus().name().equals("SUCCESS")) {
            Bill bill = billResponseDto.getBill();
            System.out.println("Bill issued:");
            System.out.println("- Bill ID: " + bill.getId());
            System.out.println("- Ticket ID: " + bill.getTicket().getId());
            System.out.println("- Amount: " + bill.getAmount());
            System.out.println("- Exit Time: " + bill.getExitTime());
            System.out.println("- Gate ID: " + bill.getGate().getId()); // Corrected: Print bill gate ID
            System.out.println("- Operator: " + bill.getOperator().getName());  // Corrected: Print bill operator name
        } else {
            System.out.println("Bill issuing failed.");
        }
    }

    private static void init() {
        vehicleRepository = new VehicleRepository();
        gateRepository = new GateRepository();
        ticketRepository = new TicketRepository();
        billRepository = new BillRepository();
        operatorRepository = new OperatorRepository();

        // Save operator
        Operator entryOperator = new Operator();
        entryOperator.setName("Ramesh");
        entryOperator.setPhoneNumber("9345234432");
        entryOperator.setEmpId(55);
        entryOperator = operatorRepository.save(entryOperator);

        // Create parking floors with parking spots
        List<ParkingFloor> floors = new ArrayList<>();
        int numberOfFloors = 2;
        int spotsPerFloor = 5;

        for (int i = 1; i <= numberOfFloors; i++) {
            ParkingFloor floor = new ParkingFloor();
            floor.setFloorNumber(i);
            floor.setParkingFloorStatus(ParkingFloorStatus.OPEN);

            List<ParkingSpot> spots = new ArrayList<>();
            for (int j = 1; j <= spotsPerFloor; j++) {
                ParkingSpot spot = new ParkingSpot();
                spot.setSpotNumber(j);
                spot.setParkingSpotStatus(ParkingSpotStatus.EMPTY);
                spot.setSupportedVehicleTypes(List.of(VehicleType.SEDAN, VehicleType.TWO_WHEELER)); // Assuming all support both

                spot.setParkingFloor(floor); // link back
                spots.add(spot);
            }

            floor.setParkingSpots(spots);
            floors.add(floor);
        }

        // Save parking lot
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("Mall Parking");
        parkingLot.setParkingLotStatus(ParkingLotStatus.OPEN);
        parkingLot.setParkingFloors(floors);
        parkingLot.setSupportedVehicleTypes(List.of(VehicleType.SEDAN, VehicleType.TWO_WHEELER)); // Supported vehicle types

        // Save entryGate with linked operator and parking lot
        Gate entryGate = new Gate();
        entryGate.setOperator(entryOperator);
        entryGate.setParkingLot(parkingLot);
        entryGate.setGateType(GateType.ENTRY);
        entryGate = gateRepository.save(entryGate);  // Assigned ID automatically

        Operator exitOperator = new Operator();
        exitOperator.setName("Mrinal");
        exitOperator.setPhoneNumber("0000034432");
        exitOperator.setEmpId(12);
        exitOperator = operatorRepository.save(exitOperator);

        Gate exitGate = new Gate();
        exitGate.setOperator(exitOperator);
        exitGate.setParkingLot(parkingLot);
        exitGate.setGateType(GateType.EXIT);
        exitGate = gateRepository.save(exitGate);  // Assigned ID automatically

        // Link entryGate back to lot
        parkingLot.setGates(List.of(entryGate, exitGate));

        // Parking strategy
        ParkingSpotAssignmentStrategy strategy = ParkingSpotAssignmentStrategyFactory.getParkingLotStrategy(ParkingSpotStrategyType.NEAREST);

        // Initialize services/controllers
        TicketService ticketService = new TicketService(gateRepository, vehicleRepository, strategy, ticketRepository, operatorRepository);
        BillService billService = new BillService(billRepository, ticketRepository, gateRepository, operatorRepository);
        ticketController = new TicketController(ticketService);
        billController = new BillController(billService);
    }
}
