package egovframework.com.domain.main.domain;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Graph {

	private List<Series> series;	
	private List<String> categories; 	
	
}