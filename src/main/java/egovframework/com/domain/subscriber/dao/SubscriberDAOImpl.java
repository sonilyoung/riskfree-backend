package egovframework.com.domain.subscriber.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.com.domain.company.parameter.CommonSearchParameter;
import egovframework.com.domain.subscriber.domain.Subscriber;
import egovframework.com.domain.subscriber.parameter.SubscriberParameter;
import egovframework.com.domain.subscriber.parameter.SubscriberSearchParameter;

@Repository
public class SubscriberDAOImpl implements SubscriberDAO{

	@Autowired
    private SqlSession sqlSession;

    private static final String Namespace = "egovframework.com.domain.subscriber.dao.SubscriberDAO";

	@Override
	public List<Subscriber> getSubscriberCompanyList(SubscriberSearchParameter parameter) {
		return sqlSession.selectList(Namespace + ".getSubscriberCompanyList", parameter);
	}

	@Override
	public void insertCompany(SubscriberParameter parameter) {
		sqlSession.insert(Namespace + ".insertCompany", parameter);
	}
	
	@Override
	public void insertWorkplace(SubscriberParameter parameter) {
		sqlSession.insert(Namespace + ".insertWorkplace", parameter);
	}
	
	@Override
	public void insertUser(SubscriberParameter parameter) {
		sqlSession.insert(Namespace + ".insertUser", parameter);
		
	}

	@Override
	public Subscriber getSubscriberCompany(Long workplaceId) {
		return sqlSession.selectOne(Namespace + ".getSubscriberCompany", workplaceId);
	}

	@Override
	public List<Subscriber> getSubscriberWorkplaceList(Long companyId) {
		return sqlSession.selectList(Namespace + ".getSubscriberWorkplaceList", companyId);
	}

	@Override
	public void updateCompany(SubscriberParameter parameter) {
		sqlSession.update(Namespace + ".updateCompany", parameter);
		
	}

	@Override
	public void updateWorkplace(SubscriberParameter parameter) {
		sqlSession.update(Namespace + ".updateWorkplace", parameter);		
	}

	@Override
	public void updateUser(SubscriberParameter parameter) {
		sqlSession.update(Namespace + ".updateUser", parameter);		
	}

	@Override
	public List<Subscriber> getSearchCompany(String companyName, String managerName) {
		Map<String,String> param = new HashMap<>();
		param.put("companyName", companyName);
		param.put("managerName", managerName);
		return sqlSession.selectList(Namespace + ".getSearchCompany", param);
		
	}

	@Override
	public void updateCeoId(SubscriberParameter parameter) {
		sqlSession.update(Namespace + ".updateCeoId", parameter);
	}

	
}
