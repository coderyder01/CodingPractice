package org.example.Utils;

import org.example.Configuration.Configuration;
import org.example.Interface.Logger;
import org.example.Service.AsyncLogger;
import org.example.Service.SyncLogger;

public class LoggerFactory {
    public static Logger createLogger(Configuration config) {
        return config.isAsync() ? new AsyncLogger(config) : new SyncLogger(config);
    }
}

