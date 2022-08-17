package egovframework.com.domain.main.domain;

import lombok.Data;

@Data
public class Improvement {

	private Long companyId;					// 회사ID
	private Long instruction;					// 지시
	private Long progress;					// 진행
	private Long complete;					// 완료
	private String role;					// 권한
}
