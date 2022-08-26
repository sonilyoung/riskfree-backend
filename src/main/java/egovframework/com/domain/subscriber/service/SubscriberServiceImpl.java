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
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import egovframework.com.global.util.AES256Util;

@Service
public class SubscriberServiceImpl implements SubscriberService{

	private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberServiceImpl.class);
	
	@Autowired
	private SubscriberDAO repository;
	
	@Autowired
	PasswordEncoder passwordEncoder; 

	@Override
	public List<Subscriber> getSubscriberCompanyList(CommonSearchParameter parameter) {
		
		List<Subscriber> dbList = repository.getSubscriberCompanyList(parameter);
		
		List<Subscriber> list = new ArrayList<>();
		
		Subscriber sb = null;
		
		// 회사 아이디 식별을 위한 변수 선언
		Long companyId = null;
		
		// 회사 아이디 별로 하나씩 리스트로 리턴하기 위한 로직
		for(int i = 0; i < dbList.size(); i++) {
			
			// 첫 리스트 삽입
			if(companyId == null) {
				
				companyId = dbList.get(i).getCompanyId();
				sb = new Subscriber();
				sb.setCompanyId(dbList.get(i).getCompanyId());
				sb.setCompanyName(dbList.get(i).getCompanyName());
				sb.setWorkplaceId(dbList.get(i).getWorkplaceId());
				sb.setWorkplaceName(dbList.get(i).getWorkplaceName());
				sb.setWorkplaceName(dbList.get(i).getWorkplaceName());
				sb.setCompanyId(dbList.get(i).getCompanyId());
				sb.setRegistNo(dbList.get(i).getRegistNo());
				sb.setScale(dbList.get(i).getScale());
				sb.setSector(dbList.get(i).getSector());
				sb.setLoginId(dbList.get(i).getLoginId());
				sb.setManagerRole(dbList.get(i).getManagerRole());
				sb.setManagerName(dbList.get(i).getManagerName());
				sb.setManagerMobile(dbList.get(i).getManagerMobile());
				sb.setContractAmount(dbList.get(i).getContractAmount());
				sb.setContractDate(dbList.get(i).getContractDate());
				sb.setStatus(dbList.get(i).getStatus());
				sb.setContractFileId(dbList.get(i).getContractFileId());
				
				list.add(sb);
			}
			
			// 회사 아이디가 다르면 다시 리스트에 추가
			if(companyId != dbList.get(i).getCompanyId()) {
				companyId = dbList.get(i).getCompanyId();
				
				sb = new Subscriber();
				sb.setCompanyId(dbList.get(i).getCompanyId());
				sb.setCompanyName(dbList.get(i).getCompanyName());
				sb.setWorkplaceId(dbList.get(i).getWorkplaceId());
				sb.setWorkplaceName(dbList.get(i).getWorkplaceName());
				sb.setWorkplaceName(dbList.get(i).getWorkplaceName());
				sb.setCompanyId(dbList.get(i).getCompanyId());
				sb.setRegistNo(dbList.get(i).getRegistNo());
				sb.setScale(dbList.get(i).getScale());
				sb.setSector(dbList.get(i).getSector());
				sb.setLoginId(dbList.get(i).getLoginId());
				sb.setManagerRole(dbList.get(i).getManagerRole());
				sb.setManagerName(dbList.get(i).getManagerName());
				sb.setManagerMobile(dbList.get(i).getManagerMobile());
				sb.setContractAmount(dbList.get(i).getContractAmount());
				sb.setContractDate(dbList.get(i).getContractDate());
				sb.setStatus(dbList.get(i).getStatus());
				sb.setContractFileId(dbList.get(i).getContractFileId());
				
				list.add(sb);
			} else {
				// 회사 아이디가 같으면 아무것도 안함
			}
			
		}
		
		return list;
	}

	@Override
	public List<Subscriber> getSubscriberWorkplaceList(Long companyId) {
		return repository.getSubscriberWorkplaceList(companyId);
	}
	
	
	@Override
	public void insertSubscriberCompany(SubscriberParameter parameter) {
		
		/************ 1. 회사 정보 등록 ***************/
		repository.insertCompany(parameter);
		
		/************ 2. 사업장 정보 등록 **************/
		repository.insertWorkplace(parameter);
		
		/************ 3. 사용자(담당자) 정보 등록 **************/
		
		// 초기 비밀번호 셋팅
		String pwEnc = null;
        
		try {
			AES256Util aesUtil = new AES256Util();
			 pwEnc = aesUtil.encrypt("12345678");
		} catch (Exception e) {
			throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
		}
		
    	// 초기 비밀번호 부여
    	parameter.setPwd(pwEnc);
    	repository.insertUser(parameter);
		
	}

	@Override
	public Subscriber getSubscriberCompany(CommonSearchParameter parameter) {
		return repository.getSubscriberCompany(parameter);
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
	
}
