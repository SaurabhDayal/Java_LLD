package aExercise.ticTacToe.factories;

import aExercise.ticTacToe.models.BotDifficultyLevel;
import aExercise.ticTacToe.strategies.botplayingStrategy.BotPlayingStrategy;
import aExercise.ticTacToe.strategies.botplayingStrategy.EasyBotPlayingStrategy;
import aExercise.ticTacToe.strategies.botplayingStrategy.HardBotPlayingStrategy;
import aExercise.ticTacToe.strategies.botplayingStrategy.MediumBotPlayingStrategy;

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
