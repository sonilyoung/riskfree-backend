package egovframework.com.domain.accident.dao;

import java.util.List;

import egovframework.com.domain.accident.domain.Accident;
import egovframework.com.domain.accident.parameter.AccidentParameter;
import egovframework.com.domain.accident.parameter.AccidentSearchParameter;

public interface AccidentDAO {

	List<Accident> getAccidentList(AccidentSearchParameter parameter);

	Accident getAccident(Long companyId, Long accidentId);

	void insertAccident(AccidentParameter parameter);

	void modifyAccident(AccidentParameter parameter);

	void deleteAccident(Long companyId, Long accidentId, Long insertId);

}
