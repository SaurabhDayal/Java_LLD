package aMachineCoding.undoOperationInGame.model;

import aMachineCoding.undoOperationInGame.strategies.ReversibleMoveStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TicTacToe {

    public String[][] board;                              // Represents the Tic-Tac-Toe board
    public Player currentPlayer;                          // Tracks the current player (X or O)
    public Stack<Move> moveStack;                         // 1. Stack-Based Undo (LIFO structure to track moves)
    public List<GameState> stateHistory;                  // 2. Snapshot-Based Undo (Stores the board's state history)
    public ReversibleMoveStrategy reversibleMoveStrategy; // The strategy currently in use

    public TicTacToe(ReversibleMoveStrategy strategy) {
        board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "_";
            }
        }
        currentPlayer = Player.X;          // Start with player X
        moveStack = new Stack<>();         // Stack-Based Undo
        stateHistory = new ArrayList<>();  // Snapshot-Based Undo
        reversibleMoveStrategy = strategy; // Set the chosen undo strategy
    }

    public void makeMove(int row, int col) {
        reversibleMoveStrategy.makeMove(this, row, col);
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
    }

    public void undoMove() {
        reversibleMoveStrategy.undoMove(this);
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
