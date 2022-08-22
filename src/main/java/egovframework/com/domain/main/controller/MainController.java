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
import egovframework.com.domain.main.domain.Baseline;
import egovframework.com.domain.main.domain.Company;
import egovframework.com.domain.main.domain.Improvement;
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
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private MainService mainService;
    
    @Autowired
    private LoginService loginService;

    
    /**
     * 스케일 정보 조회
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/getScaleInfo")
    @ApiOperation(value = "getScaleInfo information", notes = "get getScaleInfo information")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "params", value = "")
    })	       
    public BaseResponse<List<Company>> getScaleInfo(HttpServletRequest request) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			//업종 조직 정보 조회
			Company params = new Company();
			List<Company> companyInfo = mainService.getScaleInfo(params);
	        return new BaseResponse<List<Company>>(companyInfo);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
    }    
    
    
    /**
     * 업종 정보 조회
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/getSectorInfo")
    @ApiOperation(value = "getSectorInfo information", notes = "get getSectorInfo information")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "params", value = "")
    })	       
    public BaseResponse<List<Company>> getSectorInfo(HttpServletRequest request) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			//업종 조직 정보 조회
			Company params = new Company();
			List<Company> companyInfo = mainService.getSectorInfo(params);
	        return new BaseResponse<List<Company>>(companyInfo);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
    }    
        
    
    
    /**
     * 메인 top 이미지 및 회사정보
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/getCompanyInfo")
    @ApiOperation(value = "company information", notes = "get company information")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "params", value = "{'companyId' : 2} or {'companyId' : 0}")
    })	      
    public BaseResponse<Company> getCompanyInfo(HttpServletRequest request, @RequestBody Company params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			
			long companyId;
			if(params.getCompanyId() == 0){
				companyId = login.getCompanyId();
			}else {
				companyId = params.getCompanyId();
			}
			
			//회사로고 ,550~300인 , 건설업, 회사명, 목표, 방침문구
        	Company companyInfo = mainService.getCompanyInfo(companyId);
	        return new BaseResponse<Company>(companyInfo);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
    }
    
    
    
    /**
     * 사업장정보
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/getWorkplaceList")
    @ApiOperation(value = "workplace information", notes = "get workplace information")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "params", value = "{companyId : '2'}")
    })	      
    public BaseResponse<List<Workplace>> getWorkplaceList(HttpServletRequest request) {
    	LOGGER.info("selectCompanyInfo");
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			//사업장정보목록
			Workplace params = new Workplace(); 
			params.setCompanyId(login.getCompanyId());
			List<Workplace> workPlaceList = mainService.getWorkplaceList(params);
			return new BaseResponse<List<Workplace>>(workPlaceList);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
    }
       
    
    /**
     * 관리차수정보
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/getRecentBaseline")
    @ApiOperation(value = "company Recent Baseline information", notes = "get company Recent Baseline information")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "params", value = "{companyId : '2'}")
    })	         
    public BaseResponse<Baseline> getRecentBaseline(HttpServletRequest request, @RequestBody Baseline params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			
			long companyId;
			if(params.getCompanyId() == 0){
				companyId = login.getCompanyId();
			}else {
				companyId = params.getCompanyId();
			}
			
			//관리차수
			params.setCompanyId(companyId);
			Baseline baseLineInfo = mainService.getRecentBaseline(params);
			return new BaseResponse<Baseline>(baseLineInfo); 	       
        	
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
    }    
    
        
    
    
    /**
     * 관리차수정보
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/getBaseline")
    @ApiOperation(value = "company Baseline information", notes = "get company Baseline information")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "params", value = "{companyId : '2'}")
    })	         
    public BaseResponse<Baseline> getBaseline(HttpServletRequest request, @RequestBody Baseline params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			
			long companyId;
			if(params.getCompanyId() == 0){
				companyId = login.getCompanyId();
			}else {
				companyId = params.getCompanyId();
			}
			
			//관리차수
			params.setCompanyId(companyId);
			Baseline baseLineInfo = mainService.getBaseline(params);
			return new BaseResponse<Baseline>(baseLineInfo); 	       
        	
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
    }    
    
    
    /**
     * 관리차수정보 LIST
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/getBaselineList")
    @ApiOperation(value = "company workplace information", notes = "get company workplace information")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "params", value = "{companyId : '2'}")
    })	   
    public BaseResponse<List<Baseline>> getBaselineList(HttpServletRequest request, @RequestBody Baseline params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			long companyId;
			if(params.getCompanyId() == 0){
				companyId = login.getCompanyId();
			}else {
				companyId = params.getCompanyId();
			}
			params.setCompanyId(companyId);			
			
			//관리차수
			params.setCompanyId(login.getCompanyId());
			List<Baseline> baseLineInfo = mainService.getBaselineList(params);
			return new BaseResponse<List<Baseline>>(baseLineInfo); 	       
        	
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
    }
	
    
    
    /**
     * 공지사항 hot 목록 및 총 갯수 리턴
     * 
     * @param param
     * @return List<Notice>
     */
    @PostMapping("/getNoticeHotList")
    @ApiOperation(value = "List of hot notices message of the companyId.", notes = "This function returns the list of hot notices message of the companyId.")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "params", value = "{companyId : '2', prevNotice : '1' , prevNotice : '3'}")
    })	    
    public BaseResponse<List<Notice>> getNoticeHotList(HttpServletRequest request, @RequestBody Notice params) {
        
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			long companyId;
			if(params.getCompanyId() == 0){
				companyId = login.getCompanyId();
			}else {
				companyId = params.getCompanyId();
			}
			params.setCompanyId(companyId);
	    	return new BaseResponse<List<Notice>>(mainService.getNoticeHotList(params));
    	
	    } catch (Exception e) {
	        throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
	    }    	
    }       
    
    
    /**
     * 공지사항 목록 및 총 갯수 리턴
     * 
     * @param param
     * @return List<Notice>
     */
    @PostMapping("/getNoticeList")
    @ApiOperation(value = "List of notices message of the companyId.", notes = "This function returns the list of notices message of the companyId.")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "params", value = "{companyId : '2', prevNotice : '1' , prevNotice : '3'}")
    })	    
    public BaseResponse<List<Notice>> getNoticeList(HttpServletRequest request, @RequestBody Notice params) {
        
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			long companyId;
			if(params.getCompanyId() == 0){
				companyId = login.getCompanyId();
			}else {
				companyId = params.getCompanyId();
			}
			params.setCompanyId(companyId);
	    	return new BaseResponse<List<Notice>>(mainService.getNoticeList(params));
    	
	    } catch (Exception e) {
	        throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
	    }    	
    }    

    
    /**
     *  대표이사 안전보건팀장 개선조치사항 
     * 
     * @param param
     * @return List<Notice>
     */
    @PostMapping("/getImprovementList")
    @ApiOperation(value = "List of getImprovementList", notes = "This function returns the list of getImprovement")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "params", value = "{'workplaceId' : '21', 'baselineStart' : '2022-07-19' , 'baselineEnd' : '2022-08-11'}")
    })	          
    public BaseResponse<List<Improvement>> getImprovementList(HttpServletRequest request, @RequestBody Improvement params) {
        
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			params.setRole(login.getRoleCd());
			
			Long workPlaceId;
			if(params.getWorkplaceId() == 0){
				workPlaceId = login.getWorkplaceId();
			}else {
				workPlaceId = params.getWorkplaceId();
			}
			params.setWorkplaceId(workPlaceId);	
	    	return new BaseResponse<List<Improvement>>(mainService.getImprovementList(params));
    	
	    } catch (Exception e) {
	        throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
	    }        	
    }    
    
    
    /**
     * 재해발생 방지대책 및 이행현황
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/getAccidentsPrevention")
    @ApiOperation(value = "AccidentsPrevention information data", notes = "get AccidentsPrevention information data")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "params", value = "{'workplaceId' : '23', 'baselineStart' : '2022-07-01' , 'baselineEnd' : '2022-08-30'}")
    })	       
    public BaseResponse<Amount> getAccidentsPrevention(HttpServletRequest request, @RequestBody Amount params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			
			Long workPlaceId;
			if(params.getWorkplaceId() == 0){
				workPlaceId = login.getWorkplaceId();
			}else {
				workPlaceId = params.getWorkplaceId();
			}
			params.setWorkplaceId(workPlaceId);			
			
			Amount result = mainService.getAccidentsPrevention(params);
			return new BaseResponse<Amount>(result); 	       
        	
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
    }   
    
    
    
    /**
     * 관계법령에 따른 개선 시정명령조치
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/getImprovemetLawOrder")
    @ApiOperation(value = "getImprovemetLawOrder information data", notes = "get getImprovemetLawOrder information data")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "params", value = "{'workplaceId' : '23', 'baselineStart' : '2022-07-01' , 'baselineEnd' : '2022-08-30'}")
    })	       
    public BaseResponse<Amount> getImprovemetLawOrder(HttpServletRequest request, @RequestBody Amount params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			Long workPlaceId;
			if(params.getWorkplaceId() == 0){
				workPlaceId = login.getWorkplaceId();
			}else {
				workPlaceId = params.getWorkplaceId();
			}
			params.setWorkplaceId(workPlaceId);						
			
			Amount result = mainService.getImprovemetLawOrder(params);
			return new BaseResponse<Amount>(result); 	       
        	
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
    }       
    
    
    
    
    
    /**
     * 관리차수 day 확인
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/getDayInfo")
    @ApiOperation(value = "baseline information day", notes = "get baseline information day")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "params", value = "{baselineStart : '2022-08-16'}")
    })	       
    public BaseResponse<Baseline> getDayInfo(HttpServletRequest request, @RequestBody Baseline params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			
			Baseline result = mainService.getDayInfo(params);
			return new BaseResponse<Baseline>(result); 	       
        	
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
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
    public BaseResponse<List<Amount>> getAccidentsPreventionReport(HttpServletRequest request, @RequestBody Amount params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			
			Long workPlaceId;
			if(params.getWorkplaceId() == 0){
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
			if(params.getWorkplaceId() == 0){
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
