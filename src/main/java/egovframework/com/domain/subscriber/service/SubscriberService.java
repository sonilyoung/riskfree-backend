package egovframework.com.domain.subscriber.service;

import java.util.List;

import egovframework.com.domain.company.parameter.CommonSearchParameter;
import egovframework.com.domain.subscriber.domain.Subscriber;
import egovframework.com.domain.subscriber.parameter.SubscriberParameter;
import egovframework.com.domain.subscriber.parameter.SubscriberSearchParameter;

public interface SubscriberService {

	List<Subscriber> getSubscriberCompanyList(SubscriberSearchParameter parameter);

	void insertSubscriberCompany(SubscriberParameter parameter);

	Subscriber getSubscriberCompany(CommonSearchParameter parameter);

	List<Subscriber> getSubscriberWorkplaceList(Long companyId);

	void updateSubscriberCompany(SubscriberParameter parameter);

}
