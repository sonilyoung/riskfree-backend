package egovframework.com.domain.main.domain;

import lombok.Data;

@Data
public class SafeWork {

	private Long noticeId;// 사업장ID
	private Long workplaceId;// 사업장ID
	private Long companyId;// 회사아이디
	private Long attachId;// 파일아이디
	private int fire;//화기
	private int closeness;//밀폐
	private int blackout;//정전	
	private int excavation;//굴착
	private int radiation;//방사선 	
	private int Sue;//고소
	private String userId;// 등록일
	private String insertDate;// 등록일
	
	private String nowDay;// 오늘일자
	private String nowDate;// 요일
	
}
