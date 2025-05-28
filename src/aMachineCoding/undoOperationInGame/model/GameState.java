package aMachineCoding.undoOperationInGame.model;

public class GameState {
    public String[][] board;

    public GameState(String[][] board) {
        this.board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }
}
