package egovframework.com.domain.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.domain.portal.logn.service.LoginService;
import egovframework.com.domain.user.domain.User;
import egovframework.com.domain.user.parameter.UserParameter;
import egovframework.com.domain.user.service.UserService;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 사용자 API 컨트롤러
 * 
 * @author KimJuHwan
 *
 */
@RestController
@RequestMapping("/users")
@Api(tags = "User Management API")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService loginService;
	
	/**
     * 나의 정보 조회
     * 
     * @param request
     * @return User
     */
	@PostMapping("/view")
	@ApiOperation(value = "Get the user",notes = "This function returns the specified User.")
	public BaseResponse<User> getUser(HttpServletRequest request) {

		try {
        	
			Login login = loginService.getLoginInfo(request);
			if (login == null) {
				throw new BaseException(BaseResponseCode.AUTH_FAIL);
			}
			
			User user = userService.getUser(login.getUserId());
        	
        	return new BaseResponse<User>(user);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, BaseResponseCode.UNKONWN_ERROR.getMessage());
        }
		
    }
	
//	/**
//     * 나의 정보 수정
//     * 
//     * @param request, parameter
//     * @return User
//     */
//	@PostMapping("/update")
//	@ApiOperation(value = "Update user information",notes = "This function updates user information")
//	public BaseResponse<Long> modifyUser(HttpServletRequest request, @RequestBody UserParameter parameter) {
//
//		try {
//        	
//			Login login = loginService.getLoginInfo(request);
//			if (login == null) {
//				throw new BaseException(BaseResponseCode.AUTH_FAIL);
//			}
//
//			parameter.setUserId(login.getUserId());
//			userService.modifyUser(parameter);
//        	
//        	return new BaseResponse<Long>(parameter.getUserId());
//        } catch (Exception e) {
//            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, BaseResponseCode.UNKONWN_ERROR.getMessage());
//        }
//		
//    }
	
	/**
     * 비밀번호 변경
     * 
     * @param request
     * @return User
     */
	@PostMapping("/passwd/update")
	@ApiOperation(value = "Update user password",notes = "This function updates user password")
	public BaseResponse<Boolean> modifyPwd(HttpServletRequest request, @RequestBody UserParameter parameter) {

		try {
        	
			Login login = loginService.getLoginInfo(request);
			if (login == null) {
				throw new BaseException(BaseResponseCode.AUTH_FAIL);
			}
			
//	        if (!StringUtils.hasText(parameter.getCurrentPwd())) {
//	            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
//	                    new String[] {"currentPwd", "현재 비밀번호"});
//	        }
	        
	        if (!StringUtils.hasText(parameter.getChangePwd())) {
	            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
	                    new String[] {"changePwd", "변경할 비밀번호"});
	        }
	        
	        if (!StringUtils.hasText(parameter.getConfirmPwd())) {
	            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
	                    new String[] {"confirmPwd", "비밀번호 확인"});
	        }
	        
	        parameter.setUserId(login.getUserId());
	        userService.modifyPwd(parameter);
	        
	        return new BaseResponse<Boolean>(true);
        	
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, BaseResponseCode.UNKONWN_ERROR.getMessage());
        }
		
    }
}
