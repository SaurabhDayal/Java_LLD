package aExercise.elevatorSystem.models.panels;

import aExercise.elevatorSystem.enums.FloorNumber;
import aExercise.elevatorSystem.models.buttons.ElevatorButton;

import java.util.ArrayList;
import java.util.List;

public class InsidePanel implements Panel {
    private List<ElevatorButton> elevatorButtonList;

    public InsidePanel() {
        elevatorButtonList = new ArrayList<>();
        for (FloorNumber floor : FloorNumber.values()) {
            elevatorButtonList.add(new ElevatorButton(floor));
        }
    }

    public void pressFloorButton(int floorNumber) {
        if (floorNumber >= 0 && floorNumber < elevatorButtonList.size()) {
            elevatorButtonList.get(floorNumber).press();
            // TODO: add request to dispatcher
        } else {
            System.out.println("Invalid floor number: " + floorNumber);
        }
    }

    public List<ElevatorButton> getElevatorButtonList() {
        return elevatorButtonList;
    }
}
