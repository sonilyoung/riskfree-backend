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
import egovframework.com.domain.subscriber.domain.Subscriber;
import egovframework.com.domain.subscriber.parameter.SubscriberParameter;
import egovframework.com.domain.subscriber.service.SubscriberService;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 가입자 API 컨트롤러(운영자 기능)
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
	
	
	/**
     * 가입 회사 목록 
     * 
     * @param parameter
     * @return List<Subscriber>
     */
    @GetMapping("/infos")
    @ApiOperation(value = "List of subscribing companies", notes = "This function returns a list of subscribed companies.")
    public BaseResponse<List<Subscriber>> getSubscriberCompanyList(HttpServletRequest request, CommonSearchParameter parameter) {
        return new BaseResponse<List<Subscriber>>(subscriberService.getSubscriberCompanyList(parameter));
    }
    
    /**
     * 가입 회사 등록
     * 
     * @param parameter
     * @return 
     */
	@PostMapping("/infos")
	@ApiOperation(value = "Add a new subscribing company",notes = "This function adds a new Add a new subscribing company")
	public BaseResponse<Long> insertSubscriberCompany(HttpServletRequest request, @RequestBody SubscriberParameter parameter) {

		try {
        	// 세션에서 ID가져오기
        	parameter.setInsertId(1L);
        	subscriberService.insertSubscriberCompany(parameter);
        	return new BaseResponse<Long>(parameter.getCompanyId());
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
		
    }
    
	/**
     * 가입 회사 상세조회
     * 
     * @param parameter
     * @return List<Subscriber>
     */
    @GetMapping("/{companyId}/infos")
    @ApiOperation(value = "Get a subscription company", notes = "This function returns company details.")
    public BaseResponse<Subscriber> getSubscriberCompany(HttpServletRequest request, @PathVariable Long companyId) {
        return new BaseResponse<Subscriber>(subscriberService.getSubscriberCompany(companyId));
    }
    
}
