package aMachineCoding.vendingMachine.states;

import aMachineCoding.vendingMachine.exceptions.InvalidOperationException;
import aMachineCoding.vendingMachine.models.Item;
import aMachineCoding.vendingMachine.models.VendingMachine;

public class DispenseState implements State {
    private final VendingMachine vendingMachine;

    public DispenseState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int amount) throws InvalidOperationException {
        throw new InvalidOperationException("You cannot insert coin while dispensing.");
    }

    @Override
    public void selectItem(String itemCode) throws InvalidOperationException {
        throw new InvalidOperationException("You cannot select item while dispensing.");
    }

    @Override
    public void dispenseItem() {
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
    public void cancelTransaction() throws InvalidOperationException {
        throw new InvalidOperationException("Cannot cancel. Product is already being dispensed.");
    }
}
