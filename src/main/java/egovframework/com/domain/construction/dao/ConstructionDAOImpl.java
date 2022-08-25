package egovframework.com.domain.construction.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.com.domain.accident.domain.Accident;
import egovframework.com.domain.construction.parameter.ConstructionSearchParameter;

@Repository
public class ConstructionDAOImpl implements ConstructionDAO{

	@Autowired
    private SqlSession sqlSession;

    private static final String Namespace = "egovframework.com.domain.construction.dao.ConstructionDAO";

	@Override
	public List<Accident> getConstructionHistoryList(ConstructionSearchParameter parameter) {
		return sqlSession.selectList(Namespace + ".getConstructionHistoryList", parameter);
	}

}
