package egovframework.com.domain.relatedlaw.dao;

import java.util.LinkedHashMap;
import java.util.List;

import egovframework.com.domain.relatedlaw.domain.DutyBotton;
import egovframework.com.domain.relatedlaw.domain.RelatedLaw;
import egovframework.com.domain.relatedlaw.domain.UpdateList;


public interface RelatedLawDAO {

	int insertRelatedRaw(LinkedHashMap vo);
	
	List<RelatedLaw> getRelatedRaw(RelatedLaw vo);
		
	void updateRelatedRaw(UpdateList vo);
	
	int insertDutyButton(DutyBotton parameter);
	
	List<DutyBotton> getRelatedRawButton(DutyBotton vo);

}


