package aExercise.cardGame.rules;

import aExercise.cardGame.model.Card;
import aExercise.cardGame.model.Player;
import aExercise.cardGame.scoring.ScoringStrategy;

import java.util.List;

public class BlackjackWinningRule implements WinningRule {

    private final ScoringStrategy scoringStrategy;

    public BlackjackWinningRule(ScoringStrategy scoringStrategy) {
        this.scoringStrategy = scoringStrategy;
    }

    @Override
    public Player determineWinner(List<Player> players) {
        Player winner = null;
        int bestScore = -1;

        for (Player player : players) {
            int total = 0;
            for (Card card : player.getHand()) {
                total += scoringStrategy.evaluate(card);
            }

            if (total <= 21 && total > bestScore) {
                bestScore = total;
                winner = player;
            }
        }

        return winner;
    }

    @Override
    public String getWinnerSummary(Player winner) {
        int total = 0;
        for (Card card : winner.getHand()) {
            total += scoringStrategy.evaluate(card);
        }
        return winner.getName() + " with total score: " + total;
    }
}
