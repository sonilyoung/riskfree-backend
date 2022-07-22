package egovframework.com.domain.improvement.domain;

import lombok.Data;

@Data
public class Improvement {

	private Long totalCount;			// 목록 총 갯수
	private Long improveId;				// 개선사항ID
	private String workplaceName;		// 사업장명
	private String improveNo;			// 개선조치NO
	//private String reqFileId;			
	private String actionCn;			// 조치내용
	//private String 
	//private String
	private String reqDate;				// 요청일자
	private String reqUserName;			// 요청자
	private String improveCn;			// 개선조치내용
	private String finDate;				// 완료요청일
	private String status;				// 조치상태
}
