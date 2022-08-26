package egovframework.com.domain.user.parameter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PasswordParameter {

	@ApiModelProperty(example = "6", notes = "request url : post /login/passwd/change")
	private Long userId;								// 사용자ID(seq)
	
	@ApiModelProperty(example = "test1234", notes = "request url : post /login/passwd/change")
	private String changePwd;							// 변경할 비밀번호
	
	@ApiModelProperty(example = "test1234", notes = "request url : post /login/passwd/change")
	private String confirmPwd; 							// 비밀번호 확인
	
}
