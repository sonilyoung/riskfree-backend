package egovframework.com.domain.portal.logn.domain;

import java.io.Serializable;
import java.util.Date;

public class Login implements Serializable {

    private static final long serialVersionUID = -8274004534207618049L;

    private long userId;
    private String loginId;
    private String loginPw;
    private String loginIp;
    private Date loginDt;
    private String name;
    private String email;
    private String employeeNo;
    private String securityLvl;
    private String sttusCd;
    private long orgnztId;
    private String orgnztNm;
    private long posiId;
    private String posiName;
    private long dutyId;
    private String dutyName;
    private long companyId;
    private String companyName;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

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

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDt() {
        return loginDt;
    }

    public void setLoginDt(Date loginDt) {
        this.loginDt = loginDt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getSecurityLvl() {
        return securityLvl;
    }

    public void setSecurityLvl(String securityLvl) {
        this.securityLvl = securityLvl;
    }

    public String getSttusCd() {
        return sttusCd;
    }

    public void setSttusCd(String sttusCd) {
        this.sttusCd = sttusCd;
    }

    public long getOrgnztId() {
        return orgnztId;
    }

    public void setOrgnztId(long orgnztId) {
        this.orgnztId = orgnztId;
    }

    public String getOrgnztNm() {
        return orgnztNm;
    }

    public void setOrgnztNm(String orgnztNm) {
        this.orgnztNm = orgnztNm;
    }

    public long getPosiId() {
        return posiId;
    }

    public void setPosiId(long posiId) {
        this.posiId = posiId;
    }

    public String getPosiName() {
        return posiName;
    }

    public void setPosiName(String posiName) {
        this.posiName = posiName;
    }

    public long getDutyId() {
        return dutyId;
    }

    public void setDutyId(long dutyId) {
        this.dutyId = dutyId;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
