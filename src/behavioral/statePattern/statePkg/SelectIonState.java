package behavioral.statePattern.statePkg;


import behavioral.statePattern.vendingMachinePkg.Item;
import behavioral.statePattern.vendingMachinePkg.VendingMachine;

public class SelectIonState implements State {

    @Override
    public void insertCoin(VendingMachine vendingMachine, int amount) {
        System.out.println("Enough money inserted. Dispense the item or cancel the transaction.");
    }

    @Override
    public void selectItem(VendingMachine vendingMachine, String itemCode) {
        System.out.println("An item is already selected. Dispense the item or cancel the transaction.");
    }

    @Override
    public void dispenseItem(VendingMachine vendingMachine) {
        Item product = vendingMachine.getCurrentProduct();
        System.out.println("Dispensing " + product.getType() + ".");
        vendingMachine.dispenseCurrentProduct();
        vendingMachine.resetBalance();

        if (vendingMachine.isEmpty()) {
            System.out.println("The vending machine is out of stock.");
            vendingMachine.changeState(new IdleState());
        } else {
            vendingMachine.changeState(new IdleState());
        }
    }

    @Override
    public void cancelTransaction(VendingMachine vendingMachine) {
        vendingMachine.refund();
        vendingMachine.changeState(new IdleState());
    }
}
