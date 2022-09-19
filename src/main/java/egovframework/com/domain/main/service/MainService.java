package egovframework.com.domain.main.service;

import java.util.LinkedHashMap;
import java.util.List;

import egovframework.com.domain.main.domain.AccidentsAmount;
import egovframework.com.domain.main.domain.Amount;
import egovframework.com.domain.main.domain.Baseline;
import egovframework.com.domain.main.domain.Company;
import egovframework.com.domain.main.domain.EssentialInfo;
import egovframework.com.domain.main.domain.EssentialRate;
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
import egovframework.com.domain.main.domain.Weather;
import egovframework.com.domain.main.domain.Workplace;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.global.http.BaseResponse;


public interface MainService {

	List<Company> getScaleInfo(Company vo);
	
	List<Company> getSectorInfo(Company vo);
	
	Company getCompanyInfo(Company vo);

	List<Workplace> getWorkplaceList(Workplace vo);
	
	Baseline getRecentBaseline(Baseline vo);
	
	Baseline getBaseline(Baseline vo);
	
	List<Baseline> getBaselineList(Baseline vo);
	
	List<Notice> getNoticeHotList(Notice vo);
	
	List<Notice> getNoticeList(Notice vo);
	
	List<Improvement> getImprovementList(Improvement vo);
	
	List<Improvement> getLeaderImprovementList(Improvement vo);
	
	Amount getAccidentsPrevention(Amount vo);
	
	Amount getImprovemetLawOrder(Amount vo);
	
	Baseline getDayInfo(Baseline vo);
	
	
	List<Amount> getAccidentsPreventionReport(Amount vo);
	
	List<Amount> getImprovemetLawOrderReport(Amount vo);
	
	int insertEssentialDuty(List<LinkedHashMap<String, String>> vo, Login login);
	
	int insertEssentialDutyUser(MainExcelData vo);
	
	void updateScore(ParamDutyCyle vo);
	
	void updateDocumentFileId(ParamDutyCyle vo);
	
	void updateRelatedArticle(ParamDutyCyle vo);
	
	EssentialInfo getEssentialRate(PramAmount vo);
	
	EssentialRate getRate(List<Integer> rateList, PramAmount vo, String title, String rateTitle);
	
	int getEssentialDutyMasterCnt(MainExcelData vo);

	int getEssentialDutyUserCnt(MainExcelData vo);
	
	int getBaselineConfirm(MainExcelData vo);
	
	List<MainExcelData> getDutyDetailList(ParamMainExcelData vo);
	
	List<MainExcelData> getInspectiondocs(MainExcelData vo);
	
	List<MainExcelData> getDutyCyle(ParamDutyCyle vo);
	
	List<MainExcelData> getDutyAssigned(ParamDutyCyle vo);
	
	List<MainExcelData> getRelatedArticle(ParamDutyCyle vo);
	
	List<MainExcelData> getGuideLine(ParamDutyCyle vo);
	
	AccidentsAmount getAccidentTotal(AccidentsAmount vo);
	
	Amount getRelatedLawRate(PramAmount vo);
	
	SafeWork getSafeWorkHistoryList(ParamSafeWork vo);
	
	List<Report> getTitleReport(Report vo);
	
	List<Report> getBaseLineReport(Report vo);	
	
	int insertSafeWorkExcelUpload(List<LinkedHashMap<String, String>> vo, ParamSafeWork login);
	
	int getSafetyFileCnt(Setting vo);
	
	void updateUserCompany(Setting vo);
	
	int insertBaseline(Setting vo);
	
	void updateSafetyFile(Setting vo);
	
	BaseResponse<Integer> insertBaseLineDataCopy(MainExcelData vo) throws Exception;
	
	void closeBaseline(Long companyId, Long baselineId, Long updateId);
	
	int insertBaseLineDataUpdate(MainExcelData vo) throws Exception;
	
	int getBaseLineDataCnt(MainExcelData vo);
	
	Weather HttpURLConnection(String strParams);
}


