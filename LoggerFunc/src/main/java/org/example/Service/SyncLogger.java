package org.example.Service;

import org.example.Configuration.Configuration;
import org.example.Enum.LogLevel;
import org.example.Interface.Logger;
import org.example.Interface.Sink;
import org.example.Message.LoggingMessage;

public class SyncLogger implements Logger {
    private final Sink sink;

    public SyncLogger(Configuration config) {
        this.sink = config.getSink();
    }

    @Override
    public void log(LogLevel level, String message) {
        if (level.ordinal() >= sink.getLevel().ordinal()) {
            sink.log(new LoggingMessage(message, level));
        }
    }

    @Override
    public void info(String message) { log(LogLevel.INFO, message); }
    @Override
    public void warn(String message) { log(LogLevel.WARN, message); }
    @Override
    public void debug(String message) { log(LogLevel.DEBUG, message); }
    @Override
    public void error(String message) { log(LogLevel.ERROR, message); }
    @Override
    public void fatal(String message) { log(LogLevel.FATAL, message); }

    @Override
    public void shutdown() {
        // No-op for synchronous logger
    }
}