package aMachineCoding.battleshipGame;

import aMachineCoding.battleshipGame.game.Game;
import aMachineCoding.battleshipGame.models.*;
import aMachineCoding.battleshipGame.utils.SleepUtil;

import java.util.List;
import java.util.Scanner;

/*
    Battleship Game

    Design a class diagram for a software-based version of the traditional Battleship game.
    The design should clearly capture the core components, rules, and interactions of the game.

    Game Setup:
    - The game is played using two grids per player:
        one for placing their ships and another for tracking shots against the opponent.
    - Each player secretly positions their ships on their own grid.
        Ships may only be placed horizontally or vertically — diagonal placements are not allowed.
    - The standard fleet consists of different types of ships
        (e.g., Aircraft Carrier, Battleship, Submarine, Destroyer, Patrol Boat),
        with each type occupying a specific number of consecutive cells.

    Gameplay:
    - Players alternate turns, attempting to target and hit their opponent's ships.
    - After each shot, the opponent responds with either "hit" or "miss."
    - When all cells of a specific ship are hit, that ship is considered sunk.
        The opponent must announce the event (e.g., "You sunk my battleship!").

    Winning the Game:
    - A player wins when all of their opponent's ships are completely sunk.
    - The first player to sink the entire enemy fleet is declared the winner.
 */

public class BattleshipGameMain {
    public static void main(String[] args) {
        Game game = init();
        startGame(game);
    }

    public static Game init() {
        Board board1 = initBoard1();
        Player player1 = new Player("Suman", board1);

        Board board2 = initBoard2();
        Player player2 = new Player("Punit", board2);

        Game game = new Game(player1, player2);
        game.startNewGame();
        return game;
    }

    public static Board initBoard1() {
        Board board1 = new Board();
        List<Position> p1Ship1 = List.of(new Position(0, 0), new Position(0, 1));
        Ship ship1 = new Ship(ShipType.DESTROYER, p1Ship1);
        board1.getShips().add(ship1);
        for (Position pos : p1Ship1) {
            board1.getGrid()[pos.getRow()][pos.getColumn()].setShip(ship1);
        }
        return board1;
    }

    public static Board initBoard2() {
        Board board2 = new Board();
        List<Position> p2Ship1 = List.of(new Position(5, 5), new Position(5, 6));
        Ship ship2 = new Ship(ShipType.DESTROYER, p2Ship1);
        board2.getShips().add(ship2);
        for (Position pos : p2Ship1) {
            board2.getGrid()[pos.getRow()][pos.getColumn()].setShip(ship2);
        }
        return board2;
    }

    public static void startGame(Game game) {
        Scanner scanner = new Scanner(System.in);

        while (!game.isGameOver()) {
            Player currentPlayer = game.getCurrentPlayer();
            Player opponent = game.getOpponentPlayer();

            System.out.println();
            System.out.println(currentPlayer.getName() + "'s turn to attack!");

            // Display the opponent's board (for player reference)
            System.out.println();
            System.out.println(opponent.getName() + "'s board: BEFORE");
            opponent.getBoard().display(false);
            System.out.println();

            // Get player's attack position and make Move
            Position attackPosition = getPlayerMove(scanner);
            game.makeMove(attackPosition);
            System.out.println();

            // Display the player's board after their attack
            System.out.println(opponent.getName() + "'s board: AFTER");
            opponent.getBoard().display(false);

            // Only switch player if game not over
            if (!game.isGameOver()) {
                game.switchPlayer();
                System.out.println();
                System.out.println("---------------------------------");
                SleepUtil.sleep(3000);
            }
        }

        // Output the winner when the game ends
        System.out.println();
        System.out.println("Game Over! " + game.getWinner().getName() + " wins.");
        scanner.close();
    }

    public static Position getPlayerMove(Scanner scanner) {
        int row, col;
        while (true) {
            System.out.print("Enter attack row (0-9): ");
            row = scanner.nextInt();
            if (row >= 0 && row < 10) {
                break;
            } else {
                System.out.println("Invalid row! Please enter a row between 0 and 9.");
            }
        }
        while (true) {
            System.out.print("Enter attack column (0-9): ");
            col = scanner.nextInt();
            if (col >= 0 && col < 10) {
                break;
            } else {
                System.out.println("Invalid column! Please enter a column between 0 and 9.");
            }
        }
        return new Position(row, col);
    }
}
