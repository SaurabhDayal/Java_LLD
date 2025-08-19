package aMachineCoding.undoOperationInGame;

import aMachineCoding.undoOperationInGame.model.TicTacToe;
import aMachineCoding.undoOperationInGame.strategies.ReversibleMoveStrategy;
import aMachineCoding.undoOperationInGame.strategies.SnapshotStrategy;
import aMachineCoding.undoOperationInGame.strategies.StackStrategy;

import java.util.Scanner;

/*
Fundamentally, there are two core techniques for implementing undo operations in games:

1️⃣ Move-Based Undo (Stack-Based)
    - Stores only the player’s moves or incremental changes (lightweight).
    - Examples: StackStrategy, IncrementalStrategy.
    - Pros:
        1. Very memory efficient — only the minimal move information is stored.
        2. Quick to reverse the last move directly.
    - Cons:
        1. To reconstruct a specific past state, we may need to replay all moves from the beginning up to that point.
        2. Replay becomes costly in long or complex games.
    - Undo Options:
        1. Direct Undo: Simply pop and reverse the last move.
        2. Replay Undo: Rebuild the game state by replaying all moves up to the one before the undone move.

2️⃣ State-Based Undo (Snapshot-Based)
    - Stores a complete copy (snapshot) of the game state after each move (heavier).
    - Examples: SnapshotStrategy, VersioningStrategy.
    - Pros:
        1. Instant restoration — the board can be reverted immediately without replaying moves.
        2. Well-suited for games with complex internal state or cascading effects where replaying moves may be error-prone or expensive.
    - Cons:
        1. Higher memory consumption — each snapshot duplicates the entire board.
        2. Requires efficient deep-copy mechanisms to minimize overhead.
    - Undo Options:
        1. Simple Restore: Revert directly to the previously saved snapshot.
           (Unlike move-based undo, no replay option exists.)

Choosing the right strategy:
    1. Use Move-Based if memory is limited and moves are simple to replay.
    2. Use Snapshot-Based if instant restore is important or the game has complex states that are hard to reconstruct from moves.

──────────────────────────────────────────────────────────────
📌 Future Scope:
    1. Redo Support
        - Move-Based (Stack):
            • Maintain two stacks: undoStack and redoStack.
            • On undo → pop from undoStack and push onto redoStack.
            • On redo → pop from redoStack and apply again to undoStack.
        - Snapshot-Based:
            • Maintain two lists: pastStates and futureStates.
            • On undo → move the current state to futureStates and restore the last pastState.
            • On redo → move a state back from futureStates to pastStates and restore it.

    2. Linear Versioning
        - Move-Based (Stack):
            • If a new move is made after an undo, clear the redoStack
              to maintain a single linear history (no branching).
        - Snapshot-Based:
            • If a new snapshot is taken after an undo, discard all futureStates
              so history remains strictly linear.

*/

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an undo strategy:");
        System.out.println("1 - Stack Undo");
        System.out.println("2 - Snapshot Undo");
        System.out.print("Enter choice (1-2): ");
        int choice = scanner.nextInt();

        ReversibleMoveStrategy strategy = switch (choice) {
            case 1 -> new StackStrategy();    // Stack-based undo
            case 2 -> new SnapshotStrategy(); // Snapshot-based undo
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
        game.printBoard();             // Print the current board state

        System.out.println("Undo:");
        game.undoMove();               // Undo the last move (X at 0,2 is removed)
        game.printBoard();

        System.out.println("Undo:");
        game.undoMove();               // Undo the previous move (O at 2,2 is removed)
        game.printBoard();

        System.out.println("Undo:");
        game.undoMove();               // Undo another move (X at 0,1 is removed)
        game.printBoard();

        scanner.close();               // Close the scanner
    }
}
