package egovframework.com.domain.org.dao;

import java.util.HashMap;
import java.util.List;
import egovframework.com.domain.org.domain.UserAbsenceVO;
import egovframework.com.domain.org.domain.UserDefaultVO;
import egovframework.com.domain.org.domain.UserVO;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.domain.portal.logn.domain.LoginRequest;


/**
 * 사용자관리에 관한 데이터 접근 클래스를 정의한다.
 * 
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 *
 *      </pre>
 */
public interface UserManageDAO {

    public Login getByLoginId(String loginId);

	public void updateLoginTime(String loginId);
	
	public void updateLoginCnt(String loginId);

	public int getUserStatus(long workplaceId);
	
	public LoginRequest getPwdInfo(LoginRequest loginRequest);

}
