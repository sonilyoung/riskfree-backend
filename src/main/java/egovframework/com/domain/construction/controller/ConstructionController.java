package egovframework.com.domain.construction.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.com.domain.accident.domain.Accident;
import egovframework.com.domain.construction.parameter.ConstructionSearchParameter;
import egovframework.com.domain.construction.service.ConstructionService;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.domain.portal.logn.service.LoginService;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 안전작업허가 공사내역 API 컨트롤러
 * 
 * @author KimJuHwan
 *
 */
@RestController
@RequestMapping("/construction-history")
@Api(tags = "Construction Management API")
public class ConstructionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConstructionController.class);

	@Autowired
	private ConstructionService constructionService;
	
	@Autowired
	private LoginService loginService;

	
	/**
     * 안전작업허가 공사내역 목록
     * 
     * @param companyId
     * @return Company
     */
	@PostMapping("/select")
	@ApiOperation(value = "List of construction history of the company",notes = "This function returns a list of construction history for the specified company.")
	public BaseResponse<List<Accident>> getConstructionHistoryList(HttpServletRequest request, @RequestBody ConstructionSearchParameter parameter) {

		LOGGER.info("select");
    	LOGGER.info(parameter.toString());
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		parameter.setCompanyId(login.getCompanyId());
    	return new BaseResponse<List<Accident>>(constructionService.getConstructionHistoryList(parameter));
    }
	
	
}
