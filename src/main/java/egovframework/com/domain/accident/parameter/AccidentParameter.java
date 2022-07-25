package egovframework.com.domain.accident.parameter;

import lombok.Data;

@Data
public class AccidentParameter {

	private Long companyId;					// 회사ID
	private Long workplaceId; 				// 사업장ID
	private Long accidentId;				// 재해발생ID
	private Long initReportId;				// 초기사고보고서 파일ID
	private Long finalReportId;				// 최종사고보고서 파일ID
	private Long performBeforeId;			// 조치 전 이미지 파일ID
	private Long performAfterId;			// 조치 후 이미지 파일ID
	private Long insertId;					// 등록 / 수정자 ID
	private String recvDate;				// 접수일자
	private String recvUserName;			// 접수자명
	private String recvFormCd;				// 접수형태CD
	private String recvTypeCd;				// 접수유형CD
	private String accdntCn;				// 사고조치내용
	private String occurDate;				// 발생일자
	private String accTypeCd;				// 재해종류CD
	private String accLevelCd;				// 사고등급CD
	private String occurPlace;				// 발생장소
	private String managerName;				// 현장책임자
	private String occurReason;				// 발생원인
	private String preventCn;				// 재발방지대책
	
}
