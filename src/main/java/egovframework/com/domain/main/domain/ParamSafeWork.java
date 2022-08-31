package egovframework.com.domain.main.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ParamSafeWork {

	@ApiModelProperty(name = "noticeId", required = false ,example = "1" ,notes = "")
	private Long noticeId;// 사업장ID
	
	@ApiModelProperty(name = "baselineId", required = true ,example = "1" ,notes = "")
	private Long baselineId;// 관리차수
	
	@ApiModelProperty(name = "workplaceId", required = true ,example = "1" ,notes = "")
	private Long workplaceId;// 사업장ID
	
	@ApiModelProperty(name = "userId", required = false ,example = "1" ,notes = "")
	private Long userId;// 사업장ID
	
	@ApiModelProperty(name = "fire", required = false ,example = "1" ,notes = "")
	private int fire;//화기
	
	@ApiModelProperty(name = "closeness", required = false ,example = "1" ,notes = "")
	private int closeness;//밀폐
	
	@ApiModelProperty(name = "blackout", required = false ,example = "1" ,notes = "")
	private int blackout;//정전	
	
	@ApiModelProperty(name = "excavation", required = false ,example = "1" ,notes = "")
	private int excavation;//굴착
	
	@ApiModelProperty(name = "radiation", required = false ,example = "1" ,notes = "")
	private int radiation;//방사선 	
	
	@ApiModelProperty(name = "Sue", required = false ,example = "1" ,notes = "")
	private int Sue;//고소	
	
	@ApiModelProperty(name = "insertDate", required = false ,example = "yyyy-mm-dd" ,notes = "")
	private String insertDate;// 등록일
	
	@ApiModelProperty(name = "baselineStart", required = false ,example = "yyyy-mm-dd" ,notes = "")
	private String baselineStart;// 관리차수시작일
	
	@ApiModelProperty(name = "baselineEnd", required = false ,example = "yyyy-mm-dd" ,notes = "")
	private String baselineEnd;// 관리차수 종료일
	
	@ApiModelProperty(name = "companyId", required = false ,example = "1" ,notes = "")
	private long companyId;// 관리차수 종료일	
	
	@ApiModelProperty(name = "fileId", required = false ,example = "1" ,notes = "")
	private long fileId;// 관리차수 종료일		
}
