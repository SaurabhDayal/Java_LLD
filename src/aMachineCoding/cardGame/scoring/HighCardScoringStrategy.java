package aMachineCoding.cardGame.scoring;

import aMachineCoding.cardGame.model.Card;

public class HighCardScoringStrategy implements ScoringStrategy {
    @Override
    public int evaluate(Card card) {
        // Use the defined value in Rank instead of ordinal
        return card.getRank().getValue();
    }
}
