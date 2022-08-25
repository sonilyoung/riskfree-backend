package egovframework.com.domain.law.parameter;

import egovframework.com.domain.common.parameter.CommSearchParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LawParameter extends CommSearchParameter{

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
	private String recvDate;							// 접수일자
	private String cmmdOrgCd001;						// 조치명령 기관CD
	private String cmmdOrgCd002;						// 조치명령 기관CD
	private String cmmdOrgCd003;						// 조치명령 기관CD
	private String cmmdOrgCd004;						// 조치명령 기관CD
	private String occurPlace;							// 발생장소
	private String issueReason;							// 지적원인
	private String issueDate;							// 지적일자
	private String improveIssueCn;						// 개선조치 지적내용
	private String improvetCn;						    // 개선조치내용
	private String divisionCd;							// 구분코드
	private String finDate;								// 완료요청일
}
