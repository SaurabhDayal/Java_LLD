package aMachineCoding.battleshipGame.models;

public class Cell {
    private Ship ship;
    char result; // ' ' = untouched, 'M' = miss, 'H' = hit, 'X' = sunk

    public Cell() {
        this.result = ' '; // default to untouched
        this.ship = null;
    }

    public boolean isOccupied() {
        return ship != null;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Ship getShip() {
        return ship;
    }

    public char getResult() {
        return result;
    }

    public void registerAttack(Position position, Board board) {
        if (ship != null) {
            ship.registerHit(position);
            result = 'H'; // always mark as hit initially

            // If the ship is now sunk, update all positions
            if (ship.isSunk()) {
                ship.markAsSunk(board);
            }
        } else {
            result = 'M'; // miss
        }
    }
}
