package aMachineCoding.snakeAndLadderGame.element;

public class Ladder implements GameElement {
    private final int bottom;
    private final int top;

    public Ladder(int bottom, int top) {
        this.bottom = bottom;
        this.top = top;
    }

    @Override
    public int getStartPosition() {
        return bottom;
    }

    @Override
    public int getDestination() {
        return top;
    }
}
