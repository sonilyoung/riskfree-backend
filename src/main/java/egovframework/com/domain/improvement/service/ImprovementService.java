package egovframework.com.domain.improvement.service;

import java.util.List;
import java.util.Map;

import egovframework.com.domain.improvement.domain.Improvement;
import egovframework.com.domain.improvement.parameter.ImprovementParameter;
import egovframework.com.domain.improvement.parameter.ImprovementSearchParameter;

public interface ImprovementService {

	// 개선사항 목록 및 총 갯수 리턴
	List<Improvement> getImprovementList(ImprovementSearchParameter parameter);
	// 개선사항 등록
	int insertImprovement(ImprovementParameter parameter);
	// 개선사항 상세조회
	Improvement getImprovement(Long improveNo, Long companyId);
	// 개선사항 수정
	int modifyImprovement(ImprovementParameter parameter);
	// 개선사항 삭제
	int deleteImprovement(Long companyId, Long improveId);
	// 개선 요청자 리스트
	List<Map<String, String>> getReqUserNameList(Long companyId);

	Improvement getGenerateKey();
}
