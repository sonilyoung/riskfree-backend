package egovframework.com.domain.company.service;

import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.com.domain.commcode.dao.CommCodeDAO;
import egovframework.com.domain.commcode.domain.CommCode;
import egovframework.com.domain.commcode.parameter.CommCodeSearchParameter;
import egovframework.com.domain.company.dao.CompanyDAO;
import egovframework.com.domain.company.domain.Baseline;
import egovframework.com.domain.company.domain.Company;
import egovframework.com.domain.company.domain.Workplace;
import egovframework.com.domain.company.parameter.BaselineParameter;
import egovframework.com.domain.company.parameter.CommonSearchParameter;
import egovframework.com.domain.company.parameter.CompanyParameter;
import egovframework.com.domain.company.parameter.WorkplaceParameter;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;

@Service
public class CompanyServiceImpe implements CompanyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpe.class);
	
	@Autowired
	private CompanyDAO repository;
	
	@Autowired
	private CommCodeDAO commCodeDAO;
	
	@Autowired
	PasswordEncoder passwordEncoder; 

	@Override
	public Company getCompany(Long companyId) {
		return repository.getCompany(companyId);
	}

	@Override
	public void modifyCompany(CompanyParameter parameter) {
		repository.modifyCompany(parameter);
	}

	@Override
	public List<Workplace> getWorkplaceList(CommonSearchParameter parameter) {
		return repository.getWorkplaceList(parameter);
	}

	@Override
	public void insertWorkplace(WorkplaceParameter parameter) {
		
		// 사업자와 사용자를 동시에 등록하는 로직임
		
		/************ 1. 사업장 정보 등록 ***************/
		repository.insertWorkplace(parameter);
		
		/************ 2. 사용자 정보 등록 **************/
		// 사용자 ID 랜덤 생성 초기 비밀번호 부여 및 권한 부여
		int count = 0; 
		int cnt = 0;
		String id = null;
		
		// 권한CD 조회
		CommCodeSearchParameter param = new CommCodeSearchParameter();
		param.setGroupId("003");
		
		List<CommCode> commList = commCodeDAO.getCommCodeList(param);
		
		// 초기 비밀번호 셋팅
		String pwEnc = null;
		try {
			 pwEnc = passwordEncoder.encode("1234");
		} catch (Exception e) {
			throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
		}
		
		for(int i = 0; i < parameter.getUser().size(); i++) {
			// 임의로 ID의 길이를 정함
			count = (int) (Math.random() * (8-4+1)) + 4;
			cnt = (int) (Math.random() * (6-4+1)) + 4;
			id = RandomStringUtils.random(count, true, false).toLowerCase() + RandomStringUtils.random(cnt, false, true) ;
	    	LOGGER.debug("=============== id : " + id + "===============");

	    	// 권한CD 부여
	    	for(CommCode code : commList) {
	    		
	    		if(i == 0 && code.getCodeNameKor().equals("안전책임자")) {
		    		
		    		parameter.getUser().get(i).setRoleCd(code.getCodeId());
		    		
		    	} else if(i == 1 && code.getCodeNameKor().equals("안전실무자")) {
		    		
		    		parameter.getUser().get(i).setRoleCd(code.getCodeId());
		    		
		    	}
	    		
	    	}
	    	// 사용자 각각의 랜덤 ID 및 초기 비밀번호 부여
	    	parameter.getUser().get(i).setLoginId(id);
	    	parameter.getUser().get(i).setPwd(pwEnc);
	    	parameter.getUser().get(i).setInsertId(parameter.getInsertId());
	    	parameter.getUser().get(i).setWorkplaceId(parameter.getWorkplaceId());
	    	parameter.getUser().get(i).setCompanyId(parameter.getCompanyId());
	    	repository.insertUser(parameter.getUser().get(i));
		}
    	
	}

	@Override
	public Workplace getWorkplace(Long companyId, Long workplaceId) {
		return repository.getWorkplace(companyId, workplaceId);
	}

	@Override
	public void modifyWorkplace(WorkplaceParameter parameter) {
		
		/************ 1. 사업장 정보 수정 ***************/
		repository.modifyWorkplace(parameter);
		
		/************ 2. 사용자 정보 수정 ***************/
		for(int i = 0; i < parameter.getUser().size(); i++) {
			
			parameter.getUser().get(i).setInsertId(parameter.getInsertId());
			parameter.getUser().get(i).setWorkplaceId(parameter.getWorkplaceId());
	    	parameter.getUser().get(i).setCompanyId(parameter.getCompanyId());
			repository.modifyUser(parameter.getUser().get(i));
		}
		
		
	}

	@Override
	public void deleteWorkplace(Long companyId, Long workplaceId) {
		// 사업장 삭제
		repository.deleteWorkplace(companyId, workplaceId);
		// 사업장 안전책임자 및 안전실무자의 사업장ID 삭제 
		repository.deleteWorkplaceByUser(companyId, workplaceId);
		
	}

	@Override
	public List<Baseline> getBaselineList(CommonSearchParameter parameter) {
		return repository.getBaselineList(parameter);
	}

	@Override
	public Baseline getBaseline(Long companyId, Long baselineId) {
		return repository.getBaseline(companyId, baselineId);
	}

	@Override
	public void insertBaseline(BaselineParameter parameter) {
		repository.insertBaseline(parameter);
	}

	@Override
	public void modifyBaseline(BaselineParameter parameter) {
		repository.modifyBaseline(parameter);
	}

	@Override
	public void deleteBaseline(Long companyId, Long baselineId) {
		repository.deleteBaseline(companyId, baselineId);
	}

	@Override
	public void closeBaseline(Long companyId, Long baselineId) {
		repository.closeBaseline(companyId, baselineId);
	}
}
