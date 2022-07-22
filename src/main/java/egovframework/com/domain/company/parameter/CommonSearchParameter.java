package egovframework.com.domain.company.parameter;

import lombok.Data;

@Data
public class CommonSearchParameter {
	
	private Long companyId;				// 회사ID
	private String col;					// 검색조건
	private String keyword;				// 검색어
}
