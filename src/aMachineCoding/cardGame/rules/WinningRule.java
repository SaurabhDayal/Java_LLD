package aMachineCoding.cardGame.rules;

import aMachineCoding.cardGame.model.Player;

import java.util.List;

public interface WinningRule {
    Player determineWinner(List<Player> players);

    String getWinnerSummary(Player winner);
}
