package aScalerModule_08_LLD_3.assign_03.elevatorSystem.factories;

import aScalerModule_08_LLD_3.assign_03.elevatorSystem.enums.DispatchStrategyType;
import aScalerModule_08_LLD_3.assign_03.elevatorSystem.strategies.*;

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

