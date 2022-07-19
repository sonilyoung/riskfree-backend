package egovframework.com.domain.improvement.dao;

import java.util.List;

import egovframework.com.domain.improvement.domain.Improvement;
import egovframework.com.domain.improvement.parameter.ImprovementParameter;
import egovframework.com.domain.improvement.parameter.ImprovementSearchParameter;

public interface ImprovementDAO {

	List<Improvement> getImprovementList(ImprovementSearchParameter parameter);

	void insertImprovement(ImprovementParameter parameter);

	Improvement getImprovement(Long improveSeq, Long companyId);

	void modifyImprovement(ImprovementParameter parameter);

	void deleteImprovement(Long companyId, Long improveId);

}
