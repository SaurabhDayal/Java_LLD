package behavioral.statePattern.statePkg;

import behavioral.statePattern.vendingMachinePkg.VendingMachine;

public interface State {
    void insertCoin(VendingMachine vendingMachine, int amount);

    void selectItem(VendingMachine vendingMachine, String itemCode);

    void dispenseItem(VendingMachine vendingMachine);

    void cancelTransaction(VendingMachine vendingMachine);
}