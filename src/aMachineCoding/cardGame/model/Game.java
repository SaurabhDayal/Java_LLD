package aMachineCoding.cardGame.model;

import aMachineCoding.cardGame.rules.WinningRule;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private Deck deck;
    private GameState gameState;
    private WinningRule rule;

    public Game(WinningRule rule) {
        this.players = new ArrayList<>();
        this.deck = new Deck();
        this.gameState = GameState.NOT_STARTED;
        this.rule = rule;
    }

    public void addPlayer(Player player) {
        if (players.size() < 4 && gameState == GameState.NOT_STARTED) {
            players.add(player);
        }
    }

    public void startNewGame() {
        deck = new Deck();
        deck.shuffle();
        gameState = GameState.IN_PROGRESS;
    }

    public void dealInitialCards(int cardsPerPlayer) {
        for (int i = 0; i < cardsPerPlayer; i++) {
            for (Player player : players) {
                player.receiveCard(deck.dealCard());
            }
        }
    }

    public Player declareWinner() {
        gameState = GameState.COMPLETED;
        return rule.determineWinner(players);
    }

    public GameState getGameState() {
        return gameState;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
