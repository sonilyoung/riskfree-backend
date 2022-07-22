package egovframework.com.domain.accident.service;

import java.util.List;

import egovframework.com.domain.accident.domain.Accident;
import egovframework.com.domain.accident.parameter.AccidentSearchParameter;

public interface AccidentService {

	List<Accident> getAccidentList(AccidentSearchParameter parameter);

	Accident getAccident(Long companyId, Long accidentId);

}
