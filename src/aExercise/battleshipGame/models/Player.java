package aExercise.battleshipGame.models;

public class Player {
    private final String name;
    private final Board board;

    public Player(String name, Board board) {
        this.name = name;
        this.board = board;
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public boolean hasLost() {
        return board.allShipsSunk();
    }
}
