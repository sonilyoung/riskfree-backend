package egovframework.com.domain.construction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.com.domain.accident.domain.Accident;
import egovframework.com.domain.construction.dao.ConstructionDAO;
import egovframework.com.domain.construction.parameter.ConstructionSearchParameter;

@Service
public class ConstructionServiceImpl implements ConstructionService{

	@Autowired
	private ConstructionDAO repository;

	@Override
	public List<Accident> getConstructionHistoryList(ConstructionSearchParameter parameter) {
		return repository.getConstructionHistoryList(parameter);
	}
}
