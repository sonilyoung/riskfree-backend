package egovframework.com.domain.org.domain;


/**
 * 개요 : 조직도  상수 관리
 * 
 * @author : suji.h
 * @Date   : 2018. 8. 2.
 */
public interface OrgnztConsts {
	/**
	 * <p>최상위 부서의 부서ID
	 * <p>top root department's ID
	 */
	public static final String ORGNZTID_ROOT = "ORGNZT_0000000000000";
	//사용자 유형
	/**
	 * <p>시스템관리자
	 * <p>System Administrator
	 */
	public static final String USER_TYPE_SYSTEM_ADMIN = "S";
	/**
	 * <p>기관관리자
	 * <p>Organization Administrator
	 */
	public static final String USER_TYPE_ORGANIZATION_ADMIN = "A";
	/**
	 * <p>일반사용자
	 * <p>General User
	 */
	public static final String USER_TYPE_USER = "U";
	/**
	 * <p>부재 설정 여부
	 * <p>User Absence Flag
	 */
	public static final String USER_ABSENCE = "1";
	/**
	 * <p>프로필 사진
	 * <p>Profile Picture
	 */
    public static final String IMAGE_TYPE_PHOTO = "photo";
    /**
     * <p>서명 이미지
     * <p>Signature Image
     */
    public static final String IMAGE_TYPE_SIGN = "sign";
    /**
     * <p>일반 이미지
     * <p>General Image
     */
    public static final String IMAGE_TYPE_IMAGE = "image";
    /**
     * <p>스탬프 종륲
     * <p>접수 스탬프 'R'
     */
    public static final String STAMP_TYPE_RECP = "R";
    /**
     * <p>스탬프 종륲
     * <p>발송 스탬프 'S'
     */
    public static final String STAMP_TYPE_SEND = "S";
    
}
