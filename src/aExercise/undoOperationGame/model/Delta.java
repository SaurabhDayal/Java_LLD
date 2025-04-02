package aExercise.undoOperationGame.model;

public class Delta {
    public int row;
    public int col;
    public String previous;
    public String current;

    public Delta(int row, int col, String previous, String current) {
        this.row = row;
        this.col = col;
        this.previous = previous;
        this.current = current;
    }
}
