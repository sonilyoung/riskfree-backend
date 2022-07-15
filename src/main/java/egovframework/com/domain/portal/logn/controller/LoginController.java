
package egovframework.com.domain.portal.logn.controller;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import egovframework.com.domain.portal.logn.domain.LoginRequest;
import egovframework.com.domain.portal.logn.domain.TokenResponse;
import egovframework.com.domain.portal.logn.service.LoginService;
import egovframework.com.global.OfficeMessageSource;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 일반 로그인, 인증서 로그인을 처리하는 컨트롤러 클래스
 * 
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.06
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.06  박지욱          최초 생성
 *  2011.8.26	정진오			IncludedInfo annotation 추가
 *  2011.09.07  서준식          스프링 시큐리티 로그인 및 SSO 인증 로직을 필터로 분리
 *  2011.09.25  서준식          사용자 관리 컴포넌트 미포함에 대한 점검 로직 추가
 *  2011.09.27  서준식          인증서 로그인시 스프링 시큐리티 사용에 대한 체크 로직 추가
 *  2011.10.27  서준식          아이디 찾기 기능에서 사용자 리름 공백 제거 기능 추가
 *      </pre>
 */
@RestController
@Api(tags = "Login Management API")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    private OfficeMessageSource officeMessageSource;

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "Login by loginId",
            notes = "This function returns the token of the user.")
    public BaseResponse<TokenResponse> login(HttpServletRequest request,
            @ApiParam @RequestBody LoginRequest loginRequest) {
        // LoginRequest loginRequest) {
        if (StringUtils.isEmpty(loginRequest.getLoginId())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"loginId", "로그인 아이디"});
        }
        if (StringUtils.isEmpty(loginRequest.getLoginPw())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"loginPw", "로그인 암호"});
        }
        String token = loginService.createToken(request, loginRequest);
        if (token == null) {
            throw new BaseException(BaseResponseCode.AUTH_ERROR, null);
        }
        return new BaseResponse<TokenResponse>(new TokenResponse(token, "bearer"));
    }
}
