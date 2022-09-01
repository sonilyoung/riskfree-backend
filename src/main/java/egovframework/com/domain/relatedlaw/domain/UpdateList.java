package egovframework.com.domain.relatedlaw.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateList {

	@ApiModelProperty(name = "dutyImproveId", required = true ,example = "1" ,notes = "")
	private Long dutyImproveId;
	private String acctionCn;
}
