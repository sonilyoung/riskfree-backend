package egovframework.com.domain.construction.service;

import java.util.List;

import egovframework.com.domain.accident.domain.Accident;
import egovframework.com.domain.construction.parameter.ConstructionSearchParameter;

public interface ConstructionService {

	List<Accident> getConstructionHistoryList(ConstructionSearchParameter parameter);

}
