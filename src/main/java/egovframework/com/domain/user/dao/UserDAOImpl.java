package egovframework.com.domain.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.com.domain.user.domain.User;
import egovframework.com.domain.user.parameter.UserParameter;

@Repository
public class UserDAOImpl implements UserDAO{

	@Autowired
	private SqlSession sqlSession;
	
	private static final String Namespace = "egovframework.com.domain.user.dao.UserDAO";

	@Override
	public User getUser(long userId) {
		return sqlSession.selectOne(Namespace + ".getUser", userId);
	}

	@Override
	public void modifyUser(UserParameter parameter) {
		sqlSession.update(Namespace + ".modifyUser", parameter);
	}

	@Override
	public int modifyPwd(UserParameter parameter) {
		return sqlSession.update(Namespace + ".modifyPwd", parameter);
	}

	@Override
	public Long getUserCount(UserParameter parameter) {
		return sqlSession.selectOne(Namespace + ".getUserCount", parameter);
	}
}
