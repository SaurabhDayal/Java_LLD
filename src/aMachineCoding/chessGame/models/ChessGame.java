package aMachineCoding.chessGame.models;

import aMachineCoding.chessGame.factories.Piece;
import aMachineCoding.chessGame.factories.concretePieces.King;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class ChessGame {

    private final Board board;
    private final Player playerWhite;
    private final Player playerBlack;
    boolean isWhiteTurn;
    private Status status;
    private final List<Move> moveStack;

    public ChessGame(Player playerWhite, Player playerBlack) {

        this.board = Board.getInstance(8); // Initialize board with 8 rows

        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;

        this.isWhiteTurn = true;
        this.status = Status.ACTIVE;
        this.moveStack = new Stack<>();
    }

    // Start the game
    public void start() {

        Scanner scanner = new Scanner(System.in);

        while (status == Status.ACTIVE) {

            board.displayBoard();

            Player currentPlayer = isWhiteTurn ? playerWhite : playerBlack;
            System.out.println(currentPlayer.getName() + "'s turn (" + (currentPlayer.isWhiteSide() ? "White" : "Black") + ")");

            // ---------------- Source Input ----------------
            System.out.print("Enter source row and column (e.g., 6 4): ");
            String sourceInput = scanner.nextLine().trim();
            String[] sourceParts = sourceInput.split(" ");
            if (sourceParts.length != 2) {
                System.out.println("Invalid input. Please enter row and column separated by a space.");
                continue;
            }

            int startRow, startCol;
            try {
                startRow = Integer.parseInt(sourceParts[0]);
                startCol = Integer.parseInt(sourceParts[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Coordinates must be numbers.");
                continue;
            }

            // ---------------- Destination Input ----------------
            System.out.print("Enter destination row and column (e.g., 4 4): ");
            String destInput = scanner.nextLine().trim();
            String[] destParts = destInput.split(" ");
            if (destParts.length != 2) {
                System.out.println("Invalid input. Please enter row and column separated by a space.");
                continue;
            }

            int endRow, endCol;
            try {
                endRow = Integer.parseInt(destParts[0]);
                endCol = Integer.parseInt(destParts[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Coordinates must be numbers.");
                continue;
            }

            // ---------------- Validate & Execute ----------------
            Cell startCell = board.getCell(startRow, startCol);
            Cell endCell = board.getCell(endRow, endCol);

            if (startCell == null || startCell.getPiece() == null) {
                System.out.println("Invalid move: No piece at source cell.");
                continue;
            }
            
            // ✅ Check if source and destination are the same
            if (startCell == endCell) {
                System.out.println("Invalid move: Source and destination cannot be the same.");
                continue;
            }

            makeMove(new Move(startCell, endCell), currentPlayer);
        }

        System.out.println("Game Over! Status: " + this.status);
    }

    // Make a move in the game
    public void makeMove(Move move, Player player) {

        // Initial check for a valid move
        // To check if source and destination don't contain the same color pieces
        if (move.isValid()) {

            Piece sourcePiece = move.getStartCell().getPiece();

            // Check if the source piece can be moved or not
            if (sourcePiece.canMove(board, move.getStartCell(), move.getEndCell())) {

                Piece destinationPiece = move.getEndCell().getPiece();

                // Check if the destination cell contains some piece
                if (destinationPiece != null) {

                    if (destinationPiece instanceof King) {
                        if (isWhiteTurn) {
                            status = Status.WHITE_WIN;
                        } else {
                            status = Status.BLACK_WIN;
                        }
                        return;
                    }

                    // Set the destination piece as killed
                    destinationPiece.setKilled(true);
                }

                // Adding the valid move to the game logs
                moveStack.add(move);

                // Moving the source piece to the destination cell
                move.getEndCell().setPiece(sourcePiece);

                // Setting the source cell to null (means it doesn't have any piece)
                move.getStartCell().setPiece(null);

                // Toggling the turn
                isWhiteTurn = !isWhiteTurn;
                System.out.println(isWhiteTurn);
            }
        }
    }
}
