package egovframework.com.domain.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.com.domain.company.dao.CompanyDAO;
import egovframework.com.domain.company.domain.Baseline;
import egovframework.com.domain.company.domain.Company;
import egovframework.com.domain.company.domain.Workplace;
import egovframework.com.domain.company.parameter.BaselineParameter;
import egovframework.com.domain.company.parameter.CommonSearchParameter;
import egovframework.com.domain.company.parameter.CompanyParameter;
import egovframework.com.domain.company.parameter.WorkplaceParameter;

@Service
@Transactional(readOnly = true)
public class CompanyServiceImpe implements CompanyService {

	@Autowired
	private CompanyDAO repository;

	@Override
	public Company getCompany(Long companyId) {
		return repository.getCompany(companyId);
	}

	@Override
	public void modifyCompany(CompanyParameter parameter) {
		repository.modifyCompany(parameter);
	}

	@Override
	public List<Workplace> getWorkplaceList(CommonSearchParameter parameter) {
		return repository.getWorkplaceList(parameter);
	}

	@Override
	public void insertWorkplace(WorkplaceParameter parameter) {
		repository.insertWorkplace(parameter);
	}

	@Override
	public Workplace getWorkplace(Long companyId, Long workplaceId) {
		return repository.getWorkplace(companyId, workplaceId);
	}

	@Override
	public void modifyWorkplace(WorkplaceParameter parameter) {
		repository.modifyWorkplace(parameter);
		
	}

	@Override
	public void deleteWorkplace(Long companyId, Long workplaceId) {
		// 사업장 삭제
		repository.deleteWorkplace(companyId, workplaceId);
		// 사업장 안전책임자 및 안전실무자의 사업장ID 삭제 
		repository.deleteWorkplaceByUser(companyId, workplaceId);
		
	}

	@Override
	public List<Baseline> getBaselineList(CommonSearchParameter parameter) {
		return repository.getBaselineList(parameter);
	}

	@Override
	public Baseline getBaseline(Long companyId, Long baselineId) {
		return repository.getBaseline(companyId, baselineId);
	}

	@Override
	public void insertBaseline(BaselineParameter parameter) {
		repository.insertBaseline(parameter);
	}

	@Override
	public void modifyBaseline(BaselineParameter parameter) {
		repository.modifyBaseline(parameter);
	}

	@Override
	public void deleteBaseline(Long companyId, Long baselineId) {
		repository.deleteBaseline(companyId, baselineId);
	}

	@Override
	public void closeBaseline(Long companyId, Long baselineId) {
		repository.closeBaseline(companyId, baselineId);
	}
}
