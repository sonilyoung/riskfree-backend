package egovframework.com.domain.subscriber.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.com.domain.company.parameter.CommonSearchParameter;
import egovframework.com.domain.company.parameter.WorkplaceParameter;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.domain.portal.logn.service.LoginService;
import egovframework.com.domain.subscriber.domain.Subscriber;
import egovframework.com.domain.subscriber.parameter.SubscriberParameter;
import egovframework.com.domain.subscriber.parameter.SubscriberSearchParameter;
import egovframework.com.domain.subscriber.service.SubscriberService;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 운영자 기능 API 컨트롤러
 * 
 * 
 * @author KimJuHwan
 *
 */
@RestController
@RequestMapping("/subscribers")
@Api(tags = "Subscribers Management API")
public class SubscriberController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberController.class);
	
	@Autowired
	private SubscriberService subscriberService;
	
	@Autowired
    private LoginService loginService;
	
	/**
     * 가입된 회사 목록 
     * 
     * @param parameter
     * @return List<Subscriber>
     */
    @PostMapping("/select")
    @ApiOperation(value = "List of subscribing companies", notes = "This function returns a list of subscribed companies.")
    public BaseResponse<List<Subscriber>> getSubscriberCompanyList(HttpServletRequest request, @RequestBody SubscriberSearchParameter parameter) {
        
    	LOGGER.info("/select");
    	LOGGER.info(parameter.toString());
    	
    	return new BaseResponse<List<Subscriber>>(subscriberService.getSubscriberCompanyList(parameter));
    }
    
    /**
     * 가입된 회사의 사업장 목록 (+ - 버튼 클릭 시 동작)
     * 
     * @param parameter
     * @return List<Subscriber>
     */
    @PostMapping("/workplace/select")
    @ApiOperation(value = "List of subscribing workplaces", notes = "This function returns a list of subscribed workplaces.")
    public BaseResponse<List<Subscriber>> getSubscriberWorkplaceList(HttpServletRequest request, Long companyId) {
        
    	LOGGER.info("/workplace/select");
    	
    	return new BaseResponse<List<Subscriber>>(subscriberService.getSubscriberWorkplaceList(companyId));
    }
    
    /**
     * 회사 등록
     * 
     * @param parameter
     * @return 
     */
	@PostMapping("/insert")
	@ApiOperation(value = "Add a new subscribing company",notes = "This function adds a new Add a new subscribing company")
	public BaseResponse<Long> insertSubscriberCompany(HttpServletRequest request, @RequestBody SubscriberParameter parameter) {

		LOGGER.info("/insert");
    	LOGGER.info(parameter.toString());
    	
		if (parameter.getCompanyName() == null || "".equals(parameter.getCompanyName())) {
			throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {"회사명"});
		}
		
		if (parameter.getWorkplaceName() == null|| "".equals(parameter.getWorkplaceName())) {
			throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {"사업장명"});
		}		
    	
		if (parameter.getRegistNo() == null|| "".equals(parameter.getRegistNo())) {
			throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {"사업자 등록번호"});
		}			

		if (parameter.getLoginId() == null|| "".equals(parameter.getLoginId())) {
			throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {"아이디"});
		}			
		
		if (parameter.getManagerRoleCd() == null|| "".equals(parameter.getManagerRoleCd())) {
			throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {"사용자권한"});
		}		
		
		if (parameter.getManagerName() == null|| "".equals(parameter.getManagerName())) {
			throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {"담당자명"});
		}
		
		if (parameter.getStatusCd() != 0 && parameter.getStatusCd() != 1) {
			throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {"상태"});
		}			
				
		
		try {
			Login login = loginService.getLoginInfo(request);
			if (login == null) {
				throw new BaseException(BaseResponseCode.AUTH_FAIL);
			}
			
			parameter.setInsertId(login.getUserId());
			parameter.setUpdateId(login.getUserId());
            subscriberService.insertSubscriberCompany(parameter);
        	return new BaseResponse<Long>(parameter.getCompanyId());
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, BaseResponseCode.UNKONWN_ERROR.getMessage());
        }
		
    }
    
	/**
     * 가입된 회사의 상세정보 조회
     * 
     * @param parameter
     * @return List<Subscriber>
     */
    @PostMapping("/view")
    @ApiOperation(value = "Get a subscription company", notes = "This function returns company details.")
    public BaseResponse<Subscriber> getSubscriberCompany(HttpServletRequest request, Long workplaceId, Long userId) {
    	
    	LOGGER.info("/view");
    	
        return new BaseResponse<Subscriber>(subscriberService.getSubscriberCompany(workplaceId, userId));
    }
    
    /**
     * 가입된 회사 정보변경
     * 
     * @param parameter
     * @return List<Subscriber>
     */
    @PostMapping("/update")
    @ApiOperation(value = "Update company information", notes = "This function updates company information")
    public BaseResponse<Long> updateSubscriberCompany(HttpServletRequest request,  @RequestBody SubscriberParameter parameter) {
    	
    	LOGGER.info("/update");
    	LOGGER.info(parameter.toString());
    	
    	try {
			Login login = loginService.getLoginInfo(request);
			if (login == null) {
				throw new BaseException(BaseResponseCode.AUTH_FAIL);
			}
			
			parameter.setInsertId(login.getUserId());
			parameter.setUpdateId(login.getUserId());
            subscriberService.updateSubscriberCompany(parameter);
        	return new BaseResponse<Long>(parameter.getCompanyId());
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, BaseResponseCode.UNKONWN_ERROR.getMessage());
        }
    }
    
    /**
     * 회사 정보 조회
     * 
     * @param companyName
     * @return List<Subscriber>
     */
    @PostMapping("/search/companay")
    @ApiOperation(value = "Search company information", notes = "This function search company information")
    public BaseResponse<List<Subscriber>> searchCompany(HttpServletRequest request,  String companyName) {
    	
    	LOGGER.info("/search/companay");
    	
    	try {
			String managerName = null;
        	return new BaseResponse<List<Subscriber>>(subscriberService.getSearchCompany(companyName, managerName));
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, BaseResponseCode.UNKONWN_ERROR.getMessage());
        }
    }
    
    /**
     * 회사 정보 조회
     * 
     * @param parameter
     * @return List<Subscriber>
     */
    @PostMapping("/search/workplace")
    @ApiOperation(value = "Search workplace information", notes = "This function search workplace information")
    public BaseResponse<List<Subscriber>> searchWorkplace(HttpServletRequest request,  Long companyId, String workplaceName) {
    	
    	LOGGER.info("/search/workplace");
    	
    	try {
        	return new BaseResponse<List<Subscriber>>(subscriberService.getSearchWorkplace(companyId, workplaceName));
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, BaseResponseCode.UNKONWN_ERROR.getMessage());
        }
    }
    
}
