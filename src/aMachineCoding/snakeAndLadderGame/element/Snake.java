package aMachineCoding.snakeAndLadderGame.element;

public class Snake implements GameElement {
    private final int mouth;
    private final int tail;

    public Snake(int mouth, int tail) {
        this.mouth = mouth;
        this.tail = tail;
    }

    @Override
    public int getStartPosition() {
        return mouth;
    }

    @Override
    public int getDestination() {
        return tail;
    }
}
