package behavioral.statePattern;

import behavioral.statePattern.vendingMachinePkg.VendingMachine;

public class StateMain {

    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();

        // Display inventory before New Transaction
        System.out.println("\nNEW TRANSACTION\n");
        vendingMachine.displayInventory();
        System.out.println();
        vendingMachine.insertCoin(10); // Not enough money
        vendingMachine.insertCoin(15); // Now enough for Coke (C1)
        vendingMachine.selectItem("C1"); // Coke selected
        vendingMachine.dispenseItem(); // Dispense Coke
        System.out.println("------------------------------");

        // Display inventory before New Transaction
        System.out.println("\nNEW TRANSACTION\n");
        vendingMachine.displayInventory();
        System.out.println();
        vendingMachine.insertCoin(20); // Partial money for Juice (J1)
        vendingMachine.insertCoin(20); // Now enough for Juice
        vendingMachine.selectItem("J1"); // Juice selected
        vendingMachine.cancelTransaction(); // Cancel transaction, refund coins
        System.out.println("------------------------------");

        // Display inventory before New Transaction
        System.out.println("\nNEW TRANSACTION\n");
        vendingMachine.displayInventory();
        System.out.println();
        vendingMachine.insertCoin(30); // Partial money for Pepsi (P1)
        vendingMachine.insertCoin(15); // Now enough for Pepsi
        vendingMachine.selectItem("P1"); // Pepsi selected
        vendingMachine.dispenseItem(); // Dispense Pepsi
        System.out.println("------------------------------");

        // Display final inventory
        System.out.println("\nEND INVENTORY STATUS");
        vendingMachine.displayInventory();
        System.out.println("------------------------------");
    }
}
