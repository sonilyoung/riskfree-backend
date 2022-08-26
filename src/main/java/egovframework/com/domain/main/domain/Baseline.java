package egovframework.com.domain.main.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Baseline {
	
	@ApiModelProperty(name = "companyId", dataType = "long" ,required = true, example = "1", notes = "")
	private Long companyId;//회사ID
	
	@ApiModelProperty(name = "baselineId", dataType = "long" ,required = false, example = "1", notes = "1.request param 2.(post)api명 ->  logId")
	private Long baselineId;		
	// 관리차수ID
	private String baselineName;				// 관리차수명
	private String baselineStart;				// 시작일
	private String baselineEnd;					// 종료일
	private String prevBaseline;
	private String nextBaseline;
	private String day;
	
}
