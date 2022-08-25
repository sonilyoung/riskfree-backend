package egovframework.com.domain.user.service;

import egovframework.com.domain.user.domain.User;
import egovframework.com.domain.user.parameter.UserParameter;

public interface UserService {

	User getUser(long userId);

	void modifyUser(UserParameter parameter);

	void modifyPwd(UserParameter parameter);

	Long getUserCount(UserParameter parameter);

	int resetPwd(UserParameter parameter);

}
