package egovframework.com.domain.user.domain;

import lombok.Data;

@Data
public class User {

	private Long userId;						// 사용자ID(seq)
	private Long companyId;						// 회사ID
	private Long workplaceId;					// 사업장ID
	private String userName;					// 사용자명
	private String loginId;						// 사용자로그인ID
	private String role;						// 사용자권한
	private String roleCd;						// 사용자권한CD
	private String position;					// 사용자직위
	private String mobile;						// 사용자핸드폰번호
	private String pwd;							// 로그인암호
	
}
