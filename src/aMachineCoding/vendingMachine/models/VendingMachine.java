package aMachineCoding.vendingMachine.models;

import aMachineCoding.vendingMachine.inventory.Inventory;
import aMachineCoding.vendingMachine.states.*;

public class VendingMachine {

    private static VendingMachine instance;

    public Inventory inventory;

    private final State dispenseState;
    private final State idleState;
    private final State insertCoinState;
    private final State selectionState;
    private State currentState;

    private Item currentItem;
    private int balance;        // Transaction/session balance
    private int totalEarnings;  // Lifetime earnings of the vending machine

    // --------------------------------------
    // Constructor & Singleton Access
    // --------------------------------------

    private VendingMachine() {
        inventory = new Inventory();

        idleState = new IdleState(this);
        insertCoinState = new InsertCoinState(this);
        dispenseState = new DispenseState(this);
        selectionState = new SelectionState(this);
        currentState = idleState;

        balance = 0;
        totalEarnings = 0;
    }

    public static synchronized VendingMachine getInstance() {
        if (instance == null) {
            instance = new VendingMachine();
        }
        return instance;
    }

    // --------------------------------------
    // State-Driven User Actions
    // --------------------------------------

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

    // --------------------------------------
    // Item Handling
    // --------------------------------------

    public void setCurrentItem(Item product) {
        this.currentItem = product;
    }

    public Item getCurrentItem() {
        return currentItem;
    }

    public void dispenseCurrentProduct() {
        if (currentItem != null) {
            // Deduct from inventory
            inventory.reduceQuantity(currentItem.getItemCode(), 1);

            // Add transaction balance to total earnings
            finalizeTransaction();
        }
    }

    public void resetSelectedProduct() {
        currentItem = null;
    }

    // --------------------------------------
    // Inventory Operations
    // --------------------------------------

    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    public void displayInventory() {
        inventory.displayInventory();
    }

    // --------------------------------------
    // Balance & Transaction Management
    // --------------------------------------

    public void addCoin(int amount) {
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }

    public int getTotalEarnings() {
        return totalEarnings;
    }

    public void refund() {
        System.out.println("Transaction cancelled. Returning balance: " + balance);
        resetBalance();
    }

    public void resetBalance() {
        balance = 0;
    }

    public void finalizeTransaction() {
        // Add the current transaction’s money to total earnings
        totalEarnings += balance;
        resetBalance();
        resetSelectedProduct();
    }

    // --------------------------------------
    // State Management
    // --------------------------------------

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

    public void changeState(State newState) {
        currentState = newState;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
