package egovframework.com.domain.notice.parameter;

import egovframework.com.domain.common.parameter.CommSearchParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class NoticeSearchParameter extends CommSearchParameter{

	private Long companyId;			// 회사ID
	private Long noticeId;			// 공지사항 ID
	private String col;				// 검색조건명
	private String param;			// 검색키워드
}
