package aMachineCoding.snakeAndLadderGame.game;

import aMachineCoding.snakeAndLadderGame.dice.DiceStrategy;
import aMachineCoding.snakeAndLadderGame.factory.GameElementFactory;
import aMachineCoding.snakeAndLadderGame.models.Board;
import aMachineCoding.snakeAndLadderGame.models.Player;

import java.util.List;

public class Game {

    private final List<Player> players;
    private final Board board;
    private final DiceStrategy diceStrategy;
    private int currentPlayerIndex = 0;
    private boolean isGameOver = false;
    private Player winner;

    public Game(List<Player> players, GameElementFactory factory, DiceStrategy diceStrategy) {
        this.players = players;
        this.board = new Board(factory);
        this.diceStrategy = diceStrategy;
    }

    public void startGame() {
        while (!isGameOver) {
            playTurn();
        }
        System.out.println("Winner: " + winner.getName());
    }

    private void playTurn() {
        Player player = players.get(currentPlayerIndex);
        player.takeTurn(board, diceStrategy);
        System.out.println(player.getName() + " moved to " + player.getPosition());

        if (checkWin(player)) {
            isGameOver = true;
            winner = player;
        } else {
            switchToNextPlayer();
        }
    }

    private boolean checkWin(Player player) {
        return player.getPosition() == board.getSize();
    }

    private void switchToNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
}
