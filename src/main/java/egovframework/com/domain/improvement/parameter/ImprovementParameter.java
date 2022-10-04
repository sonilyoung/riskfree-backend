package egovframework.com.domain.improvement.parameter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ImprovementParameter {
	
	@ApiModelProperty(hidden = true)
	private Long companyId;				// 회사ID
	
	@ApiModelProperty(hidden = true)
	private Long baselineId;			// 관리차수ID
	
	@ApiModelProperty(hidden = true)
	private Long insertId;				// 등록자ID
	
	@ApiModelProperty(hidden = true)
	private Long updateId;				// 수정자ID
	
	@ApiModelProperty(required = true, example = "1", notes = "POST /company/workplace/select")
	private Long workplaceId;			// 사업장ID
	
	@ApiModelProperty(example = "1")
	private Long improveId;				// 개선사항ID
	
	@ApiModelProperty(required = true, example = "1")
	private Long reqFileId;				// 첨부파일ID		
	
	@ApiModelProperty(example = "2")
	private Long actionBeforeId;		// 조치 이전 파일ID
	
	@ApiModelProperty(example = "3")
	private Long actionAfterId;			// 조치 이후 파일ID
	
	@ApiModelProperty(required = true, example = "CE-001")
	private String improveNo;			// 개선조치 NO
	
	@ApiModelProperty(required = true, example = "개선조치내용입니다.")
	private String improveCn;			// 개선조치내용
	
	@ApiModelProperty(required = true, example = "2022-08-25")
	private String reqDate;				// 요청일자
	
	@ApiModelProperty(required = true, example = "001", notes = "대표이사 : 001, 안전책임자 : 002, 안전실무자 : 003")
	private String reqUserCd;			// 요청자CD
	
	@ApiModelProperty(required = true, example = "2022-08-30")
	private String finDate;				// 완료요청일
	
	@ApiModelProperty(required = true, example = "001", notes = "요청중 : 001, 접수 : 002, 진행중 : 003, 조치완료 : 004")
	private String statusCd;			// 조치상태코드
	
	@ApiModelProperty(example = "조치내용입니다.")
	private String actionCn;			// 조치내용
	
	@ApiModelProperty(example = "완료일")
	private String completeDate;			// 완료일	
	
}
