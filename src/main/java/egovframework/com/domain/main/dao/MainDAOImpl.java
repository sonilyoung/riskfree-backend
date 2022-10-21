package egovframework.com.domain.main.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	public int getCeoWorkplaceCount(Workplace vo) {
		return sqlSession.selectOne(Namespace + ".getCeoWorkplaceCount", vo);
	}
	
	@Override
	public int getWorkplaceCount(Workplace vo) {
		return sqlSession.selectOne(Namespace + ".getWorkplaceCount", vo);
	}


	@Override
	public List<Workplace> getWorkplaceRoleWorkerList(Workplace vo) {
		return sqlSession.selectList(Namespace + ".getWorkplaceRoleWorkerList", vo);
	}	
	
	@Override
	public List<Workplace> getWorkplaceRoleList(Workplace vo) {
		return sqlSession.selectList(Namespace + ".getWorkplaceRoleList", vo);
	}	
	
	@Override
	public List<Workplace> getWorkplaceList(Workplace vo) {
		return sqlSession.selectList(Namespace + ".getWorkplaceList", vo);
	}

	@Override
	public List<Workplace> getMyWorkplace(Workplace vo) {
		return sqlSession.selectList(Namespace + ".getMyWorkplace", vo);
	}
	

	@Override
	public Setting getCheckBaseline(Setting vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace + ".getCheckBaseline", vo);
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
	public List<Report> getAccidentsPreventionReport(Report vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getAccidentsPreventionReport", vo);
	}


	@Override
	public List<Report> getImprovemetLawOrderReport(Report vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getImprovemetLawOrderReport", vo);
	}

    @Override
    public MainExcelData getEssentialDutyVersion() {
        return sqlSession.selectOne(Namespace + ".getEssentialDutyVersion");
    }
    
    @Override
    public MainExcelData getEssentialDutyVersionDate() {
        return sqlSession.selectOne(Namespace + ".getEssentialDutyVersionDate");
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
	public List<MainExcelData> getEssentialDutyUserCopyData(MainExcelData vo) {
		return sqlSession.selectList(Namespace + ".getEssentialDutyUserCopyData", vo);
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
	public void updateMasterDocumentFileId(ParamDutyCyle vo) {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace + ".updateMasterDocumentFileId", vo);
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
	public List<Amount> getAdminEssentialRate(PramAmount vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getAdminEssentialRate", vo);
	}	
	
		
	
	@Override
	public List<MainExcelData> getDutyDetailList(ParamMainExcelData vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getDutyDetailList", vo);
	}		
	
	@Override
	public List<MainExcelData> getMasterInspectiondocs(MainExcelData vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getMasterInspectiondocs", vo);
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
	public Amount getRelatedLawRate(PramAmount vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace + ".getRelatedLawRate", vo);
	}	
	
	
	
	@Override
	public SafeWork getSafeWorkHistoryList(ParamSafeWork vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace + ".getSafeWorkHistoryList", vo);
	}	
	

	@Override
	public List<Report> getTitleReport1(Report vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getTitleReport1", vo);
	}
	
	@Override
	public List<Report> getTitleReport2(Report vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getTitleReport2", vo);
	}
	
	@Override
	public List<Report> getTitleReport4(Report vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getTitleReport4", vo);
	}	
	
	
	@Override
	public List<Report> getTitleReport5(Report vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getTitleReport5", vo);
	}	
	
	
	@Override
	public List<Report> getTitleReport6(Report vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getTitleReport6", vo);
	}	
	
	@Override
	public List<Report> getTitleReport7(Report vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getTitleReport7", vo);
	}		
	


	@Override
	public List<Report> getBaseLineReport1(Report vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getBaseLineReport1", vo);
	}
	
	@Override
	public List<Report> getBaseLineReport2(Report vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getBaseLineReport2", vo);
	}
	
	@Override
	public List<Report> getBaseLineReport3(Report vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getBaseLineReport3", vo);
	}
	
	@Override
	public List<Report> getBaseLineReport4(Report vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getBaseLineReport4", vo);
	}	
	
	@Override
	public List<Report> getWorkPlaceReport(Report vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getWorkPlaceReport", vo);
	}	
	
	@Override
	public List<Report> getItemsReportGraph(Report vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getItemsReportGraph", vo);
	}			
	
	@Override
	public List<Report> getItemsWorkPlaceReportGraph(Report vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getItemsWorkPlaceReportGraph", vo);
	}	
	
	@Override
	public List<Report> getAccidentsPreventionGraph(Report vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getAccidentsPreventionGraph", vo);
	}	
	
	@Override
	public List<Report> getImprovemetLawOrderGraph(Report vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getImprovemetLawOrderGraph", vo);
	}				
	
	@Override
	public List<Report> getRelatedLawRateGraph(Report vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getImprovemetLawOrderGraph", vo);
	}				
	
	@Override
	public List<Report> getItemsReport(Report vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getItemsReport", vo);
	}		


	@Override
	public int getBaselineCopyConfirm(MainExcelData vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace + ".getBaselineCopyConfirm", vo);
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
	public Setting getSafetyFileId(Setting vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace + ".getSafetyFileId", vo);
	}	
	
	
	@Override
	public void updateUserCompanyLogoId(Setting vo) {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace + ".updateUserCompanyLogoId", vo);
	}
		

	@Override
	public void updateUserCompany(Setting vo) {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace + ".updateUserCompany", vo);
	}
	
	
	@Override
	public Long insertBaseline(Setting vo) {
		// TODO Auto-generated method stub
		sqlSession.insert(Namespace + ".insertBaseline", vo);
		return this.getBaselineMaxInfo(vo);
	}	
	
	@Override
	public Long getBaselineMaxInfo(Setting vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace + ".getBaselineMaxInfo", vo);
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
	
	@Override
	public int getEducdDataConfirm(MainExcelData vo) {
		return sqlSession.selectOne(Namespace + ".getEducdDataConfirm", vo);
	}		
	
	@Override
	public void closeBaseline(Long companyId, Long baselineId, Long updateId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("companyId", companyId);
		map.put("baselineId", baselineId);
		map.put("updateId", updateId);
		sqlSession.update(Namespace + ".closeBaseline", map);
	}


	@Override
	public void deleteEssentialDutyUser(MainExcelData vo) {
		// TODO Auto-generated method stub
		sqlSession.delete(Namespace + ".deleteEssentialDutyUser", vo);
	}


	@Override
	public int getBaseLineDataCnt(MainExcelData vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert(Namespace + ".getBaseLineDataCnt", vo);
	}


	@Override
	public int getNowNoticeList(Notice vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace + ".getNowNoticeList", vo);
	}	
	
	
	@Override
	public int getBaselineCnt(Setting vo) {
		return sqlSession.selectOne(Namespace + ".getBaselineCnt", vo);
	}	
	
	@Override
	public void deleteEducdData(MainExcelData vo) {
		// TODO Auto-generated method stub
		sqlSession.delete(Namespace + ".deleteEducdData", vo);
	}


	@Override
	public List<MainExcelData> getMasterEssentialDutyList(ParamMainExcelData vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".getMasterEssentialDutyList", vo);
	}
	
		
}
