package egovframework.com.domain.accident.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.com.domain.accident.dao.AccidentDAO;
import egovframework.com.domain.accident.domain.Accident;
import egovframework.com.domain.accident.parameter.AccidentParameter;
import egovframework.com.domain.accident.parameter.AccidentSearchParameter;

@Service
public class AccidentServiceImpl implements AccidentService {

	@Autowired
	private AccidentDAO repository;

	@Override
	public List<Accident> getAccidentList(AccidentSearchParameter parameter) {
		return repository.getAccidentList(parameter);
	}

	@Override
	public Accident getAccident(Long companyId, Long accidentId) {
		return repository.getAccident(companyId, accidentId);
	}

	@Override
	public int insertAccident(AccidentParameter parameter) {
		return repository.insertAccident(parameter);
	}

	@Override
	public int modifyAccident(AccidentParameter parameter) {
		return repository.modifyAccident(parameter);
	}

	@Override
	public int deleteAccident(Long companyId, Long accidentId, Long insertId) {
		return repository.deleteAccident(companyId,accidentId,insertId);
	}

	@Override
	public List<Map<String, String>>  selectOccurPlace(AccidentSearchParameter parame) {
		return repository.selectOccurPlace(parame);
	}
}
