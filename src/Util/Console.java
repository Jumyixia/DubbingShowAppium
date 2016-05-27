package Util;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;


public class Console {
	static boolean isLog = true;
	static final Logger logger = LogManager.getLogger(Console.class.getName());
	public static Logger getLogger() {
		return logger;
	}
	static{
		DOMConfigurator.configure("Log4j.xml");
	}
	
	
	/**
	 * Debug级别LOG
	 * @param msg 用户赋值，期望打印的内容
	 */
	public static void debugLog(String msg){
		if(isLog){
		logger.debug(msg);
		}
	}
	
	/**
	 * Debug级别LOG
	 * @param message 对象信息输出
	 */
	public static void debugLog(Object message){
		if(isLog){
		logger.debug(message);
		}
	}
	
	/**
	 * Error级别的LOG
	 * @param msg 用户赋值，期望打印的内容
	 */
	public static void errorLog(String msg){
		if(isLog){
			logger.error(msg);
		}
	}
	
	public static void errorLog(String msg, Throwable t){
		if(isLog){
			logger.error(msg, t);
		}
	}
	
	/**
	 * Error级别的LOG
	 * @param message 对象信息输出
	 */
	public static void errorLog(Object message){
		if(isLog){
			logger.error(message);
		}
	}
	
	/**
	 * log中捕获异常
	 * @param message
	 * @param t
	 */
	public static void errorLog(Object message, Throwable t){
		if(isLog){
			logger.error(message, t);
		}
	}
	
	/**
	 * Info级别的LOG
	 * @param msg	用户赋值，期望打印的内容
	 */
	public static void infoLog(String msg){
		if(isLog){
			logger.info(msg);
		}
	}
	
	/**
	 * Info级别的LOG
	 * @param message 对象信息输出
	 */
	public static void infoLog(Object message){
		if(isLog){
			logger.info(message);
		}
	}
	
	/**
	 * Warn级别的LOG
	 * @param msg 用户赋值，期望打印的内容
	 */
	public static void warnLog(String msg){
		if(isLog){
			logger.warn(msg);
		}
	}
	
	/**
	 * Warn级别的LOG
	 * @param message 对象信息输出
	 */
	public static void warnLog(Object message){
		if(isLog){
			logger.warn(message);
		}
	}
	
	/**
	 * Fatal级别的LOG
	 * @param msg 用户赋值，期望打印的内容
	 */
	public static void fatalLog(String msg){
		if(isLog){
			logger.fatal(msg);
		}
	}
	
	/**
	 * Fatal级别的LOG
	 * @param message 对象信息输出
	 */
	public static void fatalLog(Object message){
		if(isLog){
			logger.fatal(message);
		}
	}
	public static void main(String arg[]){
		DOMConfigurator.configure("Log4j.xml");
		logger.debug("Here is some DEBUG");

		logger.info("Here is some INFO");

		logger.warn("Here is some WARN");

		logger.error("Here is some ERROR");

		logger.fatal("Here is some FATAL");
	}
}

