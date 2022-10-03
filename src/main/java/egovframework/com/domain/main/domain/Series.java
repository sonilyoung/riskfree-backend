package egovframework.com.domain.main.domain;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Series {

	@ApiModelProperty(name = "name", required = true ,example = "name" ,notes = "name")
	private String name;	
	
	@ApiModelProperty(name = "data", required = false ,example = "1" ,notes = "datas")
	private List<Integer> data;		
}