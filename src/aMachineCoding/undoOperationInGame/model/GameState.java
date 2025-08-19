package aMachineCoding.undoOperationInGame.model;

public class GameState {
    public String[][] board;

    public GameState(String[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        this.board = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }

    public void restoreBoard(TicTacToe game) {
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                game.board[i][j] = this.board[i][j];
            }
        }
    }
}
