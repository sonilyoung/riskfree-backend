package egovframework.com.domain.subscriber.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.com.domain.company.parameter.CommonSearchParameter;
import egovframework.com.domain.subscriber.domain.Subscriber;
import egovframework.com.domain.subscriber.service.SubscriberService;
import egovframework.com.global.http.BaseResponse;
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
    @ApiOperation(value = "List of Subscribing Companies", notes = "This function returns a list of subscribed companies.")
    public BaseResponse<List<Subscriber>> getSubscriberList(HttpServletRequest request, CommonSearchParameter parameter) {
        return new BaseResponse<List<Subscriber>>(subscriberService.getSubscriberList(parameter));
    }
    
    
    
}
