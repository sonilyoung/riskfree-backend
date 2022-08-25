package egovframework.com.domain.accident.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.com.domain.accident.domain.Accident;
import egovframework.com.domain.accident.parameter.AccidentParameter;
import egovframework.com.domain.accident.parameter.AccidentSearchParameter;

@Repository
public class AccidentDAOImpl implements AccidentDAO {

	@Autowired
    private SqlSession sqlSession;

    private static final String Namespace = "egovframework.com.domain.accident.dao.AccidentDAO";

	@Override
	public List<Accident> getAccidentList(AccidentSearchParameter parameter) {
		return sqlSession.selectList(Namespace + ".getAccidentList", parameter);
	}

	@Override
	public Accident getAccident(Long companyId, Long accidentId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("companyId", companyId);
		map.put("accidentId", accidentId);
		return sqlSession.selectOne(Namespace + ".getAccident", map);
	}

	@Override
	public int insertAccident(AccidentParameter parameter) {
		return sqlSession.insert(Namespace + ".insertAccident" , parameter);
	}

	@Override
	public int modifyAccident(AccidentParameter parameter) {
		return sqlSession.update(Namespace + ".modifyAccident", parameter);
	}

	@Override
	public int deleteAccident(Long companyId, Long accidentId, Long insertId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("companyId", companyId);
		map.put("accidentId", accidentId);
		map.put("updateId", insertId);
		return sqlSession.update(Namespace + ".deleteAccident", map);
	}

	@Override
	public List<Map<String, String>> selectOccurPlace(Long companyId) {
		return sqlSession.selectList(Namespace + ".selectOccurPlace", companyId);
	}
}
