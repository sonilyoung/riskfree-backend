package egovframework.com.domain.main.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.com.domain.main.domain.Amount;
import egovframework.com.domain.main.domain.Baseline;
import egovframework.com.domain.main.domain.Company;
import egovframework.com.domain.main.domain.Improvement;
import egovframework.com.domain.main.domain.Notice;
import egovframework.com.domain.main.domain.Workplace;


@Repository
public class MainDAOImpl implements MainDAO {

	@Autowired
	private SqlSession sqlSession;

    private static final String Namespace = "egovframework.com.domain.main.dao.MainDAO";

    
    @Override
    public List<Company> getScaleInfo(Company vo) {
    	// TODO Auto-generated method stub
    	return sqlSession.selectList(Namespace + ".getScaleInfo", vo);
    }
    
    
    @Override
    public List<Company> getSectorInfo(Company vo) {
    	// TODO Auto-generated method stub
    	return sqlSession.selectList(Namespace + ".getSectorInfo", vo);
    }    
    
	@Override
	public Company getCompanyInfo(Long companyId) {
		return sqlSession.selectOne(Namespace + ".getCompanyInfo", companyId);
	}



	@Override
	public List<Workplace> getWorkplaceList(Workplace vo) {
		return sqlSession.selectList(Namespace + ".getWorkplaceList", vo);
	}


	@Override
	public Baseline getRecentBaseline(Baseline vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace + ".getRecentBaseline", vo);
	}	


	@Override
	public Baseline getBaseline(Baseline vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace + ".getBaseline", vo);
	}	


	@Override
	public List<Baseline> getBaselineList(Baseline vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getBaselineList", vo);
	}

	

	@Override
	public List<Notice> getNoticeHotList(Notice vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getNoticeHotList", vo);
	}	


	@Override
	public List<Notice> getNoticeList(Notice vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getNoticeList", vo);
	}



	@Override
	public List<Improvement> getImprovementList(Improvement vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getImprovementList", vo);
	}
	
	@Override
	public Amount getAccidentsPrevention(Amount vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace + ".getAccidentsPrevention", vo);
	}
	
	
	@Override
	public Amount getImprovemetLawOrder(Amount vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace + ".getImprovemetLawOrder", vo);
	}	

	@Override
	public Baseline getDayInfo(Baseline vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace + ".getDayInfo", vo);
	}

}
