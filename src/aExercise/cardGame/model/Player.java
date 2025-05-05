package aScalerModule_08_LLD_3.assign_02.cardGame.model;

import aScalerModule_08_LLD_3.assign_02.cardGame.scoring.ScoringStrategy;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public void receiveCard(Card card) {
        if (card != null) {
            hand.add(card);
        }
    }

    public List<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public Card getHighestCard(ScoringStrategy strategy) {
        if (hand.isEmpty()) {
            return null;
        }

        Card highest = hand.get(0);
        int highestScore = strategy.evaluate(highest);

        for (int i = 1; i < hand.size(); i++) {
            Card current = hand.get(i);
            int currentScore = strategy.evaluate(current);

            if (currentScore > highestScore) {
                highest = current;
                highestScore = currentScore;
            }
        }

        return highest;
    }
}
