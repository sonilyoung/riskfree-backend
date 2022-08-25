package egovframework.com.domain.notice.parameter;

import egovframework.com.domain.common.parameter.CommSearchParameter;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class NoticeSearchParameter extends CommSearchParameter{

	@ApiParam(hidden = true)
	private Long companyId;			// 회사ID

	@ApiParam(hidden = true)
	private Long noticeId;			// 공지사항 ID

	@ApiParam(value = "search requirement", required = false, example = "전체 = null, 제목 = title, 작성자 = name")
	private String col;				// 검색조건명
	
	@ApiParam(value = "search keyword", required = false, example = "test")
	private String param;			// 검색키워드
}
