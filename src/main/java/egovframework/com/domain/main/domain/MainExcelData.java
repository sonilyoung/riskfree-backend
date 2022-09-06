package egovframework.com.domain.main.domain;

import lombok.Data;

@Data
public class MainExcelData {
	
	private Long totalCount;
	private Long fileCount;
	private Long articleId;
	private Long articleNo; //유저시퀀스
	private Long articleCd;
	private Long companyId;
	private Long workplaceId;
	private Long baselineId;
	private Long targetBaselineId;
	private Long groupId;	
	private String baselineStart;
	private String baselineEnd;
	private String shGoals;
	private String detailedItems;
	private String shGoal;
	private String evaluation;
	private String evaluationStr;
	private String dutyCycle;
	private String dutyAssigned;
	private String relatedArticle;
	private String guideline;
	private String updateDate;	
	private Long fileId;
	private String managerChecked;
	private Long insertId;
}
