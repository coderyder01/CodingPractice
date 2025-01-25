package org.example;


import org.example.Configuration.Configuration;
import org.example.Enum.LogLevel;
import org.example.Interface.Logger;
import org.example.Interface.Sink;
import org.example.Service.ConsoleSink;
import org.example.Utils.LoggerFactory;

public class Main {
    public static void main(String[] args) {
        Sink consoleSink = new ConsoleSink(LogLevel.INFO);
        Configuration config = new Configuration("yyyy-MM-dd HH:mm:ss", LogLevel.INFO, true, 25, consoleSink);
        Logger logger = LoggerFactory.createLogger(config);

        logger.info("Info message");
        logger.warn("Warn message");
        logger.debug("Debug message");  // This won't be logged due to log level
        logger.error("Error message");

        logger.shutdown();
    }
}
