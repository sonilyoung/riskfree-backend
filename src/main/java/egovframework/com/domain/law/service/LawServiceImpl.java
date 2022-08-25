package egovframework.com.domain.law.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.com.domain.law.dao.LawDAO;
import egovframework.com.domain.law.domain.Law;
import egovframework.com.domain.law.parameter.LawParameter;
import egovframework.com.domain.law.parameter.LawSearchParameter;

@Service
public class LawServiceImpl implements LawService {

	@Autowired
	private LawDAO repository;
	
	@Override
	public List<Law> getLawImprovementList(LawSearchParameter parameter) {
		return repository.getLawImprovementList(parameter);
	}

	@Override
	public int insertLawImprovement(LawParameter parameter) {
		return repository.insertLawImprovement(parameter);
	}

	@Override
	public Law getLawImprovement(Long companyId, Long lawImproveId) {
		return repository.getLawImprovement(companyId, lawImproveId);
	}

	@Override
	public int updateLawImprovement(LawParameter parameter) {
		return repository.updateLawImprovement(parameter);
	}

	@Override
	public int deleteLawImprovement(Long companyId, Long userId ,Long lawImproveId) {
		return repository.deleteLawImprovement(companyId, userId, lawImproveId);
	}

	@Override
	public List<Map<String, String>> getIssueReasonList(Long companyId) {
		return repository.getIssueReasonList(companyId);
	} 

}
