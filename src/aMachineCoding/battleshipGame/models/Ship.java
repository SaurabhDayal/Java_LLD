package aMachineCoding.battleshipGame.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ship {
    private final ShipType type;            // The type of the ship (e.g., Carrier, Battleship, etc.) — defines name and size
    private final List<Position> positions; // The exact grid positions this ship occupies on the board
    private final Set<Position> hits;       // The set of positions on this ship that have been hit by attacks

    public Ship(ShipType type, List<Position> positions) {
        this.type = type;
        this.positions = new ArrayList<>(positions);
        this.hits = new HashSet<>();
    }

    public void registerHit(Position position) {
        if (!hits.contains(position) && positions.contains(position)) {
            hits.add(position);
        }
    }

    public boolean isSunk() {
        return hits.containsAll(positions);
    }

    public ShipType getType() {
        return type;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public String getName() {
        return type.getName();
    }

    public int getSize() {
        return type.getSize();
    }

    public void markAsSunk(Board board) {
        for (Position pos : positions) {
            board.getGrid()[pos.getRow()][pos.getColumn()].result = 'X';
        }
    }
}
