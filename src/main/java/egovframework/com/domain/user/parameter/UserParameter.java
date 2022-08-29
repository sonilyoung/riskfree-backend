package egovframework.com.domain.user.parameter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserParameter {

	@ApiModelProperty(required = true,example = "AAA222", notes = "request url : post /login/passwd/reset")
	private String loginId;								// 로그인 ID
	
	@ApiModelProperty(required = true,example = "테스트컴퍼니", notes = "request url : post /login/passwd/reset")
	private String companyName;							// 회사명
	
	@ApiModelProperty(required = true,example = "123-20-62190", notes = "request url : post /login/passwd/reset")
	private String registNo;							// 사업자등록번호
	
	@ApiModelProperty(required = true,example = "김한영", notes = "request url : post /login/passwd/reset")
	private String managerName;							// 담당자명
	
	@ApiModelProperty(hidden = true)
	private Long userId;								// 사용자ID(seq)
	
	@ApiModelProperty(hidden = true)
	private String changePwd;							// 변경할 비밀번호
	
	@ApiModelProperty(hidden = true)
	private String confirmPwd; 
}
