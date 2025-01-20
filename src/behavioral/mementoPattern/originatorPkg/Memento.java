package behavioral.mementoPattern.originatorPkg;

public class Memento {

    private final State state;

    public Memento(State state) {
        // Creating a new copy of the state for immutability
        this.state = new State(state.getVersion(), state.getDetails());
    }

    public State getState() {
        return state;
    }
}
