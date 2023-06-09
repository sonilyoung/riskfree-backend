package egovframework.com.domain.notice.parameter;

import egovframework.com.domain.common.parameter.CommSearchParameter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class NoticeSearchParameter extends CommSearchParameter{

	@ApiModelProperty(hidden = true)
	private Long companyId;			// 회사ID
	
	@ApiModelProperty(required = true, example = "1")
	private Long workplaceId;

	@ApiModelProperty(value = "search requirement", required = false, example = "title", notes = "전체 = null, 제목 = title, 작성자 = name")
	private String col;				// 검색조건명
	
	@ApiModelProperty(value = "search keyword", required = false, example = "test")
	private String param;			// 검색키워드
	
	@ApiModelProperty(value = "roleCd", required = false, example = "test")
	private String roleCd;			// 권한	
}
