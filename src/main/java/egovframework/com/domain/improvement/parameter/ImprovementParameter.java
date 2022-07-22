package egovframework.com.domain.improvement.parameter;

import lombok.Data;

@Data
public class ImprovementParameter {
	
	private Long companyId;				// 회사ID
	private Long workplaceId;			// 사업장ID
	private Long improveId;				// 개선사항ID
	private Long insertId;				// 등록자ID
	private Long reqFileId;				// 첨부파일ID		
	private Long actionBeforeId;		// 조치 이전 파일ID
	private Long actionAfterId;			// 조치 이후 파일ID
	private String improveNo;			// 개선조치 NO
	private String improveCn;			// 개선조치내용
	private String reqDate;				// 요청일자
	private String reqUserName;			// 요청자명
	private String finDate;				// 완료요청일
	private String statusCd;			// 조치상태코드
	private String actionCn;			// 조치내용
	
}
