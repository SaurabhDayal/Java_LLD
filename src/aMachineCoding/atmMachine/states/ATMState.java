package aMachineCoding.atmMachine.states;

import aMachineCoding.atmMachine.models.ATMMachineContext;

public interface ATMState {
    String getStateName();

    ATMState next(ATMMachineContext context);
}
