package aMachineCoding.cardGame.scoring;

import aMachineCoding.cardGame.model.Card;

public interface ScoringStrategy {
    int evaluate(Card card);
}
