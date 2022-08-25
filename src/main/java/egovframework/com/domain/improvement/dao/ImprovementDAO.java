package egovframework.com.domain.improvement.dao;

import java.util.List;
import java.util.Map;

import egovframework.com.domain.improvement.domain.Improvement;
import egovframework.com.domain.improvement.parameter.ImprovementParameter;
import egovframework.com.domain.improvement.parameter.ImprovementSearchParameter;

public interface ImprovementDAO {

	List<Improvement> getImprovementList(ImprovementSearchParameter parameter);

	int insertImprovement(ImprovementParameter parameter);

	Improvement getImprovement(Long improveSeq, Long companyId);

	int modifyImprovement(ImprovementParameter parameter);

	int deleteImprovement(Long companyId, Long improveId);

	List<Map<String, String>> getReqUserNameList(Long companyId);

}
