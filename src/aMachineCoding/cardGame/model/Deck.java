package aMachineCoding.cardGame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (cards.isEmpty()) return null;
        return cards.removeLast();
    }

    public int remainingCards() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
