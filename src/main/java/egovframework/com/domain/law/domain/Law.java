package egovframework.com.domain.law.domain;

import lombok.Data;

@Data
public class Law {

	private int totalCount;							    // 목록 총 갯수
	private Long companyId;								// 회사ID
	private Long workplaceId;							// 사업장ID
	private Long lawImproveId;							// 관계법령 개선조치ID
	private Long performBeforeId;						// 조치 전 이미지 파일ID
	private Long performAfterId;						// 조치 후 이미지 파일ID
	private String workplaceName;						// 사업장명
	private String recvUserName;						// 등록자명
	private String status;								// 조치상태
	private String statusCd;							// 조치코드
	private String recvYear;							// 발생년도
	private String recvDate;							// 접수일자
	private String recvCd;								// 접수형태CD
	private String recvName;							// 접수형태
	private String cmmdOrgName001;						// 조치명령 기관(고용노동부)
	private String cmmdOrgName002;						// 조치명령 기관(소방청)
	private String cmmdOrgName003;						// 조치명령 기관(환경부)
	private String cmmdOrgName004;						// 조치명령 기관(자체점검)
	private String cmmdOrgCd001;						// 조치명령 기관CD(고용노동부)
	private String cmmdOrgCd002;						// 조치명령 기관CD(소방청)
	private String cmmdOrgCd003;						// 조치명령 기관CD(환경부)
	private String cmmdOrgCd004;						// 조치명령 기관CD(자체점검)
	private String occurPlace;							// 발생장소
	private String issueReason;							// 지적원인
	private String orderDate;	 						// 지적일자
	private String improveCn;							// 개선조치 지적내용
	private String preventCn;						    // 개선조치내용(재발방지대책)
	private String dueDate;								// 완료요청일
	private String improveTypeCd;						// 구분CD
	private String improveType;							// 구분
}
