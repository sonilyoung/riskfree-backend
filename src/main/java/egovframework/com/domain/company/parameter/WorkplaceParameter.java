package egovframework.com.domain.company.parameter;

import lombok.Data;

@Data
public class WorkplaceParameter {

	private Long companyId;						// 회사ID
	private Long workplaceId;					// 사업장ID
	private String workplaceName;				// 사업장명
}
