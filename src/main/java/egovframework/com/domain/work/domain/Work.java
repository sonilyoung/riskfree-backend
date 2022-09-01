package egovframework.com.domain.work.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Work {

	@ApiModelProperty(name = "noticeId", required = false ,example = "1" ,notes = "")
	private Long noticeId;
	
	private Long companyId;
	
	@ApiModelProperty(name = "workplaceId", required = false ,example = "1" ,notes = "")
	private Long workplaceId;
	
	private String workplaceName;
	
	@ApiModelProperty(name = "insertDate", required = false ,example = "yyyy-mm-dd" ,notes = "")
	private String insertDate;
	
	@ApiModelProperty(name = "userName", required = false ,example = "1" ,notes = "")
	private String userName;
	
	private int fire;
	private int closeness;
	private int blackout;
	private int excavation;
	private int radiation;
	private int sue;
	private int heavy;
	private int totalCount;
	
	private Long attachId;
	private String fileName;
	

	
}