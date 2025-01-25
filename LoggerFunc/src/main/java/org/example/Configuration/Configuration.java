package org.example.Configuration;

import org.example.Enum.LogLevel;
import org.example.Interface.Sink;

public class Configuration {
    private final String timestampFormat;
    private final LogLevel logLevel;
    private final boolean isAsync;
    private final int bufferSize;
    private final Sink sink;

    public Configuration(String timestampFormat, LogLevel logLevel, boolean isAsync, int bufferSize, Sink sink) {
        this.timestampFormat = timestampFormat;
        this.logLevel = logLevel;
        this.isAsync = isAsync;
        this.bufferSize = bufferSize;
        this.sink = sink;
    }

    public String getTimestampFormat() {
        return timestampFormat;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public boolean isAsync() {
        return isAsync;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public Sink getSink() {
        return sink;
    }
}
