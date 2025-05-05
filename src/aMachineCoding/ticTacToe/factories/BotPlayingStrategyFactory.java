package aMachineCoding.ticTacToe.factories;

import aMachineCoding.ticTacToe.models.BotDifficultyLevel;
import aMachineCoding.ticTacToe.strategies.botPlayingStrategy.BotPlayingStrategy;
import aMachineCoding.ticTacToe.strategies.botPlayingStrategy.EasyBotPlayingStrategy;
import aMachineCoding.ticTacToe.strategies.botPlayingStrategy.HardBotPlayingStrategy;
import aMachineCoding.ticTacToe.strategies.botPlayingStrategy.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel) {
        if (botDifficultyLevel.equals(BotDifficultyLevel.EASY)) {
            return new EasyBotPlayingStrategy();
        } else if (botDifficultyLevel.equals(BotDifficultyLevel.MEDIUM)) {
            return new MediumBotPlayingStrategy();
        } else {
            return new HardBotPlayingStrategy();
        }
    }
}
