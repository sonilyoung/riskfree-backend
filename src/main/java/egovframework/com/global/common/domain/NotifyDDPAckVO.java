package egovframework.com.global.common.domain;

import java.io.Serializable;

/**
 */
@SuppressWarnings("serial")
public class NotifyDDPAckVO implements Serializable {
    private String ntId;
    private String ntSendOrgCode;
    private String ntSendOrgName;
    private String ntSendUserName;
    private String ntSendDatetime;
    private String ntOrgCode;
    private String ntOrgName;
    private String ntDocId;


    public String getNtId() {
        return ntId;
    }

    public void setNtId(String ntId) {
        this.ntId = ntId;
    }

    public String getNtSendOrgCode() {
        return ntSendOrgCode;
    }

    public void setNtSendOrgCode(String ntSendOrgCode) {
        this.ntSendOrgCode = ntSendOrgCode;
    }

    public String getNtSendOrgName() {
        return ntSendOrgName;
    }

    public void setNtSendOrgName(String ntSendOrgName) {
        this.ntSendOrgName = ntSendOrgName;
    }

    public String getNtSendUserName() {
        return ntSendUserName;
    }

    public void setNtSendUserName(String ntSendUserName) {
        this.ntSendUserName = ntSendUserName;
    }

    public String getNtSendDatetime() {
        return ntSendDatetime;
    }

    public void setNtSendDatetime(String ntSendDatetime) {
        this.ntSendDatetime = ntSendDatetime;
    }

    public String getNtOrgCode() {
        return ntOrgCode;
    }

    public void setNtOrgCode(String ntOrgCode) {
        this.ntOrgCode = ntOrgCode;
    }

    public String getNtOrgName() {
        return ntOrgName;
    }

    public void setNtOrgName(String ntOrgName) {
        this.ntOrgName = ntOrgName;
    }

    public String getNtDocId() {
        return ntDocId;
    }

    public void setNtDocId(String ntDocId) {
        this.ntDocId = ntDocId;
    }
}
