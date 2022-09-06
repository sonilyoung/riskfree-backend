package egovframework.com.domain.main.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.com.domain.main.domain.AccidentsAmount;
import egovframework.com.domain.main.domain.Amount;
import egovframework.com.domain.main.domain.Baseline;
import egovframework.com.domain.main.domain.Company;
import egovframework.com.domain.main.domain.Improvement;
import egovframework.com.domain.main.domain.MainExcelData;
import egovframework.com.domain.main.domain.Notice;
import egovframework.com.domain.main.domain.ParamDutyCyle;
import egovframework.com.domain.main.domain.ParamMainExcelData;
import egovframework.com.domain.main.domain.ParamSafeWork;
import egovframework.com.domain.main.domain.PramAmount;
import egovframework.com.domain.main.domain.Report;
import egovframework.com.domain.main.domain.SafeWork;
import egovframework.com.domain.main.domain.Setting;
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
	public Company getCompanyInfo(Company vo) {
		return sqlSession.selectOne(Namespace + ".getCompanyInfo", vo);
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
	public List<Improvement> getLeaderImprovementList(Improvement vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getLeaderImprovementList", vo);
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
	public int getEssentialDutyMasterCnt(MainExcelData vo) {
		return sqlSession.selectOne(Namespace + ".getEssentialDutyMasterCnt", vo);
	}	
	
	@Override
	public int getEssentialDutyUserCnt(MainExcelData vo) {
		return sqlSession.selectOne(Namespace + ".getEssentialDutyUserCnt", vo);
	}


	@Override
	public void updateScore(ParamDutyCyle vo) {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace + ".updateScore", vo);
	}


	@Override
	public void updateDocumentFileId(ParamDutyCyle vo) {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace + ".updateDocumentFileId", vo);
	}
	

	@Override
	public void updateRelatedArticle(ParamDutyCyle vo) {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace + ".updateRelatedArticle", vo);
	}
	
	@Override
	public Amount getEssentialRate(PramAmount vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace + ".getEssentialRate", vo);
	}	
	
	
	
	@Override
	public List<MainExcelData> getDutyDetailList(ParamMainExcelData vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getDutyDetailList", vo);
	}		
	
	@Override
	public List<MainExcelData> getInspectiondocs(MainExcelData vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getInspectiondocs", vo);
	}		
	
	@Override
	public List<MainExcelData> getDutyCyle(ParamDutyCyle vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getDutyCyle", vo);
	}		
	
	@Override
	public List<MainExcelData> getDutyAssigned(ParamDutyCyle vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getDutyAssigned", vo);
	}		
	
	@Override
	public List<MainExcelData> getRelatedArticle(ParamDutyCyle vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getRelatedArticle", vo);
	}		
	
	@Override
	public List<MainExcelData> getGuideLine(ParamDutyCyle vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getGuideLine", vo);
	}		
	
	@Override
	public AccidentsAmount getAccidentTotal(AccidentsAmount vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace + ".getAccidentTotal", vo);
	}		
	
	@Override
	public Amount getRelatedRawRate(PramAmount vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace + ".getRelatedRawRate", vo);
	}	
	
	
	
	@Override
	public SafeWork getSafeWorkHistoryList(ParamSafeWork vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace + ".getSafeWorkHistoryList", vo);
	}	
	

	@Override
	public List<Report> getTitleReport(Report vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getTitleReport", vo);
	}


	@Override
	public List<Report> getBaseLineReport(Report vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getBaseLineReport", vo);
	}


	@Override
	public int getBaselineConfirm(MainExcelData vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace + ".getBaselineConfirm", vo);
	}


	@Override
	public int insertSafeWorkExcelUpload(LinkedHashMap vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert(Namespace + ".insertSafeWorkExcelUpload", vo);
	}
	
	@Override
	public int getSafetyFileCnt(Setting vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace + ".getSafetyFileCnt", vo);
	}	
	

	@Override
	public void updateUserCompany(Setting vo) {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace + ".updateUserCompany", vo);
	}
	
	
	@Override
	public int insertBaseline(Setting vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert(Namespace + ".insertBaseline", vo);
	}	
	
	@Override
	public void updateSafetyFile(Setting vo) {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace + ".updateSafetyFile", vo);
	}	
	
	@Override
	public int insertBaseLineDataCopy(MainExcelData vo) {
		return sqlSession.insert(Namespace + ".insertBaseLineDataCopy", vo);
	}	
		
}
