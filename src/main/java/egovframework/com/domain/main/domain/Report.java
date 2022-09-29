package egovframework.com.domain.main.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Report {

	@ApiModelProperty(name = "condition", required = true ,example = "1" ,notes = "report type condition")
	private String condition;	//레포트 조건
	
	@ApiModelProperty(name = "menuTile", required = false ,example = "menuTile" ,notes = "reposrt menuTile")
	private String menuTitle;	//레포트 항목 타이틀
	
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
	
	private String cmmdOrgCd001;						// 조치명령 기관CD(고용노동부)
	private String cmmdOrgCd002;						// 조치명령 기관CD(소방청)
	private String cmmdOrgCd003;						// 조치명령 기관CD(환경부)
	private String cmmdOrgCd004;						// 조치명령 기관CD(자체점검)
	
	private String accType001;				// 재해종류001
	private String accType002;				// 재해종류002
	private String accType003;				// 재해종류003
	private String accType004;				// 재해종류004
	private String accType005;				// 재해종류005
	private String accType006;				// 재해종류006	
}
