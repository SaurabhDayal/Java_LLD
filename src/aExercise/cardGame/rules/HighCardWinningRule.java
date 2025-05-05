package aScalerModule_08_LLD_3.assign_02.cardGame.rules;

import aScalerModule_08_LLD_3.assign_02.cardGame.model.Card;
import aScalerModule_08_LLD_3.assign_02.cardGame.model.Player;
import aScalerModule_08_LLD_3.assign_02.cardGame.scoring.ScoringStrategy;

import java.util.List;

public class HighCardWinningRule implements WinningRule {

    private final ScoringStrategy scoringStrategy;

    public HighCardWinningRule(ScoringStrategy scoringStrategy) {
        this.scoringStrategy = scoringStrategy;
    }

    @Override
    public Player determineWinner(List<Player> players) {
        Player winner = null;
        int highestScore = -1;

        for (Player player : players) {
            Card card = player.getHighestCard(scoringStrategy);
            int score = card != null ? scoringStrategy.evaluate(card) : -1;

            if (score > highestScore) {
                highestScore = score;
                winner = player;
            }
        }

        return winner;
    }

    @Override
    public String getWinnerSummary(Player winner) {
        Card highestCard = winner.getHighestCard(scoringStrategy);
        return winner.getName() + " with card: " + highestCard;
    }
}
