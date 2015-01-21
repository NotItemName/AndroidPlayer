package com.player.utils;

import com.drew.lang.StringUtil;
import org.apache.log4j.Logger;

/**
 * @author Николай
 */
public class LoggerUtils {

    public static void debug(Logger logger, String... messageParts) {
        String message = StringUtil.join(messageParts, " ");
        logger.debug(message);
    }

    public static void info(Logger logger, String... messageParts) {
        String message = StringUtil.join(messageParts, " ");
        logger.info(message);
    }
}
