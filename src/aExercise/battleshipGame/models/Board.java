package aExercise.battleshipGame.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final Cell[][] grid;
    private final List<Ship> ships;

    public Board() {
        this.grid = new Cell[10][10]; // Assuming a 10x10 grid
        this.ships = new ArrayList<>();
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public char receiveAttack(Position position) {
        Cell cell = grid[position.getRow()][position.getColumn()];
        cell.registerAttack(position, this);
        return cell.getResult();
    }

    public boolean allShipsSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    public void display(boolean showShips) {
        System.out.print("  ");
        for (int col = 0; col < 10; col++) {
            System.out.print(col + " ");
        }
        System.out.println();

        for (int row = 0; row < 10; row++) {
            System.out.print(row + " ");
            for (int col = 0; col < 10; col++) {
                Cell cell = grid[row][col];
                char result = cell.getResult();

                if (result != ' ') {
                    System.out.print(result + " "); // H, M, X
                } else if (showShips && cell.isOccupied()) {
                    System.out.print("S "); // show ship if requested
                } else {
                    System.out.print(". "); // unexplored cell
                }
            }
            System.out.println();
        }
    }

    private boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < 10 && col >= 0 && col < 10;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public List<Ship> getShips() {
        return ships;
    }
}
