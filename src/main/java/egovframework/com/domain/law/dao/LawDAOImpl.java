package egovframework.com.domain.law.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.com.domain.law.domain.DutyBotton;
import egovframework.com.domain.law.domain.Law;
import egovframework.com.domain.law.parameter.LawParameter;
import egovframework.com.domain.law.parameter.LawSearchParameter;

@Repository
public class LawDAOImpl implements LawDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private final String Namespace = "egovframework.com.domain.law.dao.LawDAO";

	@Override
	public List<Law> getLawImprovementList(LawSearchParameter parameter) {
		return sqlSession.selectList(Namespace + ".getLawImprovementList", parameter); 
	}

	@Override
	public int insertLawImprovement(LawParameter parameter) {
		return sqlSession.insert(Namespace + ".insertLawImprovement", parameter);
	}

	@Override
	public Law getLawImprovement(Long companyId, Long lawImproveId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("companyId", companyId);
		map.put("lawImproveId", lawImproveId);
		return sqlSession.selectOne(Namespace + ".getLawImprovement", map);
	}

	@Override
	public int updateLawImprovement(LawParameter parameter) {
		return sqlSession.update(Namespace + ".updateLawImprovement", parameter);
	}

	@Override
	public int deleteLawImprovement(Long companyId, Long userId, Long lawImproveId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("companyId", companyId);
		map.put("updateId", userId);
		map.put("lawImproveId", lawImproveId);
		return sqlSession.update(Namespace + ".deleteLawImprovement", map);
	}
	
	@Override
	public int insertDutyButton(DutyBotton parameter) {
		return sqlSession.insert(Namespace + ".insertDutyButton", parameter);
	}	

	@Override
	public List<Map<String, String>> getIssueReasonList(LawSearchParameter parameter) {
		return sqlSession.selectList(Namespace + ".getIssueReasonList", parameter);
	}
}
