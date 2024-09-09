package behavioral.statePattern.statePkg;


import behavioral.statePattern.vendingMachinePkg.VendingMachine;

public class IdleState implements State {

    @Override
    public void insertCoin(VendingMachine vendingMachine, int amount) {
        vendingMachine.addCoin(amount);
        System.out.println(amount + " coin inserted. Current balance: " + vendingMachine.getBalance());
        vendingMachine.changeState(new InsertCoinState());
    }

    @Override
    public void selectItem(VendingMachine vendingMachine, String itemCode) {
        System.out.println("Insert coins first before selecting an item.");
    }

    @Override
    public void dispenseItem(VendingMachine vendingMachine) {
        System.out.println("You need to insert coins and select an item first.");
    }

    @Override
    public void cancelTransaction(VendingMachine vendingMachine) {
        System.out.println("No transaction to cancel.");
    }
}



