package egovframework.com.domain.law.parameter;

import egovframework.com.domain.common.parameter.CommSearchParameter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LawSearchParameter extends CommSearchParameter {

	@ApiModelProperty(hidden = true)
	private Long companyId;					// 회사ID
	
	@ApiModelProperty(example = "1",  notes = "POST /company/workplace/select")
	private Long workplaceId;				// 사업장ID
	
	@ApiModelProperty(required = true, example = "6")
	private Long baselineId;				// 관리차수ID
	
	@ApiModelProperty(example = "005", notes = "조치중 : 005, 조치완료 : 006")
	private String statusCd;				// 상태CD
	
	@ApiModelProperty(example = "001", notes = "개선 : 001, 조치 : 002")
	private String improveTypeCd;			// 구분CD
	
	@ApiModelProperty(example = "all", notes = "checkBox")
	private String cmmdOrgCd;			
	
	@ApiModelProperty(example = "001", notes = "checkBox")
	private String cmmdOrgCd001;			// 조치명령 기관CD(고용노동부)
	
	@ApiModelProperty(example = "002", notes = "checkBox")
	private String cmmdOrgCd002;			// 조치명령 기관CD(소방청)
	
	@ApiModelProperty(example = "003", notes = "checkBox")
	private String cmmdOrgCd003;			// 조치명령 기관CD(환경부)
	
	@ApiModelProperty(example = "004", notes = "checkBox")
	private String cmmdOrgCd004;			// 조치명령 기관CD(자체점검)
	
	@ApiModelProperty(example = "지적원인", notes = "POST /improvement/law/issueReason/select")
	private String issueReason;				// 지적원인
	
	@ApiModelProperty(example = "2022-08-12")
	private String startDate;				// 검색 시작일
	
	@ApiModelProperty(example = "2022-08-15")
	private String endDate;					// 검색 종료일
	
	@ApiModelProperty(example = "2022-08-20")
	private String dueDate;					// 완료 요청일
	
	@ApiModelProperty(name = "isClose", required = false, example = "1" ,notes = "")
	private String isClose;			
}
