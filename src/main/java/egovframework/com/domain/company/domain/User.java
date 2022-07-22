package egovframework.com.domain.company.domain;

import lombok.Data;

@Data
public class User {

	private Long userId;						// 사용자ID(seq)
	private String userName;					// 사용자명
	private String id;							// 사용자로그인ID
	private String role;						// 사용자권한
	private String position;					// 사용자직위
	private String mobile;						// 사용자핸드폰번호
	
}
