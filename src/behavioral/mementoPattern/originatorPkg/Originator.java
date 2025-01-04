package behavioral.mementoPattern.originatorPkg;

public class Originator {

    private State state;

    public void setState(int version, String details) {
        this.state = new State(version, details);
    }

    public State getState() {
        return state;
    }

    public Memento save() {
        return new Memento(state);
    }

    public void restore(Memento memento) {
        this.state = memento.getState();
    }
}

