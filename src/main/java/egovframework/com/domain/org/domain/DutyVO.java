package egovframework.com.domain.org.domain;

import java.io.Serializable;

/**
 * 직책 정보를 관리하기 위한 VO 클래스
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
public class DutyVO implements Serializable {

	private static final long serialVersionUID = -1549280619907508769L;

	/* 부서ID */
	private String orgnztId;

	/* 직책ID */
	private String dutyId;

	/* 직책명 */
	private String dutyNm;

	/* 삭제가능여부 */
	private boolean deletable; 
	
	private int fixF;
	
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
     * dutyId attribute를 리턴한다.
     * @return String dutyId
     */
	public String getDutyId() {
		return dutyId;
	}
    /**
     * dutyId attribute 값을 설정한다.
     * @param String dutyId
     */
	public void setDutyId(String dutyId) {
		this.dutyId = dutyId;
	}
    /**
     * dutyNm attribute를 리턴한다.
     * @return String dutyNm
     */
	public String getDutyNm() {
		return dutyNm;
	}
    /**
     * dutyNm attribute 값을 설정한다.
     * @param String dutyNm
     */
	public void setDutyNm(String dutyNm) {
		this.dutyNm = dutyNm;
	}
    /**
     * deletable attribute를 boolean형으로 리턴한다.
     * @return boolean deletable
     */
	public boolean isDeletable() {
		return deletable;
	}
    /**
     * deletable attribute 값을 설정한다.
     * @param boolean deletable
     */
	public void setDeletable(boolean deletable) {
		this.deletable = deletable;
	}
	public int getFixF() {
		return fixF;
	}
	public void setFixF(int fixF) {
		this.fixF = fixF;
	}
}
