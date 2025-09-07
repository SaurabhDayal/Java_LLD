package aMachineCoding.chessGame.models;

public class Player {
    
    private final String name;
    private boolean isWhiteSide;

    public Player(String name, boolean isWhiteSide) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isWhiteSide() {
        return isWhiteSide;
    }
}
