package aMachineCoding.battleshipGame.game;

import aMachineCoding.battleshipGame.models.Player;
import aMachineCoding.battleshipGame.models.Position;

public class Game {
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private boolean isGameOver;
    private Player winner;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void startNewGame() {
        this.currentPlayer = player1;
        this.isGameOver = false;
        this.winner = null;
        System.out.println();
        System.out.println("------ Game started between " + player1.getName() + " and " + player2.getName() + " ------");
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOpponentPlayer() {
        return (currentPlayer == player1) ? player2 : player1;
    }

    public void makeMove(Position position) {
        Player opponent = getOpponentPlayer();
        char resultChar = opponent.getBoard().receiveAttack(position);

        // Log move details
        System.out.println(currentPlayer.getName() + " attacked " + position + ": " + resultToString(resultChar));

        // Check win condition
        if (opponent.hasLost()) {
            isGameOver = true;
            winner = currentPlayer;
        }
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    private String resultToString(char result) {
        return switch (result) {
            case 'H' -> "hit";
            case 'M' -> "miss";
            case 'X' -> "sunk";
            case ' ' -> "untouched";
            default -> "unknown";
        };
    }

    public Player getWinner() {
        return winner;
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
