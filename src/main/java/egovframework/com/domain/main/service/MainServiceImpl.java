package egovframework.com.domain.main.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.com.domain.law.domain.DutyBotton;
import egovframework.com.domain.main.dao.MainDAO;
import egovframework.com.domain.main.domain.AccidentsAmount;
import egovframework.com.domain.main.domain.Amount;
import egovframework.com.domain.main.domain.Baseline;
import egovframework.com.domain.main.domain.Company;
import egovframework.com.domain.main.domain.EssentialInfo;
import egovframework.com.domain.main.domain.EssentialRate;
import egovframework.com.domain.main.domain.ExcelTitleType;
import egovframework.com.domain.main.domain.Improvement;
import egovframework.com.domain.main.domain.MainExcelData;
import egovframework.com.domain.main.domain.Notice;
import egovframework.com.domain.main.domain.ParamDutyCyle;
import egovframework.com.domain.main.domain.ParamMainExcelData;
import egovframework.com.domain.main.domain.ParamSafeWork;
import egovframework.com.domain.main.domain.PramAmount;
import egovframework.com.domain.main.domain.Report;
import egovframework.com.domain.main.domain.SafeWork;
import egovframework.com.domain.main.domain.Workplace;
import egovframework.com.domain.portal.logn.domain.Login;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	private MainDAO repository;
	

	@Override
	public List<Company> getScaleInfo(Company vo) {
		// TODO Auto-generated method stub
		return repository.getScaleInfo(vo);
	}
	

	@Override
	public List<Company> getSectorInfo(Company vo) {
		// TODO Auto-generated method stub
		return repository.getSectorInfo(vo);
	}	
	
	@Override
	public Company getCompanyInfo(Company vo) {
		return repository.getCompanyInfo(vo);
	}

	@Override
	public List<Workplace> getWorkplaceList(Workplace vo) {
		return repository.getWorkplaceList(vo);
	}
	
	@Override
	public Baseline getRecentBaseline(Baseline vo) {
		// TODO Auto-generated method stub
		return repository.getRecentBaseline(vo);
	}		
	
	@Override
	public Baseline getBaseline(Baseline vo) {
		// TODO Auto-generated method stub
		return repository.getBaseline(vo);
	}	

	@Override
	public List<Baseline> getBaselineList(Baseline vo) {
		// TODO Auto-generated method stub
		return repository.getBaselineList(vo);
	}

	@Override
	public List<Notice> getNoticeHotList(Notice vo) {
		// TODO Auto-generated method stub
		return repository.getNoticeHotList(vo);
	}	
	
	@Override
	public List<Notice> getNoticeList(Notice vo) {
		// TODO Auto-generated method stub
		return repository.getNoticeList(vo);
	}

	@Override
	public List<Improvement> getImprovementList(Improvement vo) {
		// TODO Auto-generated method stub
		return repository.getImprovementList(vo);
	}
	
	@Override
	public List<Improvement> getLeaderImprovementList(Improvement vo) {
		// TODO Auto-generated method stub
		return repository.getLeaderImprovementList(vo);
	}
	
	@Override
	public Amount getAccidentsPrevention(Amount vo) {
		// TODO Auto-generated method stub
		return repository.getAccidentsPrevention(vo);
	}
	
	
	@Override
	public Amount getImprovemetLawOrder(Amount vo) {
		// TODO Auto-generated method stub
		return repository.getImprovemetLawOrder(vo);
	}		
	
	@Override
	public Baseline getDayInfo(Baseline vo) {
		// TODO Auto-generated method stub
		return repository.getDayInfo(vo);
	}


	@Override
	public List<Amount> getAccidentsPreventionReport(Amount vo) {
		// TODO Auto-generated method stub
		return repository.getAccidentsPreventionReport(vo);
	}


	@Override
	public List<Amount> getImprovemetLawOrderReport(Amount vo) {
		// TODO Auto-generated method stub
		return repository.getImprovemetLawOrderReport(vo);
	}
	


	@Override
	@Transactional
	public int insertEssentialDuty(List<LinkedHashMap<String, String>> vo, Login login) {
		// TODO Auto-generated method stub
		int result = 0;
		MainExcelData med = new MainExcelData();
		med.setCompanyId(login.getCompanyId());
		int baseCnt = repository.getBaselineConfirm(med);
		
		if(baseCnt > 0) {
			for(int i=0; i < vo.size(); i++) {
				repository.insertEssentialDuty(vo.get(i));
			}
			result = 1;			
		}
		return result;
	}

	@Override
	@Transactional
	public int insertRelatedRaw(List<LinkedHashMap<String, String>> vo, DutyBotton login) {
		// TODO Auto-generated method stub
		int result = 0;
		MainExcelData med = new MainExcelData();
		med.setCompanyId(login.getCompanyId());
		int baseCnt = repository.getBaselineConfirm(med);
		
		if(baseCnt > 0) {
			for(int i=0; i < vo.size(); i++) {
				repository.insertRelatedRaw(vo.get(i));
			}
			result = 1;			
		}
		return result;		
	}	
	
	
	@Override
	@Transactional
	public int insertEssentialDutyUser(MainExcelData vo) {
		// TODO Auto-generated method stub
		int result = 0;
		int cnt = repository.getEssentialDutyUserCnt(vo);
		if (cnt <= 0) {
			MainExcelData version = repository.selectEssentialDutyVer();
			
			if(version!=null) {
				List<MainExcelData> resultList = repository.getEssentialDuty(version);
				if(resultList!=null) {
					for(int i=0; i < resultList.size(); i++) {
						resultList.get(i).setWorkplaceId(vo.getWorkplaceId());
						resultList.get(i).setBaselineId(vo.getBaselineId());
						resultList.get(i).setBaselineStart(vo.getBaselineStart());
						resultList.get(i).setBaselineEnd(vo.getBaselineEnd());
						repository.insertEssentialDutyUser(resultList.get(i));
					}
					
					if(resultList.size() > 0) {
						result = 1;
					}				
				}				
			}
		}
		
		return result;		
	}


	@Override
	public void updateScore(ParamDutyCyle vo) {
		// TODO Auto-generated method stub
		repository.updateScore(vo);
	}


	@Override
	public void updateDocumentFileId(ParamDutyCyle vo) {
		// TODO Auto-generated method stub
		repository.updateDocumentFileId(vo);
	}	
	
	
	@Override
	public void updateRelatedArticle(ParamDutyCyle vo) {
		// TODO Auto-generated method stub
		repository.updateRelatedArticle(vo);
	}	
	
	
	@Override
	public EssentialInfo getEssentialRate(PramAmount vo) {
		// TODO Auto-generated method stub
		
		List<Integer> rateList = new ArrayList<Integer>(); 
		int topRate = 0;
		
		EssentialRate r1 = this.getRate(rateList, vo, ExcelTitleType.TITLE1.getCode(), ExcelTitleType.TITLE1.getRate());
		EssentialRate r2 = this.getRate(rateList, vo, ExcelTitleType.TITLE2.getCode(), ExcelTitleType.TITLE2.getRate());
		EssentialRate r3 = this.getRate(rateList, vo, ExcelTitleType.TITLE3.getCode(), ExcelTitleType.TITLE3.getRate());
		EssentialRate r4 = this.getRate(rateList, vo, ExcelTitleType.TITLE4.getCode(), ExcelTitleType.TITLE4.getRate());
		EssentialRate r5 = this.getRate(rateList, vo, ExcelTitleType.TITLE5.getCode(), ExcelTitleType.TITLE5.getRate());
		EssentialRate r6 = this.getRate(rateList, vo, ExcelTitleType.TITLE6.getCode(), ExcelTitleType.TITLE6.getRate());
		EssentialRate r7 = this.getRate(rateList, vo, ExcelTitleType.TITLE7.getCode(), ExcelTitleType.TITLE7.getRate());
		EssentialRate r8 = this.getRate(rateList, vo, ExcelTitleType.TITLE8.getCode(), ExcelTitleType.TITLE8.getRate());
		EssentialRate r9 = this.getRate(rateList, vo, ExcelTitleType.TITLE9.getCode(), ExcelTitleType.TITLE9.getRate());
		
		EssentialInfo eInfo = new EssentialInfo();
		eInfo.setRate1(r1);
		eInfo.setRate2(r2);
		eInfo.setRate3(r3);
		eInfo.setRate4(r4);
		eInfo.setRate5(r5);
		eInfo.setRate6(r6);
		eInfo.setRate7(r7);
		eInfo.setRate8(r8);
		eInfo.setRate9(r9);
		
		for(int i=0;i<rateList.size();i++) {
			topRate += rateList.get(i);
		}		
		
		topRate = (int) Math.floor(topRate/ExcelTitleType.values().length);
		
		String topScore = "";
		if(topRate >= 90) {
			//정상
			topScore = "normal";
		}else if(topRate <= 90 && topRate >= 80) {
			//주의
			topScore = "caution";
		}else if(topRate <= 79 && topRate >= 70) {
			//경고
			topScore = "warning";
		}else if(topRate < 70) {
			//위험
			topScore = "danger";
		}
		
		eInfo.setTopScore(topScore);
		eInfo.setTopRate(topRate+"%");
		return eInfo;
	}		

	
	public EssentialRate getRate(
			List<Integer> rateList
			, PramAmount vo
			, String code
			, String rateTitle) {
		
		int targetRate = 0;
		EssentialRate er = new EssentialRate();
		try {
			vo.setGroupId(code);
			Amount at = repository.getEssentialRate(vo);
			if(at!=null) {
				targetRate = at.getEvaluationRate();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {			
			//result.put(rateTitle, targetRate+"%");
			er.setGroupId(vo.getGroupId());
			er.setTitle(rateTitle);
			er.setScore(targetRate+"%");
			rateList.add(targetRate);
			targetRate = 0;
		}
		return er;
	}	
	
	
	@Override
	public List<MainExcelData> getDutyDetailList(ParamMainExcelData vo) {
		// TODO Auto-generated method stub
		return repository.getDutyDetailList(vo);
	}	
	
	@Override
	public List<MainExcelData> getInspectiondocs(MainExcelData vo) {
		// TODO Auto-generated method stub
		return repository.getInspectiondocs(vo);
	}
	
	@Override
	public List<MainExcelData> getDutyCyle(ParamDutyCyle vo) {
		// TODO Auto-generated method stub
		return repository.getDutyCyle(vo);
	}	
	
	@Override
	public List<MainExcelData> getDutyAssigned(ParamDutyCyle vo) {
		// TODO Auto-generated method stub
		return repository.getDutyAssigned(vo);
	}	
	
	@Override
	public List<MainExcelData> getRelatedArticle(ParamDutyCyle vo) {
		// TODO Auto-generated method stub
		return repository.getRelatedArticle(vo);
	}	
	
	@Override
	public List<MainExcelData> getGuideLine(ParamDutyCyle vo) {
		// TODO Auto-generated method stub
		return repository.getGuideLine(vo);
	}	
	
	@Override
	public AccidentsAmount getAccidentTotal(AccidentsAmount vo) {
		// TODO Auto-generated method stub
		return repository.getAccidentTotal(vo);
	}
	
	@Override
	public Amount getRelatedRawRate(PramAmount vo) {
		// TODO Auto-generated method stub
		return repository.getRelatedRawRate(vo);
	}			

	@Override
	public SafeWork getSafeWorkHistoryList(ParamSafeWork vo) {
		// TODO Auto-generated method stub
		return repository.getSafeWorkHistoryList(vo);
	}	
		

	@Override
	public List<Report> getTitleReport(Report vo) {
		// TODO Auto-generated method stub
		return repository.getTitleReport(vo);
	}


	@Override
	public List<Report> getBaseLineReport(Report vo) {
		// TODO Auto-generated method stub
		return repository.getBaseLineReport(vo);
	}


	@Override
	public int getEssentialDutyMasterCnt(MainExcelData vo) {
		// TODO Auto-generated method stub
		return repository.getEssentialDutyMasterCnt(vo);
	}


	@Override
	public int getEssentialDutyUserCnt(MainExcelData vo) {
		// TODO Auto-generated method stub
		return repository.getEssentialDutyUserCnt(vo);
	}


	@Override
	public int getBaselineConfirm(MainExcelData vo) {
		// TODO Auto-generated method stub
		return repository.getBaselineConfirm(vo);
	}	
	

	@Override
	@Transactional
	public int insertSafeWorkExcelUpload(List<LinkedHashMap<String, String>> vo, ParamSafeWork login) {
		// TODO Auto-generated method stub
		int result = 0;
		MainExcelData med = new MainExcelData();
		med.setCompanyId(login.getCompanyId());
		int baseCnt = repository.getBaselineConfirm(med);
		
		if(baseCnt > 0) {
			for(int i=0; i < vo.size(); i++) {
				repository.insertSafeWorkExcelUpload(vo.get(i));
			}
			result = 1;			
		}
		return result;		
	}	
		
}
