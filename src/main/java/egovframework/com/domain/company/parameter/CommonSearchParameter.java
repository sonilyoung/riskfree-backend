package egovframework.com.domain.company.parameter;

import egovframework.com.domain.common.parameter.CommSearchParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommonSearchParameter extends CommSearchParameter{
	
	private Long companyId;				// 회사ID
	private Long workplaceId;		    // 사업장ID
	private Long baselineId;			// 관리차수ID
	private String col;					// 검색조건
	private String param;				// 검색어
}
