package aExercise.ticTacToe;

import aExercise.ticTacToe.controllers.GameController;
import aExercise.ticTacToe.exception.InvalidMoveException;
import aExercise.ticTacToe.models.*;
import aExercise.ticTacToe.strategies.winningstrategy.ColWinningStrategy;
import aExercise.ticTacToe.strategies.winningstrategy.DiagonalWinningStrategy;
import aExercise.ticTacToe.strategies.winningstrategy.RowWinningStrategy;
import aExercise.ticTacToe.strategies.winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeMain {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GameController gameController = new GameController();

    public static void main(String[] args) throws InvalidMoveException {
        System.out.println("GAME STARTS");

        // Initialize the game
        Game game = init();

        // Start the game loop
        playGame(game);
    }

    private static Game init() {
        int dimension = 3; // Fixed dimension (can be changed to scanner input if needed)

        // Initialize players
        List<Player> players = new ArrayList<>();
        players.add(new Player(new Symbol('X'), "Anand", PlayerType.HUMAN));
        players.add(new Bot(new Symbol('O'), "Scaler", PlayerType.BOT, BotDifficultyLevel.EASY));

        // Define winning strategies
        List<WinningStrategy> winningStrategies = List.of(
                new RowWinningStrategy(),
                new ColWinningStrategy(),
                new DiagonalWinningStrategy()
        );

        return gameController.startGame(dimension, players, winningStrategies);
    }

    private static void playGame(Game game) throws InvalidMoveException {
        while (gameController.gameState(game).equals(GameState.IN_PROGRESS)) {
            gameController.printBoard(game);

            System.out.println("Do you want to undo? (y/n)");
            String isUndo = scanner.next();

            if (isUndo.equalsIgnoreCase("y")) {
                gameController.undo(game);
                continue;
            }

            gameController.makeMove(game);
        }

        gameController.printBoard(game);
        displayResult(game);
    }

    private static void displayResult(Game game) {
        if (gameController.gameState(game).equals(GameState.ENDED)) {
            System.out.println(gameController.getWinner(game).getName() + " has won the game.");
        } else {
            System.out.println("GAME DRAW");
        }
    }
}
