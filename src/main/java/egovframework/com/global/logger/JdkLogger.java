package egovframework.com.global.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class  to support to logging information
 * @author Vincent Han
 * @since 2015.02.05
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일        수정자       수정내용
 *  -------       --------    ---------------------------
 *   2015.02.05	표준프레임워크센터	최초 생성
 *
 * </pre>
 */
public final class JdkLogger {
	private static final Level IGNORE_INFO_LEVEL = Level.OFF;
	private static final Level DEBUG_INFO_LEVEL = Level.FINEST;
	private static final Level INFO_INFO_LEVEL = Level.INFO;
	
	private static final Logger IGNORE_LOGGER = Logger.getLogger("ignore");
	private static final Logger DEBUG_LOGGER = Logger.getLogger("debug");
	private static final Logger INFO_LOGGER = Logger.getLogger("info");
	
	private JdkLogger() {
		// no-op
	}
	
	/**
	 * 기록이나 처리가 불필요한 경우 사용.
	 * 
	 * @param message
	 * @param exception
	 */
	public static void ignore(String message, Exception exception) {
		if (exception == null) {
			IGNORE_LOGGER.log(IGNORE_INFO_LEVEL, message);
		} else {
			IGNORE_LOGGER.log(IGNORE_INFO_LEVEL, message, exception);
		}
	}
	
	/**
	 * 기록이나 처리가 불필요한 경우 사용.
	 * 
	 * @param message
	 * @param exception
	 */
	public static void ignore(String message) {
		ignore(message, null);
	}
	
	/**
	 * 디버그 정보를 기록하는 경우 사용.
	 * 
	 * @param message
	 * @param exception
	 */
	public static void debug(String message, Exception exception) {
		if (exception == null) {
			DEBUG_LOGGER.log(DEBUG_INFO_LEVEL, message);
		} else {
			DEBUG_LOGGER.log(DEBUG_INFO_LEVEL, message, exception);
		}
	}
	
	/**
	 * 디버그 정보를 기록하는 경우 사용.
	 * 
	 * @param message
	 * @param exception
	 */
	public static void debug(String message) {
		debug(message, null);
	}
	
	/**
	 * 일반적이 정보를 기록하는 경우 사용.
	 * 
	 * @param message
	 * @param exception
	 */
	public static void info(String message) {
		INFO_LOGGER.log(INFO_INFO_LEVEL, message);
	}
}
