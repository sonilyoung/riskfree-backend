package egovframework.com.domain.main.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Amount {
	
	private Long totalCount;
	private String groupId;					// 그룹아이디
	private Long workplaceId;					// 사업장ID
	private String workplaceName;				// 사업장명
	private Long companyId;						// 회사ID
	private Long baselineId;				// 차수아이디
	private String baselineStart;				// 시작일
	private String baselineEnd;					// 종료일
	private int evaluationRate;					// 시행율
	
	@ApiModelProperty(name = "baselineId", required = false ,example = "100%" ,notes = "")
	private String enforceRate;// 재해발생 방지대책 및 이행현황 %
	
	private String enforceTitle;// 재해발생 방지대책 및 이행현황 타이틀
	
	@ApiModelProperty(name = "improvemetRate", required = false ,example = "100%" ,notes = "")
	private String improvemetRate;// 관계법령에 따른 개선 시정명령조치 %
	
	private String improvemetTitle;// 관계법령에 따른 개선 시정명령조치 타이틀
	
	@ApiModelProperty(name = "relatedLawRate", required = false ,example = "100%" ,notes = "")
	private String relatedLawRate;// 관계법령에 의무이행의 관리조치 %
	
	@ApiModelProperty(name = "workplaceSize", required = true ,example = "1" ,notes = "")
	private int workplaceSize;					// 사업장정보		
}
