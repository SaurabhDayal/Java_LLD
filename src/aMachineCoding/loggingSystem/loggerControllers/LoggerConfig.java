package aMachineCoding.loggingSystem.loggerControllers;


import aMachineCoding.loggingSystem.models.LogLevel;
import aMachineCoding.loggingSystem.strategies.LogAppender;

public class LoggerConfig {

    private final LogLevel logLevel;
    private final LogAppender logAppender;

    public LoggerConfig(LogLevel logLevel, LogAppender logAppender) {
        this.logLevel = logLevel;
        this.logAppender = logAppender;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public LogAppender getLogAppender() {
        return logAppender;
    }
}
