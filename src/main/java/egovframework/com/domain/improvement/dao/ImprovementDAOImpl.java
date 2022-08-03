package egovframework.com.domain.improvement.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.com.domain.improvement.domain.Improvement;
import egovframework.com.domain.improvement.parameter.ImprovementParameter;
import egovframework.com.domain.improvement.parameter.ImprovementSearchParameter;

@Repository
public class ImprovementDAOImpl implements ImprovementDAO {

	@Autowired
    private SqlSession sqlSession;

    private static final String Namespace = "egovframework.com.domain.improvement.dao.ImprovementDAO";

	@Override
	public List<Improvement> getImprovementList(ImprovementSearchParameter parameter) {
		return sqlSession.selectList(Namespace + ".getImprovementList", parameter);
	}

	@Override
	public int insertImprovement(ImprovementParameter parameter) {
		return sqlSession.insert(Namespace + ".insertImprovement", parameter);
	}

	@Override
	public Improvement getImprovement(Long companyId, Long improveId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyId", companyId);
		map.put("improveId", improveId);
		return sqlSession.selectOne(Namespace + ".getImprovement" ,map);
	}

	@Override
	public int modifyImprovement(ImprovementParameter parameter) {
		return sqlSession.update(Namespace + ".modifyImprovement", parameter);
	}

	@Override
	public int deleteImprovement(Long companyId, Long improveId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyId", companyId);
		map.put("improveId", improveId);
		return sqlSession.delete(Namespace + ".deleteImprovement", map);
	}
}
