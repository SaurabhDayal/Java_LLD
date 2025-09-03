package aMachineCoding.elevatorSystem.models.panels;

import aMachineCoding.elevatorSystem.models.ElevatorSystem;
import aMachineCoding.elevatorSystem.models.Floor;
import aMachineCoding.elevatorSystem.models.Request;
import aMachineCoding.elevatorSystem.models.buttons.HallButton;
import aMachineCoding.elevatorSystem.models.enums.Direction;

public class OutsidePanel implements Panel {

    private final HallButton upButton;
    private final HallButton downButton;

    public OutsidePanel() {
        this.upButton = new HallButton(false, Direction.UP);
        this.downButton = new HallButton(false, Direction.DOWN);
    }

    // Programmatically press hall buttons and trigger system request
    public void pressUpButton(Floor floor, ElevatorSystem system) {
        if (!upButton.isPressed()) {
            upButton.press();
            system.handleRequest(new Request(floor.getFloorNumber(), Direction.UP));
        }
    }

    public void pressDownButton(Floor floor, ElevatorSystem system) {
        if (!downButton.isPressed()) {
            downButton.press();
            system.handleRequest(new Request(floor.getFloorNumber(), Direction.DOWN));
        }
    }
}
