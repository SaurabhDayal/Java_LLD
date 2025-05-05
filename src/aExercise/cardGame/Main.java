package aScalerModule_08_LLD_3.assign_02.cardGame;

import aScalerModule_08_LLD_3.assign_02.cardGame.model.Game;
import aScalerModule_08_LLD_3.assign_02.cardGame.model.Player;
import aScalerModule_08_LLD_3.assign_02.cardGame.rules.BlackjackWinningRule;
import aScalerModule_08_LLD_3.assign_02.cardGame.rules.WinningRule;
import aScalerModule_08_LLD_3.assign_02.cardGame.scoring.BlackjackScoringStrategy;
import aScalerModule_08_LLD_3.assign_02.cardGame.scoring.ScoringStrategy;

/*
Design class diagram for a simple card game.

A standard deck of cards consists of 52 cards, divided into four suits:
- Hearts
- Diamonds
- Clubs
- Spades

Each suit has 13 ranks:
- 2, 3, 4, 5, 6, 7, 8, 9, 10
- Jack, Queen, King, Ace

Requirements:

Model the following entities:
- Card
- Deck
- Player
- Game

Card:
- Each card has a suit and a rank.

Deck:
- Should support the following operations:
  - Shuffle the cards
  - Deal a card to a player
  - Return the number of remaining cards

Player:
- The game supports multiple players (maximum of 4).
- Each player has:
  - A hand of cards
  - An operation to receive a card
  - An operation to show their cards

Game:
- Should be able to:
  - Start a new game
  - Distribute cards to players
  - Declare a winner based on some simple criteria (e.g., the player with the highest card)
- Should have a state:
  - NOT_STARTED
  - IN_PROGRESS
  - COMPLETED
- Should support design flexibility to add new rules or scoring mechanisms in the future
- There should be a class or method to validate the game rules

*/

public class Main {

    public static void main(String[] args) {

        // Enables flexible game rules by decoupling card scoring and winner selection logic

        // Step 1: Choose scoring strategy
        ScoringStrategy scoringStrategy = new BlackjackScoringStrategy();

        // Step 2: Create rule using the scoring strategy
        WinningRule rule = new BlackjackWinningRule(scoringStrategy);

        // Step 3: Create the game with the rule
        Game game = new Game(rule);

        game.addPlayer(new Player("Alice"));
        game.addPlayer(new Player("Bob"));
        game.addPlayer(new Player("Charlie"));
        game.addPlayer(new Player("Diana"));

        game.startNewGame();
        game.dealInitialCards(1);

        Player winner = game.declareWinner();

        if (winner != null) {
            System.out.println("Winner is: " + rule.getWinnerSummary(winner));
        } else {
            System.out.println("No winner determined.");
        }
    }
}


