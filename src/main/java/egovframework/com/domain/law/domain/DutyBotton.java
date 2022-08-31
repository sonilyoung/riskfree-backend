package egovframework.com.domain.law.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DutyBotton {

	private Long userId;
	@ApiModelProperty(name = "lawButtonId", required = true ,example = "1" ,notes = "")
	private Long lawButtonId;
	private Long baselineId;
	private Long companyId;
	private Long workplaceId;
	private String lawName;
	private Long insertId;
	private String insertDate;

}
