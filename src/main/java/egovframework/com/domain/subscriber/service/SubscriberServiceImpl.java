package egovframework.com.domain.subscriber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.com.domain.company.parameter.CommonSearchParameter;
import egovframework.com.domain.subscriber.dao.SubscriberDAO;
import egovframework.com.domain.subscriber.domain.Subscriber;

@Service
@Transactional(readOnly = true)
public class SubscriberServiceImpl implements SubscriberService{

	@Autowired
	private SubscriberDAO repository;

	@Override
	public List<Subscriber> getSubscriberList(CommonSearchParameter parameter) {
		return repository.getSubscriberList(parameter);
	}
}
