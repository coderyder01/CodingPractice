package org.example.Interface;

import org.example.Enum.LogLevel;
import org.example.Message.LoggingMessage;

public interface Sink {
    void log(LoggingMessage message);
    LogLevel getLevel();
}
