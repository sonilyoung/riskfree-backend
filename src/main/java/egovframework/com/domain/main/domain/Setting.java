package egovframework.com.domain.main.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Setting {

	@ApiModelProperty(name = "companyId", dataType = "long" ,required = true, example = "2", notes = "1.request param 2.(post)api명 ->  logId")
	private Long companyId;					// 회사ID
	private Long workplaceId;					//사업장정보
	
	private Long attachFileId;					// 로고ID
	private String baselineName;				
	private String safetyGoal;				// 안전보건 목표
	private String missionStatements;		// 경영방침
	private String safetyPermitFileId;		// 안전작업허가서 양식 파일아이디
	
	@ApiModelProperty(name = "baselineId", required = true ,example = "2" ,notes = "")
	private Long baselineId;		// 관리차수ID
	
	@ApiModelProperty(name = "chBaselineId", required = true ,example = "2" ,notes = "")
	private Long chBaselineId;		// 관리차수ID

	@ApiModelProperty(name = "resultbaselineId", required = true ,example = "2" ,notes = "")
	private Long resultbaselineId;		// 관리차수ID
	
	@ApiModelProperty(name = "baselineStart", required = false, example = "2022-08-01" ,notes = "")
	private String baselineStart;				// 시작일
	
	@ApiModelProperty(name = "baselineEnd", required = false, example = "2022-08-31" ,notes = "")
	private String baselineEnd;
	
	private Long insertId;					// insert ID
	
	private Long updateId;					// UPDATE ID
	
	private Boolean baselineCheck; 
	
}
