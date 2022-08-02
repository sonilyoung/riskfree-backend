package egovframework.com.domain.subscriber.parameter;

import lombok.Data;

@Data
public class SubscriberParameter {

	private Long companyId;							// 회사ID
	private Long userCount;							// 가입자 수
	private Long contractAmount;					// 계약금액
	private Long contractFileId;					// 계약서 파일ID
	private Long insertId;							// 등록 / 수정 아이디
	private String companyName;						// 회사명
	private String registNo;						// 사업자번호
	private String ownerName;						// 대표자명
	private String managerName;						// 담당자명
	private String managerDept;						// 담당자 부서
	private String managerPosition;					// 담당자 직위
	private String managerTel;						// 담당자 사무실연락처
	private String managerMobile;					// 담당자 연락처
	private String managerEmail;					// 담당자 이메일
	private String contractStartDate;				// 계약 시작일
	private String contractEndDate;					// 계약 만료일
}
