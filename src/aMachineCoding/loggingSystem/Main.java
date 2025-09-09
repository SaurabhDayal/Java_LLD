package aMachineCoding.loggingSystem;

import aMachineCoding.loggingSystem.corPattern.DebugLogger;
import aMachineCoding.loggingSystem.corPattern.ErrorLogger;
import aMachineCoding.loggingSystem.corPattern.InfoLogger;
import aMachineCoding.loggingSystem.corPattern.LogHandler;
import aMachineCoding.loggingSystem.loggerControllers.Logger;
import aMachineCoding.loggingSystem.models.LogLevel;
import aMachineCoding.loggingSystem.strategies.ConsoleAppender;
import aMachineCoding.loggingSystem.strategies.LogAppender;

public class Main {

    // Build the chain of loggers: INFO -> DEBUG -> ERROR
    private static LogHandler getChainOfLoggers(LogAppender appender) {
        LogHandler errorLogger = new ErrorLogger(LogLevel.ERROR, appender);
        LogHandler debugLogger = new DebugLogger(LogLevel.DEBUG, appender);
        LogHandler infoLogger = new InfoLogger(LogLevel.INFO, appender);

        // The chain sequence here is flexible.
        // Why? Because each logger independently decides
        // whether to log based on msgLevel.isGreaterOrEqual(level).
        infoLogger.setNextLogger(debugLogger);
        debugLogger.setNextLogger(errorLogger);
        return infoLogger;
    }

    public static void main(String[] args) {
        LogAppender consoleAppender = new ConsoleAppender();

        // ------------------------------
        // 1️⃣ Logging via Chain of Responsibility (COR) pattern
        // Each logger in the chain decides whether to log the message based on its level.
        // Message passes along the chain, so multiple loggers can log if their level <= message level.
        // ------------------------------
        LogHandler loggerChain = getChainOfLoggers(consoleAppender);

        loggerChain.logMessage(LogLevel.INFO, "This is an information.");
        loggerChain.logMessage(LogLevel.DEBUG, "This is a debug level information.");
        loggerChain.logMessage(LogLevel.ERROR, "This is an error information.");

        // ------------------------------
        // 2️⃣ Logging via Singleton Logger
        // A single Logger instance is created per LogLevel+Appender combination.
        // The logger only logs messages that meet or exceed its configured threshold.
        // ------------------------------
        Logger logger = Logger.getInstance(LogLevel.INFO, consoleAppender);
        logger.error("Using singleton Logger - Error message");
    }
}
/*
Middleware COR
    - Middleware is different. Each middleware performs a check.
    - If a check fails, the chain stops immediately.
    - If the sequence is wrong, e.g., validating role before
      checking if the user exists, it could crash or behave incorrectly.
    - So order matters critically in middleware COR.
 */