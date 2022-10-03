package egovframework.com.domain.main.domain;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Category {

	@ApiModelProperty(name = "String", required = false ,example = "categories" ,notes = "categories")
	private List<String> categories; 	

}