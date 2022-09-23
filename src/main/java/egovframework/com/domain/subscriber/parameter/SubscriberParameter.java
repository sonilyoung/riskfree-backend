package egovframework.com.domain.subscriber.parameter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SubscriberParameter {

	// 회사 파라미터
	
	@ApiModelProperty(required = true, example = "1", notes = "사용 : 1, 사용중지 : 0")
	private int statusCd;							// 사용상태 (1 - 사용 0 - 사용중지)
	
	@ApiModelProperty(required = true, example = "100000")
	private Long contractAmount;					// 계약금액
	
	@ApiModelProperty(required = false, example = "1")
	private Long contractFileId;					// 계약서 파일ID
	
	@ApiModelProperty(required = true, example = "삼성전자")
	private String companyName;						// 회사명
	
	@ApiModelProperty(required = true, example = "2022-08-01")
	private String contractStartDate;				// 계약 시작일
	
	@ApiModelProperty(required = true, example = "2024-08-01")
	private String contractEndDate;					// 계약 만료일

	// 사업장 파라미터
	@ApiModelProperty(required = true, example = "124-12-41231")
	private String registNo;						// 사업자번호
	
	@ApiModelProperty(required = true, example = "testWorkplace")
	private String workplaceName;					// 사업장명
	
	@ApiModelProperty(required = true, example = "001", notes = "post /commCode/getCommCodeList (parameter = groupId : '001')")
	private String scaleCd;							// 규모CD
	
	@ApiModelProperty(required = true, example = "001", notes = "post /commCode/getCommCodeList (parameter = groupId : '002')")
	private String sectorCd;						// 업종CD
	
	// 사용자 파라미터
	@ApiModelProperty(required = true, example = "test4321")
	private String loginId;							// 담당자 로그인ID
	
	@ApiModelProperty(required = true, example = "testUser")
	private String managerName;						// 담당자명
	
	@ApiModelProperty(required = true, example = "001", notes = "post /commCode/getCommCodeList (parameter = groupId : '003')")
	private String managerRoleCd;					// 담당자 권한CD
	
	@ApiModelProperty(required = true, example = "010-1234-5678")
	private String managerTel;						// 담당자 연락처
	
	@ApiModelProperty(required = true, example = "test@test.com")
	private String managerEmail;					// 담당자 이메일

	
	@ApiModelProperty(required = false, example = "1")
	private Long companyId;							// 회사ID
	
	@ApiModelProperty(required = false, example = "1")
	private Long workplaceId;						// 사업장ID
	
	@ApiModelProperty(hidden = false, example = "1")
	private Long userId;							// 담당자ID
	
	@ApiModelProperty(hidden = true)
	private String pwd;								// 로그인 비밀번호
	
	@ApiModelProperty(hidden = true)
	private Long insertId;							// 등록ID
	
	@ApiModelProperty(hidden = true)
	private Long updateId;							// 수정ID
	
	@ApiModelProperty(hidden = true)
	private String condition;							// 조건 (1:회사명검색 2: 회사명 사업장검색) 	
}
