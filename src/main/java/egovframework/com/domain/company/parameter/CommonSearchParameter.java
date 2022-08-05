package egovframework.com.domain.company.parameter;

import lombok.Data;

@Data
public class CommonSearchParameter {
	
	private Long companyId;				// 회사ID
	private Long workplaceId;		    // 사업장ID
	private Long baselineId;			// 관리차수ID
	private String col;					// 검색조건
	private String keyword;				// 검색어
}
