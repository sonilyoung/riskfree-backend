package egovframework.com.domain.work.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.com.domain.work.domain.Work;


@Repository
public class WorkDAOImpl implements WorkDAO {

	@Autowired
	private SqlSession sqlSession;

    private static final String Namespace = "egovframework.com.domain.work.dao.WorkDAO";

    @Override
    public List<Work> getSafeWork(Work vo) {
    	// TODO Auto-generated method stub
    	return sqlSession.selectList(Namespace + ".getSafeWork", vo);
    }
    
	@Override
	public Work getSafeWorkFileTopInfo(Work vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace + ".getSafeWorkFileTopInfo", vo);
	}    
    
	@Override
	public List<Work> getSafeWorkFile(Work vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getSafeWorkFile", vo);
	}

	@Override
	public int deleteSafeWork(Work vo) {
		// TODO Auto-generated method stub
		return sqlSession.delete(Namespace + ".deleteSafeWork", vo);
	}


}
