package egovframework.com.domain.subscriber.parameter;

import egovframework.com.domain.common.parameter.CommSearchParameter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SubscriberSearchParameter extends CommSearchParameter{

//	@ApiModelProperty(example = "1")
//	private Long companyId;				// 회사ID
//	
//	@ApiModelProperty(example = "1")
//	private Long workplaceId;		    // 사업장ID
	
	@ApiModelProperty(example = "companyName", notes = "selctBox // 전체 : all , 회사명 : companyName, 담당자 : userName, ID : loginId")
	private String col;					// 검색조건
	
	@ApiModelProperty(example = "테스트", notes = "search value")
	private String param;				// 검색어
	
	@ApiModelProperty(example = "YYYY-MM-DD", notes = "계약일")
	private String contractDay; //계약일
}
