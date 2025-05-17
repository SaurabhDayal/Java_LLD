package aMachineCoding.vendingMachine.states;

import aMachineCoding.vendingMachine.exceptions.InvalidOperationException;
import aMachineCoding.vendingMachine.exceptions.NoTransactionException;
import aMachineCoding.vendingMachine.models.VendingMachine;

public class IdleState implements State {
    private final VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int amount) throws Exception {
        vendingMachine.changeState(vendingMachine.getInsertCoinState());
        vendingMachine.insertCoin(amount);
    }

    @Override
    public void selectItem(String itemCode) throws InvalidOperationException {
        throw new InvalidOperationException("Insert coins first before selecting an item.");
    }

    @Override
    public void dispenseItem() throws InvalidOperationException {
        throw new InvalidOperationException("You need to insert coins and select an item first.");
    }

    @Override
    public void cancelTransaction() throws NoTransactionException {
        throw new NoTransactionException();
    }
}
