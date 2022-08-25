package egovframework.com.domain.notice.parameter;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class NoticeParameter {

	@ApiParam(hidden = true)
	private Long companyId;         // 회사ID
	
	@ApiParam(hidden = true)
	private Long insertId;			// 등록자 ID
	
	@ApiParam(hidden = true)
	private Long updateId;		    // 수정자 ID
	
	@ApiParam(value = "noticeId", example = "1")
	private Long noticeId;			// 공지사항ID
	
	@ApiParam(value = "attachId", required = false, example = "1")
	private Long attachId;			// 첨부파일ID
	
	@ApiParam(value = "title", required = true, example = "API test title")
	private String title;			// 제목
	
	@ApiParam(value = "importCd", required = true, example = "중요 = 001 일반 = 002")
	private String importCd;		// 중요공지CD
	
	@ApiParam(value = "content", required = true, example = "content")
	private String content;		 	// 내용
	
}
