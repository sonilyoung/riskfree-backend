package egovframework.com.domain.org.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 조직 정보를 관리하기 위한  VO 클래스
 * @author paul
 * @since 2016.02.04
 * @version 1.0
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일     	     수정자            수정내용
 *  -------       --------    ---------------------------
 *   2016.02.04    paul        최초 생성
 *   2017.04.05    suji.h      변수명 정리
 * </pre>
 */
public class OrgnztVO implements Serializable {

	private static final long serialVersionUID = 5878580717298229832L;
	
	/* 조직ID */
	private String orgnztId;
	
	/* 조직명 */
	private String orgnztNm;
	
	/* 조직코드 */
	private String orgnztCd;		// ddpCode, DEPT_CD
	
	/* 최상위 기관 여부 */
	private String orgnztTopF;
	
    /* 조직 상태 */
	private String orgnztStatus;
	
	/* orgnztDc */
	private String orgnztDc;
	
	/* 상위 조직ID */
	private String orgnztParID;
	
	/* 상위 조직명 */
	private String orgnztParNm;
	
	/* 트리정렬시 필요한 baseOrgnztID */
	private String baseOrgnztID;
	
	/* 순번 */
	private int orgnztSeq;
	
	/* 조직 레벨 */
	private int orgnztLevel;
	
	/* CA(Child Add), CM(Child Move), D(Delete), U(Update) */
	private String funcType;
	
	/* 정보수정일시 */
	private Date orgnztUpdateDateTime;
	
	/* OutBox(발신함)메뉴 표시 여부 */
	private String orgnztOutboxF = "2";
	
	/* InBox(수신함)메뉴 표시 여부 */
	private String orgnztInboxF = "2";
	
	/* 수신담당자ID */
	private String incomingManagerID;
	
	/* 조직(부서)담당자ID */
	private String deptManagerID;
	
	/* 발신담당자ID */
	private String outgoingManagerID;
	
	/* 기관(회사)담당자ID */
    private String companyAdminID;
    
	/* 수신담당자명 */
	private String incomingManagerNm;
	
	/* 조직(부서)담당자명 */
	private String deptManagerNm;
	
	/* 발신담당자명 */
	private String outgoingManagerNm;
	
	/* 기관(회사)담당자명 */
	private String companyAdminNm;

	/* 기관(회사) 도메인 */
	private String orgDomain;

	/*AD 도메인*/
	private String msDomain;

	/*Mail 타입*/
	private String mailTy;

	public String getMailTy() {
		return mailTy;
	}

	public void setMailTy(String mailTy) {
		this.mailTy = mailTy;
	}

	public String getMsDomain() {
		return msDomain;
	}

	public void setMsDomain(String msDomain) {
		this.msDomain = msDomain;
	}

