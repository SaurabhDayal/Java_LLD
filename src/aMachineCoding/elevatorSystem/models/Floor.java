package aMachineCoding.elevatorSystem.models;

import aMachineCoding.elevatorSystem.enums.FloorNumber;
import aMachineCoding.elevatorSystem.models.panels.OutsidePanel;

public class
Floor {
    private final FloorNumber floorNumber;
    private final OutsidePanel outsidePanel;

    public Floor(FloorNumber floorNumber) {
        this.floorNumber = floorNumber;
        this.outsidePanel = new OutsidePanel();
    }

    public FloorNumber getFloorNumber() {
        return floorNumber;
    }

    public OutsidePanel getOutsidePanel() {
        return outsidePanel;
    }
}
