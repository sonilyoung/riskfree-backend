package egovframework.com.domain.main.dao;

import java.util.List;

import egovframework.com.domain.main.domain.Baseline;
import egovframework.com.domain.main.domain.Company;
import egovframework.com.domain.main.domain.Improvement;
import egovframework.com.domain.main.domain.Notice;
import egovframework.com.domain.main.domain.Workplace;


public interface MainDAO {

	
	List<Company> getScaleSectorInfo(Company vo);
	
	Company getCompanyInfo(Long companyId);

	List<Workplace> getWorkplaceList(Workplace vo);
	
	Baseline getBaseline(Baseline vo);

	List<Baseline> getBaselineList(Baseline vo);
	
	List<Notice> getNoticeList(Notice vo);

	
	List<Improvement> getImprovementList(Improvement vo);
	
	
	Baseline getDayInfo(Baseline vo);
}
