package aMachineCoding.loggingSystem.corPattern;

import aMachineCoding.loggingSystem.models.LogLevel;
import aMachineCoding.loggingSystem.models.LogMessage;
import aMachineCoding.loggingSystem.strategies.LogAppender;

public abstract class LogHandler {

    protected LogLevel level; // Use enum
    protected LogAppender appender;
    protected LogHandler nextLogger;

    public LogHandler(LogLevel level, LogAppender appender) {
        this.level = level;
        this.appender = appender;
    }

    public void setNextLogger(LogHandler nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(LogLevel msgLevel, String message) {

        // Compare using enum
        // true if the message’s severity is greater than or equal to the logger’s severity.
        if (msgLevel.isGreaterOrEqual(level)) {
            LogMessage logMsg = new LogMessage(msgLevel, message);
            if (appender != null) {
                appender.append(logMsg);
            }
            write(message); // Optional if you want COR-specific print
        } else if (nextLogger != null) {
            nextLogger.logMessage(msgLevel, message);
        }
    }

    abstract protected void write(String message);
}
