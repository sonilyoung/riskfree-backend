package egovframework.com.domain.company.parameter;

import java.util.List;

import lombok.Data;

@Data
public class WorkplaceParameter {

	private Long companyId;						// 회사ID
	private Long workplaceId;					// 사업장ID
	private Long insertId;						// 등록자ID
	private Long updateId;						// 수정자ID
	private String workplaceName;				// 사업장명
	private String remark;						// 비고
	private List<UserParameter> user;			// 사용자정보
	
}
