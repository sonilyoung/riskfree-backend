package egovframework.com.domain.relatedlaw.domain;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RelatedLaw {

	@ApiModelProperty(name = "dutyImproveId", required = false ,example = "1" ,notes = "")
	private Long dutyImproveId;
	
	private Long companyId;
	private Long workplaceId;
	private Long baselineId;
	
	@ApiModelProperty(name = "lawId", required = true ,example = "1" ,notes = "")
	private Long lawId;
	private String relatedArticle;
	private String articleItem;
	private String seriousAccdntDecree;
	private String violatedArticle;
	private String violatedActivity;
	private String violationDetail1;
	private String violationDetail2;
	private String baseArticle;
	private String stPenalty1;
	private String stPenalty2;
	private String stPenalty3;
	private String acctionCn;
	
	List<UpdateList> updateList;//체크목록
}
