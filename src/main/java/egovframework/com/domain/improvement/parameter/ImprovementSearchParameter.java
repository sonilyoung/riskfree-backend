package egovframework.com.domain.improvement.parameter;

import egovframework.com.domain.common.parameter.CommSearchParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(callSuper = false)
public class ImprovementSearchParameter extends CommSearchParameter{

	private Long companyId;						// 회사 ID
	private Long workplaceId;					// 사업장 ID
	private Long improveId;						// 개선조치 사항 ID
	private String startDate;					// 검색 시작일
	private String endDate;						// 검색 종료일
	private String reqUserName;					// 요청자명
	private String statusCd;					// 상태
}
