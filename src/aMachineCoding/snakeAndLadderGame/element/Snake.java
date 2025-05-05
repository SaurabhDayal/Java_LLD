package aMachineCoding.snakeAndLadderGame.element;

public class Snake implements GameElement {
    private int mouth;
    private int tail;

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
