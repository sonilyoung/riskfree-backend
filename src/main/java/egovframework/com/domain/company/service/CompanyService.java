package egovframework.com.domain.company.service;

import java.util.List;

import egovframework.com.domain.company.domain.Baseline;
import egovframework.com.domain.company.domain.Company;
import egovframework.com.domain.company.domain.Workplace;
import egovframework.com.domain.company.parameter.CompanyParameter;
import egovframework.com.domain.company.parameter.WorkplaceParameter;
import egovframework.com.domain.company.parameter.BaselineParameter;
import egovframework.com.domain.company.parameter.CommonSearchParameter;

public interface CompanyService {

	Company getCompany(Long companyId);

	void modifyCompany(CompanyParameter parameter);

	List<Workplace> getWorkplaceList(CommonSearchParameter parameter);

	void insertWorkplace(WorkplaceParameter parameter);

	Workplace getWorkplace(Long companyId, Long workplaceId);

	void modifyWorkplace(WorkplaceParameter parameter);

	void deleteWorkplace(Long companyId, Long workplaceId, Long updateId);

	List<Baseline> getBaselineList(CommonSearchParameter parameter);

	Baseline getBaseline(Long companyId, Long baselineId);

	void insertBaseline(BaselineParameter parameter);

	void modifyBaseline(BaselineParameter parameter);

	void deleteBaseline(Long companyId, Long baselineId, Long updateId);

	void closeBaseline(Long companyId, Long baselineId, Long updateId);

}
