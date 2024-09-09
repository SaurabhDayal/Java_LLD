package behavioral.statePattern;

import behavioral.statePattern.vendingMachinePkg.VendingMachine;

public class StateMain {

    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();

        // Display initial inventory
        System.out.println("------------------------------");
        vendingMachine.displayInventory();

        // Test with coin insertion, item selection, and cancellation
        vendingMachine.insertCoin(10); // Not enough money
        vendingMachine.insertCoin(15); // Now enough for Coke (C1)
        vendingMachine.selectItem("C1"); // Coke selected
        vendingMachine.dispenseItem(); // Dispense Coke

        // Display inventory after dispensing
        System.out.println("------------------------------");
        vendingMachine.displayInventory();

        vendingMachine.insertCoin(20); // Partial money for Juice (J1)
        vendingMachine.insertCoin(20); // Now enough for Juice
        vendingMachine.selectItem("J1"); // Juice selected
        vendingMachine.cancelTransaction(); // Cancel transaction, refund coins

        // Display inventory after cancellation
        System.out.println("------------------------------");
        vendingMachine.displayInventory();

        vendingMachine.insertCoin(30); // Partial money for Pepsi (P1)
        vendingMachine.insertCoin(15); // Now enough for Pepsi
        vendingMachine.selectItem("P1"); // Pepsi selected
        vendingMachine.dispenseItem(); // Dispense Pepsi

        // Display final inventory
        System.out.println("------------------------------");
        vendingMachine.displayInventory();
    }
}

