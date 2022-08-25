package egovframework.com.domain.accident.parameter;

import lombok.Data;

@Data
public class AccidentParameter {

	private Integer deathToll;					// 사망자수
	private Integer sameAccidentInjury;			// 동일사고유형 부상자수
	private Integer jobDeseaseToll;				// 직업성질환 부상자수
	private Long companyId;					// 회사ID
	private Long workplaceId; 				// 사업장ID
	private Long accidentId;				// 재해발생ID
	private Long baselineId;				// 관리차수ID
	private Long initReportId;				// 초기사고보고서 파일ID
	private Long finalReportId;				// 최종사고보고서 파일ID
	private Long performBeforeId;			// 조치 전 이미지 파일ID
	private Long performAfterId;			// 조치 후 이미지 파일ID
	private Long insertId;					// 등록자 ID
	private Long updateId;					// 수정자 ID
	private String recvDate;				// 접수일자
	private String recvUserName;			// 접수자명
	private String recvFormCd;				// 접수형태CD
	private String recvTypeCd001;			// 접수유형CD
	private String recvTypeCd002;			// 접수유형CD
	private String recvTypeCd003;			// 접수유형CD
	private String recvTypeCd004;			// 접수유형CD
	private String recvTypeCd005;			// 접수유형CD
	private String recvTypeCd006;			// 접수유형CD
	private String accdntCn;				// 사고조치내용
	private String occurDate;				// 발생일자
	private String accTypeCd001;			// 재해종류CD
	private String accTypeCd002;			// 재해종류CD
	private String accTypeCd003;			// 재해종류CD
	private String accTypeCd004;			// 재해종류CD
	private String accTypeCd005;			// 재해종류CD
	private String accTypeCd006;			// 재해종류CD
	private String accLevelCd;				// 사고등급CD
	private String occurPlace;				// 발생장소
	private String managerName;				// 현장책임자
	private String occurReason;				// 발생원인
	private String preventCn;				// 재발방지대책
	private String accidentTypeCd;			// 사고구분CD
	
}
