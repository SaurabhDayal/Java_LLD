package aScalerModule_08_LLD_3.assign_03.elevatorSystem.models;

import aScalerModule_08_LLD_3.assign_03.elevatorSystem.enums.FloorNumber;
import aScalerModule_08_LLD_3.assign_03.elevatorSystem.models.panels.OutsidePanel;

public class Floor {
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
