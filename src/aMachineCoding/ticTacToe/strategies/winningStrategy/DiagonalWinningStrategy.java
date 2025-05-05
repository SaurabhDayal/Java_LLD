package aMachineCoding.ticTacToe.strategies.winningStrategy;

import aMachineCoding.ticTacToe.models.Board;
import aMachineCoding.ticTacToe.models.Move;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy {
    //2 Diagonals.
    private final Map<Character, Integer> leftDiagonalMap = new HashMap<>(); //starting from 0,0
    private final Map<Character, Integer> rightDiagonalMap = new HashMap<>(); //starting from 0,n-1

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Character aChar = move.getPlayer().getSymbol().getaChar();
        int boardSize = board.getDimension();  // Store dimension once for efficiency

        // Left diagonal condition (row == col)
        if (row == col) {
            leftDiagonalMap.put(aChar, leftDiagonalMap.getOrDefault(aChar, 0) + 1);
            if (leftDiagonalMap.get(aChar) == boardSize) {
                return true;
            }
        }

        // Right diagonal condition (row + col == boardSize - 1)
        if (row + col == boardSize - 1) {
            rightDiagonalMap.put(aChar, rightDiagonalMap.getOrDefault(aChar, 0) + 1);
            if (rightDiagonalMap.get(aChar) == boardSize) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Character aChar = move.getPlayer().getSymbol().getaChar();
        int boardSize = board.getDimension();

        if (row == col && leftDiagonalMap.containsKey(aChar)) {
            int count = leftDiagonalMap.get(aChar) - 1;
            if (count <= 0) {
                leftDiagonalMap.remove(aChar);
            } else {
                leftDiagonalMap.put(aChar, count);
            }
        }

        if (row + col == boardSize - 1 && rightDiagonalMap.containsKey(aChar)) {
            int count = rightDiagonalMap.get(aChar) - 1;
            if (count <= 0) {
                rightDiagonalMap.remove(aChar);
            } else {
                rightDiagonalMap.put(aChar, count);
            }
        }
    }
}
