package egovframework.com.domain.subscriber.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.com.domain.company.parameter.CommonSearchParameter;
import egovframework.com.domain.subscriber.domain.Subscriber;

@Repository
public class SubscriberDAOImpl implements SubscriberDAO{

	@Autowired
    private SqlSession sqlSession;

    private static final String Namespace = "egovframework.com.domain.subscriber.dao.SubscriberDAO";

	@Override
	public List<Subscriber> getSubscriberList(CommonSearchParameter parameter) {
		return sqlSession.selectList(Namespace + ".getSubscriberList", parameter);
	}
}
