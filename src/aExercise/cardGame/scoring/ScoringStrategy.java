package aExercise.cardGame.scoring;

import aExercise.cardGame.model.Card;

public interface ScoringStrategy {
    int evaluate(Card card);
}
