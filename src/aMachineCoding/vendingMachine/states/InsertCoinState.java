package aMachineCoding.vendingMachine.states;

import aMachineCoding.vendingMachine.models.Item;
import aMachineCoding.vendingMachine.models.VendingMachine;

public class InsertCoinState implements State {
    private final VendingMachine vendingMachine;

    public InsertCoinState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int amount) {
        vendingMachine.addCoin(amount);
        System.out.println(amount + " coin inserted. Current balance: " + vendingMachine.getBalance());
    }

    @Override
    public void selectItem(String itemCode) {
        Item item = vendingMachine.inventory.findItemByCode(itemCode);
        if (item != null && item.isAvailable() && vendingMachine.getBalance() >= item.getPrice()) {
            vendingMachine.setCurrentProduct(item);
            System.out.println(item.getType() + " selected.");
            vendingMachine.changeState(vendingMachine.getSelectionState());
        } else {
            System.out.println("Not enough money or item out of stock.");
        }
    }

    @Override
    public void dispenseItem() {
        System.out.println("Select an item first.");
    }

    @Override
    public void cancelTransaction() {
        vendingMachine.refund();
        vendingMachine.changeState(vendingMachine.getIdleState());
    }
}
