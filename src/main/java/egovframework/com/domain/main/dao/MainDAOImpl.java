package egovframework.com.domain.main.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.com.domain.main.domain.Amount;
import egovframework.com.domain.main.domain.Baseline;
import egovframework.com.domain.main.domain.Company;
import egovframework.com.domain.main.domain.Improvement;
import egovframework.com.domain.main.domain.MainExcelData;
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


	@Override
	public List<Amount> getAccidentsPreventionReport(Amount vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getAccidentsPreventionReport", vo);
	}


	@Override
	public List<Amount> getImprovemetLawOrderReport(Amount vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getImprovemetLawOrderReport", vo);
	}

    @Override
    public MainExcelData selectEssentialDutyVer() {
        return sqlSession.selectOne(Namespace + ".selectEssentialDutyVer");
    }
	
	@Override
	public int insertEssentialDuty(LinkedHashMap vo) {
		return sqlSession.insert(Namespace + ".insertEssentialDuty", vo);
	}
	

	@Override
	public List<MainExcelData> getEssentialDuty(MainExcelData vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getEssentialDuty", vo);
	}	
	
	@Override
	public int insertEssentialDutyUser(MainExcelData vo) {
		return sqlSession.insert(Namespace + ".insertEssentialDutyUser", vo);
	}

	
	@Override
	public int getEssentialDutyUserCnt(MainExcelData vo) {
		return sqlSession.selectOne(Namespace + ".getEssentialDutyUserCnt", vo);
	}


	@Override
	public void updateScore(MainExcelData vo) {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace + ".updateScore", vo);
	}


	@Override
	public void updateDocumentFileId(MainExcelData vo) {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace + ".updateDocumentFileId", vo);
	}
	

	@Override
	public void updateRelatedArticle(MainExcelData vo) {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace + ".updateRelatedArticle", vo);
	}
	
	@Override
	public Amount getEssentialRate(Amount vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace + ".getEssentialRate", vo);
	}	
	
	
	
	@Override
	public List<MainExcelData> getDutyDetailList(MainExcelData vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getDutyDetailList", vo);
	}		
	
	@Override
	public List<MainExcelData> getInspectiondocs(MainExcelData vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getInspectiondocs", vo);
	}		
	
	@Override
	public List<MainExcelData> getDutyCyle(MainExcelData vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getDutyCyle", vo);
	}		
	
	@Override
	public List<MainExcelData> getDutyAssigned(MainExcelData vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getDutyAssigned", vo);
	}		
	
	@Override
	public List<MainExcelData> getRelatedArticle(MainExcelData vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getRelatedArticle", vo);
	}		
	
	@Override
	public List<MainExcelData> getGuideLine(MainExcelData vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getGuideLine", vo);
	}		
	
	

}
