package aExercise.ticTacToe.strategies.winningStrategy;

import aExercise.ticTacToe.models.Board;
import aExercise.ticTacToe.models.Move;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {
    private final Map<Integer, HashMap<Character, Integer>> rowMaps = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Character aChar = move.getPlayer().getSymbol().getaChar();

        if (!rowMaps.containsKey(row)) {
            rowMaps.put(row, new HashMap<>());
        }

        Map<Character, Integer> currRowMap = rowMaps.get(row);

        if (!currRowMap.containsKey(aChar)) {
            currRowMap.put(aChar, 0);
        }
        currRowMap.put(aChar, currRowMap.get(aChar) + 1);

        return currRowMap.get(aChar).equals(board.getDimension());
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        Character aChar = move.getPlayer().getSymbol().getaChar();

        if (rowMaps.containsKey(row)) {
            Map<Character, Integer> currRowMap = rowMaps.get(row);
            currRowMap.put(aChar, currRowMap.getOrDefault(aChar, 1) - 1);

            if (currRowMap.get(aChar) <= 0) {
                currRowMap.remove(aChar);
            }
        }
    }
}
