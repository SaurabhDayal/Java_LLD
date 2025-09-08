package aMachineCoding.chessGame;

import aMachineCoding.chessGame.models.ChessGame;
import aMachineCoding.chessGame.models.Player;

public class Main {
    public static void main(String[] args) {
        // Create players
        Player player1 = new Player("MrWhite", true); // White
        Player player2 = new Player("BlackMamba", false); // Black
        // Initialize game
        ChessGame chessGame = new ChessGame(player1, player2);
        // Start the game
        chessGame.start();
    }
}