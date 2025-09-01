package aMachineCoding.battleshipGame;

import aMachineCoding.battleshipGame.controllers.GameController;
import aMachineCoding.battleshipGame.models.Game;

public class BattleshipGameMain {
    private static final GameController gameController = new GameController();

    public static void main(String[] args) {
        System.out.println("GAME STARTS");
        Game game = gameController.startGame();  // Game setup happens inside controller
        playGame(game);
        displayResult(game);
    }

    private static void playGame(Game game) {
        while (!gameController.isGameOver(game)) {
            gameController.playTurn(game);  // Play one full turn
        }
    }

    private static void displayResult(Game game) {
        System.out.println("\nGame Over! " + gameController.getWinner(game).getName() + " wins.");
        gameController.displayLoserBoard(game);
    }
}
