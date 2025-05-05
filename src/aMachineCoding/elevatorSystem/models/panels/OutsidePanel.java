package aMachineCoding.elevatorSystem.models.panels;

import aMachineCoding.elevatorSystem.enums.Direction;
import aMachineCoding.elevatorSystem.models.buttons.HallButton;

public class OutsidePanel implements Panel {
    private HallButton upButton;    // Button to request upward movement
    private HallButton downButton;  // Button to request downward movement

    public OutsidePanel() {
        upButton = new HallButton(false, Direction.UP);
        downButton = new HallButton(false, Direction.DOWN);
    }

    public HallButton getUpButton() {
        return upButton;
    }

    public HallButton getDownButton() {
        return downButton;
    }

    public void pressUpButton() {
        upButton.press();  // Just presses the button without returning anything
    }

    public void pressDownButton() {
        downButton.press();  // Same here
    }
}
