package egovframework.com.domain.main.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PramAmount {
	
	@ApiModelProperty(name = "BaselineId", required = true ,example = "2" ,notes = "")
	private Long BaselineId;					// 차수아이디
	
	@ApiModelProperty(name = "groupId", required = false ,example = "" ,notes = "")
	private String groupId;					// 그룹아이디
	
	@ApiModelProperty(name = "workplaceId", required = true ,example = "1" ,notes = "")
	private Long workplaceId;					// 사업장ID
	
}
