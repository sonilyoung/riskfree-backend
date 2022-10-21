package egovframework.com.domain.subscriber.dao;

import java.util.List;

import egovframework.com.domain.company.parameter.CommonSearchParameter;
import egovframework.com.domain.main.domain.Workplace;
import egovframework.com.domain.portal.logn.domain.LoginRequest;
import egovframework.com.domain.subscriber.domain.Subscriber;
import egovframework.com.domain.subscriber.parameter.SubscriberParameter;
import egovframework.com.domain.subscriber.parameter.SubscriberSearchParameter;

public interface SubscriberDAO {

	List<Subscriber> getSubscriberCompanyList(SubscriberSearchParameter parameter);

	void insertCompany(SubscriberParameter parameter);

	Subscriber getSubscriberCompany(Long workplaceId, Long userId);

	void insertSubWorkplace(SubscriberParameter parameter);
	
	void insertWorkplace(SubscriberParameter parameter);

	void insertUser(SubscriberParameter parameter);

	List<Subscriber> getSubscriberWorkplaceList(Long companyId);

	void updateCompany(SubscriberParameter parameter);

	void updateWorkplace(SubscriberParameter parameter);

	void updateUser(SubscriberParameter parameter);

	List<Subscriber> getSearchCompany(String companyName, String managerName);

	void updateCeoId(SubscriberParameter parameter);

	List<Subscriber> getSearchWorkplace(Long companyId, String workplaceName);
	
	Subscriber getCompanyInfo(SubscriberParameter parameter);
	
	int getLoginIdCnt(SubscriberParameter parameter);

	public int deleteUser(SubscriberParameter parameter);
}
