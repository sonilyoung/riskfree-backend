package egovframework.com.domain.company.parameter;

import lombok.Data;

@Data
public class BaselineParameter {
	
	private Long companyId;							// 회사ID
	private Long baselineId;						// 관리차수ID
	private Long insertId;							// 등록자 / 수정자ID
	private String baselineName;					// 관리차수명
	private String baselineStart;					// 시작일
	private String baselineEnd;						// 종료일
}
