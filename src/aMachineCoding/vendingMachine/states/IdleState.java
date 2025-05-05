package aMachineCoding.vendingMachine.states;

import aMachineCoding.vendingMachine.models.VendingMachine;

public class IdleState implements State {
    private final VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int amount) {
        vendingMachine.addCoin(amount);
        System.out.println(amount + " coin inserted. Current balance: " + vendingMachine.getBalance());
        vendingMachine.changeState(vendingMachine.getInsertCoinState());
    }

    @Override
    public void selectItem(String itemCode) {
        System.out.println("Insert coins first before selecting an item.");
    }

    @Override
    public void dispenseItem() {
        System.out.println("You need to insert coins and select an item first.");
    }

    @Override
    public void cancelTransaction() {
        System.out.println("No transaction to cancel.");
    }
}
