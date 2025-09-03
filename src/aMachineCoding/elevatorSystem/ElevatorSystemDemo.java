package aMachineCoding.elevatorSystem;

import aMachineCoding.elevatorSystem.factories.DispatcherFactory;
import aMachineCoding.elevatorSystem.listeners.FloorReachedListener;
import aMachineCoding.elevatorSystem.models.Elevator;
import aMachineCoding.elevatorSystem.models.ElevatorSystem;
import aMachineCoding.elevatorSystem.models.Floor;
import aMachineCoding.elevatorSystem.models.enums.DispatchStrategy;
import aMachineCoding.elevatorSystem.models.enums.ElevatorID;
import aMachineCoding.elevatorSystem.models.enums.FloorNumber;
import aMachineCoding.elevatorSystem.models.enums.SchedulingStrategy;
import aMachineCoding.elevatorSystem.strategies.ElevatorDispatcher;

public class ElevatorSystemDemo {

    private static ElevatorSystem init() {
        DispatchStrategy dispatchType = DispatchStrategy.NEAREST_CAR; // system-level dispatcher
        SchedulingStrategy schedulerType = SchedulingStrategy.LOOK;   // elevator-level scheduler

        ElevatorDispatcher dispatcher = DispatcherFactory.getStrategy(dispatchType);
        ElevatorSystem system = new ElevatorSystem(dispatcher, schedulerType);
        system.startElevators();
        registerListener(system);  // Keep listener for dynamic internal requests
        return system;
    }

    private static void registerListener(ElevatorSystem elevatorSystem) {
        elevatorSystem.setFloorReachedListener(new FloorReachedListener() {
            @Override
            public void onFloorReached(Elevator elevator, FloorNumber floor) {
                // Internal request when Elevator A reaches Floor 3
                if (elevator.getId().equals(ElevatorID.A) && floor == FloorNumber.F_3) {
                    System.out.printf("[Triggered] Passenger entered Elevator %s at Floor 3. Requesting Floor 5\n", elevator.getId());
                    elevator.getInsidePanel().pressFloorButton(FloorNumber.F_5.getFloorValue());
                }

                // Internal request when Elevator B reaches Floor 4
                if (elevator.getId().equals(ElevatorID.B) && floor == FloorNumber.F_4) {
                    System.out.printf("[Triggered] Passenger entered Elevator %s at Floor 4. Requesting Floor 1\n", elevator.getId());
                    elevator.getInsidePanel().pressFloorButton(FloorNumber.F_1.getFloorValue());
                }
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {
        ElevatorSystem elevatorSystem = init();
        simulateElevatorSystem(elevatorSystem);
    }

    private static void simulateElevatorSystem(ElevatorSystem system) throws InterruptedException {
        // Create Floor objects
        Floor floor3 = new Floor(FloorNumber.F_3);
        Floor floor4 = new Floor(FloorNumber.F_4);
        Floor floor6 = new Floor(FloorNumber.F_6);
        Floor floor2 = new Floor(FloorNumber.F_2);
        Floor floor9 = new Floor(FloorNumber.F_9);
        Floor floor0 = new Floor(FloorNumber.GROUND);

        // ---- Simulate hall button presses ----
        System.out.println("\n[Action 1] Floor 3 UP button pressed");
        floor3.pressUp(system);
        Thread.sleep(2000);
        printElevatorStatus(system);  // <-- print status

        System.out.println("\n[Action 2] Floor 4 UP button pressed");
        floor4.pressUp(system);
        Thread.sleep(2000);
        printElevatorStatus(system);

        System.out.println("\n[Action 3] Floor 6 DOWN button pressed");
        floor6.pressDown(system);
        Thread.sleep(3000);
        printElevatorStatus(system);

        System.out.println("\n[Action 4] Floor 2 UP button pressed");
        floor2.pressUp(system);
        Thread.sleep(3000);
        printElevatorStatus(system);

        System.out.println("\n[Action 5] Floor 9 DOWN button pressed");
        floor9.pressDown(system);
        Thread.sleep(3000);
        printElevatorStatus(system);

        System.out.println("\n[Action 6] Floor 0 UP button pressed");
        floor0.pressUp(system);
        Thread.sleep(6000);
        printElevatorStatus(system);

        // ---- STOP ALL ELEVATOR THREADS ----
        system.stopElevators();
        printElevatorStatus(system);
    }

    private static void printElevatorStatus(ElevatorSystem system) {
        System.out.println("------------------------ Elevator System Status ------------------------");
        system.getElevators().forEach(elevator -> {
            System.out.printf("  [Elevator %s] Floor: %s | Status: %s | Direction: %s | Pending Requests: %s%n",
                    elevator.getId(),
                    elevator.getCurrentFloor(),
                    elevator.getStatus(),
                    elevator.getDirection(),
                    elevator.getPendingRequestCount());
        });
        System.out.println("------------------------------------------------------------------------");
    }
}
