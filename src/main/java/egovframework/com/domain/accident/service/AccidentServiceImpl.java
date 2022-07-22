package egovframework.com.domain.accident.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.com.domain.accident.dao.AccidentDAO;
import egovframework.com.domain.accident.domain.Accident;
import egovframework.com.domain.accident.parameter.AccidentSearchParameter;

@Service
@Transactional(readOnly = true)
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
}
