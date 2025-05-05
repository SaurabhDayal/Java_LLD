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
    private Item currentItem;
    private int balance;

    public Inventory inventory;

    private VendingMachine() {
        inventory = new Inventory();

        idleState = new IdleState(this);
        insertCoinState = new InsertCoinState(this);
        dispenseState = new DispenseState(this);
        selectionState = new SelectionState(this);

        currentState = idleState;
    }

    public static synchronized VendingMachine getInstance() {
        if (instance == null) {
            instance = new VendingMachine();
        }
        return instance;
    }

    public State getDispenseState() {
        return dispenseState;
    }

    public Inventory getInventory() {
        return inventory;
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

    public void addCoin(int amount) {
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }

    public void resetBalance() {
        balance = 0;
    }

    public void resetSelectedProduct() {
        currentItem = null;
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

    public void setCurrentItem(Item product) {
        this.currentItem = product;
    }

    public Item getCurrentItem() {
        return currentItem;
    }

    public void dispenseCurrentProduct() {
        if (currentItem != null) {
            inventory.reduceQuantity(currentItem.getItemCode(), 1);
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

