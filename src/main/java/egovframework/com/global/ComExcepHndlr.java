package egovframework.com.global;


import egovframework.com.global.exception.ExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Class Name : ComExcepHndlr.java
 * @Description : 공통서비스의 exception 처리 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 13.     이삼섭
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 13.
 * @version
 * @see
 *
 */
public class ComExcepHndlr implements ExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComExcepHndlr.class);

    /*
    @Resource(name = "otherSSLMailSender")
    private SimpleSSLMail mailSender;
     */
    /**
     * 발생된 Exception을 처리한다.
     */
    public void occur(Exception ex, String packageName) {
    	//log.debug(" ServiceExceptionHandler run...............");
    	
    	/*
		try {
			mailSender. send(ex, packageName);
			log.debug(" sending a alert mail  is completed ");		
		} catch (Exception e) {
			LOGGER.error(packageName, ex);
		}
		*/
		
    	LOGGER.error(packageName, ex);
	}
}
