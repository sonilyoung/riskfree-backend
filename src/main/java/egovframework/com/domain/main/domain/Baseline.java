package egovframework.com.domain.main.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Baseline {
	
	@ApiModelProperty(name = "companyId", required = true ,example = "1" ,notes = "")
	private Long companyId;//회사ID
	
	@ApiModelProperty(name = "baselineId", required = true ,example = "2" ,notes = "")
	private Long baselineId;		// 관리차수ID
	
	@ApiModelProperty(name = "baselineName", required = false, example = "23년 2차" ,notes = "")
	private String baselineName;				// 관리차수명
	
	@ApiModelProperty(name = "baselineStart", required = false, example = "2022-08-01" ,notes = "")
	private String baselineStart;				// 시작일
	
	@ApiModelProperty(name = "baselineEnd", required = false, example = "2022-08-31" ,notes = "")
	private String baselineEnd;					// 종료일
	
	@ApiModelProperty(name = "prevBaseline", required = false, example = "3" ,notes = "")
	private String prevBaseline;
	
	@ApiModelProperty(name = "nextBaseline", required = false, example = "5" ,notes = "")
	private String nextBaseline;
	
	@ApiModelProperty(name = "day", required = false, example = "28" ,notes = "")
	private String day;
	
	@ApiModelProperty(name = "isClose", required = false, example = "1" ,notes = "")
	private String isClose;	
	
}
