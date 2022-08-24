package egovframework.com.domain.main.domain;

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
	
	private String enforceRate;// 재해발생 방지대책 및 이행현황 %
	private String improvemetRate;// 관계법령에 따른 개선 시정명령조치 %
	private String relatedlawRate;// 관계법령에 의무이행의 관리조치 %
}
