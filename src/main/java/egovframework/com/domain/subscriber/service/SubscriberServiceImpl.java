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
	@Transactional
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
			// 회사 정보가 이미 등록되었는지 확인
	    	parameter.setCondition("1");
			Subscriber company = repository.getCompanyInfo(parameter);
			
			if(company==null) {
				// 등록된 회사가 없으면 회사 , 사업장, 관리자 등록
				repository.insertCompany(parameter);
				repository.insertWorkplace(parameter);
				repository.insertUser(parameter);
				
				if("001".equals(parameter.getManagerRoleCd())) {
					// ceoId 등록
					repository.updateCeoId(parameter);					
				}					
			}else {
				
		    	parameter.setCondition("2");
				Subscriber wp = repository.getCompanyInfo(parameter);
				if(wp==null) {//사업장이없으면 생성
					parameter.setCompanyId(company.getCompanyId());
					repository.insertWorkplace(parameter);
					repository.insertUser(parameter);	
				}else {
					parameter.setCompanyId(wp.getCompanyId());
					parameter.setWorkplaceId(wp.getWorkplaceId());
					repository.insertUser(parameter);					
				}
				
				if("001".equals(parameter.getManagerRoleCd())) {
					// ceoId 등록
					repository.updateCeoId(parameter);					
				}			
			}
    	
		} catch (Exception e) {
			throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
		}
    	
		
	}

	@Override
	public Subscriber getSubscriberCompany(Long workplaceId, Long userId) {
		return repository.getSubscriberCompany(workplaceId, userId);
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
	
	@Override
	public int getLoginIdCnt(SubscriberParameter vo) {
		return repository.getLoginIdCnt(vo);
	}
	
	@Override
	public int getCompanyCeoInfo(SubscriberParameter vo) {
		return repository.getCompanyCeoInfo(vo);
	}	

	@Override
	public int deleteUser(SubscriberParameter parameter) {
		// TODO Auto-generated method stub
		return repository.deleteUser(parameter);
	}		
	
}
