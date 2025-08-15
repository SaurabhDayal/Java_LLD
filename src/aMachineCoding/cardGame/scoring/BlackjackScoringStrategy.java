package aMachineCoding.cardGame.scoring;

import aMachineCoding.cardGame.model.Card;
import aMachineCoding.cardGame.model.Rank;

public class BlackjackScoringStrategy implements ScoringStrategy {

    @Override
    public int evaluate(Card card) {
        Rank rank = card.getRank();
        return switch (rank) {
            case JACK, QUEEN, KING -> 10;
            case ACE -> 11; // Treat Ace as 11 for simplicity
            default -> rank.getValue(); // Correct and clean
        };
    }

}
