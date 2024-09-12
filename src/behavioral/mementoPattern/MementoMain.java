package behavioral.mementoPattern;

import behavioral.mementoPattern.caretakerPkg.Caretaker;
import behavioral.mementoPattern.orinatorPkg.Originator;

public class MementoMain {

    public static void main(String[] args) {

        Originator configManager = new Originator();
        Caretaker caretaker = new Caretaker();
        System.out.println();

        // Set and save the first state
        configManager.setState(1, "Initial configuration");
        System.out.println("Current State: " + configManager.getState());
        caretaker.saveState(configManager);

        // Set and save the second state
        configManager.setState(2, "Added new feature");
        System.out.println("Current State: " + configManager.getState());
        caretaker.saveState(configManager);

        // Set the third state
        configManager.setState(3, "Updated configuration");
        System.out.println("Current State: " + configManager.getState());
        System.out.println();

        // Undo last change
        caretaker.undo(configManager);
        System.out.println("After Undo 1: " + configManager.getState());

        // Undo second change
        caretaker.undo(configManager);
        System.out.println("After Undo 2: " + configManager.getState());
    }
}
