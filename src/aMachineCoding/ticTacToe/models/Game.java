package aMachineCoding.ticTacToe.models;

import aMachineCoding.ticTacToe.exception.InvalidMoveException;
import aMachineCoding.ticTacToe.strategies.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Board board;
    private final List<Player> players;
    private final List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextMovePlayerIndex;

    private final List<WinningStrategy> winningStrategies;

    public Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.board = new Board(dimension);
        this.players = players;
        this.nextMovePlayerIndex = 0;
        this.gameState = GameState.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.winningStrategies = winningStrategies;
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

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        // Update board state
        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(currentPlayer);

        // Save move in history
        moves.add(new Move(currentPlayer, cell));

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
            throw new InvalidMoveException("Cannot undo move, please make a move first");
        }

        // Remove last move
        Move lastMove = moves.removeLast();

        // Reset cell to empty
        Cell cell = lastMove.getCell();
        cell.setCellState(CellState.EMPTY);
        cell.setPlayer(null);

        // Roll back winning strategies
        for (WinningStrategy winningStrategy : winningStrategies) {
            winningStrategy.handleUndo(board, lastMove);
        }

        // Rewind turn
        nextMovePlayerIndex = (nextMovePlayerIndex - 1 + players.size()) % players.size();

        // Reset winner/game state
        winner = null;
        gameState = GameState.IN_PROGRESS;
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

    public void printBoard() {
        board.printBoard();
    }
}
