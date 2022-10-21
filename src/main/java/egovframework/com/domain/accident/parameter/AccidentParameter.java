package egovframework.com.domain.accident.parameter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AccidentParameter {

	@ApiModelProperty(hidden = true)
	private Long companyId;					// 회사ID
	
	@ApiModelProperty(hidden = true)
	private Long workplaceId; 				// 사업장ID
	
	@ApiModelProperty(hidden = true)
	private Long insertId;					// 등록자 ID
	
	@ApiModelProperty(hidden = true)
	private Long updateId;					// 수정자 ID
	
	@ApiModelProperty(hidden = true)
	private Long baselineId;				// 관리차수ID
	
	@ApiModelProperty(example = "1")
	private Long accidentId;				// 재해발생ID
	
	@ApiModelProperty(example = "1")
	private Long initReportId;				// 초기사고보고서 파일ID
	
	@ApiModelProperty(example = "2")
	private Long finalReportId;				// 최종사고보고서 파일ID
	
	@ApiModelProperty(example = "3")
	private Long performBeforeId;			// 조치 전 이미지 파일ID
	
	@ApiModelProperty(example = "4")
	private Long performAfterId;			// 조치 후 이미지 파일ID
	
	@ApiModelProperty(example = "1")
	private Integer deathToll;				// 사망자수
	
	@ApiModelProperty(example = "5")
	private Integer sameAccidentInjury;		// 동일사고유형 부상자수
	
	@ApiModelProperty(example = "7")
	private Integer jobDeseaseToll;			// 직업성질환 부상자수
	
	@ApiModelProperty(required = true, example = "2022-08-25", notes = "Show today's date in frontend")
	private String recvDate;				// 접수일자
	
	@ApiModelProperty(required = true, example = "김한영", notes = "")
	private String recvUserName;			// 접수자명
	
	@ApiModelProperty(required = true, example = "001", notes = "radioButton // 전화 : 001, 싸이렌 : 002, 안전순찰중 : 003")
	private String recvFormCd;				// 접수형태CD
	
	@ApiModelProperty(example = "001", notes = "checkBox // It should return at least one of 001-006.")
	private String recvTypeCd001;			// 접수유형CD
	
	@ApiModelProperty(example = "002", notes = "checkBox // It should return at least one of 001-006.")
	private String recvTypeCd002;			// 접수유형CD
	
	@ApiModelProperty(example = "003", notes = "checkBox // It should return at least one of 001-006.")
	private String recvTypeCd003;			// 접수유형CD
	
	@ApiModelProperty(example = "004", notes = "checkBox // It should return at least one of 001-006.")
	private String recvTypeCd004;			// 접수유형CD
	
	@ApiModelProperty(example = "005", notes = "checkBox // It should return at least one of 001-006.")
	private String recvTypeCd005;			// 접수유형CD
	
	@ApiModelProperty(example = "006", notes = "checkBox // It should return at least one of 001-006.")
	private String recvTypeCd006;			// 접수유형CD
	
	@ApiModelProperty(required = true, example = "사고조치내용입니다")
	private String accdntCn;				// 사고조치내용
	
	@ApiModelProperty(required = true, example = "2022-08-25")
	private String occurDate;				// 발생일자
	
	@ApiModelProperty(value = "추락", example = "001" , notes = "checkBox // It should return at least one of 001-006.")
	private String accTypeCd001;			// 재해유형CD
	
	@ApiModelProperty(value = "끼임", example = "002" , notes = "checkBox // It should return at least one of 001-006.")
	private String accTypeCd002;			// 재해유형CD
	
	@ApiModelProperty(value = "화재", example = "003" , notes = "checkBox // It should return at least one of 001-006.")
	private String accTypeCd003;			// 재해유형CD
	
	@ApiModelProperty(value = "전기", example = "004" , notes = "checkBox // It should return at least one of 001-006.")
	private String accTypeCd004;			// 재해유형CD
	
	@ApiModelProperty(value = "밀폐", example = "005" , notes = "checkBox // It should return at least one of 001-006.")
	private String accTypeCd005;			// 재해유형CD
	
	@ApiModelProperty(value = "중량물", example = "006", notes = "checkBox // It should return at least one of 001-006.")
	private String accTypeCd006;		    // 재해유형CD
	
	@ApiModelProperty(required = true, example = "001", notes = "selectBox // 1급 : 001, 2급 : 002, 3급 : 003, 4급 : 004, 5급 : 005")
	private String accLevelCd;				// 사고등급CD
	
	@ApiModelProperty(required = true, example = "1층 작업실")
	private String occurPlace;				// 발생장소
	
	@ApiModelProperty(required = true, example = "박한이")
	private String managerName;				// 현장책임자
	
	@ApiModelProperty(required = true, example = "발생원인입니다.")
	private String occurReason;				// 발생원인
	
	@ApiModelProperty(example = "재발방지대책입니다.")
	private String preventCn;				// 재발방지대책
	
	@ApiModelProperty(required = true, example = "001", notes = "selectBox // 자사 : 001, 도급 : 002, 기타 : 003")
	private String accidentTypeCd;			// 사고구분CD
	
	@ApiModelProperty(name = "isClose", required = false, example = "1" ,notes = "")
	private String isClose;			
	
}
