package egovframework.com.domain.user.dao;

import egovframework.com.domain.user.domain.User;
import egovframework.com.domain.user.parameter.UserParameter;

public interface UserDAO {

	User getUser(long userId);

	void modifyUser(UserParameter parameter);

	int modifyPwd(UserParameter parameter);

	Long getUserCount(UserParameter parameter);

}
