package egovframework.com.domain.company.parameter;

import lombok.Data;

@Data
public class CompanyParameter {

	private Long companyId;					// 회사ID
	private Long logoId;					// 로고ID
	private Long userCount;					// 가입자 수
	private Long insertId;					// 수정자ID
	private Long contactAmount; 			// 계약금액
	private String scaleCd;					// 회사규모코드
	private String sectorCd;				// 회사업종코드
	private String companyTel;				// 회사 전화번호
	private String companyFax;				// 회사 팩스번호
	private String managerName;				// 담당자 성명
	private String managerPosition;			// 담당자 직위
	private String managerMobile;			// 담당자 연락처
	private String managerEmail;			// 담당자 이메일
	private String safetyGoal;				// 안전보건 목표
	private String missionStatements;		// 경영방침
}
