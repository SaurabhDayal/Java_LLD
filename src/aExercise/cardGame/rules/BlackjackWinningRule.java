package aScalerModule_08_LLD_3.assign_02.cardGame.rules;

import aScalerModule_08_LLD_3.assign_02.cardGame.model.Card;
import aScalerModule_08_LLD_3.assign_02.cardGame.model.Player;
import aScalerModule_08_LLD_3.assign_02.cardGame.scoring.ScoringStrategy;

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
