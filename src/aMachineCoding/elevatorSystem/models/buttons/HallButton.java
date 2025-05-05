package aMachineCoding.elevatorSystem.models.buttons;

import aMachineCoding.elevatorSystem.enums.Direction;

public class HallButton implements Button {
    private boolean isPressed;
    private Direction direction;

    public HallButton(boolean isPressed, Direction direction) {
        this.isPressed = isPressed;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
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
