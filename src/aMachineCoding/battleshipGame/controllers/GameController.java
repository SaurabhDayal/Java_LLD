package aMachineCoding.battleshipGame.controllers;

import aMachineCoding.battleshipGame.models.*;
import aMachineCoding.battleshipGame.utils.SleepUtil;

import java.util.List;

public class GameController {

    public Game startGame() {
        // Setup Player 1
        Board board1 = new Board();
        List<Position> p1Ship1 = List.of(new Position(0, 0), new Position(0, 1));
        Ship ship1 = new Ship(ShipType.DESTROYER, p1Ship1);
        board1.getShips().add(ship1);
        for (Position pos : p1Ship1) {
            board1.getGrid()[pos.getRow()][pos.getColumn()].setShip(ship1);
        }
        Player player1 = new Player("Suman", board1);

        // Setup Player 2
        Board board2 = new Board();
        List<Position> p2Ship1 = List.of(new Position(5, 5), new Position(5, 6));
        Ship ship2 = new Ship(ShipType.DESTROYER, p2Ship1);
        board2.getShips().add(ship2);
        for (Position pos : p2Ship1) {
            board2.getGrid()[pos.getRow()][pos.getColumn()].setShip(ship2);
        }
        Player player2 = new Player("Punit", board2);

        // Initialize game
        Game game = new Game(player1, player2);
        game.startNewGame();
        return game;
    }

    public void playTurn(Game game) {
        Player currentPlayer = game.getCurrentPlayer();
        Player opponent = game.getOpponentPlayer();

        System.out.println();
        System.out.println(currentPlayer.getName() + "'s turn to attack!");

        // Show opponent board before attack
        System.out.println();
        System.out.println(opponent.getName() + "'s board: BEFORE");
        opponent.getBoard().display(false);
        System.out.println();

        // Player makes move (scanner inside Player)
        Position attackPosition = currentPlayer.makeMove();
        game.makeMove(attackPosition);
        System.out.println();

        // Show opponent board after attack
        System.out.println(opponent.getName() + "'s board: AFTER");
        opponent.getBoard().display(false);

        // Switch player if game not over
        if (!game.isGameOver()) {
            game.switchPlayer();
            System.out.println();
            System.out.println("---------------------------------");
            SleepUtil.sleep(3000);
        }
    }

    public boolean isGameOver(Game game) {
        return game.isGameOver();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public void displayLoserBoard(Game game) {
        System.out.println(game.getOpponentPlayer().getName() + "'s board");
        game.getOpponentPlayer().getBoard().display(true);
    }
}
