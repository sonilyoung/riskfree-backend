package egovframework.com.domain.user.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.com.domain.user.dao.UserDAO;
import egovframework.com.domain.user.domain.User;
import egovframework.com.domain.user.parameter.UserParameter;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import egovframework.com.global.util.AES256Util;

@Service
public class UserServiceImpl implements UserService{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDAO repository;
	
	
	@Override
	public User getUser(long userId) {
		return repository.getUser(userId);
	}

	@Override
	public void modifyUser(UserParameter parameter) {
		repository.modifyUser(parameter);
	}

	@Override
	public void modifyPwd(UserParameter parameter, String loginPwd) {
		
		try {
			AES256Util aesUtil = new AES256Util();
	        String pwEnc = aesUtil.encrypt(parameter.getCurrentPwd());
	
	        LOGGER.debug("loginPwd    ]" + loginPwd + "[");
	        LOGGER.debug("pwEnc       ]" + pwEnc + "[");
	
	        if (StringUtils.equals(pwEnc, loginPwd)) {
	        	
	        	if(StringUtils.equals(parameter.getChangePwd(), parameter.getConfirmPwd())) {
	        		
	        		parameter.setChangePwd(aesUtil.encrypt(parameter.getChangePwd()));
	        		repository.modifyPwd(parameter);
	        		
	        	} else {
	        		
	        		throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {"비밀번호가 일치하지 않습니다."});
	        	}
	        	
	        } else {
	        	throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {"비밀번호가 일치하지 않습니다."});
	        }
		
		} catch(Exception e) {
			
			throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {e.getMessage()});
			
		}
		
	}

	@Override
	public Long getUserCount(UserParameter parameter) {
		
		return repository.getUserCount(parameter);
	}

	@Override
	public void resetPwd(UserParameter parameter) {
		
		try {
			AES256Util aesUtil = new AES256Util();
			
	        LOGGER.debug("parameter.getChangePwd()    ]" + parameter.getChangePwd() + "[");
	        LOGGER.debug("parameter.getConfirmPwd()   ]" + parameter.getConfirmPwd() + "[");
	
	        if (StringUtils.equals(parameter.getChangePwd(), parameter.getConfirmPwd())) {
	        		
        		parameter.setChangePwd(aesUtil.encrypt(parameter.getChangePwd()));
        		repository.modifyPwd(parameter);
	        	
	        } else {
	        	throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {"비밀번호","비밀번호가 일치하지 않습니다."});
	        }
		
		} catch(Exception e) {
			
			throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {e.getMessage()});
			
		}
	}
}
