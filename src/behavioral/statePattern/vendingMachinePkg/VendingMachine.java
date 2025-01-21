package behavioral.statePattern.vendingMachinePkg;


import behavioral.statePattern.statePkg.IdleState;
import behavioral.statePattern.statePkg.State;

public class VendingMachine {
    
    private State currentState;
    private Item currentProduct;
    private int balance;
    public Inventory inventory;

    public VendingMachine() {
        inventory = new Inventory();
        currentState = new IdleState();  // Initial state
    }

    public void addCoin(int amount) {
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }

    public int getRequiredAmount() {
        return (currentProduct == null) ? 0 : currentProduct.getPrice();
    }

    public void resetBalance() {
        balance = 0;
    }

    public void changeState(State newState) {
        currentState = newState;
    }

    public void insertCoin(int amount) {
        currentState.insertCoin(this, amount);
    }

    public void selectItem(String itemCode) {
        currentState.selectItem(this, itemCode);
    }

    public void dispenseItem() {
        currentState.dispenseItem(this);
    }

    public void cancelTransaction() {
        currentState.cancelTransaction(this);
    }

    public boolean isProductAvailable(String itemCode) {
        return inventory.isProductAvailable(itemCode);
    }

    public void setCurrentProduct(Item product) {
        this.currentProduct = product;
    }

    public Item getCurrentProduct() {
        return currentProduct;
    }

    public void dispenseCurrentProduct() {
        if (currentProduct != null) {
            inventory.reduceQuantity(currentProduct.getItemCode(), 1);
        }
    }

    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    public void refund() {
        System.out.println("Transaction cancelled. Returning balance: " + balance);
        resetBalance();
    }

    public void displayInventory() {
        inventory.displayInventory();
    }
}
