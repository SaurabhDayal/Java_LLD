package aExercise.ticTacToe.builders;

import aExercise.ticTacToe.models.Game;
import aExercise.ticTacToe.models.Player;
import aExercise.ticTacToe.models.PlayerType;
import aExercise.ticTacToe.strategies.reversibleMoveStrategy.ReversibleMoveStrategy;
import aExercise.ticTacToe.strategies.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameBuilder {
    private int dimension;
    private List<Player> players;
    private List<WinningStrategy> winningStrategies;
    private ReversibleMoveStrategy reversibleMoveStrategy;

    public GameBuilder() {
        this.players = new ArrayList<>();
        this.dimension = 0;
        this.winningStrategies = new ArrayList<>();
        this.reversibleMoveStrategy = null;
    }

    public Game build() {
        validations();
        return new Game(dimension, players, winningStrategies, reversibleMoveStrategy);
    }

    private void validations() {
        validateBotCount();
        validateUniqueSymbols();
    }

    private void validateBotCount() {
        int count = 0;
        for (Player player : players) {
            if (player.getPlayerType().equals(PlayerType.BOT)) {
                count += 1;
                if (count > 1) {
                    throw new RuntimeException("Only one BOT is allowed per game");
                }
            }
        }
    }

    private void validateUniqueSymbols() {
        Set<Character> symbolSet = new HashSet<>();
        for (Player player : players) {
            symbolSet.add(player.getSymbol().getaChar());
        }

        if (symbolSet.size() != dimension - 1) {
            throw new RuntimeException("Every player should have a unique symbol");
        }
    }

    public GameBuilder setDimension(int dimension) {
        this.dimension = dimension;
        return this;
    }

    public GameBuilder setPlayers(List<Player> players) {
        this.players = players;
        return this;
    }

    public GameBuilder setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
        return this;
    }

    public GameBuilder setReversibleMoveStrategy(ReversibleMoveStrategy reversibleMoveStrategy) {
        this.reversibleMoveStrategy = reversibleMoveStrategy;
        return this;
    }
}
