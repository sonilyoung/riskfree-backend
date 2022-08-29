package egovframework.com.domain.improvement.parameter;

import egovframework.com.domain.common.parameter.CommSearchParameter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(callSuper = false)
public class ImprovementSearchParameter extends CommSearchParameter{

	@ApiModelProperty(hidden = true)
	private Long companyId;						// 회사 ID
	
	@ApiModelProperty(example = "1",  notes = "POST /company/workplace/select")
	private Long workplaceId;					// 사업장 ID
	
	@ApiModelProperty(required = true, example = "6")
	private Long baselineId;					// 관리차수ID
	
	@ApiModelProperty(example = "2022-08-10")
	private String startDate;					// 검색 시작일
	
	@ApiModelProperty(example = "2022-08-12")
	private String endDate;						// 검색 종료일
	
	@ApiModelProperty(example = "001", notes = "대표이사 : 001, 안전책임자 : 002, 안전실무자 : 003")
	private String reqUserCd;					// 요청자Cd
	
	@ApiModelProperty(example = "001", notes = "요청중 : 001, 접수 : 002, 진행중 : 003, 조치완료 : 004")
	private String statusCd;					// 상태
}
