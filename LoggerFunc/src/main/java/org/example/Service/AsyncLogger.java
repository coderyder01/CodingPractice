package org.example.Service;

import org.example.Configuration.Configuration;
import org.example.Enum.LogLevel;
import org.example.Interface.Logger;
import org.example.Interface.Sink;
import org.example.Message.LoggingMessage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AsyncLogger implements Logger {
    private final Sink sink;
    private final BlockingQueue<LoggingMessage> queue;
    private final Thread worker;
    private volatile boolean running;

    public AsyncLogger(Configuration config) {
        this.sink = config.getSink();
        this.queue = new LinkedBlockingQueue<>(config.getBufferSize());
        this.running = true;

        this.worker = new Thread(() -> {
            while (running || !queue.isEmpty()) {
                try {
                    LoggingMessage message = queue.take();
                    sink.log(message);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        worker.start();
    }

    @Override
    public void log(LogLevel level, String message) {
        if (level.ordinal() >= sink.getLevel().ordinal()) {
            queue.offer(new LoggingMessage(message, level));
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
        running = false;
        worker.interrupt();
    }
}

