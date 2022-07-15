package egovframework.com.domain.org.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import egovframework.com.domain.org.dao.UserManageDAO;


/**
 * 사용자관리에 관한 비지니스 클래스를 정의한다.
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
 *   2014.12.08	 이기하			암호화방식 변경(OfficeFileScrty.encryptPassword)
 *
 *      </pre>
 */
@Service
@Transactional(readOnly = true)
public class UserManageServiceImpl implements UserManageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserManageServiceImpl.class);

    @Autowired
    private UserManageDAO repository;

    /*
     * public String createToken(HttpServletRequest request, LoginRequest loginRequest) { Login
     * login = null; String tokenStr = null;
     * 
     * try { System.out.println("loginRequest.getLoginId()    ]" + loginRequest.getLoginId() + "[");
     * login = repository.getByLoginId(loginRequest.getLoginId()); if (login != null) { AES256Util
     * aesUtil = new AES256Util(); String pwEnc = aesUtil.encrypt(loginRequest.getLoginPw());
     * 
     * System.out.println("login.getName()    ]" + login.getName() + "[");
     * System.out.println("login.getLoginPw() ]" + login.getLoginPw() + "[");
     * System.out.println("loginRequest.getLoginPw() ]" + loginRequest.getLoginPw() + "[");
     * System.out.println("pwEnc                     ]" + pwEnc + "[");
     * 
     * if (StringUtils.equals(pwEnc, login.getLoginPw())) {
     * login.setLoginIp(OfficeClntInfo.getClntIP((HttpServletRequest) request));
     * login.setLoginDt(new Date()); tokenStr = login.toString(login); } else { return null; } }
     * else { return null; } } catch (Exception e) { e.printStackTrace(); return null; }
     * 
     * System.out.println("tokenStr                  ]" + tokenStr + "[");
     * 
     * return login.createToken(tokenStr); }
     */
}
