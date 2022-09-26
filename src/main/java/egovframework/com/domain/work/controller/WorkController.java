package egovframework.com.domain.work.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.domain.portal.logn.service.LoginService;
import egovframework.com.domain.work.domain.Work;
import egovframework.com.domain.work.service.WorkService;
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
@RequestMapping("/work")
@Api(tags = "SafeWork Management API")
public class WorkController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkController.class);

    @Autowired
    private WorkService workService;
    
    @Autowired
    private LoginService loginService;
    
    
    
    /**
     * 안전작업허가 공사현황관리
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/getSafeWork")
    @ApiOperation(value = "getSafeWork information", notes = "get SafeWork information")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "companyId,workplaceId,workplaceName,insertDate,userName,fire,closeness,blackout,excavation,radiation,sue,heavy,totalCount")
    })	       
    public BaseResponse<List<Work>> getSafeWork(HttpServletRequest request
    		, @RequestBody Work params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		params.setCompanyId(login.getCompanyId());
		try {
	    	return new BaseResponse<List<Work>>(workService.getSafeWork(params));
    	
	    } catch (Exception e) {
	    	LOGGER.error("error:", e);
	        throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
	    }  
    }        
    
    
    /**
     * 안전작업허가 공사현황관리 파일조회 (상단) 
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/getSafeWorkFileTopInfo")
    @ApiOperation(value = "getSafeWorkFileTopInfo information", notes = "get SafeWorkFileTopInfo information")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "attachId,fileName")
    })	       
    public BaseResponse<Work> getSafeWorkFileTopInfo(HttpServletRequest request
    		, @RequestBody Work params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getNoticeId() ==null || params.getNoticeId()==0){				
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
            		new String[] {"noticeId", "안전작업허가 공사현황관리 id"});
		}		
		
		try {
	    	return new BaseResponse<Work>(workService.getSafeWorkFileTopInfo(params));
    	
	    } catch (Exception e) {
	    	LOGGER.error("error:", e);
	        throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
	    }  
    }   
    	    
   
    
    /**
     * 안전작업허가 공사현황관리 파일조회 
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/getSafeWorkFile")
    @ApiOperation(value = "getSafeWorkFile information", notes = "get SafeWorkFile information")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "attachId,fileName")
    })	       
    public BaseResponse<List<Work>> getSafeWorkFile(HttpServletRequest request
    		, @RequestBody Work params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getWorkplaceId() ==null || params.getWorkplaceId()==0){				
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"workplaceId", "사업장id"});			
		}
		
		if(params.getConstructionType() ==null || "".equals(params.getConstructionType())){				
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"constructionType", "공사종류"});			
		}		
		
		params.setCompanyId(login.getCompanyId());
		try {
	    	return new BaseResponse<List<Work>>(workService.getSafeWorkFile(params));
    	
	    } catch (Exception e) {
	    	LOGGER.error("error:", e);
	        throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
	    }  
    }   
    
    /**
     * 안전작업허가 공사현황관리 데이터 삭제 
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/deleteSafeWork")
    @ApiOperation(value = "deleteSafeWork information", notes = "deleteSafeWork information")
    public BaseResponse<Integer> deleteSafeWork(HttpServletRequest request
    		, @RequestBody Work params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getNoticeId() ==null || params.getNoticeId()==0){				
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"noticeId", "안전작업허가 공사현황관리 id"});			
		}		
		int result = 0;
		try {
	    	result = workService.deleteSafeWork(params);
	    } catch (Exception e) {
	    	LOGGER.error("error:", e);
	        throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
	    }  
		
		if(result==0) {
			return new BaseResponse<Integer>(BaseResponseCode.DELETE_ERROR);
		}else {
			return new BaseResponse<Integer>(BaseResponseCode.SUCCESS);
		}			
    }   
    
    	
}
