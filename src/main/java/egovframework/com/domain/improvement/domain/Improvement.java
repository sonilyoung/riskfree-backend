package egovframework.com.domain.improvement.domain;

import lombok.Data;

@Data
public class Improvement {

	private int totalCount;				// 목록 총 갯수
	private Long improveId;				// 개선사항ID
	private Long workplaceId;			// 사업장ID
	private String workplaceName;		// 사업장명
	private String improveNo;			// 개선조치NO
	private String reqFileId;			// 첨부파일ID
	private String actionCn;			// 조치내용
	private String reqDate;				// 요청일자
	private String reqUserName;			// 요청자
	private String reqUserCd;			// 요청자CD
	private String improveCn;			// 개선조치내용
	private String finDate;				// 완료요청일
	private String status;				// 조치상태
	private String statusCd;			// 조치상태CD
	private String actionBeforeId; 		// 조치 전 이미지 ID
	private String actionAfterId;       // 조치 후 이미지 ID
	private String improveKey; //랜덤키
} 
