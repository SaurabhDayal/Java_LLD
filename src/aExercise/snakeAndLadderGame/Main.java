package aScalerModule_08_LLD_3.assign_02.snakeAndLadderGame;

import aScalerModule_08_LLD_3.assign_02.snakeAndLadderGame.dice.StandardDice;
import aScalerModule_08_LLD_3.assign_02.snakeAndLadderGame.factory.DefaultGameElementFactory;
import aScalerModule_08_LLD_3.assign_02.snakeAndLadderGame.game.Game;
import aScalerModule_08_LLD_3.assign_02.snakeAndLadderGame.models.HumanPlayer;
import aScalerModule_08_LLD_3.assign_02.snakeAndLadderGame.models.Player;

import java.util.ArrayList;
import java.util.List;

/*
Design a class diagram for the traditional game of Snake and Ladder with the following requirements:

- Board:   A standard Snake and Ladder board contains 100 cells numbered from 1 to 100.

- Players: The game can be played by 2-4 players.

- Dice:    The game will have a standard dice that can roll numbers between 1 and 6.

- Snakes:  Snakes will have a start point (mouth) and an endpoint (tail).
           If a player lands on the mouth, they are taken down to the tail.

- Ladders: Ladders will have a start point (bottom) and an endpoint (top).
           If a player lands on the bottom, they climb up to the top.

- Turns:   Players take turns in a round-robin fashion to roll the dice and move.

- Winning Condition: A player wins if they land on the 100th cell exactly.
                     For example, if a player is on the 99th cell, they must roll a 1 to win.
*/

public class Main {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        players.add(new HumanPlayer("Alice"));
        players.add(new HumanPlayer("Bob"));
        Game game = new Game(players, new DefaultGameElementFactory(), new StandardDice());
        game.startGame();
    }
}
