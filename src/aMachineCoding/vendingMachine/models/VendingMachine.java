package aMachineCoding.vendingMachine.models;

import aMachineCoding.vendingMachine.inventory.Inventory;
import aMachineCoding.vendingMachine.states.*;

public class VendingMachine {

    private static VendingMachine instance;

    private final State dispenseState;
    private final State idleState;
    private final State insertCoinState;
    private final State selectionState;

    private State currentState;
    private Item currentProduct;
    private int balance;
    public Inventory inventory;

    private VendingMachine() {
        inventory = new Inventory();

        idleState = new IdleState(this);
        insertCoinState = new InsertCoinState(this);
        dispenseState = new DispenseState(this);
        selectionState = new SelectItemState(this);

        currentState = idleState;
    }

    public static synchronized VendingMachine getInstance() {
        if (instance == null) {
            instance = new VendingMachine();
        }
        return instance;
    }

    public static void setInstance(VendingMachine instance) {
        VendingMachine.instance = instance;
    }

    public State getDispenseState() {
        return dispenseState;
    }

    public State getIdleState() {
        return idleState;
    }

    public State getInsertCoinState() {
        return insertCoinState;
    }

    public State getSelectionState() {
        return selectionState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
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

    public void resetSelectedProduct() {
        currentProduct = null;
    }

    public void changeState(State newState) {
        currentState = newState;
    }

    public void insertCoin(int amount) {
        try {
            currentState.insertCoin(amount);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void selectItem(String itemCode) {
        try {
            currentState.selectItem(itemCode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void dispenseItem() {
        try {
            currentState.dispenseItem();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelTransaction() {
        try {
            currentState.cancelTransaction();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

