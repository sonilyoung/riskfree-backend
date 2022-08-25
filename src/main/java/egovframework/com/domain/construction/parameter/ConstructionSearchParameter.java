package egovframework.com.domain.construction.parameter;

import lombok.Data;

@Data
public class ConstructionSearchParameter {

	private Long companyId;						// 회사ID
	private Long workplaceId;					// 사업장ID
	private Long constructionId;				// 공사내역ID
	private String insertDate;					// 등록일
	private String insertUserName;				// 등록자명
}
