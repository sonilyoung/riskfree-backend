package egovframework.com.domain.work.dao;

import java.util.List;

import egovframework.com.domain.work.domain.Work;


public interface WorkDAO {
	
	List<Work> getSafeWork(Work vo);
	
	Work getSafeWorkFileTopInfo(Work vo);
	
	List<Work> getSafeWorkFile(Work vo);
}


