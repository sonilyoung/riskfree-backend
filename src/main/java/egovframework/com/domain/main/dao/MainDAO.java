package egovframework.com.domain.main.dao;

import java.util.LinkedHashMap;
import java.util.List;

import egovframework.com.domain.company.parameter.CompanyParameter;
import egovframework.com.domain.main.domain.Amount;
import egovframework.com.domain.main.domain.Baseline;
import egovframework.com.domain.main.domain.Company;
import egovframework.com.domain.main.domain.Improvement;
import egovframework.com.domain.main.domain.MainExcelData;
import egovframework.com.domain.main.domain.Notice;
import egovframework.com.domain.main.domain.Workplace;


public interface MainDAO {

	
	List<Company> getScaleInfo(Company vo);
	
	List<Company> getSectorInfo(Company vo);
	
	Company getCompanyInfo(Long companyId);

	List<Workplace> getWorkplaceList(Workplace vo);
	
	Baseline getRecentBaseline(Baseline vo);
	
	Baseline getBaseline(Baseline vo);

	List<Baseline> getBaselineList(Baseline vo);
	
	List<Notice> getNoticeHotList(Notice vo);	
	
	List<Notice> getNoticeList(Notice vo);

	List<Improvement> getImprovementList(Improvement vo);
	
	Amount getAccidentsPrevention(Amount vo);
	
	Amount getImprovemetLawOrder(Amount vo);
	
	Baseline getDayInfo(Baseline vo);
	
	
	List<Amount> getAccidentsPreventionReport(Amount vo);
	
	List<Amount> getImprovemetLawOrderReport(Amount vo);	
	
	MainExcelData selectEssentialDutyVer();
	
	int insertEssentialDuty(LinkedHashMap vo);
	
	List<MainExcelData> getEssentialDuty(MainExcelData vo);
	
	int insertEssentialDutyUser(MainExcelData vo);
	
	int getEssentialDutyUserCnt(MainExcelData vo);
	
	
	void updateScore(MainExcelData vo);
	
	void updateDocumentFileId(MainExcelData vo);
	
	void updateRelatedArticle(MainExcelData vo);
	
	Amount getEssentialRate(Amount vo);
	
	List<MainExcelData> getDutyDetailList(MainExcelData vo);
	
	List<MainExcelData> getInspectiondocs(MainExcelData vo);
	
	List<MainExcelData> getDutyCyle(MainExcelData vo);
	
	List<MainExcelData> getDutyAssigned(MainExcelData vo);
	
	List<MainExcelData> getRelatedArticle(MainExcelData vo);
	
	List<MainExcelData> getGuideLine(MainExcelData vo);
	
	

	
}
