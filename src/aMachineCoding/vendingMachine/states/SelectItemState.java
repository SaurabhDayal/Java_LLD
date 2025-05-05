package aMachineCoding.vendingMachine.states;

import aMachineCoding.vendingMachine.models.Item;
import aMachineCoding.vendingMachine.models.VendingMachine;

public class SelectItemState implements State {
    private final VendingMachine vendingMachine;

    public SelectItemState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int amount) throws Exception {
        System.out.println("Enough money inserted. Dispense the item or cancel the transaction.");
    }

    @Override
    public void selectItem(String itemCode) throws Exception {
        System.out.println("An item is already selected. Dispense the item or cancel the transaction.");
    }

    @Override
    public void dispenseItem() throws Exception {
        Item item = vendingMachine.getCurrentProduct();
        System.out.println("Dispensing " + item.getType() + ".");
        vendingMachine.dispenseCurrentProduct();
        vendingMachine.resetBalance();

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
