package aMachineCoding.elevatorSystem.models.buttons;

public interface Button {
    boolean isPressed();

    void press();  // Changed from boolean return to void
}
