package behavioral.mementoPattern;

import behavioral.mementoPattern.caretakerPkg.Caretaker;
import behavioral.mementoPattern.originatorPkg.Originator;

public class MementoMain {

    public static void main(String[] args) {

        Originator originator = new Originator(); // originator variable better name is Config Manager
        Caretaker caretaker = new Caretaker();
        System.out.println();

        // Set and save the first state
        originator.setState(1, "Initial configuration");
        System.out.println("Current State \n\t" + originator.getState());
        caretaker.saveState(originator);

        // Set and save the second state
        originator.setState(2, "Added new feature");
        System.out.println("Current State \n\t" + originator.getState());
        caretaker.saveState(originator);

        // Set the third state
        originator.setState(3, "Updated configuration");
        System.out.println("Current State \n\t" + originator.getState());
        System.out.println();

        // Undo last change
        caretaker.undo(originator);
        System.out.println("After Undo 1 \n\t" + originator.getState());

        // Undo second change
        caretaker.undo(originator);
        System.out.println("After Undo 2 \n\t" + originator.getState());
    }
}
