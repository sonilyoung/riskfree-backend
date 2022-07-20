package egovframework.com.domain.notice.parameter;

import lombok.Data;

@Data
public class NoticeParameter {

	private Long companyId;         // 회사ID
	private Long noticeId;			// 공지사항ID
	private Long userId;			// 사용자ID
	private Long attachId;			// 첨부파일ID
	private String title;			// 제목
	private String improtCd;		// 중요공지CD
	private String content;		 	// 내용
	
}
