package aMachineCoding.battleshipGame.models;

import java.util.Scanner;

public class Player {
    private final String name;
    private final Board board;
    private static final Scanner scanner = new Scanner(System.in);

    public Player(String name, Board board) {
        this.name = name;
        this.board = board;
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public boolean hasLost() {
        return board.allShipsSunk();
    }

    // Player takes input for attack move
    public Position makeMove() {
        int row, col;
        while (true) {
            System.out.print(name + ", enter attack row (0-9): ");
            row = scanner.nextInt();
            if (row >= 0 && row < 10) break;
            System.out.println("Invalid row! Please enter between 0 and 9.");
        }
        while (true) {
            System.out.print(name + ", enter attack column (0-9): ");
            col = scanner.nextInt();
            if (col >= 0 && col < 10) break;
            System.out.println("Invalid column! Please enter between 0 and 9.");
        }
        return new Position(row, col);
    }
}
