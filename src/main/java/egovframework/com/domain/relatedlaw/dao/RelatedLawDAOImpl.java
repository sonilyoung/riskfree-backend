package egovframework.com.domain.relatedlaw.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.com.domain.relatedlaw.domain.DutyBotton;
import egovframework.com.domain.relatedlaw.domain.RelatedLaw;
import egovframework.com.domain.relatedlaw.domain.UpdateList;


@Repository
public class RelatedLawDAOImpl implements RelatedLawDAO {

	@Autowired
	private SqlSession sqlSession;

    private static final String Namespace = "egovframework.com.domain.relatedlaw.dao.RelatedLawDAO";

	
	@Override
	public int insertRelatedRaw(LinkedHashMap vo) {
		return sqlSession.insert(Namespace + ".insertRelatedRaw", vo);
	}


	@Override
	public List<RelatedLaw> getRelatedRaw(RelatedLaw vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getRelatedRaw", vo);
	}


	@Override
	public void updateRelatedRaw(UpdateList vo) {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace + ".updateRelatedRaw", vo);
	}
	
	
	@Override
	public int insertDutyButton(DutyBotton parameter) {
		return sqlSession.insert(Namespace + ".insertDutyButton", parameter);
	}	
	
	@Override
	public List<DutyBotton> getRelatedRawButton(DutyBotton vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getRelatedRawButton", vo);
	}	

	
}