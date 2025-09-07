package aMachineCoding.vendingMachine.states;

import aMachineCoding.vendingMachine.exceptions.InsufficientBalanceException;
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
        Item item = vendingMachine.getInventory().findItemByCode(itemCode);
        if (item == null) {
            throw new InvalidOperationException("Invalid item code.");
        }
        if (!item.isAvailable()) {
            throw new InvalidOperationException("Item out of stock.");
        }
        if (vendingMachine.getBalance() < item.getPrice()) {
            throw new InsufficientBalanceException("Insert more coins to purchase this item.");
        }
        vendingMachine.setCurrentItem(item);
        System.out.println(item.getType() + " selected.");
    }

    @Override
    public void dispenseItem() throws Exception {
        Item item = vendingMachine.getCurrentItem();
        if (item != null) {
            vendingMachine.changeState(vendingMachine.getDispenseState());
            vendingMachine.dispenseItem();
        } else {
            throw new InvalidOperationException("No item selected to dispense.");
        }
    }

    @Override
    public void cancelTransaction() throws Exception {
        vendingMachine.refund();
        vendingMachine.resetSelectedProduct();
        vendingMachine.changeState(vendingMachine.getIdleState());
    }
}
