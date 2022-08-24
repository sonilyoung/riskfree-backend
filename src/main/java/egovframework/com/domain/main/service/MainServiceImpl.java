package egovframework.com.domain.main.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.com.domain.main.dao.MainDAO;
import egovframework.com.domain.main.domain.Amount;
import egovframework.com.domain.main.domain.Baseline;
import egovframework.com.domain.main.domain.Company;
import egovframework.com.domain.main.domain.ExcelTitleType;
import egovframework.com.domain.main.domain.Improvement;
import egovframework.com.domain.main.domain.MainExcelData;
import egovframework.com.domain.main.domain.Notice;
import egovframework.com.domain.main.domain.Workplace;

@Service
public class MainServiceImpl implements MainService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainServiceImpl.class);
	
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
	public Company getCompanyInfo(Long companyId) {
		return repository.getCompanyInfo(companyId);
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
	public int insertEssentialDuty(List<LinkedHashMap<String, String>> vo) {
		// TODO Auto-generated method stub
		int result = 0;
		
		for(int i=0; i < vo.size(); i++) {
			repository.insertEssentialDuty(vo.get(i));
		}
		result = 1;
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
			List<MainExcelData> resultList = repository.getEssentialDuty(version);
			for(int i=0; i < resultList.size(); i++) {
				resultList.get(i).setWorkplaceId(vo.getWorkplaceId());
				resultList.get(i).setBaselineId(vo.getBaselineId());
				resultList.get(i).setBaselineStart(vo.getBaselineStart());
				resultList.get(i).setBaselineEnd(vo.getBaselineEnd());
				repository.insertEssentialDutyUser(resultList.get(i));
			}
			result = 1;			
		}
		
		return result;		
	}


	@Override
	public void updateScore(MainExcelData vo) {
		// TODO Auto-generated method stub
		repository.updateScore(vo);
	}


	@Override
	public void updateDocumentFileId(MainExcelData vo) {
		// TODO Auto-generated method stub
		repository.updateDocumentFileId(vo);
	}	
	
	
	@Override
	public void updateRelatedArticle(MainExcelData vo) {
		// TODO Auto-generated method stub
		repository.updateRelatedArticle(vo);
	}	
	
	
	@Override
	public LinkedHashMap<String, String> getEssentialRate(Amount vo) {
		// TODO Auto-generated method stub
		
		LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
		List<Integer> rateList = new ArrayList<Integer>(); 
		int topRate = 0;
		
		this.getRate(result, rateList, vo, ExcelTitleType.TITLE1.getCode(), ExcelTitleType.TITLE1.getRate());
		this.getRate(result, rateList, vo, ExcelTitleType.TITLE2.getCode(), ExcelTitleType.TITLE2.getRate());
		this.getRate(result, rateList, vo, ExcelTitleType.TITLE3.getCode(), ExcelTitleType.TITLE3.getRate());
		this.getRate(result, rateList, vo, ExcelTitleType.TITLE4.getCode(), ExcelTitleType.TITLE4.getRate());
		this.getRate(result, rateList, vo, ExcelTitleType.TITLE5.getCode(), ExcelTitleType.TITLE5.getRate());
		this.getRate(result, rateList, vo, ExcelTitleType.TITLE6.getCode(), ExcelTitleType.TITLE6.getRate());
		this.getRate(result, rateList, vo, ExcelTitleType.TITLE7.getCode(), ExcelTitleType.TITLE7.getRate());
		this.getRate(result, rateList, vo, ExcelTitleType.TITLE8.getCode(), ExcelTitleType.TITLE8.getRate());
		this.getRate(result, rateList, vo, ExcelTitleType.TITLE9.getCode(), ExcelTitleType.TITLE9.getRate());
		
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
		
		result.put("TOPSCORE", topScore);
		result.put("TOPRATE", topRate+"%");
		return result;
	}		

	
	public void getRate(
			LinkedHashMap<String, String> result 
			,List<Integer> rateList
			, Amount vo
			, String code
			, String rateTitle) {
		
		int targetRate = 0;
		try {
			vo.setGroupId(code);
			Amount at = repository.getEssentialRate(vo);
			if(at!=null) {
				targetRate = at.getEvaluationRate();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {			
			result.put(rateTitle, targetRate+"%");
			rateList.add(targetRate);
			targetRate = 0;
		}
	}	
	
	
	@Override
	public List<MainExcelData> getDutyDetailList(MainExcelData vo) {
		// TODO Auto-generated method stub
		return repository.getDutyDetailList(vo);
	}	
	
	@Override
	public List<MainExcelData> getInspectiondocs(MainExcelData vo) {
		// TODO Auto-generated method stub
		return repository.getInspectiondocs(vo);
	}	
	
	@Override
	public List<MainExcelData> getDutyCyle(MainExcelData vo) {
		// TODO Auto-generated method stub
		return repository.getDutyCyle(vo);
	}	
	
	@Override
	public List<MainExcelData> getDutyAssigned(MainExcelData vo) {
		// TODO Auto-generated method stub
		return repository.getDutyAssigned(vo);
	}	
	
	@Override
	public List<MainExcelData> getRelatedArticle(MainExcelData vo) {
		// TODO Auto-generated method stub
		return repository.getRelatedArticle(vo);
	}	
	
	@Override
	public List<MainExcelData> getGuideLine(MainExcelData vo) {
		// TODO Auto-generated method stub
		return repository.getGuideLine(vo);
	}	

}
