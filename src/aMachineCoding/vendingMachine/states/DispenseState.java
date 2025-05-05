package aMachineCoding.vendingMachine.states;

import aMachineCoding.vendingMachine.models.Item;
import aMachineCoding.vendingMachine.models.VendingMachine;

public class DispenseState implements State {
    private final VendingMachine vendingMachine;

    public DispenseState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int amount) throws Exception {
        System.out.println("You cannot insert coin in DispenseState.");
    }

    @Override
    public void selectItem(String itemCode) throws Exception {
        System.out.println("You cannot select item in DispenseState.");
    }

    @Override
    public void dispenseItem() throws Exception {
        Item item = vendingMachine.getCurrentProduct();
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
        System.out.println("Cannot cancel, product is already being dispensed.");
    }
}
