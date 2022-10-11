package egovframework.com.domain.main.domain;

import lombok.Data;

@Data
public class EssentialRate {
	private String groupId;					// 그룹아이디
	private String title;					// 타이틀
	private String score;					// 퍼센트
	private int scoreSum;					// 퍼센트
	
	
}
