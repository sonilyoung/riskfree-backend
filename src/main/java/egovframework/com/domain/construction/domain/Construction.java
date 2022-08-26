package egovframework.com.domain.construction.domain;

import lombok.Data;

@Data
public class Construction {

	private Long companyId;							// 회사ID
	private Long workplaceId;						// 사업장ID
	private Long constructionId;					// 공사내역ID
	private String workplaceName;					// 사업장명
	private String insertDate;						// 등록일
	private String insertUserName;					// 등록자명
	private String insertInfo;						// 등록정보
}