	/**
     * orgnztId attribute를 리턴한다.
     * @return String orgnztId
     */
	public String getOrgnztId() {
		return orgnztId;
	}
    /**
     * orgnztId attribute 값을 설정한다.
     * @param String orgnztId
     */
	public void setOrgnztId(String orgnztId) {
		this.orgnztId = orgnztId;
	}
    /**
     * orgnztNm attribute를 리턴한다.
     * @return String orgnztNm
     */
	public String getOrgnztNm() {
		return orgnztNm;
	}
    /**
     * orgnztNm attribute 값을 설정한다.
     * @param String orgnztNm
     */
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}
    /**
     * orgnztInboxF attribute를 리턴한다.
     * @return String orgnztInboxF
     */
	public String getOrgnztInboxF() {
		return orgnztInboxF;
	}
    /**
     * orgnztInboxF attribute 값을 설정한다.
     * @param String orgnztInboxF
     */
	public void setOrgnztInboxF(String orgnztInboxF) {
		this.orgnztInboxF = orgnztInboxF;
	}
    /**
     * orgnztOutboxF attribute를 리턴한다.
     * @return String orgnztOutboxF
     */
	public String getOrgnztOutboxF() {
		return orgnztOutboxF;
	}
    /**
     * orgnztOutboxF attribute 값을 설정한다.
     * @param String orgnztOutboxF
     */
	public void setOrgnztOutboxF(String orgnztOutboxF) {
		this.orgnztOutboxF = orgnztOutboxF;
	}
    /**
     * orgnztTopF attribute를 리턴한다.
     * @return String orgnztTopF
     */
	public String getOrgnztTopF() {
		return orgnztTopF;
	}
    /**
     * orgnztTopF attribute 값을 설정한다.
     * @param String orgnztTopF
     */
	public void setOrgnztTopF(String orgnztTopF) {
		this.orgnztTopF = orgnztTopF;
	}

    /**
     * orgnztStatus attribute를 리턴한다.
     * @return String orgnztStatus
     */
	public String getOrgnztStatus() {
		return orgnztStatus;
	}
    /**
     * orgnztStatus attribute 값을 설정한다.
     * @param String orgnztStatus
     */
	public void setOrgnztStatus(String orgnztStatus) {
		this.orgnztStatus = orgnztStatus;
	}
    /**
     * orgnztDc attribute를 리턴한다.
     * @return String orgnztDc
     */
	public String getOrgnztDc() {
		return orgnztDc;
	}
    /**
     * orgnztDc attribute 값을 설정한다.
     * @param String orgnztDc
     */
	public void setOrgnztDc(String orgnztDc) {
		this.orgnztDc = orgnztDc;
	}
    /**
     * orgnztParID attribute를 리턴한다.
     * @return String orgnztParID
     */
	public String getOrgnztParId() {
		return orgnztParID;
	}
    /**
     * orgnztParID attribute 값을 설정한다.
     * @param String orgnztParID
     */
	public void setOrgnztParId(String orgnztParID) {
		this.orgnztParID = orgnztParID;
	}
    /**
     * orgnztParNm attribute를 리턴한다.
     * @return String orgnztParNm
     */
    public String getOrgnztParNm() {
		return orgnztParNm;
	}
    /**
     * orgnztParNm attribute 값을 설정한다.
     * @param String orgnztParNm
     */
	public void setOrgnztParNm(String orgnztParNm) {
		this.orgnztParNm = orgnztParNm;
	}
	/**
     * orgnztCd attribute를 리턴한다.
     * @return String orgnztCd
     */
	public String getOrgnztCd() {
		return orgnztCd;
	}
    /**
     * orgnztCd attribute 값을 설정한다.
     * @param String orgnztCd
     */
	public void setOrgnztCd(String orgnztCd) {
		this.orgnztCd = orgnztCd;
	}	
    /**
     * funcType attribute를 리턴한다.
     * @return String funcType
     */
	public String getFuncType() {
		return funcType;
	}
    /**
     * funcType attribute 값을 설정한다.
     * @param String funcType
     */
	public void setFuncType(String funcType) {
		this.funcType = funcType;
	}
    /**
     * baseOrgnztID attribute를 리턴한다.
     * @return String baseOrgnztID
     */
	public String getBaseOrgnztId() {
		return baseOrgnztID;
	}
    /**
     * baseOrgnztID attribute 값을 설정한다.
     * @param String baseOrgnztID
     */
	public void setBaseOrgnztId(String baseOrgnztID) {
		this.baseOrgnztID = baseOrgnztID;
	}	
    /**
     * orgnztSeq attribute를 리턴한다.
     * @return int orgnztSeq
     */
	public int getOrgnztSeq() {
		return orgnztSeq;
	}
    /**
     * orgnztSeq attribute 값을 설정한다.
     * @param int orgnztSeq
     */
	public void setOrgnztSeq(int orgnztSeq) {
		this.orgnztSeq = orgnztSeq;
	}
    /**
     * orgnztLevel attribute를 리턴한다.
     * @return int orgnztLevel
     */
	public int getOrgnztLevel() {
		return orgnztLevel;
	}
    /**
     * orgnztLevel attribute 값을 설정한다.
     * @param int orgnztLevel
     */
	public void setOrgnztLevel(int orgnztLevel) {
		this.orgnztLevel = orgnztLevel;
	}
    /**
     * orgnztUpdateDateTime attribute를 리턴한다.
     * @return Date orgnztUpdateDateTime
     */
	public Date getOrgnztUpdateDateTime() {
		return orgnztUpdateDateTime;
	}
    /**
     * orgnztUpdateDateTime attribute 값을 설정한다.
     * @param Date orgnztUpdateDateTime
     */
	public void setOrgnztUpdateDateTime(Date orgnztUpdateDateTime) {
		this.orgnztUpdateDateTime = orgnztUpdateDateTime;
	}	
    /**
     * incomingManagerID attribute를 리턴한다.
     * @return String incomingManagerID
     */
	public String getIncomingManagerId() {
		return incomingManagerID;
	}
    /**
     * incomingManagerID attribute 값을 설정한다.
     * @param String incomingManagerID
     */
	public void setIncomingManagerId(String incomingManagerID) {
		this.incomingManagerID = incomingManagerID;
	}
    /**
     * deptManagerID attribute를 리턴한다.
     * @return String deptManagerID
     */
	public String getDeptManagerId() {
		return deptManagerID;
	}
    /**
     * deptManagerID attribute 값을 설정한다.
     * @param String deptManagerID
     */
	public void setDeptManagerId(String deptManagerID) {
		this.deptManagerID = deptManagerID;
	}
    /**
     * outgoingManagerID attribute를 리턴한다.
     * @return String outgoingManagerID
     */
	public String getOutgoingManagerId() {
		return outgoingManagerID;
	}
    /**
     * outgoingManagerID attribute 값을 설정한다.
     * @param String outgoingManagerID
     */
	public void setOutgoingManagerId(String outgoingManagerID) {
		this.outgoingManagerID = outgoingManagerID;
	}
    /**
     * companyAdminID attribute를 리턴한다.
     * @return String companyAdminID
     */
	public String getCompanyAdminId() {
		return companyAdminID;
	}
    /**
     * companyAdminID attribute 값을 설정한다.
     * @param String companyAdminID
     */
	public void setCompanyAdminId(String companyAdminID) {
		this.companyAdminID = companyAdminID;
	}	
    /**
     * incomingManagerNm attribute를 리턴한다.
     * @return String incomingManagerNm
     */
	public String getIncomingManagerNm() {
		return incomingManagerNm;
	}
    /**
     * incomingManagerNm attribute 값을 설정한다.
     * @param String incomingManagerNm
     */
	public void setIncomingManagerNm(String incomingManagerNm) {
		this.incomingManagerNm = incomingManagerNm;
	}
    /**
     * deptManagerNm attribute를 리턴한다.
     * @return String deptManagerNm
     */
	public String getDeptManagerNm() {
		return deptManagerNm;
	}
    /**
     * deptManagerNm attribute 값을 설정한다.
     * @param String deptManagerNm
     */
	public void setDeptManagerNm(String deptManagerNm) {
		this.deptManagerNm = deptManagerNm;
	}
    /**
     * outgoingManagerNm attribute를 리턴한다.
     * @return String outgoingManagerNm
     */
	public String getOutgoingManagerNm() {
		return outgoingManagerNm;
	}
    /**
     * outgoingManagerNm attribute 값을 설정한다.
     * @param String outgoingManagerNm
     */
	public void setOutgoingManagerNm(String outgoingManagerNm) {
		this.outgoingManagerNm = outgoingManagerNm;
	}
    /**
     * companyAdminNm attribute를 리턴한다.
     * @return String companyAdminNm
     */
	public String getCompanyAdminNm() {
		return companyAdminNm;
	}
    /**
     * companyAdminNm attribute 값을 설정한다.
     * @param String companyAdminNm
     */
	public void setCompanyAdminNm(String companyAdminNm) {
		this.companyAdminNm = companyAdminNm;
	}

	public String getOrgDomain() {
		return orgDomain;
	}

	public void setOrgDomain(String orgDomain) {
		this.orgDomain = orgDomain;
	}
}
