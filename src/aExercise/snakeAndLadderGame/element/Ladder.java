package aExercise.snakeAndLadderGame.element;

public class Ladder implements GameElement {
    private int bottom;
    private int top;

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
