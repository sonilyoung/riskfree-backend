package egovframework.com.domain.example.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import egovframework.com.domain.example.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String Namespace = "egovframework.com.domain.example.mapper.memberMapper";
	
	@Override
	public List<MemberVO> selectMember() throws Exception {

		return sqlSession.selectList(Namespace+".selectMember");
	}

}
