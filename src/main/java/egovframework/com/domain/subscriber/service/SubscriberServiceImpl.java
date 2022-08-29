package egovframework.com.domain.subscriber.service;

import java.util.ArrayList;
import java.util.Iterator;
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
import egovframework.com.domain.subscriber.parameter.SubscriberSearchParameter;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import egovframework.com.global.util.AES256Util;

@Service
public class SubscriberServiceImpl implements SubscriberService{

	private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberServiceImpl.class);
	
	@Autowired
	private SubscriberDAO repository;
	
	@Override
	public List<Subscriber> getSubscriberCompanyList(SubscriberSearchParameter parameter) {
		return repository.getSubscriberCompanyList(parameter);
	}

	@Override
	public List<Subscriber> getSubscriberWorkplaceList(Long companyId) {
		return repository.getSubscriberWorkplaceList(companyId);
	}
	
	
	@Override
	public void insertSubscriberCompany(SubscriberParameter parameter) {

		try {
			/************ 회사 정보 등록 ***************/
			
			// 관리자 초기 비밀번호 셋팅
			String pwEnc = null;
	        
			try {
				AES256Util aesUtil = new AES256Util();
				 pwEnc = aesUtil.encrypt("0000");
			} catch (Exception e) {
				throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
			}

	    	// 초기 비밀번호 부여
	    	parameter.setPwd(pwEnc);
			
			// 1. 회사 신규등록
			if(parameter.getCompanyId() == null) {
				
				// 회사 정보가 이미 등록되었는지 확인
				List<Subscriber> list = repository.getSearchCompany(parameter.getCompanyName(),parameter.getManagerName());
				
				if(list.size() > 0) {
					// 등록된 회사가 있으면 exception	발생
					throw new BaseException(BaseResponseCode.DATA_IS_DUPLICATE);
				
				} else {
					// 등록된 회사가 없으면 회사 , 사업장, 관리자 등록
					repository.insertCompany(parameter);
					repository.insertWorkplace(parameter);
					repository.insertUser(parameter);
					// ceoId 등록
					repository.updateCeoId(parameter);
				}
			} else {
				// 2. 회사의 하위 사업장 등록
				
				// 사업장 신규등록
				if(parameter.getWorkplaceId() == null) {
					
					repository.insertWorkplace(parameter);
					// 관리자 등록
					repository.insertUser(parameter);
				} else {
					
					// 기존에 있는 사업장에 관리자만 등록
					repository.insertUser(parameter);
				}
				
			}
    	
		} catch (Exception e) {
			throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
		}
    	
		
	}

	@Override
	public Subscriber getSubscriberCompany(Long workplaceId) {
		return repository.getSubscriberCompany(workplaceId);
	}

	@Override
	public void updateSubscriberCompany(SubscriberParameter parameter) {
		
		/************ 1. 회사 정보 수정 ***************/
		repository.updateCompany(parameter);
		
		/************ 2. 사업장 정보 수정 **************/
		repository.updateWorkplace(parameter);
		
		/************ 3. 사용자(담당자) 정보 수정 **************/
		repository.updateUser(parameter);
	}

	@Override
	public List<Subscriber> getSearchCompany(String companyName, String managerName) {
		return repository.getSearchCompany(companyName, managerName);
	}

	@Override
	public List<Subscriber> getSearchWorkplace(Long companyId, String workplaceName) {
		return repository.getSearchWorkplace(companyId, workplaceName);
	}
	
}
