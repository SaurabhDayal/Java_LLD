package aMachineCoding.undoOperationInGame;

import aMachineCoding.undoOperationInGame.model.TicTacToe;
import aMachineCoding.undoOperationInGame.strategies.*;

import java.util.Scanner;

/*
So, fundamentally, there are two core techniques for implementing undo operations:

1️⃣ Move-Based Undo (Stack-Based & Incremental)
    - Stores only the player's move or changes (lightweight).
    - Examples: StackStrategy, IncrementalStrategy.
    - Pros: Memory efficient, easy to reverse small changes quickly.
    - Cons: For full game state reconstruction, may require replaying all moves from the start, which can be costly in complex games.

2️⃣ State-Based Undo (Snapshot & Versioning)
    - Stores complete board state (snapshot) after or before each move (heavier).
    - Examples: SnapshotStrategy, VersioningStrategy.
    - Pros: Instant state restoration without replay; better suited for games with complex internal state or cascading effects.
    - Cons: Higher memory consumption; requires efficient snapshot/copy mechanisms to minimize overhead.

🔄 Undo/Redo Implementation Patterns:
- Linear Undo:
    * Simple Last-In-First-Out (LIFO) undo, using either a stack of moves (StackStrategy) or a stack/list of snapshots (SnapshotStrategy).
    * Supports straightforward “undo last action” behavior.

- Replay Undo:
    * Rebuilds the game state from the beginning by replaying all moves except the last undone one.
    * Useful when only moves are stored and state is expensive to save, but can be slow as the game progresses.

- Versioned Undo/Redo:
    * Maintains a timeline of saved states or moves allowing navigation backward and forward without deletion.
    * Enables redo functionality and branching in complex editors or games.

- Incremental Undo:
    * Stores only the incremental differences (deltas) between states.
    * More memory-efficient than full snapshots, and faster than replay in many cases.
    * Requires precise tracking and careful application/reversion of changes.

Choose the appropriate undo strategy based on your game’s complexity, memory constraints, and desired user experience.
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
