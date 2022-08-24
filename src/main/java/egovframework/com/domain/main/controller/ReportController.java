package egovframework.com.domain.main.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.com.domain.company.parameter.CompanyParameter;
import egovframework.com.domain.main.domain.Amount;
import egovframework.com.domain.main.domain.Baseline;
import egovframework.com.domain.main.domain.Company;
import egovframework.com.domain.main.domain.Improvement;
import egovframework.com.domain.main.domain.MainExcelData;
import egovframework.com.domain.main.domain.Notice;
import egovframework.com.domain.main.domain.Workplace;
import egovframework.com.domain.main.service.MainService;
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
 * 공지사항 API 컨트롤러
 * 
 * @author KimJuHwan
 *
 */
@RestController
@RequestMapping("/main")
@Api(tags = "Main Management API")
public class ReportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private MainService mainService;
    
    @Autowired
    private LoginService loginService;

	
    
    /**
     * 재해발생 방지대책 및 이행현황 (레포트)
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/getAccidentsPreventionReport")
    @ApiOperation(value = "AccidentsPrevention information Rport data", notes = "get AccidentsPrevention information Report data")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "params", value = "{'workplaceId' : '23', 'baselineStart' : '2022-07-01' , 'baselineEnd' : '2022-08-30'}")
    })	       
    public BaseResponse<List<Amount>> getAccidentsPreventionReport(HttpServletRequest request, @RequestBody Amount params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			
			Long workPlaceId;
			if(params.getWorkplaceId() !=null){
				workPlaceId = login.getWorkplaceId();
			}else {
				workPlaceId = params.getWorkplaceId();
			}
			params.setWorkplaceId(workPlaceId);			
			
			List<Amount> result = mainService.getAccidentsPreventionReport(params);
			return new BaseResponse<List<Amount>>(result); 	       
        	
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
    }   
    
    
    
    /**
     * 관계법령에 따른 개선 시정명령조치 (레포트) 
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/getImprovemetLawOrderReport")
    @ApiOperation(value = "getImprovemetLawOrder information Report data", notes = "get getImprovemetLawOrder information Report data")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "params", value = "{'workplaceId' : '23', 'baselineStart' : '2022-07-01' , 'baselineEnd' : '2022-08-30'}")
    })	       
    public BaseResponse<List<Amount>> getImprovemetLawOrderReport(HttpServletRequest request, @RequestBody Amount params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			Long workPlaceId;
			if(params.getWorkplaceId() !=null){
				workPlaceId = login.getWorkplaceId();
			}else {
				workPlaceId = params.getWorkplaceId();
			}
			params.setWorkplaceId(workPlaceId);						
			
			List<Amount> result = mainService.getImprovemetLawOrderReport(params);
			return new BaseResponse<List<Amount>>(result); 	       
        	
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
    }    
    	
}
