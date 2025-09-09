package aMachineCoding.loggingSystem.corPattern;

import aMachineCoding.loggingSystem.models.LogLevel;
import aMachineCoding.loggingSystem.strategies.LogAppender;

public class DebugLogger extends LogHandler {
    public DebugLogger(LogLevel level, LogAppender appender) {
        super(level, appender);
    }

    @Override
    protected void write(String message) {
        System.out.println("DEBUG: " + message);
    }
}