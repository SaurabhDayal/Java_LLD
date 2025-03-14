package aExercise.ticTacToe.factories;

import aExercise.ticTacToe.models.BotDifficultyLevel;
import aExercise.ticTacToe.strategies.botplayingstrategy.BotPlayingStrategy;
import aExercise.ticTacToe.strategies.botplayingstrategy.EasyBotPlayingStrategy;
import aExercise.ticTacToe.strategies.botplayingstrategy.HardBotPlayingStrategy;
import aExercise.ticTacToe.strategies.botplayingstrategy.MediumBotPlayingStrategy;

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
