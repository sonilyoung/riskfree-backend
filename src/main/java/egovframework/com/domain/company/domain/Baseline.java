package egovframework.com.domain.company.domain;

import lombok.Data;

@Data
public class Baseline {

	private Long companyId;						// 회사ID
	private Long baselineId;					// 관리차수ID
	private String baselineName;				// 관리차수명
	private String baselineStart;				// 시작일
	private String baselineEnd;					// 종료일
}
