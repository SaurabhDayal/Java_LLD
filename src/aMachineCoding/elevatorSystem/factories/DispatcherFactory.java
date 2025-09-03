package aMachineCoding.elevatorSystem.factories;

import aMachineCoding.elevatorSystem.models.enums.DispatchStrategy;
import aMachineCoding.elevatorSystem.strategies.ElevatorDispatcher;
import aMachineCoding.elevatorSystem.strategies.dispatcher.IdleFirstDispatcher;
import aMachineCoding.elevatorSystem.strategies.dispatcher.LoadBalancingDispatcher;
import aMachineCoding.elevatorSystem.strategies.dispatcher.NearestCarDispatcher;

public class DispatcherFactory {

    public static ElevatorDispatcher getStrategy(DispatchStrategy type) {
        return switch (type) {
            case IDLE_FIRST -> new IdleFirstDispatcher();
            case LOAD_BALANCING -> new LoadBalancingDispatcher();
            case NEAREST_CAR -> new NearestCarDispatcher();
        };
    }
}
