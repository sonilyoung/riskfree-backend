package egovframework.com.domain.company.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.com.domain.company.domain.Baseline;
import egovframework.com.domain.company.domain.Company;
import egovframework.com.domain.company.domain.User;
import egovframework.com.domain.company.domain.Workplace;
import egovframework.com.domain.company.parameter.BaselineParameter;
import egovframework.com.domain.company.parameter.CommonSearchParameter;
import egovframework.com.domain.company.parameter.CompanyParameter;
import egovframework.com.domain.company.parameter.UserParameter;
import egovframework.com.domain.company.parameter.WorkplaceParameter;

@Repository
public class CompanyDAOImpl implements CompanyDAO {

	@Autowired
	private SqlSession sqlSession;

    private static final String Namespace = "egovframework.com.domain.company.dao.CompanyDAO";

	@Override
	public Company getCompany(Long companyId) {
		return sqlSession.selectOne(Namespace + ".getCompany", companyId);
	}

	@Override
	public void modifyCompany(CompanyParameter parameter) {
		sqlSession.update(Namespace + ".modifyCompany", parameter);
	}

	@Override
	public List<Workplace> getWorkplaceList(CommonSearchParameter parameter) {
		return sqlSession.selectList(Namespace + ".getWorkplaceList", parameter);
	}

	@Override
	public void insertWorkplace(WorkplaceParameter parameter) {
		sqlSession.insert(Namespace + ".insertWorkplace", parameter);
		
	}
	

	@Override
	public void insertUser(UserParameter userParameter) {
		sqlSession.insert(Namespace + ".insertUser", userParameter);
		
	}

	@Override
	public Workplace getWorkplace(Long companyId, Long workplaceId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("companyId", companyId);
		map.put("workplaceId", workplaceId);
		return sqlSession.selectOne(Namespace + ".getWorkplace", map);
	}

	@Override
	public void modifyWorkplace(WorkplaceParameter parameter) {
		sqlSession.update(Namespace + ".modifyWorkplace", parameter);
	}
	
	@Override
	public void modifyUser(UserParameter userParameter) {
		sqlSession.update(Namespace + ".modifyUser", userParameter);
	}

	@Override
	public void deleteWorkplace(Long companyId, Long workplaceId, Long updateId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("companyId", companyId);
		map.put("workplaceId", workplaceId);
		map.put("updateId", updateId);
		sqlSession.update(Namespace + ".deleteWorkplace", map);
	}

	@Override
	public void deleteWorkplaceByUser(Long companyId, Long workplaceId, Long updateId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("companyId", companyId);
		map.put("workplaceId", workplaceId);
		map.put("updateId", updateId);
		sqlSession.delete(Namespace + ".deleteWorkplaceByUser", map);
	}

	@Override
	public List<Baseline> getBaselineList(CommonSearchParameter parameter) {
		return sqlSession.selectList(Namespace + ".getBaselineList", parameter);
	}

	@Override
	public Baseline getBaseline(Long companyId, Long baselineId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("companyId", companyId);
		map.put("baselineId", baselineId);
		return sqlSession.selectOne(Namespace + ".getBaseline", map);
	}

	@Override
	public void insertBaseline(BaselineParameter parameter) {
		sqlSession.insert(Namespace + ".insertBaseline", parameter);
	}

	@Override
	public void modifyBaseline(BaselineParameter parameter) {
		sqlSession.update(Namespace + ".modifyBaseline", parameter);
	}

	@Override
	public void deleteBaseline(Long companyId, Long baselineId, Long updateId ) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("companyId", companyId);
		map.put("baselineId", baselineId);
		map.put("updateId", updateId);
		sqlSession.update(Namespace + ".deleteBaseline", map);
	}

	@Override
	public void closeBaseline(Long companyId, Long baselineId, Long updateId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("companyId", companyId);
		map.put("baselineId", baselineId);
		map.put("updateId", updateId);
		sqlSession.update(Namespace + ".closeBaseline", map);
	}

	
}
