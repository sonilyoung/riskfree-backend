package egovframework.com.domain.accident.dao;

import java.util.List;
import java.util.Map;

import egovframework.com.domain.accident.domain.Accident;
import egovframework.com.domain.accident.parameter.AccidentParameter;
import egovframework.com.domain.accident.parameter.AccidentSearchParameter;

public interface AccidentDAO {

	List<Accident> getAccidentList(AccidentSearchParameter parameter);

	Accident getAccident(Long companyId, Long accidentId);

	int insertAccident(AccidentParameter parameter);

	int modifyAccident(AccidentParameter parameter);

	int deleteAccident(Long companyId, Long accidentId, Long insertId);

	List<Map<String, String>> selectOccurPlace(Long companyId);

}
