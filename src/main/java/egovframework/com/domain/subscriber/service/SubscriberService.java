package egovframework.com.domain.subscriber.service;

import java.util.List;

import egovframework.com.domain.company.parameter.CommonSearchParameter;
import egovframework.com.domain.subscriber.domain.Subscriber;

public interface SubscriberService {

	List<Subscriber> getSubscriberList(CommonSearchParameter parameter);

}
