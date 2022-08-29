package egovframework.com.domain.law.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DutyBotton {

	private long userId;
	@ApiModelProperty(name = "lawButtonId", required = true ,example = "1" ,notes = "")
	private long lawButtonId;
	private long baselineId;
	private long companyId;
	private long workplaceId;
	private String lawName;
	private long insertId;
	private String insertDate;

}
