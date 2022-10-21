package egovframework.com.domain.main.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ParamMainExcelData {
	@ApiModelProperty(name = "groupId", required = true ,example = "4" ,notes = "")
	private Long groupId;	
	
	@ApiModelProperty(name = "workplaceId", required = true ,example = "1" ,notes = "")
	private Long workplaceId;
	
	@ApiModelProperty(name = "baselineId", required = true ,example = "2" ,notes = "")
	private Long baselineId;
	
	@ApiModelProperty(name = "articleVersion", required = false ,example = "1" ,notes = "")
	private Long articleVersion;
	
	@ApiModelProperty(name = "attachFileId", required = false ,example = "1" ,notes = "")
	private Long attachFileId;	
}
