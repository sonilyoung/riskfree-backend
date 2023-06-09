package egovframework.com.domain.main.domain;

import java.util.List;

import egovframework.com.domain.relatedlaw.domain.UpdateList;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Datapublic class ParamDutyCyle{
	
	@ApiModelProperty(name = "articleId", required = true ,example = "1066" ,notes = "")
	private Long articleId; //시퀀스	
	
	@ApiModelProperty(name = "articleNo", required = true ,example = "1066" ,notes = "")
	private Long articleNo; //시퀀스
	
	@ApiModelProperty(name = "evaluation", required = false ,example = "10;7;5" ,notes = "")
	private String evaluation; //평가
	
	@ApiModelProperty(name = "managerChecked", required = false ,example = "1;1;1" ,notes = "")
	private String managerChecked; //법령체크
	
	@ApiModelProperty(name = "fileId", required = false ,example = "1" ,notes = "")
	private String fileId; //파일업로드
	
	@ApiModelProperty(name = "rowNumber", required = false ,example = "1" ,notes = "")
	private Long rowNumber;	
	
	@ApiModelProperty(name = "updateFileId", required = false ,example = "1" ,notes = "")
	private String updateFileId;		
	
	List<ParamDutyCyle> updateList;//수정목록
	
	List<ParamDutyCyle> updateFileList;//수정된 파일정보
	
	
	
	
}