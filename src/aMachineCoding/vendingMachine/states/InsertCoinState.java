package aMachineCoding.vendingMachine.states;

import aMachineCoding.vendingMachine.exceptions.InsufficientBalanceException;
import aMachineCoding.vendingMachine.exceptions.InvalidOperationException;
import aMachineCoding.vendingMachine.models.Item;
import aMachineCoding.vendingMachine.models.VendingMachine;

public class InsertCoinState implements State {
    private final VendingMachine vendingMachine;

    public InsertCoinState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int amount) throws Exception {
        vendingMachine.addCoin(amount);
        System.out.println(amount + " coin inserted. Current balance: " + vendingMachine.getBalance());
    }

    @Override
    public void selectItem(String itemCode) throws Exception {

        vendingMachine.changeState(vendingMachine.getSelectionState());

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
    public void dispenseItem() throws InvalidOperationException {
        throw new InvalidOperationException("Select an item first before dispensing.");
    }

    @Override
    public void cancelTransaction() throws Exception {
        vendingMachine.refund();
        vendingMachine.changeState(vendingMachine.getIdleState());
    }
}
