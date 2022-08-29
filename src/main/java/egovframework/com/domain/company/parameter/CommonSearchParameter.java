package egovframework.com.domain.company.parameter;

import egovframework.com.domain.common.parameter.CommSearchParameter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommonSearchParameter extends CommSearchParameter{
	
	@ApiModelProperty(hidden = true)
	private Long companyId;				// 회사ID
	
	@ApiModelProperty(example = "1")
	private Long baselineId;			// 관리차수ID
	private Long workplaceId;		    // 사업장ID
	
	@ApiModelProperty(example = "company", notes = "selctBox // company or manager or id")
	private String col;					// 검색조건
	
	@ApiModelProperty(example = "테스트", notes = "search value")
	private String param;				// 검색어
}
