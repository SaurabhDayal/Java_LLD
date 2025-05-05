package aMachineCoding.vendingMachine.exceptions;

public class NoTransactionException extends VendingMachineException {
    public NoTransactionException() {
        super("No transaction in progress.");
    }
}
