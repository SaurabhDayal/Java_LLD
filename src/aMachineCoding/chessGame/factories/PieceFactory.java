package aMachineCoding.chessGame.factories;

import aMachineCoding.chessGame.factories.concretePieces.*;

public class PieceFactory {
    public static Piece createPiece(String pieceType, boolean isWhitePiece) {
        return switch (pieceType.toLowerCase()) {
            case "king" -> new King(isWhitePiece);
            case "queen" -> new Queen(isWhitePiece);
            case "bishop" -> new Bishop(isWhitePiece);
            case "knight" -> new Knight(isWhitePiece);
            case "rook" -> new Rook(isWhitePiece);
            case "pawn" -> new Pawn(isWhitePiece);
            default -> throw new IllegalArgumentException("Unknown piece type: " + pieceType);
        };
    }
}
