package egovframework.com.domain.user.parameter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserParameter {

	@ApiModelProperty(example = "6", notes = "request url : post /login/passwd/change")
	private Long userId;								// 사용자ID(seq)
	
	@ApiModelProperty(example = "test1234", notes = "request url : post /login/passwd/change")
	private String changePwd;							// 변경할 비밀번호
	
	@ApiModelProperty(example = "test1234", notes = "request url : post /login/passwd/change")
	private String confirmPwd; 							// 비밀번호 확인
	
	@ApiModelProperty(example = "AAA222", notes = "request url : post /login/passwd/reset")
	private String loginId;								// 로그인 ID
	
	@ApiModelProperty(example = "테스트컴퍼니", notes = "request url : post /login/passwd/reset")
	private String companyName;							// 회사명
	
	@ApiModelProperty(example = "테스트컴퍼니", notes = "request url : post /login/passwd/reset")
	private String registNo;							// 사업자등록번호
	
	@ApiModelProperty(example = "김한영", notes = "request url : post /login/passwd/reset")
	private String managerName;							// 담당자명
}
