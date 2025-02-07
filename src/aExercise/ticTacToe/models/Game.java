package aExercise.ticTacToe.models;

import aExercise.ticTacToe.exception.InvalidMoveException;
import aExercise.ticTacToe.strategies.winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextMovePlayerIndex;
    private List<WinningStrategy> winningStrategies;

    public Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.board = new Board(dimension);
        this.players = players;
        this.nextMovePlayerIndex = 0;
        this.gameState = GameState.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.winningStrategies = winningStrategies;
    }

    private boolean validateMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if (row < 0 || row >= board.getDimension() || col < 0 || col >= board.getDimension()) {
            return false;
        }

        return board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY);
    }

    public void makeMove() throws InvalidMoveException {
        Player currentPlayer = players.get(nextMovePlayerIndex);
        System.out.println("This is " + currentPlayer.getName() + "'s move.");

        Move move = currentPlayer.makeMove(board);

        if (!validateMove(move)) {
            throw new InvalidMoveException("Invalid move, please retry");
        }

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(currentPlayer);

        moves.add(new Move(currentPlayer, cell));

        nextMovePlayerIndex = (nextMovePlayerIndex + 1) % players.size();

        if (checkWinner(move)) {
            winner = currentPlayer;
            gameState = GameState.ENDED;
        } else if (moves.size() == board.getDimension() * board.getDimension()) {
            gameState = GameState.DRAW;
        }
    }

    private boolean checkWinner(Move move) {
        for (WinningStrategy winningStrategy : winningStrategies) {
            if (winningStrategy.checkWinner(board, move)) {
                return true;
            }
        }
        return false;
    }

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
