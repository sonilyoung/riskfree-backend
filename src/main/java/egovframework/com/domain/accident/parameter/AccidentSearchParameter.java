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
	private Long baselineId;				// 관리차수ID
	private String accTypeCd001;			// 재해유형CD
	private String accTypeCd002;			// 재해유형CD
	private String accTypeCd003;			// 재해유형CD
	private String accTypeCd004;			// 재해유형CD
	private String accTypeCd005;			// 재해유형CD
	private String accTypeCd006;		    // 재해유형CD
	private String accLevelCd;				// 사고등급CD
	private String occurPlace;				// 발생장소
	private String startDate;				// 시작일
	private String endDate;					// 종료일
	private String managerName;				// 현장책임자명
	private String accidentTypeCd;			// 사고구분CD
	private String death;					// 사고유형
	private String same;					// 사고유형
	private String job;						// 사고유형
}
