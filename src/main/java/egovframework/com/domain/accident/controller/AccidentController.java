package egovframework.com.domain.accident.controller;

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

import egovframework.com.domain.accident.domain.Accident;
import egovframework.com.domain.accident.parameter.AccidentParameter;
import egovframework.com.domain.accident.parameter.AccidentSearchParameter;
import egovframework.com.domain.accident.service.AccidentService;
import egovframework.com.domain.company.parameter.WorkplaceParameter;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.domain.portal.logn.service.LoginService;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 재해발생 및 방지대책 등 이행현황 API 컨트롤러
 * 
 * @author KimJuHwan
 *
 */
@RestController
@RequestMapping("/accidents")
@Api(tags = "Accident Management API")
public class AccidentController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccidentController.class);

	@Autowired
	private AccidentService accidentService;
	
	@Autowired
	private LoginService loginService;
	
	/**
     * 재해발생 이행조치 목록
     * 
     * @param companyId
     * @return Company
     */
	@PostMapping("/select")
	@ApiOperation(value = "List of accidents of the company",notes = "This function returns a list of accidents for the specified company.")
	public BaseResponse<List<Accident>> getAccidentList(HttpServletRequest request, @ApiParam @RequestBody AccidentSearchParameter parameter) {

		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		parameter.setCompanyId(login.getCompanyId());
    	
    	return new BaseResponse<List<Accident>>(accidentService.getAccidentList(parameter));
    }
	
	
	/**
     * 재해발생 이행조치 상세정보
     * 
     * @param accidentId
     * @return Accident
     */
	@PostMapping("/view")
	@ApiOperation(value = "Get the accident",notes = "This function returns the specified accident")
	public BaseResponse<Accident> getAccident(HttpServletRequest request, @RequestBody Long accidentId ) {

		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		return new BaseResponse<Accident>(accidentService.getAccident(login.getCompanyId(), accidentId));
		
    }
	
	/**
     * 재해발생 이행조치 등록
     * 
     * @param parameter
     * @return 
     */
	@PostMapping("/insert")
	@ApiOperation(value = "Add a new accident",notes = "This function adds a new accident")
	public BaseResponse<Long> insertAccident(HttpServletRequest request, @RequestBody AccidentParameter parameter) {

		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
    	parameter.setCompanyId(login.getCompanyId());
    	parameter.setInsertId(login.getUserId());
    	parameter.setUpdateId(login.getUserId());
    	accidentService.insertAccident(parameter);
    	return new BaseResponse<Long>(parameter.getCompanyId());
		
    }
	
	/**
     * 재해발생 이행조치 수정
     * 
     * @param parameter
     * @return 
     */
	@PostMapping("/update")
	@ApiOperation(value = "Update a accident",notes = "This function update a accident")
	public BaseResponse<Long> modifyAccident(HttpServletRequest request, @RequestBody AccidentParameter parameter) {

		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
    	parameter.setCompanyId(login.getCompanyId());
    	parameter.setUpdateId(login.getUserId());
    	accidentService.modifyAccident(parameter);
    	return new BaseResponse<Long>(parameter.getAccidentId());
		
    }
	
	/**
     * 재해발생 이행조치 삭제
     * 
     * @param accidentId
     * @return 
     */
	@PostMapping("/delete")
	@ApiOperation(value = "Delete a accident",notes = "This function delete a accident")
	public BaseResponse<Boolean> deleteAccident(HttpServletRequest request, @RequestBody Long accidentId) {

		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		accidentService.deleteAccident(login.getCompanyId(), accidentId, login.getUserId());
    	return new BaseResponse<Boolean>(true);
		
    }
	
}
