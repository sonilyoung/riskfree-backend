package egovframework.com.domain.main.dao;

import java.util.LinkedHashMap;
import java.util.List;

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


public interface MainDAO {

	
	List<Company> getScaleInfo(Company vo);
	
	List<Company> getSectorInfo(Company vo);
	
	Company getCompanyInfo(Company vo);

	int getCeoWorkplaceCount(Workplace vo);
	
	int getWorkplaceCount(Workplace vo);
	
	List<Workplace> getWorkplaceRoleWorkerList(Workplace vo);
	
	List<Workplace> getWorkplaceRoleList(Workplace vo);
	
	List<Workplace> getWorkplaceList(Workplace vo);
	
	List<Workplace> getMyWorkplace(Workplace vo);
	
	Setting getCheckBaseline(Setting vo);
	
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
	
	
	List<Report> getAccidentsPreventionReport(Report vo);
	
	List<Report> getImprovemetLawOrderReport(Report vo);	
	
	MainExcelData getEssentialDutyVersion();
	
	MainExcelData getEssentialDutyVersionDate();
	
	int insertEssentialDuty(LinkedHashMap vo);
	
	List<MainExcelData> getMasterEssentialDutyList(ParamMainExcelData vo);
	
	List<MainExcelData> getEssentialDuty(MainExcelData vo);
	
	int insertEssentialDutyUser(MainExcelData vo);
	
	int getEssentialDutyMasterCnt(MainExcelData vo);
	
	int getBaselineCopyConfirm(MainExcelData vo);
	
	int getBaselineConfirm(MainExcelData vo);

	List<MainExcelData> getEssentialDutyUserCopyData(MainExcelData vo);
	
	int getEssentialDutyUserCnt(MainExcelData vo);
	
	void updateScore(ParamDutyCyle vo);
	
	void updateMasterDocumentFileId(ParamDutyCyle vo);
	
	void updateDocumentFileId(ParamDutyCyle vo);
	
	void updateRelatedArticle(ParamDutyCyle vo);
	
	Amount getEssentialRate(PramAmount vo);
	
	List<Amount> getAdminEssentialRate(PramAmount vo);
	
	List<MainExcelData> getDutyDetailList(ParamMainExcelData vo);
	
	List<MainExcelData> getMasterInspectiondocs(MainExcelData vo);
	
	List<MainExcelData> getInspectiondocs(MainExcelData vo);
	
	List<MainExcelData> getDutyCyle(ParamDutyCyle vo);
	
	List<MainExcelData> getDutyAssigned(ParamDutyCyle vo);
	
	List<MainExcelData> getRelatedArticle(ParamDutyCyle vo);
	
	List<MainExcelData> getGuideLine(ParamDutyCyle vo);
	
	AccidentsAmount getAccidentTotal(AccidentsAmount vo);	
	
	Amount getRelatedLawRate(PramAmount vo);
	
	SafeWork getSafeWorkHistoryList(ParamSafeWork vo);
	
	List<Report> getTitleReport1(Report vo);
	
	List<Report> getTitleReport2(Report vo);
	
	//List<Report> getTitleReport3(Report vo);
	
	List<Report> getTitleReport4(Report vo);

	List<Report> getTitleReport5(Report vo);
	
	List<Report> getTitleReport6(Report vo);
	
	List<Report> getTitleReport7(Report vo);
	
	List<Report> getBaseLineReport1(Report vo);
	
	List<Report> getBaseLineReport2(Report vo);
	
	List<Report> getBaseLineReport3(Report vo);
	
	List<Report> getBaseLineReport4(Report vo);
	
	List<Report> getWorkPlaceReport(Report vo);
	
	List<Report> getItemsReport(Report vo);
	
	List<Report> getItemsReportGraph(Report vo);
	
	List<Report> getItemsWorkPlaceReportGraph(Report vo);
	
	List<Report> getAccidentsPreventionGraph(Report vo);
	
	List<Report> getImprovemetLawOrderGraph(Report vo);
	
	List<Report> getRelatedLawRateGraph(Report vo);
		
	
	int insertSafeWorkExcelUpload(LinkedHashMap vo);
	
	Setting getSafetyFileId(Setting vo);
	
	void updateUserCompanyLogoId(Setting vo);
	
	void updateUserCompany(Setting vo);
	
	Long insertBaseline(Setting vo);
	
	Long insertWorkplaceBaseline(Setting vo);
	
	Long getBaselineMaxInfo(Setting vo);
	
	void updateSafetyFile(Setting vo);
	
	int insertBaseLineDataCopy(MainExcelData vo);
	
	int getEducdDataConfirm(MainExcelData vo);
	
	void closeBaseline(Baseline vo);
	
	void deleteEssentialDutyUser(MainExcelData vo);
	
	int getBaseLineDataCnt(MainExcelData vo);
	
	int getNowNoticeList(Notice vo);
	
	int getBaselineCnt(Setting vo);
	
	int getFirstBaselineCnt(Setting vo);
	
	void deleteEducdData(MainExcelData vo);
}




