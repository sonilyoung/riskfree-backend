package egovframework.com.domain.accident.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.com.domain.accident.domain.Accident;
import egovframework.com.domain.accident.parameter.AccidentParameter;
import egovframework.com.domain.accident.parameter.AccidentSearchParameter;
import egovframework.com.domain.accident.service.AccidentService;
import egovframework.com.domain.main.domain.Baseline;
import egovframework.com.domain.main.domain.Company;
import egovframework.com.domain.main.service.MainService;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.domain.portal.logn.service.LoginService;
import egovframework.com.global.OfficeMessageSource;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
	
	@Autowired
    private MainService mainService;
	
	/**
     * 재해발생 이행조치 목록
     * 
     * @param companyId
     * @return Company
     */
	@PostMapping("/select")
	@ApiOperation(value = "List of accidents of the company",notes = "This function returns a list of accidents for the specified company.")
	public BaseResponse<List<Accident>> getAccidentList(HttpServletRequest request, @RequestBody AccidentSearchParameter parameter) {

		LOGGER.debug("select");
    	LOGGER.debug(parameter.toString());
    	
    	LOGGER.debug("============ : " + parameter.getAccTypeCd001() + "================");
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			parameter.setCompanyId(login.getCompanyId());
			//parameter.setWorkplaceId(login.getWorkplaceId());
			
			return new BaseResponse<List<Accident>>(accidentService.getAccidentList(parameter));
		} catch (Exception e) {
			throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
		}
		
    }
	
	
	/**
     * 재해발생 이행조치 상세정보
     * 
     * @param accidentId
     * @return Accident
     */
	@PostMapping("/view")
	@ApiOperation(value = "Get the accident",notes = "This function returns the specified accident")
	public BaseResponse<Accident> getAccident(HttpServletRequest request, Long accidentId) {

		LOGGER.debug("view");
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
			
		Accident accident = accidentService.getAccident(login.getCompanyId(), accidentId);
		
		// null이면 0으로 변경
		accident.setDeathToll(Optional.ofNullable(accident.getDeathToll()).orElse(0));
		accident.setSameAccidentInjury(Optional.ofNullable(accident.getSameAccidentInjury()).orElse(0));
		accident.setJobDeseaseToll(Optional.ofNullable(accident.getJobDeseaseToll()).orElse(0));
		
		return new BaseResponse<Accident>(accident);
		
    }
	
	/**
     * 재해발생 이행조치 등록
     * 
     * @param parameter
     * @return 
     */
	@PostMapping("/insert")
	@ApiOperation(value = "Add a new accident",notes = "This function adds a new accident")
	public BaseResponse<Integer> insertAccident(HttpServletRequest request, @RequestBody AccidentParameter parameter) {

		LOGGER.debug("insert");
    	LOGGER.debug(parameter.toString());
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
    	if (!StringUtils.hasText(parameter.getRecvDate())) {
    		throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
    				new String[] {"recvDate", "접수일자"});
    	}

    	if (!StringUtils.hasText(parameter.getRecvUserName())) {
    		throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
    				new String[] {"recvUserName", "접수자명"});
    	}
    	
    	if (!StringUtils.hasText(parameter.getRecvFormCd())) {
    		throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
    				new String[] {"recvFormCd", "사고접수형태"});
    	}
    	
    	if (!StringUtils.hasText(parameter.getAccdntCn())) {
    		throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
    				new String[] {"accdntCn", "사고조치내용"});
    	}
    	
    	
        
        // 관리차수 셋팅
        Baseline params = new Baseline();
        params.setCompanyId(login.getCompanyId());
        Baseline baseLineInfo = mainService.getRecentBaseline(params);
        
		if(baseLineInfo==null) {
			return new BaseResponse<Integer>(BaseResponseCode.DATA_IS_NULL, OfficeMessageSource.getMessage("baseline.nodata"));
		}        
		
       try {
			
			parameter.setDeathToll(Optional.ofNullable(parameter.getDeathToll()).orElse(0));
			parameter.setSameAccidentInjury(Optional.ofNullable(parameter.getSameAccidentInjury()).orElse(0));
			parameter.setJobDeseaseToll(Optional.ofNullable(parameter.getJobDeseaseToll()).orElse(0));
	        parameter.setBaselineId(baseLineInfo.getBaselineId());
	    	parameter.setCompanyId(login.getCompanyId());
	    	parameter.setWorkplaceId(login.getWorkplaceId());
	    	parameter.setInsertId(login.getUserId());
	    	parameter.setUpdateId(login.getUserId());
	    	
	    	int cnt = accidentService.insertAccident(parameter);
	    	return new BaseResponse<Integer>(cnt);
	    	
        } catch (Exception e) {
        	
        	throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
		}
    	
    }
	
	/**
     * 재해발생 이행조치 수정
     * 
     * @param parameter
     * @return 
     */
	@PostMapping("/update")
	@ApiOperation(value = "Update a accident",notes = "This function update a accident")
	public BaseResponse<Integer> modifyAccident(HttpServletRequest request, @RequestBody AccidentParameter parameter) {

		LOGGER.debug("update");
    	LOGGER.debug(parameter.toString());
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
	
    	
    	if (!StringUtils.hasText(parameter.getRecvDate())) {
    		throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
    				new String[] {"recvDate", "접수일자"});
    	}

    	if (!StringUtils.hasText(parameter.getRecvUserName())) {
    		throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
    				new String[] {"recvUserName", "접수자명"});
    	}
    	
    	if (!StringUtils.hasText(parameter.getAccdntCn())) {
    		throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
    				new String[] {"accdntCn", "사고조치내용"});
    	}
    	
		
        try {
        	// 관리차수 셋팅
    	    Baseline params = new Baseline();
			params.setCompanyId(login.getCompanyId());
	        
			Baseline baseLineInfo = mainService.getRecentBaseline(params);
        	
	        // 사망자 수 부상자 수 입력값이 없으면 null로 입력
	        parameter.setDeathToll(Optional.ofNullable(parameter.getDeathToll()).orElse(0));
	        parameter.setSameAccidentInjury(Optional.ofNullable(parameter.getSameAccidentInjury()).orElse(0));
	        parameter.setJobDeseaseToll(Optional.ofNullable(parameter.getJobDeseaseToll()).orElse(0));
	        parameter.setBaselineId(baseLineInfo.getBaselineId());
	    	parameter.setCompanyId(login.getCompanyId());
	    	parameter.setWorkplaceId(login.getWorkplaceId());
	    	parameter.setUpdateId(login.getUserId());
	    	int cnt = accidentService.modifyAccident(parameter);
	    	return new BaseResponse<Integer>(cnt);
	    	
        } catch (Exception e) {
        	
        	throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
		}
		
    }
	
	/**
     * 재해발생 이행조치 삭제
     * 
     * @param accidentId
     * @return 
     */
	@PostMapping("/delete")
	@ApiOperation(value = "Delete a accident",notes = "This function delete a accident")
	public BaseResponse<Integer> deleteAccident(HttpServletRequest request, Long accidentId) {

		LOGGER.debug("delete");
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			
			int cnt = accidentService.deleteAccident(login.getCompanyId(), accidentId, login.getUserId());
	    	return new BaseResponse<Integer>(cnt);
			
		} catch (Exception e) {
		
			throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
		}
		
    }
	
	/**
     * 발생장소 리스트
     * 
     * @return List<String>
     */
	@PostMapping("/occurPlace/select")
	@ApiOperation(value = "List of occur place by company",notes = "This function returns a list of occur place by company.")
	public BaseResponse<List<Map<String,String>>> selectOccurPlace(HttpServletRequest request, Long baselineId) {

		LOGGER.debug("/occurPlace/select");
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
	
			
		 AccidentSearchParameter param = new AccidentSearchParameter();
		 param.setCompanyId(login.getCompanyId());
		 param.setWorkplaceId(login.getWorkplaceId());
		 param.setBaselineId(baselineId);
		
		return new BaseResponse<List<Map<String,String>>>(accidentService.selectOccurPlace(param));
			
    }
	
}
