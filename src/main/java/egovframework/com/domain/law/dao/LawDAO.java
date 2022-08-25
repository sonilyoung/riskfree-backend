package egovframework.com.domain.law.dao;

import java.util.List;
import java.util.Map;

import egovframework.com.domain.law.domain.Law;
import egovframework.com.domain.law.parameter.LawParameter;
import egovframework.com.domain.law.parameter.LawSearchParameter;

public interface LawDAO {

	List<Law> getLawImprovementList(LawSearchParameter parameter);

	int insertLawImprovement(LawParameter parameter);

	Law getLawImprovement(Long companyId, Long lawImproveId);

	int updateLawImprovement(LawParameter parameter);

	int deleteLawImprovement(Long companyId, Long userId, Long lawImproveId);

	List<Map<String, String>> getIssueReasonList(Long companyId);

}
