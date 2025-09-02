package aMachineCoding.elevatorSystem.models.buttons;

import aMachineCoding.elevatorSystem.models.FloorNumber;

public class ElevatorButton implements Button {
    private boolean isPressed;
    private FloorNumber floorNumber;

    public ElevatorButton(FloorNumber floorNumber) {
        this.floorNumber = floorNumber;
        this.isPressed = false;
    }

    public FloorNumber getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(FloorNumber floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void reset() {
        isPressed = false;
    }

    public void setStatus(boolean isPressed) {
        this.isPressed = isPressed;
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
