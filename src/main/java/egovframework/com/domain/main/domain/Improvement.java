package egovframework.com.domain.main.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Improvement {

	@ApiModelProperty(name = "companyId", required = false ,example = "1" ,notes = "")
	private Long companyId;					// 회사ID
	
	@ApiModelProperty(name = "workplaceId", required = true ,example = "1" ,notes = "")
	private Long workplaceId;					// 사업장ID
	
	@ApiModelProperty(name = "instruction", required = true ,example = "1" ,notes = "")
	private int instruction;					// 지시
	
	@ApiModelProperty(name = "progress", required = false ,example = "1" ,notes = "")
	private int progress;					// 진행
	
	@ApiModelProperty(name = "complete", required = false ,example = "1" ,notes = "")
	private int complete;					// 완료
	
	@ApiModelProperty(name = "role", required = false ,example = "1" ,notes = "")
	private String role;					// 권한
	
	@ApiModelProperty(name = "roleCd", required = false ,example = "1" ,notes = "")
	private String roleCd;
	
	@ApiModelProperty(name = "baselineStart", required = false ,example = "1" ,notes = "")
	private String baselineStart;				// 시작일
	
	@ApiModelProperty(name = "baselineEnd", required = false ,example = "1" ,notes = "")
	private String baselineEnd;					// 종료일	
	
	@ApiModelProperty(name = "baselineId", required = true ,example = "1" ,notes = "")
	private Long baselineId;					// 종료일		
}
