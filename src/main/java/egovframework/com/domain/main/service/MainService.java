package egovframework.com.domain.main.service;

import java.util.LinkedHashMap;
import java.util.List;

import egovframework.com.domain.main.domain.AccidentsAmount;
import egovframework.com.domain.main.domain.Amount;
import egovframework.com.domain.main.domain.Baseline;
import egovframework.com.domain.main.domain.Company;
import egovframework.com.domain.main.domain.Improvement;
import egovframework.com.domain.main.domain.MainExcelData;
import egovframework.com.domain.main.domain.Notice;
import egovframework.com.domain.main.domain.Workplace;


public interface MainService {

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
	
	int insertEssentialDuty(List<LinkedHashMap<String, String>> vo);
	
	int insertrelatedRaw(List<LinkedHashMap<String, String>> vo);
	
	int insertEssentialDutyUser(MainExcelData vo);
	
	void updateScore(MainExcelData vo);
	
	void updateDocumentFileId(MainExcelData vo);
	
	void updateRelatedArticle(MainExcelData vo);
	
	LinkedHashMap<String, String> getEssentialRate(Amount vo);
	
	void getRate(LinkedHashMap<String, String> result ,List<Integer> rateList, Amount vo, String title, String rateTitle);
	
	List<MainExcelData> getDutyDetailList(MainExcelData vo);
	
	List<MainExcelData> getInspectiondocs(MainExcelData vo);
	
	List<MainExcelData> getDutyCyle(MainExcelData vo);
	
	List<MainExcelData> getDutyAssigned(MainExcelData vo);
	
	List<MainExcelData> getRelatedArticle(MainExcelData vo);
	
	List<MainExcelData> getGuideLine(MainExcelData vo);
	
	AccidentsAmount getAccidentTotal(AccidentsAmount vo);
	
}


