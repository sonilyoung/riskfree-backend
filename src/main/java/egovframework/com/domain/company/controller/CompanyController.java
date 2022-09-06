package egovframework.com.domain.company.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.com.domain.company.domain.Baseline;
import egovframework.com.domain.company.domain.Company;
import egovframework.com.domain.company.domain.Workplace;
import egovframework.com.domain.company.parameter.BaselineParameter;
import egovframework.com.domain.company.parameter.CommonSearchParameter;
import egovframework.com.domain.company.parameter.CompanyParameter;
import egovframework.com.domain.company.parameter.WorkplaceParameter;
import egovframework.com.domain.company.service.CompanyService;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.domain.portal.logn.service.LoginService;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 회사 운영자 기능 API 컨트롤러
 * 
 * @author KimJuHwan
 *
 */
public class CompanyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private LoginService loginService;
	

	/**
     * 회사정보 상세조회
     * 
     * @param companyId
     * @return Company
     */
	@PostMapping("/view")
	@ApiOperation(value = "Get the Company",notes = "This function returns the specified Company.")
	public BaseResponse<Company> getCompany(HttpServletRequest request) {

		LOGGER.info("view");
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
        	Company company = companyService.getCompany(login.getCompanyId());
        	return new BaseResponse<Company>(company);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
		
    }
	
	/**
     * 회사정보 수정
     * 
     * @param parameter
     * @return Company
     */
	@PostMapping("/update")
	@ApiOperation(value = "Update company information",notes = "This function updates company information")
	public BaseResponse<Long> modifyCompany(HttpServletRequest request, @RequestBody CompanyParameter parameter) {
		
		LOGGER.info("update");
		LOGGER.info(parameter.toString());
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		parameter.setCompanyId(login.getCompanyId());
		parameter.setUpdateId(login.getUserId());
        
        try {
        	companyService.modifyCompany(parameter);    	
            return new BaseResponse<Long>(parameter.getCompanyId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(BaseResponseCode.SAVE_ERROR,
                    new String[] {"등록 중에 오류가 발행했습니다. (" + e.getMessage() + ")"});
        }
		
    }
	
	/************************************************   사업장    *************************************************************/
	
	/**
     * 사업장 목록
     * 
     * @param companyId
     * @return Workplace
     */
	@PostMapping("/workplace/select")
	@ApiOperation(value = "List of workspaces of the company",notes = "This function returns a list of workplace for the specified company")
	public BaseResponse<List<Workplace>> getWorkplaceList(HttpServletRequest request) {

		LOGGER.info("workplace/select");
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		CommonSearchParameter parameter = new CommonSearchParameter();
		parameter.setCompanyId(login.getCompanyId());
		
		try {
        	return new BaseResponse<List<Workplace>>(companyService.getWorkplaceList(parameter));
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
		
    }
	
	/**
     * 사업장 상세정보
     * 
     * @param workplaceId
     * @return Workplace
     */
	@PostMapping("/workspaces/view")
	@ApiOperation(value = "Get the workspace",notes = "This function returns the specified workspace")
	public BaseResponse<Workplace> getWorkplace(HttpServletRequest request, @RequestBody CommonSearchParameter parameter) {

		LOGGER.info("workplace/view");
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		parameter.setCompanyId(login.getCompanyId());
		
		try {
			
        	return new BaseResponse<Workplace>(companyService.getWorkplace(parameter.getCompanyId(), parameter.getWorkplaceId()));
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
		
    }
	
	
	/**
     * 사업장 등록
     * 
     * @param parameter
     * @return 
     */
	@PostMapping("/workspaces/insert")
	@ApiOperation(value = "Add a new workspace",notes = "This function adds a new workspace")
	public BaseResponse<Long> insertWorkplace(HttpServletRequest request, @RequestBody WorkplaceParameter parameter) {

		LOGGER.info("workplace/insert");
		LOGGER.info(parameter.toString());
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			
			parameter.setCompanyId(login.getCompanyId());
        	parameter.setInsertId(login.getUserId());
        	parameter.setUpdateId(login.getUserId());
        	companyService.insertWorkplace(parameter);
        	return new BaseResponse<Long>(parameter.getWorkplaceId());
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
		
    }
	
	/**
     * 사업장 수정
     * 
     * @param parameter
     * @return 
     */
	@PostMapping("/workspaces/update")
	@ApiOperation(value = "Update a workspace",notes = "This function update a workspace")
	public BaseResponse<Long> modifyWorkplace(HttpServletRequest request, @RequestBody WorkplaceParameter parameter) {
		
		LOGGER.info("workplace/update");
		LOGGER.info(parameter.toString());
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}

		try {
			parameter.setCompanyId(login.getCompanyId());
			parameter.setUpdateId(login.getUserId());
        	companyService.modifyWorkplace(parameter);
        	return new BaseResponse<Long>(parameter.getWorkplaceId());
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
		
    }
	
	/**
     * 사업장 삭제
     * 
     * @apiNote 현재 로그인한 사용자의 ID를 받아와야함
     * @param companyId, workplaceId
     * @return 
     */
	@PostMapping("/workspaces/delete")
	@ApiOperation(value = "Delete a workspace",notes = "This function delete a workspace")
	public BaseResponse<Boolean> deleteWorkplace(HttpServletRequest request, @RequestBody CommonSearchParameter parameter) {

		LOGGER.info("workplace/delete");
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			parameter.setCompanyId(login.getCompanyId());
        	companyService.deleteWorkplace(parameter.getCompanyId(), parameter.getWorkplaceId(), login.getUserId());
        	return new BaseResponse<Boolean>(true);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
		
    }
	
	/************************************************      관리차수       *************************************************************/
	
	
	/**
     * 관리차수 목록
     * 
     * @param companyId
     * @return Workplace
     */
	@PostMapping("/baselines/select")
	@ApiOperation(value = "List of baselines of the company",notes = "This function returns a list of baselines for the specified company")
	public BaseResponse<List<Baseline>> getBaselineList(HttpServletRequest request, @RequestBody CommonSearchParameter parameter) {

		LOGGER.info("baselines/select");
		LOGGER.info(parameter.toString());
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
        	parameter.setCompanyId(login.getCompanyId());
        	return new BaseResponse<List<Baseline>>(companyService.getBaselineList(parameter));
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
		
    }
	
	/**
     * 관리차수 상세정보
     * 
     * @param companyId, baselineId
     * @return Baseline
     */
	@PostMapping("/baselines/view")
	@ApiOperation(value = "Get the baseline",notes = "This function returns the specified baseline")
	public BaseResponse<Baseline> getBaseline(HttpServletRequest request, @RequestBody CommonSearchParameter parameter) {

		LOGGER.info("baselines/view");
		LOGGER.info(parameter.toString());
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			parameter.setCompanyId(login.getCompanyId());
        	return new BaseResponse<Baseline>(companyService.getBaseline(parameter.getCompanyId(), parameter.getBaselineId()));
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
		
    }
	
	
	/**
     * 관리차수 등록
     * 
     * @param parameter
     * @return 
     */
	@PostMapping("/baselines/insert")
	@ApiOperation(value = "Add a new baseline",notes = "This function adds a new baseline")
	public BaseResponse<Long> insertBaseline(HttpServletRequest request, @RequestBody BaselineParameter parameter) {

		LOGGER.info("baselines/insert");
		LOGGER.info(parameter.toString());
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			parameter.setCompanyId(login.getCompanyId());
			parameter.setInsertId(login.getUserId());
			parameter.setUpdateId(login.getUserId());
        	companyService.insertBaseline(parameter);
        	return new BaseResponse<Long>(parameter.getCompanyId());
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
		
    }
	
	/**
     * 관리차수 수정
     * 
     * @param parameter
     * @return 
     */
	@PostMapping("/baselines/update")
	@ApiOperation(value = "Update a baseline",notes = "This function update a baseline")
	public BaseResponse<Long> modifyBaseline(HttpServletRequest request, @RequestBody BaselineParameter parameter) {

		LOGGER.info("baselines/update");
		LOGGER.info(parameter.toString());
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			parameter.setCompanyId(login.getCompanyId());
			parameter.setUpdateId(login.getUserId());
        	companyService.modifyBaseline(parameter);
        	return new BaseResponse<Long>(parameter.getBaselineId());
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
		
    }

	/**
     * 관리차수 삭제
     * 
     * @param parameter
     * @return 
     */
	@PostMapping("/baselines/delete")
	@ApiOperation(value = "Delete a baseline",notes = "This function delete a baseline")
	public BaseResponse<Boolean> deleteBaseline(HttpServletRequest request, @RequestBody CommonSearchParameter parameter) {

		LOGGER.info("baselines/delete");
		LOGGER.info(parameter.toString());
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		
		if(parameter.getBaselineId() ==null || parameter.getBaselineId()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}			
		
		if(parameter.getCompanyId() ==null || parameter.getCompanyId()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}			
		
		try {
			parameter.setCompanyId(login.getCompanyId());
        	companyService.deleteBaseline(parameter.getCompanyId(), parameter.getBaselineId(), login.getUserId());
        	return new BaseResponse<Boolean>(true);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
		
    }
	
	/**
     * 관리차수 마감
     * 
     * @apiNote 현재 로그인한 사용자의 ID를 받아와야함
     * @param companyId, baselineId
     * @return 
     */
	@PostMapping("/baselines/close")
	@ApiOperation(value = "Delete a baseline",notes = "This function delete a baseline")
	@ApiImplicitParams({@ApiImplicitParam(name = "companyId", value = "Id of the company",dataType = "long"),
						@ApiImplicitParam(name = "baselineId", value = "Id of the baseline",dataType = "long")})
	public BaseResponse<Boolean> closeBaseline(HttpServletRequest request, @RequestBody CommonSearchParameter parameter) {

		LOGGER.info("baselines/close");
		LOGGER.info(parameter.toString());

		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
        	companyService.closeBaseline(parameter.getCompanyId(), parameter.getBaselineId(), login.getUserId());
        	return new BaseResponse<Boolean>(true);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
		
    }
	
}
