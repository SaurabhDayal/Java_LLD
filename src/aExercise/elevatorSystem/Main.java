package aExercise.elevatorSystem;

import aExercise.elevatorSystem.enums.Direction;
import aExercise.elevatorSystem.enums.DispatchStrategyType;
import aExercise.elevatorSystem.enums.ElevatorID;
import aExercise.elevatorSystem.enums.FloorNumber;
import aExercise.elevatorSystem.factories.DispatcherFactory;
import aExercise.elevatorSystem.listeners.FloorReachedListener;
import aExercise.elevatorSystem.models.Elevator;
import aExercise.elevatorSystem.models.ElevatorSystem;
import aExercise.elevatorSystem.models.Request;
import aExercise.elevatorSystem.strategies.ElevatorDispatcher;

/*
    Elevator Class Diagram

    Design a class diagram for an elevator system in a multi-story building,
    addressing the core functionalities and interactions among different
    components of the system.

    Requirements:

    Multiple Elevators Support:
        The system should support multiple elevators.

    Floor Navigation:
        Ability to move to different floors and maintain the current floor state.

    Request Handling:
        Process external requests (floor button presses) and internal requests
        (buttons inside the elevator).

    Direction and Status Indicators:
        Display current direction (up or down) and status (moving, idle, maintenance).

    Queue Management:
        Manage a queue of floor requests and optimize the order of execution.

    Overload Protection:
        Prevent operation if the elevator is overloaded.

    Emergency Handling:
        Implement emergency features like alarm buttons and emergency stop functionality.

    Scheduled Maintenance Mode:
        Enable/disable elevators for scheduled maintenance.

    Energy Efficiency Mode:
        Optimize the operation for energy efficiency during low usage periods.

    User Interface:
        Provide a user interface inside the elevator for floor selection and
        information display.
*/

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ElevatorSystem elevatorSystem = init();
        simulateElevatorSystem(elevatorSystem);
        // TODO: Change the code to get trigger at pushing buttons
    }

    private static ElevatorSystem init() {
        DispatchStrategyType strategyType = DispatchStrategyType.LOOK;
        ElevatorDispatcher dispatcher = DispatcherFactory.getStrategy(strategyType);
        ElevatorSystem system = new ElevatorSystem(dispatcher);
        system.startElevators();
        registerListener(system);  // Register the listener here
        return system;
    }

    private static void registerListener(ElevatorSystem elevatorSystem) {
        elevatorSystem.setFloorReachedListener(new FloorReachedListener() {
            @Override
            public void onFloorReached(Elevator elevator, FloorNumber floor) {
                // Internal request when Elevator 1 reaches Floor 3
                if (elevator.getId().equals(ElevatorID.A) && floor == FloorNumber.F_3) {
                    System.out.printf("\n[Triggered] Passenger entered Elevator %s at Floor 3. Requesting Floor 5\n", elevator.getId());
                    elevator.addRequest(FloorNumber.F_5); // Only add request after reaching Floor 3
                }

                // Internal request when Elevator 2 reaches Floor 4
                if (elevator.getId().equals(ElevatorID.B) && floor == FloorNumber.F_4) {
                    System.out.printf("\n[Triggered] Passenger entered Elevator %s at Floor 4. Requesting Floor 1\n", elevator.getId());
                    elevator.addRequest(FloorNumber.F_1); // Only add request after reaching Floor 4
                }
            }
        });
    }

    private static void simulateElevatorSystem(ElevatorSystem elevatorSystem) throws InterruptedException {
        System.out.println("\n[Action 1] External Request: Floor 3, Direction: UP");
        elevatorSystem.handleRequest(new Request(FloorNumber.F_3, Direction.UP));
        printElevatorStatus(elevatorSystem);
        Thread.sleep(2000);

        // Removed premature internal request for Floor 5.
        // It will be handled inside the listener when Elevator A reaches Floor 3.

        System.out.println("\n[Action 2] External Request: Floor 4, Direction: UP");
        elevatorSystem.handleRequest(new Request(FloorNumber.F_4, Direction.UP));
        printElevatorStatus(elevatorSystem);
        Thread.sleep(2000);

        System.out.println("\n[Action 3] External Request: Floor 6, Direction: DOWN");
        elevatorSystem.handleRequest(new Request(FloorNumber.F_6, Direction.DOWN));
        printElevatorStatus(elevatorSystem);
        Thread.sleep(3000);

        // Removed premature internal request for Floor 1.
        // It will be handled inside the listener when Elevator B reaches Floor 4.

        System.out.println("\n[Action 4] External Request: Floor 2, Direction: UP");
        elevatorSystem.handleRequest(new Request(FloorNumber.F_2, Direction.UP));
        printElevatorStatus(elevatorSystem);
        Thread.sleep(3000);

        System.out.println("\n[Action 5] External Request: Floor 9, Direction: DOWN");
        elevatorSystem.handleRequest(new Request(FloorNumber.F_9, Direction.DOWN));
        printElevatorStatus(elevatorSystem);
        Thread.sleep(3000);

        System.out.println("\n[Action 6] External Request: Floor 0, Direction: UP");
        elevatorSystem.handleRequest(new Request(FloorNumber.F_0, Direction.UP));
        printElevatorStatus(elevatorSystem);
        Thread.sleep(6000);

        // ---- STOP ALL ELEVATOR THREADS ----
        elevatorSystem.stopElevators();

        printElevatorStatus(elevatorSystem);
    }

    private static void printElevatorStatus(ElevatorSystem elevatorSystem) {
        System.out.println("\n------------------------ Elevator System Status ------------------------");
        elevatorSystem.getElevators().forEach(elevator -> {
            System.out.printf("  [Elevator %s] Floor: %s | Status: %s | Direction: %s | Pending Requests: %s%n",
                    elevator.getId(),
                    elevator.getCurrentFloor(),
                    elevator.getStatus(),
                    elevator.getDirection(),
                    elevator.getRequests());
        });
        System.out.println("------------------------------------------------------------------------");
    }
}
