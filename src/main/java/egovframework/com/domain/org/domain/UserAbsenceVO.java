package egovframework.com.domain.org.domain;

import java.util.Date;

public class UserAbsenceVO {
	
	private static final long serialVersionUID = 1L;
	
	/* */
	private String absId; 
	/* */
	private String esntlId;
	/* */
	private Date startDt;
	/* */
	private Date endDt;
	/* */
	private String mailF;
	/* */
	private String mailMsg;
	/* */
	private String apprF;
	/* */
	private String apprDepuEsntlId;	
	/* */
	private String apprDepuEsntlNm;
	
	/**
     * absId attribute를 리턴한다.
     * @return String absId
     */
	public String getAbsId() {
		return absId;
	}
	
	/**
     * absId attribute 값을 설정한다.
     * @param String absId
     */
	public void setAbsId(String absId) {
		this.absId = absId;
	}
	
	/**
     * esntlId attribute를 리턴한다.
     * @return String esntlId
     */
	public String getEsntlId() {
		return esntlId;
	}
	
	/**
     * esntlId attribute 값을 설정한다.
     * @param String esntlId
     */
	public void setEsntlId(String esntlId) {
		this.esntlId = esntlId;
	}
	
	/**
     * deptBoxNm attribute를 리턴한다.
     * @return Date startDt
     */
	public Date getStartDt() {
		return startDt;
	}
	
	/**
     * startDt attribute 값을 설정한다.
     * @param Date startDt
     */
	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}
	
	/**
     * endDt attribute를 리턴한다.
     * @return String endDt
     */
	public Date getEndDt() {
		return endDt;
	}
	
	/**
     * endDt attribute 값을 설정한다.
     * @param Date endDt
     */
	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}
	
	/**
     * mailF attribute를 리턴한다.
     * @return String mailF
     */
	public String getMailF() {
		return mailF;
	}
	
	/**
     * mailF attribute 값을 설정한다.
     * @param String mailF
     */
	public void setMailF(String mailF) {
		this.mailF = mailF;
	}
	
	/**
     * mailMsg attribute를 리턴한다.
     * @return String mailMsg
     */
	public String getMailMsg() {
		return mailMsg;
	}
	
	/**
     * mailMsg attribute 값을 설정한다.
     * @param String mailMsg
     */
	public void setMailMsg(String mailMsg) {
		this.mailMsg = mailMsg;
	}
	
	/**
     * apprF attribute를 리턴한다.
     * @return String apprF
     */
	public String getApprF() {
		return apprF;
	}
	
	/**
     * apprF attribute 값을 설정한다.
     * @param String apprF
     */
	public void setApprF(String apprF) {
		this.apprF = apprF;
	}
	
	/**
     * apprDepuEsntlId attribute를 리턴한다.
     * apprDepuEsntlId String deptBoxNm
     */
	public String getApprDepuEsntlId() {
		return apprDepuEsntlId;
	}
	
	/**
     * apprDepuEsntlId attribute 값을 설정한다.
     * @param String deptBoxNm
     */
	public void setApprDepuEsntlId(String apprDepuEsntlId) {
		this.apprDepuEsntlId = apprDepuEsntlId;
	}
	
	/**
     * apprDepuEsntlNm attribute를 리턴한다.
     * apprDepuEsntlNm String deptBoxNm
     */
	public String getApprDepuEsntlNm() {
		return apprDepuEsntlNm;
	}
	
	/**
     * apprDepuEsntlNm attribute 값을 설정한다.
     * @param String deptBoxNm
     */
	public void setApprDepuEsntlNm(String apprDepuEsntlNm) {
		this.apprDepuEsntlNm = apprDepuEsntlNm;
	}
	
	
	
}
