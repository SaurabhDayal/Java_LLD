package aExercise.undoOperationInGame.model;

public class GameState {
    public String[][] board;

    public GameState(String[][] board) {
        this.board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(board[i], 0, this.board[i], 0, 3);
        }
    }
}
