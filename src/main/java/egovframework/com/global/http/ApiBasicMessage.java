package egovframework.com.global.http;

/**
 * 응답 코드 정의 클래스
 * 
 * @author jkj0411
 *
 */
public enum ApiBasicMessage {
	ESSENTIAL("는 필수값 입니다."),
	CONDITION_SELECTION("조건을 선택해주세요(condition is null) 1:전체(all), 2:사업장별, 3:그룹별, 4:그룹사업장별"),
	NOBASELINE("관리차수 정보가 없습니다.")
	;

	private String message;

	private ApiBasicMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
