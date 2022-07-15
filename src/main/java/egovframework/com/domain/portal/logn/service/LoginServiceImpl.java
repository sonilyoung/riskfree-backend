package egovframework.com.domain.portal.logn.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import egovframework.com.domain.org.dao.UserManageDAO;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.domain.portal.logn.domain.LoginRequest;
import egovframework.com.global.util.AES256Util;
import egovframework.com.global.util.sim.service.OfficeClntInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


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
public class LoginServiceImpl implements LoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);
    private static final String secretKey = "secretsecretsecretsecretsecret";
    private static final long validityInMilliseconds = 3600000;
    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Autowired
    private UserManageDAO repository;

    public String createToken(HttpServletRequest request, LoginRequest loginRequest) {
        Login login = null;
        String tokenStr = null;

        try {
            System.out.println("loginRequest.getLoginId()    ]" + loginRequest.getLoginId() + "[");
            login = repository.getByLoginId(loginRequest.getLoginId());
            if (login != null) {
                AES256Util aesUtil = new AES256Util();
                String pwEnc = aesUtil.encrypt(loginRequest.getLoginPw());

                System.out.println("login.getName()    ]" + login.getName() + "[");
                System.out.println("login.getLoginPw() ]" + login.getLoginPw() + "[");
                System.out.println("loginRequest.getLoginPw() ]" + loginRequest.getLoginPw() + "[");
                System.out.println("pwEnc                     ]" + pwEnc + "[");

                if (StringUtils.equals(pwEnc, login.getLoginPw())) {
                    login.setLoginIp(OfficeClntInfo.getClntIP((HttpServletRequest) request));
                    login.setLoginDt(new Date());
                    tokenStr = toString(login);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        System.out.println("tokenStr                  ]" + tokenStr + "[");

        return createToken(tokenStr);
    }

    public Login getLoginInfo(HttpServletRequest request) {
        Login login = new Login();
        try {
            String header = request.getHeader("Authorization");
            if (header == null || !header.startsWith("Bearer ")) {
                LOGGER.error("Authorizaton을 찾을 수 없습니다.");
                return login;
            }

            String token = header.replace("Bearer ", "");
            if (!validateToken(token)) {
                LOGGER.error("Bearer을 찾을 수 없습니다.");
                return login;
            }

            String str = getSubject(token);
            login = toVo(str);
            LOGGER.info("Name ]" + login.getName());
        } catch (Exception e) {
            LOGGER.error("Token을 구할 수 없습니다. " + e.getMessage());
            return login;
        }
        return login;
    }

    private String toString(Login login) throws Exception {
        String str = login.getUserId() + "|" + login.getLoginId() + "|" + login.getLoginPw() + "|"
                + login.getLoginIp() + "|" + (String) df.format(login.getLoginDt()) + "|"
                + login.getName() + "|" + login.getEmail() + "|" + login.getEmployeeNo() + "|"
                + login.getSecurityLvl() + "|" + login.getSttusCd() + "|" + login.getOrgnztId()
                + "|" + login.getOrgnztNm() + "|" + login.getPosiId() + "|" + login.getPosiName()
                + "|" + login.getDutyId() + "|" + login.getDutyName() + "|" + login.getCompanyId()
                + "|" + login.getCompanyName();
        return str;
    }

    private Login toVo(String str) throws Exception {
        Login login = new Login();
        String strSplit[] = StringUtils.split(str, '|');
        login.setUserId(Long.parseLong(strSplit[0]));
        login.setLoginId(strSplit[1]);
        login.setLoginPw(strSplit[2]);
        login.setLoginIp(strSplit[3]);
        login.setLoginDt((Date) df.parse(strSplit[4]));
        login.setName(strSplit[5]);
        login.setEmail(strSplit[6]);
        login.setEmployeeNo(strSplit[7]);
        login.setSecurityLvl(strSplit[8]);
        login.setSttusCd(strSplit[9]);
        login.setOrgnztId(Long.parseLong(strSplit[10]));
        login.setOrgnztNm(strSplit[11]);
        login.setPosiId(Long.parseLong(strSplit[12]));
        login.setPosiName(strSplit[13]);
        login.setDutyId(Long.parseLong(strSplit[14]));
        login.setDutyName(strSplit[15]);
        login.setCompanyId(Long.parseLong(strSplit[16]));
        login.setCompanyName(strSplit[17]);
        return login;
    }

    private String createToken(String subject) {
        Claims claims = Jwts.claims().setSubject(subject);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    // 토큰에서 값 추출
    private String getSubject(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // 유효한 토큰인지 확인
    private boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
