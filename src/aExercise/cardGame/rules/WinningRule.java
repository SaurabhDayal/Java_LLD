package aScalerModule_08_LLD_3.assign_02.cardGame.rules;

import aScalerModule_08_LLD_3.assign_02.cardGame.model.Player;

import java.util.List;

public interface WinningRule {
    Player determineWinner(List<Player> players);

    String getWinnerSummary(Player winner);
}
