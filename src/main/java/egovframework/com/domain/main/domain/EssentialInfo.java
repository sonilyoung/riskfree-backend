package egovframework.com.domain.main.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data	
public class EssentialInfo {
	
	@ApiModelProperty(name = "baselineId", required = false ,example = "2" ,notes = "")
	private long baselineId;
	
	@ApiModelProperty(name = "workplaceId", required = false ,example = "1" ,notes = "")
	private long workplaceId;
	
	@ApiModelProperty(name = "rate1", required = false ,example = "11%" ,notes = "")
	private EssentialRate rate1;// 안전보건 목표 및 경영방침
	
	@ApiModelProperty(name = "rate2", required = false ,example = "22%" ,notes = "")
	private EssentialRate rate2;// 유해위험요인개선업무절차 마련 및 점검
	
	@ApiModelProperty(name = "rate3", required = false ,example = "33%" ,notes = "")
	private EssentialRate rate3;//안전보건업무 총괄관리 전담조직 구축
	
	@ApiModelProperty(name = "rate4", required = false ,example = "44%" ,notes = "")
	private EssentialRate rate4;// 안전보건관리책임자 권한 부여 및 집행 점검
	
	@ApiModelProperty(name = "rate5", required = false ,example = "55%" ,notes = "")
	private EssentialRate rate5;// 안전보건관련 필요예산 편성 및 집행
	
	@ApiModelProperty(name = "rate6", required = false ,example = "66%" ,notes = "")
	private EssentialRate rate6;// 안전보건전문인력 배치 및 업무시간 보장
	
	@ApiModelProperty(name = "rate7", required = false ,example = "77%" ,notes = "")
	private EssentialRate rate7;// 종사자 의견수렴 및 개선방안 이행점검
	
	@ApiModelProperty(name = "rate8", required = false ,example = "88" ,notes = "")
	private EssentialRate rate8;// 중대재해발생 비상대응 매뉴얼 마련 점검
	
	@ApiModelProperty(name = "rate9", required = false ,example = "99%" ,notes = "")
	private EssentialRate rate9;// 도급용역 위탁시 평가기준 및 절차 점검
	
	@ApiModelProperty(name = "topScore", required = false ,example = "danger" ,notes = "")
	private String topScore;// 총점수
	
	@ApiModelProperty(name = "topRate", required = false ,example = "100%" ,notes = "")
	private String topRate;// 총퍼센트
}
