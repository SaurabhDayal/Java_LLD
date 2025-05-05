package aMachineCoding.vendingMachine.states;

import aMachineCoding.vendingMachine.exceptions.InvalidOperationException;
import aMachineCoding.vendingMachine.models.Item;
import aMachineCoding.vendingMachine.models.VendingMachine;

public class SelectionState implements State {
    private final VendingMachine vendingMachine;

    public SelectionState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int amount) throws InvalidOperationException {
        throw new InvalidOperationException("Enough money inserted. Dispense the item or cancel the transaction.");
    }

    @Override
    public void selectItem(String itemCode) throws InvalidOperationException {
        throw new InvalidOperationException("An item is already selected. Dispense the item or cancel the transaction.");
    }

    @Override
    public void dispenseItem() throws Exception {
        Item item = vendingMachine.getCurrentItem();
        if (item != null) {
            System.out.println("Dispensing: " + item.getType());
            vendingMachine.dispenseCurrentProduct();
            vendingMachine.resetBalance();
            vendingMachine.resetSelectedProduct();
        } else {
            System.out.println("No item selected to dispense.");
        }
        if (vendingMachine.isEmpty()) {
            System.out.println("The vending machine is out of stock.");
        }
        vendingMachine.changeState(vendingMachine.getIdleState());
    }

    @Override
    public void cancelTransaction() throws Exception {
        vendingMachine.refund();
        vendingMachine.changeState(vendingMachine.getIdleState());
    }
}
