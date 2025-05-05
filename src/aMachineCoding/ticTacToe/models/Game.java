package aMachineCoding.ticTacToe.models;

import aMachineCoding.ticTacToe.exception.InvalidMoveException;
import aMachineCoding.ticTacToe.strategies.reversibleMoveStrategy.ReversibleMoveStrategy;
import aMachineCoding.ticTacToe.strategies.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Game {

    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextMovePlayerIndex;

    private List<WinningStrategy> winningStrategies;
    public ReversibleMoveStrategy reversibleMoveStrategy;

    private Stack<Move> moveStack = new Stack<>();
    private List<List<List<Cell>>> stateHistory = new ArrayList<>();

    public Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies, ReversibleMoveStrategy reversibleMoveStrategy) {
        this.board = new Board(dimension);
        this.players = players;
        this.nextMovePlayerIndex = 0;
        this.gameState = GameState.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.winningStrategies = winningStrategies;
        this.reversibleMoveStrategy = reversibleMoveStrategy;
    }

    // --------------------------------------
    // Public Game Play Methods
    // --------------------------------------

    public void makeMove() throws InvalidMoveException {
        Player currentPlayer = players.get(nextMovePlayerIndex);
        System.out.println("This is " + currentPlayer.getName() + "'s move.");

        Move move = currentPlayer.makeMove(board);

        if (!validateMove(move)) {
            throw new InvalidMoveException("Invalid move, please retry");
        }

        // Conditionally save BEFORE move (Snapshot strategy)
        if (reversibleMoveStrategy.shouldSaveBeforeMove()) {
            reversibleMoveStrategy.onMove(this);
        }

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(currentPlayer);

        moves.add(new Move(currentPlayer, cell));

        // Conditionally save AFTER move (Stack strategy)
        if (!reversibleMoveStrategy.shouldSaveBeforeMove()) {
            reversibleMoveStrategy.onMove(this);
        }

        if (checkWinner(move)) {
            winner = currentPlayer;
            gameState = GameState.ENDED;
        } else if (moves.size() == board.getDimension() * board.getDimension()) {
            gameState = GameState.DRAW;
        } else {
            nextMovePlayerIndex = (nextMovePlayerIndex + 1) % players.size();
        }
    }

    public void undoMove() throws InvalidMoveException {
        if (moves.isEmpty()) {
            throw new InvalidMoveException("Cannot undo move, please make Move");
        }

        reversibleMoveStrategy.undo(this);

        Move lastMove = moves.remove(moves.size() - 1);
        for (WinningStrategy winningStrategy : winningStrategies) {
            winningStrategy.handleUndo(board, lastMove);
        }

        nextMovePlayerIndex = (nextMovePlayerIndex - 1 + players.size()) % players.size();
    }

    // --------------------------------------
    // Move Validation
    // --------------------------------------

    private boolean validateMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if (row < 0 || row >= board.getDimension() || col < 0 || col >= board.getDimension()) {
            return false;
        }

        return board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY);
    }

    // --------------------------------------
    // Winning Logic
    // --------------------------------------

    private boolean checkWinner(Move move) {
        for (WinningStrategy winningStrategy : winningStrategies) {
            if (winningStrategy.checkWinner(board, move)) {
                return true;
            }
        }
        return false;
    }

    // --------------------------------------
    // Snapshot Handling (for undo)
    // --------------------------------------

    public void saveBoardSnapshot() {
        List<List<Cell>> original = board.getBoard();
        List<List<Cell>> snapshot = new ArrayList<>();

        for (List<Cell> row : original) {
            List<Cell> newRow = new ArrayList<>();
            for (Cell c : row) {
                Cell copy = new Cell(c.getRow(), c.getCol());
                copy.setCellState(c.getCellState());
                copy.setPlayer(c.getPlayer());
                newRow.add(copy);
            }
            snapshot.add(newRow);
        }

        stateHistory.add(snapshot);
    }

    public void restoreBoardSnapshot(List<List<Cell>> snapshot) {
        for (int i = 0; i < board.getDimension(); i++) {
            for (int j = 0; j < board.getDimension(); j++) {
                board.getBoard().get(i).get(j).setCellState(snapshot.get(i).get(j).getCellState());
                board.getBoard().get(i).get(j).setPlayer(snapshot.get(i).get(j).getPlayer());
            }
        }
    }

    // --------------------------------------
    // Accessors
    // --------------------------------------

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public Player getWinner() {
        return winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Stack<Move> getMoveStack() {
        return moveStack;
    }

    public List<List<List<Cell>>> getStateHistory() {
        return stateHistory;
    }

    public void printBoard() {
        board.printBoard();
    }
}
