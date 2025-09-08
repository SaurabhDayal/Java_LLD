package aMachineCoding.loggingSystem.strategies;

import aMachineCoding.loggingSystem.models.LogMessage;

public interface LogAppender {
    void append(LogMessage logMessage);
}
