package egovframework.com.domain.law.parameter;

import egovframework.com.domain.common.parameter.CommSearchParameter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LawParameter extends CommSearchParameter{

	@ApiModelProperty(hidden = true)
	private Long companyId;								// 회사ID
	
	@ApiModelProperty(hidden = true)
	private Long workplaceId;							// 사업장ID
	
	@ApiModelProperty(hidden = true)
	private Long baselineId;							// 관리차수ID
	
	@ApiModelProperty(hidden = true)
	private Long insertId;								// 등록자ID
	
	@ApiModelProperty(hidden = true)
	private Long updateId;								// 수정자ID
	
	@ApiModelProperty(example = "1")
	private Long lawImproveId;							// 관계법령 개선조치ID

	@ApiModelProperty(example = "1")
	private Long performBeforeId;						// 조치 전 이미지 파일ID
	
	@ApiModelProperty(example = "2")
	private Long performAfterId;						// 조치 후 이미지 파일ID
	
	@ApiModelProperty(required = true, example = "001", notes = "공문 : 001, 현장점검 : 002, 신고 : 003")
	private String recvCd;								// 접수형태CD

	@ApiModelProperty(example = "김한영")
	private String recvUserName;						// 접수자명
	
	@ApiModelProperty(hidden = true)
	private String statusCd;							// 조치코드
	
	@ApiModelProperty(required = true, example = "2022-08-25", notes = "Show today's date in frontend")
	private String recvDate;							// 접수일자
	
	@ApiModelProperty(example = "001", notes = "checkBox// It should return at least one of 001-004.")
	private String cmmdOrgCd001;						// 조치명령 기관CD
	
	@ApiModelProperty(example = "002", notes = "checkBox// It should return at least one of 001-004.")
	private String cmmdOrgCd002;						// 조치명령 기관CD
	
	@ApiModelProperty(example = "003", notes = "checkBox// It should return at least one of 001-004.")
	private String cmmdOrgCd003;						// 조치명령 기관CD
	
	@ApiModelProperty(example = "004", notes = "checkBox// It should return at least one of 001-004.")
	private String cmmdOrgCd004;						// 조치명령 기관CD
	
	@ApiModelProperty(required = true, example = "1층작업실")
	private String occurPlace;							// 발생장소
	
	@ApiModelProperty(required = true, example = "지적원인입니다.")
	private String issueReason;							// 지적원인
	
	@ApiModelProperty(required = true, example = "2022-08-15")
	private String orderDate;							// 지적일자
	
	@ApiModelProperty(required = true, example = "지적내용입니다.")
	private String improveCn;							// 개선조치 지적내용
	
	@ApiModelProperty(example = "방지대책입니다.")
	private String preventCn;						    // 개선조치내용 (재발방지대책)
	
	@ApiModelProperty(required = true, example = "001", notes = "개선 : 001, 조치 : 002")
	private String improveTypeCd;						// 구분코드
	
	@ApiModelProperty(required = true, example = "2022-08-25")
	private String dueDate;								// 완료요청일
	
	@ApiModelProperty(required = true, example = "2022-08-25")
	private String completeDate;								// 완료일
	
	@ApiModelProperty(name = "isClose", required = false, example = "1" ,notes = "")
	private String isClose;			
}
