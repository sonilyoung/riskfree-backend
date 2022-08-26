package egovframework.com.domain.subscriber.parameter;

import egovframework.com.domain.common.parameter.CommSearchParameter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SubscriberSearchParameter extends CommSearchParameter{

	@ApiModelProperty(example = "1")
	private Long companyId;				// 회사ID
	
	@ApiModelProperty(example = "1")
	private Long workplaceId;		    // 사업장ID
	
	@ApiModelProperty(example = "company", notes = "selctBox // company or manager or id")
	private String col;					// 검색조건
	
	@ApiModelProperty(example = "테스트", notes = "search value")
	private String param;				// 검색어
}
