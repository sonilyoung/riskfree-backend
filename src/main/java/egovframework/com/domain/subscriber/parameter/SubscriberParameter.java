package egovframework.com.domain.subscriber.parameter;

import lombok.Data;

@Data
public class SubscriberParameter {

	// 회사 파라미터
	private int statusCd;							// 사용상태 (1 - 사용 0 - 사용중지)
	private Long companyId;							// 회사ID
	private Long contractAmount;					// 계약금액
	private Long contractFileId;					// 계약서 파일ID
	private String companyName;						// 회사명
	private String registNo;						// 사업자번호
	private String contractStartDate;				// 계약 시작일
	private String contractEndDate;					// 계약 만료일

	// 사업장 파라미터
	private Long workplaceId;						// 사업장ID
	private String workplaceName;					// 사업장명
	private String scaleCd;							// 규모CD
	private String sectorCd;						// 업종CD
	
	// 사용자 파라미터
	private Long userId;							// 담당자ID
	private String loginId;							// 담당자 로그인ID
	private String pwd;								// 로그인 비밀번호
	private String managerName;						// 담당자명
	private String managerRoleCd;					// 담당자 권한CD
	private String managerMobile;					// 담당자 연락처
	private String managerEmail;					// 담당자 이메일

	private Long insertId;							// 등록ID
	private Long updateId;							// 수정ID
}
