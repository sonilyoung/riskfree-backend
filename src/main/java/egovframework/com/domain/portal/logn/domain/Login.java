package egovframework.com.domain.portal.logn.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Login implements Serializable {

    private static final long serialVersionUID = -8274004534207618049L;

    private long userId;
    private long companyId;
    private long workplaceId;
    private String loginId;
    private String loginPw;
    private String name;
    private String email;
    private String companyName;
    private String workplaceName;
    private String roleCd;
    private String roleName;
    private String loginIp;
    private Date loginDt;
    private long loginCnt;
    

}
