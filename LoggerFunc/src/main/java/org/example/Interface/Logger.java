package org.example.Interface;

import org.example.Enum.LogLevel;

public interface Logger {
    void log(LogLevel level, String message);
    void info(String message);
    void warn(String message);
    void debug(String message);
    void error(String message);
    void fatal(String message);
    void shutdown();
}
