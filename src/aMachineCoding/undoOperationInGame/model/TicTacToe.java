package aMachineCoding.undoOperationInGame.model;

import aMachineCoding.undoOperationInGame.strategies.ReversibleMoveStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TicTacToe {

    // =========================
    // Player Enumeration
    // =========================
    public enum Player {X, O}

    // =========================
    // Board and Game State Variables
    // =========================
    public String[][] board; // Represents the Tic-Tac-Toe board
    public Player currentPlayer; // Tracks the current player (X or O)

    // =========================
    // Undo Strategies Data Structures
    // =========================

    // 1. Stack-Based Undo (LIFO structure to track moves)
    public Stack<Move> moveStack;

    // 2. Snapshot-Based Undo (Stores the board's state history)
    public List<GameState> stateHistory;

    // 3. Incremental Undo (Stores the difference between consecutive moves)
    public Stack<Delta> deltaStack;

    // 4. Versioning-Based Undo (Maintains a history of board versions)
    public List<GameState> versionHistory;

    // The strategy currently in use
    public ReversibleMoveStrategy reversibleMoveStrategy;

    // =========================
    // Constructor
    // =========================
    public TicTacToe(ReversibleMoveStrategy strategy) {
        // Initialize the board with empty values
        board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "_";
            }
        }

        currentPlayer = Player.X; // Start with player X

        // Initialize stacks and lists for different undo strategies
        moveStack = new Stack<>(); // Stack-Based Undo

        stateHistory = new ArrayList<>(); // Snapshot-Based Undo

        deltaStack = new Stack<>(); // Incremental Undo

        versionHistory = new ArrayList<>(); // Versioning-Based Undo
        versionHistory.add(new GameState(board)); // Store initial empty board state

        reversibleMoveStrategy = strategy; // Set the chosen undo strategy
    }

    // =========================
    // Core Gameplay Methods
    // =========================

    // Makes a move using the selected undo strategy
    public void makeMove(int row, int col) {
        reversibleMoveStrategy.makeMove(this, row, col);
    }

    // Undo the last move using the selected undo strategy
    public void undo() {
        reversibleMoveStrategy.undo(this);
    }

    // =========================
    // Utility Methods
    // =========================

    // Restores the board state from a saved GameState
    public void restoreBoard(GameState state) {
        for (int i = 0; i < 3; i++) {
            System.arraycopy(state.board[i], 0, board[i], 0, 3);
        }
    }

    // Switches turn between Player X and Player O
    public void switchPlayer() {
        currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
    }

    // Prints the current board state
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
