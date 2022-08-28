package egovframework.com.domain.main.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Company {

	@ApiModelProperty(name = "companyId", dataType = "long" ,required = true, example = "2", notes = "1.request param 2.(post)api명 ->  logId")
	private Long companyId;					// 회사ID
	
	
	private Long logoId;					// 로고ID
	private String logoImg;					// 로고이미지
	private Long userCount;					// 가입자 수
	private Long contractAmount; 			// 계약금액
	private String companyName;				// 회사명
	private String registNo;				// 사업자등록번호
	private String scale;					// 회사규모
	private String sector;					// 회사업종
	private String managerName;				// 담당자 성명
	private String managerPosition;			// 담당자 직위
	private String managerMobile;			// 담당자 연락처
	private String managerEmail;			// 담당자 이메일
	private String safetyGoal;				// 안전보건 목표
	private String missionStatements;		// 경영방침
	private String contractStartDate;		// 계약기간 시작일
	private String contractEndDate;		// 계약기간 종료일
	
	private String scaleCd;				//회사규모 CD 
	private String sectorCd;				// 업종 CD
	
}
