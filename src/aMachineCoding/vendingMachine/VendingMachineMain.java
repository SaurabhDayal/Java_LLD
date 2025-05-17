package aMachineCoding.vendingMachine;

import aMachineCoding.vendingMachine.models.VendingMachine;

public class VendingMachineMain {
    static VendingMachine vendingMachine;

    public static void main(String[] args) {
        vendingMachine = VendingMachine.getInstance();

        runTransaction(new int[]{10, 15}, "coke", true); // Dispense Coke
        runTransaction(new int[]{20, 20}, "juice", false); // Cancel Juice
        runTransaction(new int[]{30, 15}, "pepsi", true); // Dispense Pepsi

        System.out.println("\nEND INVENTORY STATUS");
        System.out.println();
        vendingMachine.displayInventory();
        System.out.println("------------------------------");
    }

    private static void runTransaction(int[] coins, String itemCode, boolean shouldDispense) {
        System.out.println("\nNEW TRANSACTION\n");
        vendingMachine.displayInventory();
        System.out.println();

        try {
            for (int coin : coins) {
                vendingMachine.insertCoin(coin);
            }
            vendingMachine.selectItem(itemCode);
            if (shouldDispense) {
                vendingMachine.dispenseItem();
            } else {
                vendingMachine.cancelTransaction();
            }
        } catch (Exception e) {
            System.out.println("Transaction failed: " + e.getMessage());
        }

        System.out.println("------------------------------");
    }
}
