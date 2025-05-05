package behavioral.statePattern.statePkg;


import behavioral.statePattern.vendingMachinePkg.Item;
import behavioral.statePattern.vendingMachinePkg.VendingMachine;

public class InsertCoinState implements State {

    @Override
    public void insertCoin(VendingMachine vendingMachine, int amount) {
        vendingMachine.addCoin(amount);
        System.out.println(amount + " coin inserted. Current balance: " + vendingMachine.getBalance());
    }

    @Override
    public void selectItem(VendingMachine vendingMachine, String itemCode) {
        Item item = vendingMachine.inventory.findItemByCode(itemCode);
        if (item != null && item.isAvailable() && vendingMachine.getBalance() >= item.getPrice()) {
            vendingMachine.setCurrentProduct(item);
            System.out.println(item.getType() + " selected.");
            vendingMachine.changeState(new SelectIonState());
        } else {
            System.out.println("Not enough money or item out of stock.");
        }
    }

    @Override
    public void dispenseItem(VendingMachine vendingMachine) {
        System.out.println("Select an item first.");
    }

    @Override
    public void cancelTransaction(VendingMachine vendingMachine) {
        vendingMachine.refund();
        vendingMachine.changeState(new IdleState());
    }
}
