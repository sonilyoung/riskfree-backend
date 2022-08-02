package egovframework.com.domain.user.parameter;

import lombok.Data;

@Data
public class UserParameter {

	private Long userId;								// 사용자ID(seq)
	private String roleCd;							    // 권한CD
	private String email;								// 이메일
	private String mobile;								// 휴대전화번호
	private String currentPwd;							// 현재 비밀번호
	private String changePwd;							// 변경할 비밀번호
	private String confirmPwd; 							// 비밀번호 확인
	
	private String loginId;								// 로그인 ID
	private String companyName;							// 회사명
	private String registNo;							// 사업자등록번호
	private String managerName;							// 담당자명
}
