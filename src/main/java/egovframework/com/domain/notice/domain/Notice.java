package egovframework.com.domain.notice.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class Notice {

	@ApiModelProperty(value = "조회수")
	private int viewCnt;					// 조회수
	private Long totalCount;        		// 목록 총 갯수
	private Long companyId;					// 회사ID
	private Long noticeId;					// 공지사항ID
	private Long attachId;					// 첨부파일ID
	private String title;					// 제목
	private String insertName;				// 작성자
	private String insertDate;     		 	// 작성일
	private String importCd;				// 중요공지CD
	private String content;					// 내용
	private String originalFilename;  		// 파일명
	private String filePath;				// 파일path
	
}
