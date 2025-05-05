package aMachineCoding.elevatorSystem.factories;

import aMachineCoding.elevatorSystem.enums.DispatchStrategyType;
import aMachineCoding.elevatorSystem.strategies.*;

public class DispatcherFactory {

    public static ElevatorDispatcher getStrategy(DispatchStrategyType type) {
        return switch (type) {
            case FIRST_COME_FIRST_SERVE -> new FCFSDispatcher();
            case SHORTEST_SEEK_TIME_FIRST -> new SSTFDispatcher();
            case SCAN -> new SCANDispatcher();
            case LOOK -> new LOOKDispatcher();
        };
    }
}

