package egovframework.com.global.http;

/**
 * 응답 코드 정의 클래스
 * 
 * @author jkj0411
 *
 */
public enum BaseResponseCode {
	SUCCESS(
			"0000", "성공"
	), SAVE_SUCCESS(
			"0201", "저장성공"
	), AUTH_FAIL(			
			"0401", "인증실패"
	), AUTH_ERROR(
			"0403", "인증된 사용자가 아닙니다."
	), DATA_IS_NULL(
			"0600", "데이터가 없습니다."
	), DELETE_ERROR(
			"0700", "삭제 중 내부 오류가 발생"
	), SAVE_ERROR(
			"0800", "저장시 내부 오류가 발생"
	), INPUT_CHECK_ERROR(
			"0900", "입력값 무결성 오류"
	), UNKONWN_ERROR(
	        "9998", "내부 오류가 발생"
	), PARAMS_ERROR(
	        "422", "파라미터 오류"	        
	), INFORMATION_ERROR(
			"9999", "입력 정보 없음");
	

	private String code;
	private String message;

	private BaseResponseCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
