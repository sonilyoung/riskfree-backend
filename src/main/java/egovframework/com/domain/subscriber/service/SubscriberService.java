package egovframework.com.domain.subscriber.service;

import java.util.List;

import egovframework.com.domain.company.parameter.CommonSearchParameter;
import egovframework.com.domain.subscriber.domain.Subscriber;
import egovframework.com.domain.subscriber.parameter.SubscriberParameter;
import egovframework.com.domain.subscriber.parameter.SubscriberSearchParameter;

public interface SubscriberService {

	List<Subscriber> getSubscriberCompanyList(SubscriberSearchParameter parameter);

	void insertSubscriberCompany(SubscriberParameter parameter);

	Subscriber getSubscriberCompany(Long workplaceId, Long userId);

	List<Subscriber> getSubscriberWorkplaceList(Long companyId);

	void updateSubscriberCompany(SubscriberParameter parameter);

	List<Subscriber> getSearchCompany(String companyName, String managerName);

	List<Subscriber> getSearchWorkplace(Long companyId, String workplaceName);
	
	int getLoginIdCnt(SubscriberParameter parameter);

	public int deleteUser(SubscriberParameter parameter);
}
