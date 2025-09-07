package aMachineCoding.chessGame.models;

public class Player {

    private final String name;
    private final boolean isWhiteSide;

    public Player(String name, boolean isWhiteSide) {
        this.name = name;
        this.isWhiteSide = isWhiteSide;
    }

    public String getName() {
        return name;
    }

    public boolean isWhiteSide() {
        return isWhiteSide;
    }
}
