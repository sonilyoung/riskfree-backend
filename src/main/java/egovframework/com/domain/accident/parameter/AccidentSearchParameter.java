package egovframework.com.domain.accident.parameter;

import egovframework.com.domain.common.parameter.CommSearchParameter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AccidentSearchParameter extends CommSearchParameter {

	@ApiModelProperty(hidden = true)
	private Long companyId;					// 회사ID
	
	@ApiModelProperty(example = "1", notes = "POST /company/workplace/select")
	private Long workplaceId;				// 사업장ID
	
	@ApiModelProperty(required = true, example = "6", notes = "baselineId")
	private Long baselineId;				// 관리차수ID
	
	@ApiModelProperty(value = "전체", example = "all", notes = "checkBox")
	private String accType;		
	
	@ApiModelProperty(value = "추락", example = "001", notes = "checkBox")
	private String accTypeCd001;			// 재해유형CD
	
	@ApiModelProperty(value = "끼임", example = "002" , notes = "checkBox")
	private String accTypeCd002;			// 재해유형CD
	
	@ApiModelProperty(value = "화재", example = "003" , notes = "checkBox")
	private String accTypeCd003;			// 재해유형CD
	
	@ApiModelProperty(value = "전기", example = "004" , notes = "checkBox")
	private String accTypeCd004;			// 재해유형CD
	
	@ApiModelProperty(value = "밀폐", example = "005" , notes = "checkBox")
	private String accTypeCd005;			// 재해유형CD
	
	@ApiModelProperty(value = "중량물", example = "006", notes = "checkBox")
	private String accTypeCd006;		    // 재해유형CD
	
	@ApiModelProperty(value = "사고등급", example = "001", notes = "1급 : 001, 2급 : 002, 3급 : 003, 4급 : 004, 5급 : 005")
	private String accLevelCd;				// 사고등급CD
	
	@ApiModelProperty(value = "발생장소", example = "1층 작업실")
	private String occurPlace;				// 발생장소
	
	@ApiModelProperty(value = "발행일자", example = "2022-08-10")
	private String startDate;				// 시작일
	
	@ApiModelProperty(value = "발행일자", example = "2022-08-20")
	private String endDate;					// 종료일
	
	@ApiModelProperty(value = "현장책임자명", example = "홍길동")
	private String managerName;				// 현장책임자명
	
	@ApiModelProperty(value = "사고구분CD", example = "001", notes = "자사 : 001, 도급 : 002, 기타 : 003")
	private String accidentTypeCd;			// 사고구분CD
	
	@ApiModelProperty(value = "사망", example = "Y")
	private String death;					// 사고유형
	
	@ApiModelProperty(value = "동일사고유형", example = "Y")
	private String same;					// 사고유형
	
	@ApiModelProperty(value = "직업성질환", example = "Y")
	private String job;						// 사고유형
	
	@ApiModelProperty(name = "isClose", required = false, example = "1" ,notes = "")
	private String isClose;			
}
