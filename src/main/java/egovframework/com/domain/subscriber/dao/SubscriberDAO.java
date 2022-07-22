package egovframework.com.domain.subscriber.dao;

import java.util.List;

import egovframework.com.domain.company.parameter.CommonSearchParameter;
import egovframework.com.domain.subscriber.domain.Subscriber;

public interface SubscriberDAO {

	List<Subscriber> getSubscriberList(CommonSearchParameter parameter);

}
