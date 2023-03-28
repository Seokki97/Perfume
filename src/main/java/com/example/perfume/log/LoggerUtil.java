package com.example.perfume.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LoggerUtil {
    private static final Logger LOGGER_UTIL = LoggerFactory.getLogger(LoggerUtil.class);

    private static int FIRST_API_COUNT = 0;
    private static int SECOND_API_COUNT = 0;
    private static int THIRD_API_COUNT = 0;

    public static void countFirstApi(String message) {
        FIRST_API_COUNT += 1;
        LOGGER_UTIL.info(message + FIRST_API_COUNT);
    }

    public static void countSecondApi(String message) {
        SECOND_API_COUNT += 1;
        LOGGER_UTIL.info(message + SECOND_API_COUNT);
    }

    public static void countThirdApi(String message) {
        THIRD_API_COUNT += 1;
        LOGGER_UTIL.info(message + THIRD_API_COUNT);
    }


    public static void logError(String message, Throwable throwable) {
        LOGGER_UTIL.error(message, throwable);
    }
}
