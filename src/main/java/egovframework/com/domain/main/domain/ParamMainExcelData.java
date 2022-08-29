package egovframework.com.domain.main.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ParamMainExcelData {
	@ApiModelProperty(name = "baselineId", required = true ,example = "4" ,notes = "")
	private String groupId;	
	
	@ApiModelProperty(name = "baselineId", required = true ,example = "1" ,notes = "")
	private Long workplaceId;
	
	@ApiModelProperty(name = "baselineId", required = true ,example = "2" ,notes = "")
	private Long baselineId;

}
