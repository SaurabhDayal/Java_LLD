package aMachineCoding.undoOperationInGame;

import aMachineCoding.undoOperationInGame.model.TicTacToe;
import aMachineCoding.undoOperationInGame.strategies.*;

import java.util.Scanner;

/*
If you analyze closely,
Incremental Reversal (Rollback Strategy) is a variation of the Stack-Based Approach,
and Versioning System is an extension of the State Snapshot (Memento Pattern).

So, fundamentally, there are two core techniques for implementing undo operations:

1️⃣ Move-Based Undo (Stack-Based Approach & Incremental Reversal)
    - Uses a stack or delta tracking to store only small reversible changes.
    - Efficient in terms of memory (does not store full states).
    - Ideal for games with small, easily reversible moves.

2️⃣ State-Based Undo (Snapshot & Versioning System)
    - Stores full snapshots or state history at each step.
    - Requires more memory, but restoring a state is instant (O(1)).
    - Useful for complex games where reconstructing state is difficult.
 */
public class UndoOperationGameMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner for user input

        // Prompt user for undo strategy selection
        System.out.println("Choose an undo strategy:");
        System.out.println("1 - Stack Undo");
        System.out.println("2 - Snapshot Undo");
        System.out.println("3 - Incremental Undo");
        System.out.println("4 - Versioning Undo");
        System.out.print("Enter choice (1-4): ");

        int choice = scanner.nextInt(); // Read user input
        ReversibleMoveStrategy strategy = switch (choice) {
            case 1 -> new StackStrategy(); // Stack-based undo
            case 2 -> new SnapshotStrategy(); // Snapshot-based undo
            case 3 -> new IncrementalStrategy(); // Incremental undo
            case 4 -> new VersioningStrategy(); // Versioning undo
            default -> {
                System.out.println("Invalid choice, defaulting to Stack Undo.");
                yield new StackStrategy();
            }
        };

        // Assign the selected strategy based on user input

        TicTacToe game = new TicTacToe(strategy); // Initialize game with selected strategy

        game.makeMove(0, 0); // Player X moves at (0,0)
        game.makeMove(1, 1); // Player O moves at (1,1)
        game.makeMove(0, 1); // Player X moves at (0,1)
        game.makeMove(2, 2); // Player O moves at (2,2)
        game.makeMove(0, 2); // Player X moves at (0,2), forming a possible row

        game.printBoard(); // Print the current board state

        System.out.println("Undo:");
        game.undo(); // Undo the last move (X at 0,2 is removed)
        game.printBoard();

        System.out.println("Undo:");
        game.undo(); // Undo the previous move (O at 2,2 is removed)
        game.printBoard();

        System.out.println("Undo:");
        game.undo(); // Undo another move (X at 0,1 is removed)
        game.printBoard();

        scanner.close(); // Close the scanner
    }
}
