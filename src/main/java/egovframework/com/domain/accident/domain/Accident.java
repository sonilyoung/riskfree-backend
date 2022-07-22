package egovframework.com.domain.accident.domain;

import lombok.Data;

@Data
public class Accident {

	private Long totalCount;				// 목록 총 갯수
	private Long accidentId;				// 재해발생ID
	private String occurYear;				// 발생년도
	private String workplaceName;			// 사업장명
	private String occurDate;				// 발생일자
	private String accType;					// 재해종류
	private String accLevel;				// 사고등급
	private String occurPlace;				// 발생장소
	private String managerName;				// 현장책임자
	private String occurReason;				// 발생원인
	private String preventCn;				// 재발방지대책
	private String recvUserName;			// 접수자명
	private String recvForm;				// 접수형태
	private String recvType;				// 접수유형
	
	private Long initReportId;
	private Long finalReportId;
	private Long performBeforeId;
	private Long performAfterId;
}
