package aMachineCoding.elevatorSystem.factories;

import aMachineCoding.elevatorSystem.models.enums.SchedulingStrategy;
import aMachineCoding.elevatorSystem.strategies.ElevatorScheduler;
import aMachineCoding.elevatorSystem.strategies.scheduler.FCFSScheduler;
import aMachineCoding.elevatorSystem.strategies.scheduler.LOOKScheduler;
import aMachineCoding.elevatorSystem.strategies.scheduler.SCANScheduler;
import aMachineCoding.elevatorSystem.strategies.scheduler.SSTFScheduler;

public class SchedulerFactory {

    public static ElevatorScheduler getScheduler(SchedulingStrategy type) {
        return switch (type) {
            case FIRST_COME_FIRST_SERVE -> new FCFSScheduler();
            case SHORTEST_SEEK_TIME_FIRST -> new SSTFScheduler();
            case LOOK -> new LOOKScheduler();
            case SCAN -> new SCANScheduler();
        };
    }
}
