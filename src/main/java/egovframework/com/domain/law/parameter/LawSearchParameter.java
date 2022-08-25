package egovframework.com.domain.law.parameter;

import egovframework.com.domain.common.parameter.CommSearchParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LawSearchParameter extends CommSearchParameter {

	private Long companyId;					// 회사ID
	private Long workplaceId;				// 사업장ID
	private Long lawImproveId;				// 관계법령 개선조치ID
	private Long statusCd;					// 상태CD
	private Long divisionCd;				// 구분CD
	private String cmmdOrgCd001;			// 조치명령 기관CD(고용노동부)
	private String cmmdOrgCd002;			// 조치명령 기관CD(소방청)
	private String cmmdOrgCd003;			// 조치명령 기관CD(환경부)
	private String cmmdOrgCd004;			// 조치명령 기관CD(자체점검)
	private String issueReason;				// 지적원인
	private String startDate;				// 검색 시작일
	private String endDate;					// 검색 종료일
	private String finDate;					// 완료 요청일
}
