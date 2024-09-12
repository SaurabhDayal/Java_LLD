package behavioral.mementoPattern.caretakerPkg;

import behavioral.mementoPattern.orinatorPkg.Memento;
import behavioral.mementoPattern.orinatorPkg.Originator;

import java.util.Stack;

public class Caretaker {

    private Stack<Memento> history = new Stack<>();

    public void saveState(Originator originator) {
        history.push(originator.save());
    }

    public void undo(Originator originator) {
        if (!history.isEmpty()) {
            Memento memento = history.pop();
            originator.restore(memento);
        } else {
            System.out.println("No states to undo.");
        }
    }
}
