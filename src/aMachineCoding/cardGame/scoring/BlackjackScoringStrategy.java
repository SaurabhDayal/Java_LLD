package aMachineCoding.cardGame.scoring;

import aMachineCoding.cardGame.model.Card;
import aMachineCoding.cardGame.model.Rank;

public class BlackjackScoringStrategy implements ScoringStrategy {

    @Override
    public int evaluate(Card card) {
        Rank rank = card.getRank();
        switch (rank) {
            case JACK:
            case QUEEN:
            case KING:
                return 10;
            case ACE:
                return 11; // Treat Ace as 11 for simplicity
            default:
                return rank.getValue(); // Correct and clean
        }
    }

}
