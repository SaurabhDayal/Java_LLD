package aExercise.undoOperationInGame.model;

public class Move {
    public int row;
    public int col;
    public TicTacToe.Player player;

    public Move(int row, int col, TicTacToe.Player player) {
        this.row = row;
        this.col = col;
        this.player = player;
    }
}
