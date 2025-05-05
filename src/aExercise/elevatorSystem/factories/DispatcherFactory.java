package aExercise.elevatorSystem.factories;

import aExercise.elevatorSystem.enums.DispatchStrategyType;
import aExercise.elevatorSystem.strategies.*;

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

