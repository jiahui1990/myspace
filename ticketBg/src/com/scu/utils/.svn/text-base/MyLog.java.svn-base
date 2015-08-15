package com.scu.utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * 封装Log，方便使用
 * @author jiahui.li
 *
 */
public class MyLog {
	
	
	private static Logger logger = Logger.getLogger("Log");

	/**
	 * @param logMessage
	 * @param e
	 */
	public static void debug(String logMessage, Throwable e) {
		logger.debug(logMessage, e);
	}

	/**
	 * @param logMessage
	 */
	public static void debug(String logMessage) {
		logger.debug(logMessage);
	}

	/**
	 * @param className
	 * @param logMessage
	 */
	public static void debug(String className, String logMessage) {
		debug("*** " + className + " " + logMessage + " ***");
	}

	/**
	 * @param className
	 * @param logMessage
	 * @param e
	 */
	public static void debug(String className, String logMessage, Throwable e) {
		debug("*** " + className + " " + logMessage + " ***", e);
	}

	/**
	 * @param logMessage
	 * @param e
	 */
	public static void info(String logMessage, Throwable e) {
		logger.info(logMessage, e);
	}

	/**
	 * @param logMessage
	 */
	public static void info(String logMessage) {
		logger.info(logMessage);
	}

	/**
	 * @param className
	 * @param logMessage
	 */
	public static void info(String className, String logMessage) {
		info("*** " + className + " " + logMessage + " ***");
	}

	/**
	 * @param className
	 * @param logMessage
	 * @param e
	 */
	public static void info(String className, String logMessage, Throwable e) {
		info("*** " + className + " " + logMessage + " ***", e);
	}

	/**
	 * @param logMessage
	 * @param e
	 */
	public static void warn(String logMessage, Throwable e) {
		logger.warn(logMessage, e);
	}

	/**
	 * @param logMessage
	 */
	public static void warn(String logMessage) {
		logger.warn(logMessage);
	}

	/**
	 * @param className
	 * @param logMessage
	 */
	public static void warn(String className, String logMessage) {
		warn("*** " + className + " " + logMessage + " ***");
	}

	/**
	 * @param className
	 * @param logMessage
	 * @param e
	 */
	public static void warn(String className, String logMessage, Throwable e) {
		warn("*** " + className + " " + logMessage + " ***", e);
	}

	/**
	 * @param logMessage
	 * @param e
	 */
	public static void error(String logMessage, Throwable e) {
		logger.error(logMessage, e);
	}

	/**
	 * @param logMessage
	 */
	public static void error(String logMessage) {
		logger.error(logMessage);
	}

	/**
	 * @param className
	 * @param logMessage
	 */
	public static void error(String className, String logMessage) {
		error("*** " + className + " " + logMessage + " ***");
	}

	/**
	 * @param className
	 * @param logMessage
	 * @param e
	 */
	public static void error(String className, String logMessage, Throwable e) {
		error("*** " + className + " " + logMessage + " ***", e);
	}

	/**
	 * @param logMessage
	 * @param e
	 */
	public static void fatal(String logMessage, Throwable e) {
		logger.fatal(logMessage, e);
	}

	/**
	 * @param logMessage
	 */
	public static void fatal(String logMessage) {
		logger.fatal(logMessage);
	}

	/**
	 * @param className
	 * @param logMessage
	 */
	public static void fatal(String className, String logMessage) {
		fatal("*** " + className + " " + logMessage + " ***");
	}

	/**
	 * @param className
	 * @param logMessage
	 * @param e
	 */
	public static void fatal(String className, String logMessage, Throwable e) {
		fatal("*** " + className + " " + logMessage + " ***", e);
	}

	/**
	 * @param className
	 */
	public static void startLog(String className) {
		logger.info("*** " + className + " Start ***");
	}

	/**
	 * @param className
	 */
	public static void endLog(String className) {
		logger.info("*** " + className + " End ***");
	}

	/**
	 * @param l
	 * @param msg
	 */
	public static void log(Level l, String msg) {
		logger.log(l, msg);
	}
}
