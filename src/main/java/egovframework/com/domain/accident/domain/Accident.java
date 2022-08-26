package egovframework.com.domain.accident.domain;

import lombok.Data;

@Data
public class Accident {

	private int totalCount;				    // 목록 총 갯수
	private Integer deathToll;				// 사망자수
	private Integer sameAccidentInjury;		// 동일사고유형 부상자수
	private Integer jobDeseaseToll;			// 직업성질환 부상자수
	private Long accidentId;				// 재해발생ID
	private Long workplaceId;				// 사업장ID
	private Long baselineId;				// 관리차수ID
	private Long initReportId;				// 초기보고서ID
	private Long finalReportId;				// 최종보고서ID
	private Long performBeforeId;			// 조치 전 이미지 ID
	private Long performAfterId;			// 조치 후 이미지 ID
	private String occurYear;				// 발생년도
	private String workplaceName;			// 사업장명
	private String occurDate;				// 발생일자
	private String accdntCn;				// 사고조치내용
	private String recvFormCd;				// 접수형태CD
	private String recvTypeCd001;			// 접수유형CD001
	private String recvTypeCd002;			// 접수유형CD002
	private String recvTypeCd003;			// 접수유형CD003
	private String recvTypeCd004;			// 접수유형CD004
	private String recvTypeCd005;			// 접수유형CD005
	private String recvTypeCd006;			// 접수유형CD006
	private String accTypeCd001;			// 재해종류CD001
	private String accTypeCd002;			// 재해종류CD002
	private String accTypeCd003;			// 재해종류CD003
	private String accTypeCd004;			// 재해종류CD004
	private String accTypeCd005;			// 재해종류CD005
	private String accTypeCd006;			// 재해종류CD006
	private String accType001;				// 재해종류001
	private String accType002;				// 재해종류002
	private String accType003;				// 재해종류003
	private String accType004;				// 재해종류004
	private String accType005;				// 재해종류005
	private String accType006;				// 재해종류006
	private String accLevelCd;				// 사고등급CD
	private String accLevel;				// 사고등급
	private String occurPlace;				// 발생장소
	private String managerName;				// 현장책임자
	private String occurReason;				// 발생원인
	private String preventCn;				// 재발방지대책
	private String recvDate;				// 접수일자
	private String recvUserName;			// 접수자명
	private String recvForm;				// 접수형태
	private String recvType;				// 접수유형
	private String accidentTypeCd;			// 사고구분CD
	private String accidentType;			// 사고구분
	
}
