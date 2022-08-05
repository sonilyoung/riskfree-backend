package egovframework.com.domain.accident.parameter;

import egovframework.com.domain.common.parameter.CommSearchParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AccidentSearchParameter extends CommSearchParameter {

	private Long companyId;					// 회사ID
	private Long workplaceId;				// 사업장ID
	private Long accidentId;				// 재해ID
	private Long accTypeCd001;					// 재해유형CD
	private Long accTypeCd002;					// 재해유형CD
	private Long accTypeCd003;					// 재해유형CD
	private Long accTypeCd004;					// 재해유형CD
	private Long accTypeCd005;					// 재해유형CD
	private Long accTypeCd006;					// 재해유형CD
	private Long accLevelCd;				// 사고등급CD
	private String startDate;				// 시작일
	private String endDate;					// 종료일
	private String occPlace;				// 발생장소
	private String managerName;				// 현장책임자명
}
