package com.example.perfume.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LoggerUtil {
    private static final Logger LOGGER_UTIL = LoggerFactory.getLogger(LoggerUtil.class);

    public static void logInfo(String message){
        LOGGER_UTIL.info(message);
    }

    public static void logError(String message, Throwable throwable){
        LOGGER_UTIL.error(message,throwable);
    }
}
