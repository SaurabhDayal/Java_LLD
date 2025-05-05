package aScalerModule_08_LLD_3.assign_02.cardGame.scoring;

import aScalerModule_08_LLD_3.assign_02.cardGame.model.Card;

public class HighCardScoringStrategy implements ScoringStrategy {
    @Override
    public int evaluate(Card card) {
        // Use the defined value in Rank instead of ordinal
        return card.getRank().getValue();
    }
}
