package egovframework.com.domain.law.domain;

import lombok.Data;

@Data
public class Law {

	private Long totalCount;							// 목록 총 갯수
	private Long companyId;								// 회사ID
	private Long workplaceId;							// 사업장ID
	private Long lawImproveId;							// 관계법령 개선조치ID
	private String workplaceName;						// 사업장명
	private String status;								// 조치상태
	private String statusCd;							// 조치코드
	private String recvYear;							// 발생년도
	private String recvDate;							// 지적일자 / 접수일자
	private String recvCd;								// 접수형태CD
	private String recvName;							// 접수형태
	private String cmmdOrgName;							// 조치명령 기관
	private String cmmdOrgCd;							// 조치명령 기관CD
	private String occurPlace;							// 발생장소
	private String issueReason;							// 지적원인
	private String improveCn;							// 개선조치 내용
	private String preventCn;						    // 재발방지대책
}
