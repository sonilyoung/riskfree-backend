package egovframework.com.domain.main.domain;

import lombok.Data;

@Data
public class Workplace {

	private Long workplaceId;					// 사업장ID
	private Long companyId;						// 회사ID
	private String workplaceName;				// 사업장명
	private String remark;						// 비고
}
