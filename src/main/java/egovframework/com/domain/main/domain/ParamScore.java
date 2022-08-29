package egovframework.com.domain.main.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ParamScore {
	@ApiModelProperty(name = "articleNo", required = true ,example = "1071" ,notes = "")
	private Long articleNo; //유저시퀀스
	
	@ApiModelProperty(name = "evaluation", required = true ,example = "10;7;5" ,notes = "")
	private String evaluation;
}
