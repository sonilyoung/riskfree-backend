package egovframework.com.domain.main.domain;

import lombok.Data;

@Data
public class AccidentsAmount {
	
	private Long workplaceId;					// 사업장ID
	private Long companyId;						// 회사ID
	private Long baselineId;				// 차수아이디

	private int deathTollCnt; //사망
	private int sameAccidentInjuryCnt;//동일사고
	private int jobDeseaseTollCnt;	//작업질환	
	private int caughtCnt; //끼임
	private int fallCnt; //추락
	private int electCnt; //전기
	private int confinedCnt; //밀폐
	private int heavyCnt;	//중량물

}


