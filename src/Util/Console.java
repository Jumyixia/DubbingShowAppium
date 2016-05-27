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
	 * Debug����LOG
	 * @param msg �û���ֵ��������ӡ������
	 */
	public static void debugLog(String msg){
		if(isLog){
		logger.debug(msg);
		}
	}
	
	/**
	 * Debug����LOG
	 * @param message ������Ϣ���
	 */
	public static void debugLog(Object message){
		if(isLog){
		logger.debug(message);
		}
	}
	
	/**
	 * Error�����LOG
	 * @param msg �û���ֵ��������ӡ������
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
	 * Error�����LOG
	 * @param message ������Ϣ���
	 */
	public static void errorLog(Object message){
		if(isLog){
			logger.error(message);
		}
	}
	
	/**
	 * log�в����쳣
	 * @param message
	 * @param t
	 */
	public static void errorLog(Object message, Throwable t){
		if(isLog){
			logger.error(message, t);
		}
	}
	
	/**
	 * Info�����LOG
	 * @param msg	�û���ֵ��������ӡ������
	 */
	public static void infoLog(String msg){
		if(isLog){
			logger.info(msg);
		}
	}
	
	/**
	 * Info�����LOG
	 * @param message ������Ϣ���
	 */
	public static void infoLog(Object message){
		if(isLog){
			logger.info(message);
		}
	}
	
	/**
	 * Warn�����LOG
	 * @param msg �û���ֵ��������ӡ������
	 */
	public static void warnLog(String msg){
		if(isLog){
			logger.warn(msg);
		}
	}
	
	/**
	 * Warn�����LOG
	 * @param message ������Ϣ���
	 */
	public static void warnLog(Object message){
		if(isLog){
			logger.warn(message);
		}
	}
	
	/**
	 * Fatal�����LOG
	 * @param msg �û���ֵ��������ӡ������
	 */
	public static void fatalLog(String msg){
		if(isLog){
			logger.fatal(msg);
		}
	}
	
	/**
	 * Fatal�����LOG
	 * @param message ������Ϣ���
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

