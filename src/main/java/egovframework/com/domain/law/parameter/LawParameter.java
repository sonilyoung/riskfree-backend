package egovframework.com.domain.law.parameter;

import lombok.Data;

@Data
public class LawParameter {

	private Long companyId;								// 회사ID
	private Long workplaceId;							// 사업장ID
	private Long lawImproveId;							// 관계법령 개선조치ID
	private Long insertId;								// 등록자ID
	private Long updateId;								// 수정자ID
	private Long performBeforeId;						// 조치 전 이미지 파일ID
	private Long performAfterId;						// 조치 후 이미지 파일ID
	private String recvCd;								// 접수형태CD
	private String recvUserName;						// 접수자명
	private String statusCd;							// 조치코드
	private String recvDate;							// 지적일자 / 접수일자
	private String cmmdOrgCd;							// 조치명령 기관CD
	private String occurPlace;							// 발생장소
	private String issueReason;							// 지적원인
	private String improveCn;							// 개선조치 내용
	private String preventCn;						    // 재발방지대책
}
