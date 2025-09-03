package aMachineCoding.elevatorSystem.models.buttons;

import aMachineCoding.elevatorSystem.models.enums.FloorNumber;

public class ElevatorButton implements Button {

    private boolean isPressed;
    private final FloorNumber floorNumber;

    public ElevatorButton(FloorNumber floorNumber) {
        this.floorNumber = floorNumber;
        this.isPressed = false;
    }

    public FloorNumber getFloorNumber() {
        return floorNumber;
    }

    public void reset() {
        isPressed = false;
    }

    @Override
    public boolean isPressed() {
        return isPressed;
    }

    @Override
    public void press() {
        if (!isPressed) {
            isPressed = true;
        }
    }
}
