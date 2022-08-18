package egovframework.com.domain.main.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.com.domain.main.dao.MainDAO;
import egovframework.com.domain.main.domain.Amount;
import egovframework.com.domain.main.domain.Baseline;
import egovframework.com.domain.main.domain.Company;
import egovframework.com.domain.main.domain.Improvement;
import egovframework.com.domain.main.domain.Notice;
import egovframework.com.domain.main.domain.Workplace;

@Service
public class MainServiceImpe implements MainService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainServiceImpe.class);
	
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



}
