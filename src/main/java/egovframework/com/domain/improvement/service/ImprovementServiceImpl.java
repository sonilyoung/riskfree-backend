package egovframework.com.domain.improvement.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.com.domain.improvement.dao.ImprovementDAO;
import egovframework.com.domain.improvement.domain.Improvement;
import egovframework.com.domain.improvement.parameter.ImprovementParameter;
import egovframework.com.domain.improvement.parameter.ImprovementSearchParameter;

@Service
public class ImprovementServiceImpl implements ImprovementService {

	@Autowired
	private ImprovementDAO repository;

	@Override
	public List<Improvement> getImprovementList(ImprovementSearchParameter parameter) {

		return repository.getImprovementList(parameter);
	}

	@Override
	public int insertImprovement(ImprovementParameter parameter) {
		return repository.insertImprovement(parameter);
	}

	@Override
	public Improvement getImprovement(Long companyId, Long improveSeq) {
		return repository.getImprovement(companyId, improveSeq);
	}

	@Override
	public int modifyImprovement(ImprovementParameter parameter) {
		return repository.modifyImprovement(parameter);
	}

	@Override
	public int deleteImprovement(Long companyId, Long improveId) {
		return repository.deleteImprovement(companyId, improveId);
	}

	@Override
	public List<Map<String, String>> getReqUserNameList(Long companyId) {
		return repository.getReqUserNameList(companyId);
	}

	@Override
	public Improvement getGenerateKey() {
		// TODO Auto-generated method stub
		String dateTime = new SimpleDateFormat("yyyyMMdd").format(new Date());
		Improvement im = repository.getGenerateKey();
		Improvement result = new Improvement();
		if(im!=null) {
			result.setImproveKey(dateTime + "-" + Long.toString(im.getImproveId()));
		}else {
			result.setImproveKey(dateTime + "-1");
		}
		return result;
	}

}
