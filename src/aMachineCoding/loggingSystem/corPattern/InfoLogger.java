package aMachineCoding.loggingSystem.corPattern;

import aMachineCoding.loggingSystem.models.LogLevel;
import aMachineCoding.loggingSystem.strategies.LogAppender;

public class InfoLogger extends LogHandler {
    public InfoLogger(LogLevel level, LogAppender appender) {
        super(level, appender);
    }

    @Override
    protected void write(String message) {
        System.out.println("INFO: " + message);
    }
}