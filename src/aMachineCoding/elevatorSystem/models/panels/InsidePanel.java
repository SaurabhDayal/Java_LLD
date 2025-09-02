package aMachineCoding.elevatorSystem.models.panels;

import aMachineCoding.elevatorSystem.models.Elevator;
import aMachineCoding.elevatorSystem.models.FloorNumber;
import aMachineCoding.elevatorSystem.models.buttons.ElevatorButton;

import java.util.ArrayList;
import java.util.List;

public class InsidePanel implements Panel {

    private final List<ElevatorButton> elevatorButtonList; // List of buttons for each floor
    private final Elevator elevator;                        // Reference to the parent elevator

    public InsidePanel(Elevator elevator) {
        this.elevator = elevator;                           // Attach panel to this elevator
        elevatorButtonList = new ArrayList<>();
        for (FloorNumber floor : FloorNumber.values()) {    // Create a button for each floor
            elevatorButtonList.add(new ElevatorButton(floor));
        }
    }

    public void pressFloorButton(int floorNumber) {
        // Validate floor number within elevator range
        if (floorNumber >= 0 && floorNumber < elevatorButtonList.size()) {
            ElevatorButton button = elevatorButtonList.get(floorNumber);
            if (!button.isPressed()) {                     // Only press if not already pressed
                button.press();                            // Set button pressed state
                elevator.addRequest(FloorNumber.values()[floorNumber]); // Add request to elevator
            }
        } else {
            System.out.println("Invalid floor number: " + floorNumber); // Handle invalid input
        }
    }

    public List<ElevatorButton> getElevatorButtonList() {
        return elevatorButtonList;                         // Return all buttons for reset/use
    }
}
