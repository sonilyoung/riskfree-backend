package egovframework.com.domain.notice.parameter;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class NoticeParameter {

	@ApiModelProperty(hidden = true)
	private Long companyId;         // 회사ID
	
	@ApiModelProperty(hidden = true)
	private Long workplaceId;
	
	@ApiModelProperty(hidden = true)
	private Long insertId;			// 등록자 ID
	
	@ApiModelProperty(hidden = true)
	private Long updateId;		    // 수정자 ID
	
	@ApiModelProperty(value = "noticeId", example = "1")
	private Long noticeId;			// 공지사항ID
	
	@ApiModelProperty(required = false, example = "1")
	private Long attachId;			// 첨부파일ID
	
	@ApiModelProperty(required = true, example = "API test title")
	private String title;			// 제목
	
	@ApiModelProperty(required = true, example = "중요 = 001 일반 = 002")
	private String importCd;		// 중요공지CD
	
	@ApiModelProperty(required = true, example = "content")
	private String content;		 	// 내용
	
}
