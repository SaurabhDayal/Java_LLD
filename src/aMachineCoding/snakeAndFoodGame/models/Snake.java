package aMachineCoding.snakeAndFoodGame.models;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Snake {

    private final Deque<Pair> body; // Snake body as a deque of positions
    private final Map<Pair, Boolean> positionMap; // For O(1) collision check

    public Snake() {
        this.body = new LinkedList<>();
        this.positionMap = new HashMap<>();
        // Initialize snake at position [0,0]
        Pair initialPos = new Pair(0, 0);
        this.body.offerFirst(initialPos);
        this.positionMap.put(initialPos, true);
    }

    public Deque<Pair> getBody() {
        return body;
    }

    public Map<Pair, Boolean> getPositionMap() {
        return positionMap;
    }

    public int size() {
        return body.size();
    }
}
