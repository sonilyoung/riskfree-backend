package egovframework.com.domain.improvement.parameter;

import lombok.Data;

@Data
public class ImprovementParameter {
	
	private Long companyId;
	private Long workplaceId;
	private Long reqUserId;
	private Long improveSeq;
	private String improveNo;
	private String reqContents;
	private String reqDate;
	private String reqCompleteDate;
	//private List<>
	private String actionStatus;
	private String actionContents;
}
