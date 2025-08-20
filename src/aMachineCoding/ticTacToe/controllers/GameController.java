package aMachineCoding.ticTacToe.controllers;

import aMachineCoding.ticTacToe.builders.GameBuilder;
import aMachineCoding.ticTacToe.exception.InvalidMoveException;
import aMachineCoding.ticTacToe.models.Game;
import aMachineCoding.ticTacToe.models.GameState;
import aMachineCoding.ticTacToe.models.Player;
import aMachineCoding.ticTacToe.strategies.winningStrategy.WinningStrategy;

import java.util.List;

public class GameController {

    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        return new GameBuilder() // Use GameBuilder directly
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
    }

    public void makeMove(Game game) throws InvalidMoveException {
        game.makeMove();
    }

    public GameState gameState(Game game) {
        return game.getGameState();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public void printBoard(Game game) {
        game.printBoard();
    }

    public void undo(Game game) throws InvalidMoveException {
        game.undoMove();
    }
}
