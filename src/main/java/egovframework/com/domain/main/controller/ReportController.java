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

import egovframework.com.domain.main.domain.Amount;
import egovframework.com.domain.main.domain.Graph;
import egovframework.com.domain.main.domain.Report;
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
     *  레포트 아이템 항목
     * 
     * @param param
     * @return List<Report>
     */
    @PostMapping("/getTitleReport")
    @ApiOperation(value = "List of getTitleReport", notes = "This function returns the list of getTitleReport")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "workspaceId, groupId, menuTitle, workspaceName")
    })	          
    public BaseResponse<List<Report>> getTitleReport(HttpServletRequest request, @RequestBody Report params) {
        
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getCondition() ==null || "".equals(params.getCondition())){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR, "조건을 선택해주세요(condition is null) "
					+ "1:차수별 대응수준 현황 (통합) "
					+ ", 2:차수별 대응수준 현황 (사업장별)"
					+ ", 3:항목별 대응수준 현황 (통합)"
					+ ", 4:항목별 대응수준 현황 (사업장별)"
					+ ", 5:사업장별 재해발생 통계 "
					+ ", 6:개선.시정명령 조치내역 통계  "
					+ "\")");
		}
		
		try {
			params.setCompanyId(login.getCompanyId());
			params.setWorkplaceId(login.getWorkplaceId());
			params.setRoleCd(login.getRoleCd());				
	    	return new BaseResponse<List<Report>>(mainService.getTitleReport(params));
    	
	    } catch (Exception e) {
	    	LOGGER.error("error:", e);
	        throw new BaseException(BaseResponseCode.PARAMS_ERROR, new String[] {e.getMessage()});
	    }        	
    }    
    
    
    
    
    /**
     *  레포트 정보 조회 
     * 
     * @param param
     * @return List<Report>
     */
    @PostMapping("/getBaseLineReportGraph")
    @ApiOperation(value = "Graph of getBaseLineReport", notes = "This function returns the Graph of getBaseLineReport")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "workspaceId, groupId, evaluationRate")
    })	          
    public BaseResponse<Graph> getBaseLineReportGraph(HttpServletRequest request, @RequestBody Report params) {
        
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getCondition() ==null || "".equals(params.getCondition())){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR, "조건을 선택해주세요(condition is null) "
					+ "1:차수별 대응수준 현황 (통합) "
					+ ", 2:차수별 대응수준 현황 (사업장별)"
					+ ", 3:항목별 대응수준 현황 (통합)"
					+ ", 4:항목별 대응수준 현황 (사업장별)"
					+ ", 5:사업장별 재해발생 통계 "
					+ ", 6:개선.시정명령 조치내역 통계  "
					+ "\")");
		}
		
		if(params.getBaselineId() ==null || params.getBaselineId()==0){				
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
            		new String[] {"baselineId", "차수id"});				
		}
		
		if(params.getWorkplaceId() ==null || params.getWorkplaceId()==0){				
			params.setWorkplaceId((long) 0);	
		}			
		
		try {
			params.setCompanyId(login.getCompanyId());
			params.setWorkplaceId(login.getWorkplaceId());
			params.setRoleCd(login.getRoleCd());			
			Graph result = null;
			if("1".equals(params.getCondition())) {//차수별 대응수전 현황(통합)
				result = mainService.getBaseLineReportGraph(params);
			}else if("2".equals(params.getCondition())) {//차수별 대응수전 현황(사업장별)
				result = mainService.getWorkPlaceReportGraph(params);
			}else if("3".equals(params.getCondition())) {//항목별대응수준 현황(통합)
				result = mainService.getItemsReportGraph(params);
			}else if("4".equals(params.getCondition())) {//항목별대응수준 현황(사업장별)
				result = mainService.getItemsWorkPlaceReportGraph(params);
			}else if("5".equals(params.getCondition())) {//사업장별 재해발생 통계
				result = mainService.getAccidentsPreventionReportGraph(params);
			}else if("6".equals(params.getCondition())) {//개선.시정명령 조치내역 통계
				result = mainService.getImprovemetLawOrderReportGraph(params);
			}
	    	return new BaseResponse<Graph>(result);
    	
	    } catch (Exception e) {
	    	LOGGER.error("error:", e);
	        throw new BaseException(BaseResponseCode.UNKONWN_ERROR, BaseResponseCode.UNKONWN_ERROR.getMessage());
	    }        	
    }  
    
    
    
    
    /**
     *  레포트 정보 조회 
     * 
     * @param param
     * @return List<Report>
     */
    @PostMapping("/getBaseLineReport")
    @ApiOperation(value = "List of getBaseLineReport", notes = "This function returns the list of getBaseLineReport")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "workspaceId, groupId, evaluationRate")
    })	          
    public BaseResponse<List<List<Report>>> getBaseLineReport(HttpServletRequest request, @RequestBody Report params) {
        
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getCondition() ==null || "".equals(params.getCondition())){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR, "조건을 선택해주세요(condition is null) "
					+ "1:차수별 대응수준 현황 (통합) "
					+ ", 2:차수별 대응수준 현황 (사업장별)"
					+ ", 3:항목별 대응수준 현황 (통합)"
					+ ", 4:항목별 대응수준 현황 (사업장별)"
					+ ", 5:사업장별 재해발생 통계 "
					+ ", 6:개선.시정명령 조치내역 통계  "
					+ "\")");
		}
		
		if(params.getBaselineId() ==null || params.getBaselineId()==0){				
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
            		new String[] {"baselineId", "차수id"});				
		}
		
		if(params.getWorkplaceId() ==null || params.getWorkplaceId()==0){				
			params.setWorkplaceId((long) 0);	
		}			
		
		try {
			params.setCompanyId(login.getCompanyId());
			params.setWorkplaceId(login.getWorkplaceId());
			params.setRoleCd(login.getRoleCd());				
			List<List<Report>> result = null;
			if("1".equals(params.getCondition())) {//차수별 대응수전 현황(통합)
				result = mainService.getBaseLineReport(params);
			}else if("2".equals(params.getCondition())) {//차수별 대응수전 현황(사업장별)
				result = mainService.getWorkPlaceReport(params);
			}else if("3".equals(params.getCondition())) {//항목별대응수준 현황(통합)
				//result = mainService.getItemsReport(params);
				result = mainService.getWorkplaceItemsReport(params);
			}else if("4".equals(params.getCondition())) {//항목별대응수준 현황(사업장별)
				result = mainService.getWorkplaceItemsReport(params);
			}else if("5".equals(params.getCondition())) {//사업장별 재해발생 통계
				result = mainService.getAccidentsPreventionReport(params);
			}else if("6".equals(params.getCondition())) {//개선.시정명령 조치내역 통계
				result = mainService.getImprovemetLawOrderReport(params);
			}
	    	return new BaseResponse<List<List<Report>>>(result);
    	
	    } catch (Exception e) {
	    	LOGGER.error("error:", e);
	        throw new BaseException(BaseResponseCode.UNKONWN_ERROR, BaseResponseCode.UNKONWN_ERROR.getMessage());
	    }        	
    }  
    
    
	
    
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
    public BaseResponse<List<List<Report>>> getAccidentsPreventionReport(HttpServletRequest request, @RequestBody Report params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getWorkplaceId() ==null || params.getWorkplaceId()==0){				
			params.setWorkplaceId((long) 0);	
		}		
		
		if(params.getBaselineId() ==null || params.getBaselineId()==0){				
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
            		new String[] {"baselineId", "차수id"});		
		}		
		
		try {
			params.setCompanyId(login.getCompanyId());
			params.setWorkplaceId(login.getWorkplaceId());
			params.setRoleCd(login.getRoleCd());	
			List<List<Report>> result = mainService.getAccidentsPreventionReport(params);
			return new BaseResponse<List<List<Report>>>(result); 	       
        	
        } catch (Exception e) {
        	LOGGER.error("error:", e);
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, BaseResponseCode.UNKONWN_ERROR.getMessage());
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
    public BaseResponse<List<List<Report>>> getImprovemetLawOrderReport(HttpServletRequest request, @RequestBody Report params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getWorkplaceId() ==null || params.getWorkplaceId()==0){				
			params.setWorkplaceId((long) 0);	
		}		
		
		if(params.getBaselineId() ==null || params.getBaselineId()==0){				
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
            		new String[] {"baselineId", "차수id"});				
		}		
		
		try {
			params.setCompanyId(login.getCompanyId());
			params.setWorkplaceId(login.getWorkplaceId());
			params.setRoleCd(login.getRoleCd());	
			List<List<Report>> result = mainService.getImprovemetLawOrderReport(params);
			return new BaseResponse<List<List<Report>>>(result); 	       
        	
        } catch (Exception e) {
        	LOGGER.error("error:", e);
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, BaseResponseCode.UNKONWN_ERROR.getMessage());
        }
    }    
    	
}
