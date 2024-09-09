package behavioral.statePattern.statePkg;


import behavioral.statePattern.vendingMachinePkg.VendingMachine;

public class DispenseState implements State {

    @Override
    public void insertCoin(VendingMachine vendingMachine, int amount) {
        System.out.println("Please wait, dispensing the product.");
    }

    @Override
    public void selectItem(VendingMachine vendingMachine, String itemCode) {
        System.out.println("Product is already being dispensed.");
    }

    @Override
    public void dispenseItem(VendingMachine vendingMachine) {
        System.out.println("Item is already being dispensed.");
    }

    @Override
    public void cancelTransaction(VendingMachine vendingMachine) {
        System.out.println("Cannot cancel, product is being dispensed.");
    }
}
