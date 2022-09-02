package egovframework.com.domain.relatedlaw.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.com.domain.main.dao.MainDAO;
import egovframework.com.domain.main.domain.MainExcelData;
import egovframework.com.domain.relatedlaw.dao.RelatedLawDAO;
import egovframework.com.domain.relatedlaw.domain.DutyBotton;
import egovframework.com.domain.relatedlaw.domain.RelatedLaw;

@Service
public class RelatedLawServiceImpl implements RelatedLawService {

	@Autowired
	private RelatedLawDAO repository;
	
	@Autowired
	private MainDAO mainRepository;	

	@Override
	@Transactional
	public int insertRelatedRaw(List<LinkedHashMap<String, String>> vo, DutyBotton login) {
		// TODO Auto-generated method stub
		int result = 0;
		MainExcelData med = new MainExcelData();
		med.setCompanyId(login.getCompanyId());
		int baseCnt = mainRepository.getBaselineConfirm(med);
		
		if(baseCnt > 0) {
			for(int i=0; i < vo.size(); i++) {
				repository.insertRelatedRaw(vo.get(i));
			}
			result = 1;			
		}
		return result;		
	}

	@Override
	public List<RelatedLaw> getRelatedRaw(RelatedLaw vo) {
		// TODO Auto-generated method stub
		return repository.getRelatedRaw(vo);
	}

	@Override
	@Transactional
	public int updateRelatedRaw(RelatedLaw vo) {
		// TODO Auto-generated method stub
		int result = 0;
		if(vo.getUpdateList()!=null) {
			for(int i=0; i < vo.getUpdateList().size(); i++) {
				repository.updateRelatedRaw(vo.getUpdateList().get(i));
			}			
			result = 1;			
		}

		return result;			
	}	
	
	@Override
	public int insertDutyButton(DutyBotton parameter) {
		return repository.insertDutyButton(parameter);
	}	

	
	@Override
	public List<DutyBotton> getRelatedRawButton(DutyBotton vo) {
		// TODO Auto-generated method stub
		return repository.getRelatedRawButton(vo);
	}	
	
	@Override
	public void updateButtonAttachId(DutyBotton vo) {
		// TODO Auto-generated method stub
		repository.updateButtonAttachId(vo);
	}		
	
		
}
