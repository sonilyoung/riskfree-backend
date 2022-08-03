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
	private Long cmmdOrgCd;					// 조치명령 기관CD
	private Long statusCd;					// 상태CD
	private String issueReason;				// 지적원인
	private String startDate;				// 검색 시작일
	private String endDate;					// 검색 종료일
}
