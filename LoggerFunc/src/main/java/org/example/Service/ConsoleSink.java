package org.example.Service;

import org.example.Enum.LogLevel;
import org.example.Interface.Sink;
import org.example.Message.LoggingMessage;

public class ConsoleSink implements Sink {
    private final LogLevel level;

    public ConsoleSink(LogLevel level) {
        this.level = level;
    }

    @Override
    public void log(LoggingMessage message) {
        if (message.getLevel().ordinal() >= level.ordinal()) {
            System.out.println(message);
        }
    }

    @Override
    public LogLevel getLevel() {
        return level;
    }
}