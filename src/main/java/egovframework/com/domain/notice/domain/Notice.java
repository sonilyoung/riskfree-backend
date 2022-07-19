package egovframework.com.domain.notice.domain;

import lombok.Data;

@Data
public class Notice {

	private Long totalCount;        // 목록 총 갯수
	private Long noticeId;			// 공지사항ID
	private int viewCnt;			// 조회수
	private String title;			// 제목
	private String insertName;		// 작성자
	private String insertDate;      // 작성일
	private String improtCd;		// 중요공지CD
	private String content;			// 내용
	private String attachId;		// 첨부파일ID
}
