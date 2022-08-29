package egovframework.com.domain.subscriber.domain;

import lombok.Data;

@Data
public class Subscriber {

	// 회사 정보
	private Long companyId;							// 회사ID
	private String companyName;						// 회사명
	
	// 사업장 정보
	private int statusCd;							// 상태CD
	private Long workplaceId;						// 사업장ID
	private Long contractAmount;					// 계약금액
	private Long contractFileId;					// 계약서 파일ID
	private String workplaceName;					// 사업장명
	private String scale;							// 규모
	private String scaleCd;							// 규모CD
	private String sector;							// 업종
	private String sectorCd;						// 업종CD
	private String status;							// 사용상태 
	private String registNo;						// 사업자번호
	private String contractDate;					// 계약일
	private String contractStartDate;				// 시작일
	private String contractEndDate;					// 종료일
	
	
	// 사용자 정보
	private Long userId;							// 담당자 ID
	private String loginId;							// 담당자 로그인ID
	private String managerName;						// 담당자명
	private String managerRole;						// 담당자 권한
	private String managerRoleCd;					// 권한CD
	private String managerTel;						// 담당자 연락처
	private String managerEmail;					// 담당자 이메일
}
