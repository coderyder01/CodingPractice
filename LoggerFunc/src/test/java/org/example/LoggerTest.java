package org.example;

import org.example.Configuration.Configuration;
import org.example.Enum.LogLevel;
import org.example.Interface.Logger;
import org.example.Interface.Sink;
import org.example.Service.ConsoleSink;
import org.example.Utils.LoggerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoggerTest {
    private Logger logger;
    private Configuration syncConfig;
    private Configuration asyncConfig;

    @BeforeEach
    public void setUp() {
        Sink consoleSink = new ConsoleSink(LogLevel.INFO);
        syncConfig = new Configuration("yyyy-MM-dd HH:mm:ss", LogLevel.INFO, false, 25, consoleSink);
        asyncConfig = new Configuration("yyyy-MM-dd HH:mm:ss", LogLevel.INFO, true, 25, consoleSink);
    }

    @AfterEach
    public void tearDown() {
        if (logger != null) {
            logger.shutdown();
        }
    }

    @Test
    public void testSyncLogging() {
        logger = LoggerFactory.createLogger(syncConfig);
        logger.info("Sync Info message");
        logger.warn("Sync Warn message");
        logger.error("Sync Error message");
    }

    @Test
    public void testAsyncLogging() {
        logger = LoggerFactory.createLogger(asyncConfig);
        logger.info("Async Info message");
        logger.warn("Async Warn message");
        logger.error("Async Error message");
    }

    @Test
    public void testConcurrentLogging() throws InterruptedException {
        logger = LoggerFactory.createLogger(asyncConfig);

        Runnable logTask = () -> {
            for (int i = 0; i < 100; i++) {
                logger.info("Concurrent Info message " + i);
            }
        };

        Thread thread1 = new Thread(logTask);
        Thread thread2 = new Thread(logTask);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        logger.shutdown();
    }
}
