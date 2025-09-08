package aMachineCoding.loggingSystem.corPattern;


import aMachineCoding.loggingSystem.strategies.LogAppender;

public class ErrorLogger extends LogHandler {

    public ErrorLogger(int level, LogAppender appender) {
        super(level, appender);
    }

    @Override
    protected void write(String message) {
        System.out.println("ERROR: " + message);
    }
}
