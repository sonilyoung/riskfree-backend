package egovframework.com.domain.law.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LawDAOImpl implements LawDAO {

	@Autowired
	private SqlSession sqlSession;
}
