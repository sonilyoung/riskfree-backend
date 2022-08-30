package egovframework.com.domain.main.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Report {

	@ApiModelProperty(name = "condition", required = true ,example = "1" ,notes = "report type condition")
	private String condition;	//레포트 조건
	
	@ApiModelProperty(name = "menuTile", required = false ,example = "menuTile" ,notes = "reposrt menuTile")
	private String menuTile;	//레포트 항목 타이틀
	
	private Long totalCount;
	private String groupId;					// 그룹아이디
	private Long workplaceId;	
	// 사업장ID
	@ApiModelProperty(name = "workplaceName", required = false ,example = "workplaceName" ,notes = "report workplaceName")
	private String workplaceName;				// 사업장명
	private Long companyId;						// 회사ID
	private Long baselineId;				// 차수아이디
	private String baselineStart;				// 시작일
	private String baselineEnd;					// 종료일
	private int evaluationRate;					// 시행율
	private String improvemetRate;// 관계법령에 따른 개선 시정명령조치 %
	
}
