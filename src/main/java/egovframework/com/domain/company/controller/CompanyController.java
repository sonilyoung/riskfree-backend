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
import egovframework.com.domain.company.parameter.CompanyParameter;
import egovframework.com.domain.company.parameter.WorkplaceParameter;
import egovframework.com.domain.company.parameter.BaselineParameter;
import egovframework.com.domain.company.parameter.CommonSearchParameter;
import egovframework.com.domain.company.service.CompanyService;
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
@RestController
@RequestMapping("/companies")
@Api(tags = "Company Management API")
public class CompanyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);
	
	@Autowired
	private CompanyService companyService;
	

	/**
     * 회사정보 상세조회
     * 
     * @param companyId
     * @return Company
     */
	@GetMapping("/{companyId}/infos")
	@ApiOperation(value = "Get the Company",notes = "This function returns the specified Company.")
	@ApiImplicitParams({@ApiImplicitParam(name = "companyId", value = "Id of the company",dataType = "long")})
	public BaseResponse<Company> getCompany(HttpServletRequest request, @PathVariable Long companyId) {

		try {
        	Company company = companyService.getCompany(companyId);
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
	@PutMapping("/{companyId}/infos")
	@ApiOperation(value = "Update company information",notes = "This function updates company information")
	@ApiImplicitParams({@ApiImplicitParam(name = "companyId", value = "Id of the company",dataType = "long")})
	public BaseResponse<Long> modifyCompany(HttpServletRequest request, @PathVariable Long companyId, @RequestBody CompanyParameter parameter) {
		
        parameter.setCompanyId(companyId);
        
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
	@GetMapping("/{companyId}/workspaces/infos")
	@ApiOperation(value = "List of workspaces of the company",notes = "This function returns a list of workplace for the specified company")
	@ApiImplicitParams({@ApiImplicitParam(name = "companyId", value = "Id of the company",dataType = "long")})
	public BaseResponse<List<Workplace>> getWorkplaceList(HttpServletRequest request, @PathVariable Long companyId, CommonSearchParameter parameter) {

		try {
        	parameter.setCompanyId(companyId);
        	return new BaseResponse<List<Workplace>>(companyService.getWorkplaceList(parameter));
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
		
    }
	
	/**
     * 사업장 상세정보
     * 
     * @param companyId
     * @return Workplace
     */
	@GetMapping("/{companyId}/workspaces/{workplaceId}")
	@ApiOperation(value = "Get the workspace",notes = "This function returns the specified workspace")
	@ApiImplicitParams({@ApiImplicitParam(name = "companyId", value = "Id of the company",dataType = "long"),
						@ApiImplicitParam(name = "workplaceId", value = "Id of the workplace",dataType = "long")})
	public BaseResponse<Workplace> getWorkplace(HttpServletRequest request, @PathVariable Long companyId, @PathVariable Long workplaceId) {

		try {
        	return new BaseResponse<Workplace>(companyService.getWorkplace(companyId, workplaceId));
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
	@PostMapping("/{companyId}/workspaces/infos")
	@ApiOperation(value = "Add a new workspace",notes = "This function adds a new workspace")
	@ApiImplicitParams({@ApiImplicitParam(name = "companyId", value = "Id of the company",dataType = "long")})
	public BaseResponse<Long> insertWorkplace(HttpServletRequest request, @PathVariable Long companyId, 
			@RequestBody WorkplaceParameter parameter) {

		try {
        	parameter.setCompanyId(companyId);
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
	@PutMapping("/{companyId}/workspaces/{workplaceId}")
	@ApiOperation(value = "Update a workspace",notes = "This function update a workspace")
	@ApiImplicitParams({@ApiImplicitParam(name = "companyId", value = "Id of the company",dataType = "long"),
						@ApiImplicitParam(name = "workplaceId", value = "Id of the workplace",dataType = "long")})
	public BaseResponse<Long> modifyWorkplace(HttpServletRequest request, @PathVariable Long companyId, 
			@RequestBody WorkplaceParameter parameter) {

		try {
        	parameter.setCompanyId(companyId);
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
	@DeleteMapping("/{companyId}/workspaces/{workplaceId}")
	@ApiOperation(value = "Delete a workspace",notes = "This function delete a workspace")
	@ApiImplicitParams({@ApiImplicitParam(name = "companyId", value = "Id of the company",dataType = "long"),
						@ApiImplicitParam(name = "workplaceId", value = "Id of the workplace",dataType = "long")})
	public BaseResponse<Boolean> deleteWorkplace(HttpServletRequest request, @PathVariable Long companyId, @PathVariable Long workplaceId) {

		try {
        	companyService.deleteWorkplace(companyId, workplaceId);
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
	@GetMapping("/{companyId}/baselines/infos")
	@ApiOperation(value = "List of baselines of the company",notes = "This function returns a list of baselines for the specified company")
	@ApiImplicitParams({@ApiImplicitParam(name = "companyId", value = "Id of the company",dataType = "long")})
	public BaseResponse<List<Baseline>> getBaselineList(HttpServletRequest request, @PathVariable Long companyId, CommonSearchParameter parameter) {

		try {
        	parameter.setCompanyId(companyId);
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
	@GetMapping("/{companyId}/baselines/{baselineId}")
	@ApiOperation(value = "Get the baseline",notes = "This function returns the specified baseline")
	@ApiImplicitParams({@ApiImplicitParam(name = "companyId", value = "Id of the company",dataType = "long"),
						@ApiImplicitParam(name = "baselineId", value = "Id of the baseline",dataType = "long")})
	public BaseResponse<Baseline> getBaseline(HttpServletRequest request, @PathVariable Long companyId, @PathVariable Long baselineId) {

		try {
        	return new BaseResponse<Baseline>(companyService.getBaseline(companyId, baselineId));
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
	@PostMapping("/{companyId}/baselines/infos")
	@ApiOperation(value = "Add a new baseline",notes = "This function adds a new baseline")
	@ApiImplicitParams({@ApiImplicitParam(name = "companyId", value = "Id of the company",dataType = "long")})
	public BaseResponse<Long> insertBaseline(HttpServletRequest request, @PathVariable Long companyId, 
			@RequestBody BaselineParameter parameter) {

		try {
        	parameter.setCompanyId(companyId);
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
	@PutMapping("/{companyId}/baselines/{baselineId}")
	@ApiOperation(value = "Update a baseline",notes = "This function update a baseline")
	@ApiImplicitParams({@ApiImplicitParam(name = "companyId", value = "Id of the company",dataType = "long"),
						@ApiImplicitParam(name = "baselineId", value = "Id of the baseline",dataType = "long")})
	public BaseResponse<Long> modifyBaseline(HttpServletRequest request, @PathVariable Long companyId, @PathVariable Long baselineId,
			@RequestBody BaselineParameter parameter) {

		try {
        	parameter.setCompanyId(companyId);
        	parameter.setBaselineId(baselineId);
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
	@DeleteMapping("/{companyId}/baselines/{baselineId}")
	@ApiOperation(value = "Delete a baseline",notes = "This function delete a baseline")
	@ApiImplicitParams({@ApiImplicitParam(name = "companyId", value = "Id of the company",dataType = "long"),
						@ApiImplicitParam(name = "baselineId", value = "Id of the baseline",dataType = "long")})
	public BaseResponse<Boolean> deleteBaseline(HttpServletRequest request, @PathVariable Long companyId, @PathVariable Long baselineId) {

		try {
        	companyService.deleteBaseline(companyId, baselineId);
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
	@PostMapping("/{companyId}/baselines/{baselineId}/closed")
	@ApiOperation(value = "Delete a baseline",notes = "This function delete a baseline")
	@ApiImplicitParams({@ApiImplicitParam(name = "companyId", value = "Id of the company",dataType = "long"),
						@ApiImplicitParam(name = "baselineId", value = "Id of the baseline",dataType = "long")})
	public BaseResponse<Boolean> closeBaseline(HttpServletRequest request, @PathVariable Long companyId, @PathVariable Long baselineId) {

		try {
        	companyService.closeBaseline(companyId, baselineId);
        	return new BaseResponse<Boolean>(true);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
		
    }
	
}
