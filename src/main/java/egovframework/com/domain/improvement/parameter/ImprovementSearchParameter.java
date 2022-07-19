package egovframework.com.domain.improvement.parameter;

import egovframework.com.domain.common.parameter.CommSearchParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(callSuper = false)
public class ImprovementSearchParameter extends CommSearchParameter{

	private Long companyId;
	private Long workplaceId;
	private String startDate;
	private String endDate;
	private String reqUserName;
	private String statusCd;
}
