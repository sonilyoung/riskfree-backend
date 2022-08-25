package egovframework.com.domain.subscriber.dao;

import java.util.List;

import egovframework.com.domain.company.parameter.CommonSearchParameter;
import egovframework.com.domain.subscriber.domain.Subscriber;
import egovframework.com.domain.subscriber.parameter.SubscriberParameter;

public interface SubscriberDAO {

	List<Subscriber> getSubscriberCompanyList(CommonSearchParameter parameter);

	void insertCompany(SubscriberParameter parameter);

	Subscriber getSubscriberCompany(CommonSearchParameter parameter);

	void insertWorkplace(SubscriberParameter parameter);

	void insertUser(SubscriberParameter parameter);

	List<Subscriber> getSubscriberWorkplaceList(Long companyId);

	void updateCompany(SubscriberParameter parameter);

	void updateWorkplace(SubscriberParameter parameter);

	void updateUser(SubscriberParameter parameter);

}
