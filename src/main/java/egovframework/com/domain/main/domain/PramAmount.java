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
	
	@ApiModelProperty(name = "workplaceSize", required = true ,example = "1" ,notes = "")
	private int workplaceSize;					// 사업장정보	
	
	private String roleCd;					// 권한
	
	private Long companyId;					// 회사id
	
	private String condition;					// 조회조건
	
}
