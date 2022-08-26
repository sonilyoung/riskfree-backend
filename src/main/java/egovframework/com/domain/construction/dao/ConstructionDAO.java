package egovframework.com.domain.construction.dao;

import java.util.List;

import egovframework.com.domain.accident.domain.Accident;
import egovframework.com.domain.construction.parameter.ConstructionSearchParameter;

public interface ConstructionDAO {

	List<Accident> getConstructionHistoryList(ConstructionSearchParameter parameter);

}
