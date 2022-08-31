package egovframework.com.domain.work.service;

import java.util.List;

import egovframework.com.domain.work.domain.Work;


public interface WorkService {

	List<Work> getSafeWork(Work vo);

	Work getSafeWorkFileTopInfo(Work vo);
	
	List<Work> getSafeWorkFile(Work vo);
}


