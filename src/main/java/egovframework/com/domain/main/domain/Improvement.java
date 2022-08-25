package egovframework.com.domain.main.domain;

import lombok.Data;

@Data
public class Improvement {

	private Long companyId;					// 회사ID
	private Long workplaceId;					// 사업장ID
	private int instruction;					// 지시
	private int progress;					// 진행
	private int complete;					// 완료
	private String role;					// 권한
	private String baselineStart;				// 시작일
	private String baselineEnd;					// 종료일	
}
