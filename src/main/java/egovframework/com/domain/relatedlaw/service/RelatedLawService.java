package egovframework.com.domain.relatedlaw.service;

import java.util.LinkedHashMap;
import java.util.List;

import egovframework.com.domain.relatedlaw.domain.DutyBotton;
import egovframework.com.domain.relatedlaw.domain.RelatedLaw;


public interface RelatedLawService {

	int insertRelatedRaw(List<LinkedHashMap<String, String>> vo, DutyBotton pswVo);
	
	List<RelatedLaw> getRelatedRaw(RelatedLaw vo);
	
	int updateRelatedRaw(RelatedLaw vo);
	
	int insertDutyButton(DutyBotton parameter);
	
	List<DutyBotton> getRelatedRawButton(DutyBotton vo);

	void updateButtonAttachId(DutyBotton parameter);
	
	List<RelatedLaw> getRelatedRawCopyData(RelatedLaw vo);
	
	int insertRelatedRawCopy(RelatedLaw vo);
	
	public int deleteButton(DutyBotton vo);
	
}


