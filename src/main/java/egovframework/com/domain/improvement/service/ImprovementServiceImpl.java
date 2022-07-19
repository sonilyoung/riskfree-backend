package egovframework.com.domain.improvement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.com.domain.improvement.dao.ImprovementDAO;
import egovframework.com.domain.improvement.domain.Improvement;
import egovframework.com.domain.improvement.parameter.ImprovementParameter;
import egovframework.com.domain.improvement.parameter.ImprovementSearchParameter;

@Service
@Transactional(readOnly = true)
public class ImprovementServiceImpl implements ImprovementService {

	@Autowired
	private ImprovementDAO repository;

	@Override
	public List<Improvement> getImprovementList(ImprovementSearchParameter parameter) {

		return repository.getImprovementList(parameter);
	}

	@Override
	public void insertImprovement(ImprovementParameter parameter) {
		repository.insertImprovement(parameter);
	}

	@Override
	public Improvement getImprovement(Long companyId, Long improveSeq) {
		return repository.getImprovement(companyId, improveSeq);
	}

	@Override
	public void modifyImprovement(ImprovementParameter parameter) {
		repository.modifyImprovement(parameter);
	}

	@Override
	public void deleteImprovement(Long companyId, Long improveId) {
		repository.deleteImprovement(companyId, improveId);
	}

}
