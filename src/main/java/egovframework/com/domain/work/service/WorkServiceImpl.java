package egovframework.com.domain.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.com.domain.work.dao.WorkDAO;
import egovframework.com.domain.work.domain.Work;

@Service
public class WorkServiceImpl implements WorkService {

	@Autowired
	private WorkDAO repository;
	

	@Override
	public List<Work> getSafeWork(Work vo) {
		// TODO Auto-generated method stub
		return repository.getSafeWork(vo);
	}
	
	@Override
	public Work getSafeWorkFileTopInfo(Work vo) {
		// TODO Auto-generated method stub
		return repository.getSafeWorkFileTopInfo(vo);
	}	
	
	@Override
	public List<Work> getSafeWorkFile(Work vo) {
		// TODO Auto-generated method stub
		return repository.getSafeWorkFile(vo);
	}

	@Override
	public int deleteSafeWork(Work vo) {
		// TODO Auto-generated method stub
		return repository.deleteSafeWork(vo);
	}		
}
