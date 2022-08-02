package egovframework.com.domain.subscriber.service;

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
import egovframework.com.domain.company.parameter.CommonSearchParameter;
import egovframework.com.domain.company.parameter.UserParameter;
import egovframework.com.domain.subscriber.dao.SubscriberDAO;
import egovframework.com.domain.subscriber.domain.Subscriber;
import egovframework.com.domain.subscriber.parameter.SubscriberParameter;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;

@Service
@Transactional(readOnly = true)
public class SubscriberServiceImpl implements SubscriberService{

	private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberServiceImpl.class);
	
	@Autowired
	private CompanyDAO companyDAO;
	
	@Autowired
	private SubscriberDAO repository;
	
	@Autowired
	private CommCodeDAO commCodeDAO;
	
	@Autowired
	PasswordEncoder passwordEncoder; 

	@Override
	public List<Subscriber> getSubscriberCompanyList(CommonSearchParameter parameter) {
		return repository.getSubscriberCompanyList(parameter);
	}

	@Override
	public void insertSubscriberCompany(SubscriberParameter parameter) {
		
		/************ 1. 회사 정보 등록 ***************/
		repository.insertCompany(parameter);
		
		/************ 2. 사용자(담당자) 정보 등록 **************/
		
		UserParameter userParameter = new UserParameter();
		
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
		
		// 임의로 ID의 길이를 정함
		count = (int) (Math.random() * (8-4+1)) + 4;
		cnt = (int) (Math.random() * (6-4+1)) + 4;
		id = RandomStringUtils.random(count, true, false).toLowerCase() + RandomStringUtils.random(cnt, false, true) ;
    	LOGGER.debug("=============== id : " + id + "===============");

    	// 권한CD 부여
    	for(CommCode code : commList) {
    		
    		if(code.getCodeNameKor().equals("대표이사")) {
	    		
    			userParameter.setRoleCd(code.getCodeId());
	    		
	    	} 
    		
    	}
    	// 사용자 각각의 랜덤 ID 및 초기 비밀번호 부여
    	userParameter.setCompanyId(parameter.getCompanyId());
    	userParameter.setUserName(parameter.getManagerName());
    	userParameter.setEmail(parameter.getManagerEmail());
    	userParameter.setMobile(parameter.getManagerMobile());
    	userParameter.setPosition(parameter.getManagerPosition());
    	userParameter.setInsertId(parameter.getInsertId());
    	userParameter.setLoginId(id);
    	userParameter.setPwd(pwEnc);
    	userParameter.setWorkplaceId(null);
    	companyDAO.insertUser(userParameter);
		
	}

	@Override
	public Subscriber getSubscriberCompany(Long companyId) {
		return repository.getSubscriberCompany(companyId);
	}
}
