package behavioral.mementoPattern.orinatorPkg;

public class Memento {

    private final State state;

    public Memento(State state) {
        this.state = new State(state.getVersion(), state.getDetails());
    }

    public State getState() {
        return state;
    }
}
