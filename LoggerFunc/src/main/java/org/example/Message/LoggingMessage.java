package org.example.Message;

import org.example.Enum.LogLevel;

import java.time.LocalDateTime;

public class LoggingMessage {
    private final String content;
    private final LogLevel level;
    private final LocalDateTime timestamp;

    public LoggingMessage(String content, LogLevel level) {
        this.content = content;
        this.level = level;
        this.timestamp = LocalDateTime.now();
    }

    public String getContent() {
        return content;
    }

    public LogLevel getLevel() {
        return level;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("%s [%s] %s", timestamp, level, content);
    }
}
