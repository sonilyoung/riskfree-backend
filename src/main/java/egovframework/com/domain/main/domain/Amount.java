package egovframework.com.domain.main.domain;

import lombok.Data;

@Data
public class Amount {
	
	private Long workplaceId;					// 사업장ID
	private Long companyId;						// 회사ID
	private String baselineStart;				// 시작일
	private String baselineEnd;					// 종료일
	
	private String enforceRate;// 재해발생 방지대책 및 이행현황 %
	private String improvemetRate;// 관계법령에 따른 개선 시정명령조치 %
}
