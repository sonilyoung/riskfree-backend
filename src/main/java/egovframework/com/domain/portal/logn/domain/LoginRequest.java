package egovframework.com.domain.portal.logn.domain;

import lombok.Data;

@Data
public class LoginRequest {
    private String loginId;
    private String loginPw;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPw() {
        return loginPw;
    }

    public void setLoginPw(String loginPw) {
        this.loginPw = loginPw;
    }
}
