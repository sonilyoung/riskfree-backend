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
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.domain.portal.logn.service.LoginService;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
	public BaseResponse<List<Accident>> getAccidentList(HttpServletRequest request, @RequestBody AccidentSearchParameter parameter) {

		LOGGER.info("select");
    	LOGGER.info(parameter.toString());
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
//		if(!login.getRoleName().equals("대표이사")) {
//			parameter.setWorkplaceId(login.getWorkplaceId());
//		}
		
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
	public BaseResponse<Accident> getAccident(HttpServletRequest request, @RequestBody AccidentSearchParameter parameter) {

		LOGGER.info("view");
    	LOGGER.info(parameter.toString());
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		return new BaseResponse<Accident>(accidentService.getAccident(login.getCompanyId(), parameter.getAccidentId()));
		
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

		LOGGER.info("insert");
    	LOGGER.info(parameter.toString());
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if (ObjectUtils.isEmpty(parameter.getWorkplaceId())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"workplaceId", "사업장ID"});
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
    	
        if (!StringUtils.hasText(parameter.getOccurDate())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"occurDate", "발생일자"});
        }
        
        if (!StringUtils.hasText(parameter.getOccurPlace())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"occurPlace", "발생장소"});
        }
        
        if (!StringUtils.hasText(parameter.getManagerName())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"managerName", "현장책임자"});
        }
        
        if (!StringUtils.hasText(parameter.getOccurReason())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"occurReason", "발생원인"});
        }
		
        // 사망자 수 부상자 수 입력값이 없으면 null로 입력
        parameter.setDeathToll(Optional.ofNullable(parameter.getDeathToll()).orElse(null));
        parameter.setSameAccidentInjury(Optional.ofNullable(parameter.getSameAccidentInjury()).orElse(null));
        parameter.setJobDeseaseToll(Optional.ofNullable(parameter.getJobDeseaseToll()).orElse(null));
        
    	parameter.setCompanyId(login.getCompanyId());
    	parameter.setWorkplaceId(login.getWorkplaceId());
    	parameter.setInsertId(login.getUserId());
    	parameter.setUpdateId(login.getUserId());
    	int cnt = accidentService.insertAccident(parameter);
    	
    	
    	return new BaseResponse<Integer>(cnt);
		
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

		LOGGER.info("update");
    	LOGGER.info(parameter.toString());
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if (ObjectUtils.isEmpty(parameter.getWorkplaceId())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"workplaceId", "사업장ID"});
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
    	
        if (!StringUtils.hasText(parameter.getOccurDate())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"occurDate", "발생일자"});
        }
        
        if (!StringUtils.hasText(parameter.getOccurPlace())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"occurPlace", "발생장소"});
        }
        
        if (!StringUtils.hasText(parameter.getManagerName())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"managerName", "현장책임자"});
        }
        
        if (!StringUtils.hasText(parameter.getOccurReason())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"occurReason", "발생원인"});
        }
		
        // 사망자 수 부상자 수 입력값이 없으면 null로 입력
        parameter.setDeathToll(Optional.ofNullable(parameter.getDeathToll()).orElse(null));
        parameter.setSameAccidentInjury(Optional.ofNullable(parameter.getSameAccidentInjury()).orElse(null));
        parameter.setJobDeseaseToll(Optional.ofNullable(parameter.getJobDeseaseToll()).orElse(null));
        
    	parameter.setCompanyId(login.getCompanyId());
    	parameter.setWorkplaceId(login.getWorkplaceId());
    	parameter.setUpdateId(login.getUserId());
    	int cnt = accidentService.modifyAccident(parameter);
    	return new BaseResponse<Integer>(cnt);
		
    }
	
	/**
     * 재해발생 이행조치 삭제
     * 
     * @param accidentId
     * @return 
     */
	@PostMapping("/delete")
	@ApiOperation(value = "Delete a accident",notes = "This function delete a accident")
	public BaseResponse<Integer> deleteAccident(HttpServletRequest request, @RequestBody AccidentSearchParameter parameter) {

		LOGGER.info("delete");
    	LOGGER.info(parameter.toString());
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		int cnt = accidentService.deleteAccident(login.getCompanyId(), parameter.getAccidentId(), login.getUserId());
    	return new BaseResponse<Integer>(cnt);
		
    }
	
	/**
     * 발생장소 리스트
     * 
     * @return List<String>
     */
	@PostMapping("/occurPlace/select")
	@ApiOperation(value = "List of occur place by company",notes = "This function returns a list of occur place by company.")
	public BaseResponse<List<Map<String,String>>> selectOccurPlace(HttpServletRequest request) {

		LOGGER.info("/occurPlace/select");
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
	
    	return new BaseResponse<List<Map<String,String>>>(accidentService.selectOccurPlace(login.getCompanyId()));
		
    }
	
}
