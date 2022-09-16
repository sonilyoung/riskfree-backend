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

import egovframework.com.domain.company.parameter.CommonSearchParameter;
import egovframework.com.domain.main.domain.AccidentsAmount;
import egovframework.com.domain.main.domain.Amount;
import egovframework.com.domain.main.domain.Baseline;
import egovframework.com.domain.main.domain.Company;
import egovframework.com.domain.main.domain.EssentialInfo;
import egovframework.com.domain.main.domain.Improvement;
import egovframework.com.domain.main.domain.MainExcelData;
import egovframework.com.domain.main.domain.Notice;
import egovframework.com.domain.main.domain.ParamDutyCyle;
import egovframework.com.domain.main.domain.ParamMainExcelData;
import egovframework.com.domain.main.domain.ParamSafeWork;
import egovframework.com.domain.main.domain.PramAmount;
import egovframework.com.domain.main.domain.SafeWork;
import egovframework.com.domain.main.domain.Setting;
import egovframework.com.domain.main.domain.Weather;
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
     * 로그인 정보 조회
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/getLoginInfo")
    @ApiOperation(value = "getLoginInfo information", notes = "get login information")
    public BaseResponse<Login> getLoginInfo(HttpServletRequest request) {
		try {
			Login login = loginService.getLoginInfo(request);
	        return new BaseResponse<Login>(login);
        } catch (Exception e) {
        	throw new BaseException(BaseResponseCode.AUTH_FAIL);
        }
    }        
    
    
    /**
     * 스케일 정보 조회
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/getScaleInfo")
    @ApiOperation(value = "getScaleInfo information", notes = "get getScaleInfo information")
    public BaseResponse<List<Company>> getScaleInfo(HttpServletRequest request) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			//업종 조직 정보 조회
			Company params = new Company();
			params.setCompanyId(login.getCompanyId());
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
    public BaseResponse<List<Company>> getSectorInfo(HttpServletRequest request) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			//업종 조직 정보 조회
			Company params = new Company();
			params.setCompanyId(login.getCompanyId());
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
    	@ApiImplicitParam(name = "response", value = "shGoal,logoImg,missionStatements,scale,sector,contractStartDate,contractEndDate")
    })	           
    public BaseResponse<Company> getCompanyInfo(HttpServletRequest request, @RequestBody Company params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			//회사로고 ,550~300인 , 건설업, 회사명, 목표, 방침문구
			params.setCompanyId(login.getCompanyId());
        	Company companyInfo = mainService.getCompanyInfo(params);
	        return new BaseResponse<Company>(companyInfo);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
    }
    
    
    
    /**
     * 사업장정보 (대표이사, 관리자에 따라서 목록 변경 ROLE_CD 001 대표이사)
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/getWorkplaceList")
    @ApiOperation(value = "workplace information", notes = "get workplace information")
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
			params.setWorkplaceId(login.getWorkplaceId());
			params.setRoleCd(login.getRoleCd());
			List<Workplace> workPlaceList = mainService.getWorkplaceList(params);
			return new BaseResponse<List<Workplace>>(workPlaceList);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
    }
       
    
    /**
     * 최신 관리차수 조회 버튼 사용유무 확인 IS_CLOSE
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/getRecentBaseline")
    @ApiOperation(value = "company Recent Baseline information", notes = "request:")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "companyId", value = "2", required = true, type = "long")
    })	
    public BaseResponse<Baseline> getRecentBaseline(HttpServletRequest request, @RequestBody Baseline params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getCompanyId() ==null || params.getCompanyId()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}		
		
		try {
			
			//관리차수
			params.setCompanyId(params.getCompanyId());
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
    	@ApiImplicitParam(name = "response", value = "baselineId,baselineName,baselineStart,baselineEnd")
    })	    
    public BaseResponse<Baseline> getBaseline(HttpServletRequest request, @RequestBody Baseline params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			//관리차수
			params.setCompanyId(login.getCompanyId());
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
    @ApiOperation(value = "company workplaceList information", notes = "get company workplaceList information")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", required = false)
    })	        
    public BaseResponse<List<Baseline>> getBaselineList(HttpServletRequest request, @RequestBody Baseline params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			
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
    	@ApiImplicitParam(name = "response", value = "noticeId,title,importCd,insertDate,attachId")
    })	    
    public BaseResponse<List<Notice>> getNoticeHotList(HttpServletRequest request, @RequestBody Notice params) {
        
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			params.setCompanyId(login.getCompanyId());
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
    	@ApiImplicitParam(name = "response", value = "noticeId,title,newYn,importCd,insertDate")
    })	    
    public BaseResponse<List<Notice>> getNoticeList(HttpServletRequest request, @RequestBody Notice params) {
        
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		try {
			params.setCompanyId(login.getCompanyId());
	    	return new BaseResponse<List<Notice>>(mainService.getNoticeList(params));
    	
	    } catch (Exception e) {
	        throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
	    }    	
    }    

    
    /**
     *  대표이사 개선조치사항 
     * 
     * @param param
     * @return List<Notice>
     */
    @PostMapping("/getImprovementList")
    @ApiOperation(value = "List of getImprovementList", notes = "This function returns the list of getImprovement")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "instruction, progress, complete")
    })	          
    public BaseResponse<List<Improvement>> getImprovementList(HttpServletRequest request, @RequestBody Improvement params) {
        
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getWorkplaceId() ==null || params.getWorkplaceId()==0){				
			params.setWorkplaceId((long) 0);	
		}					
		
		try {
	    	return new BaseResponse<List<Improvement>>(mainService.getImprovementList(params));
    	
	    } catch (Exception e) {
	        throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
	    }        	
    }    
    
    
    
    /**
     *  안전보건팀장 개선조치사항
     * 
     * @param param
     * @return List<Notice>
     */
    @PostMapping("/getLeaderImprovementList")
    @ApiOperation(value = "List of getLeaderImprovementList", notes = "This function returns the list of getLeaderImprovementList")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "instruction, progress, complete")
    })	          
    public BaseResponse<List<Improvement>> getLeaderImprovementList(HttpServletRequest request, @RequestBody Improvement params) {
        
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getWorkplaceId() ==null || params.getWorkplaceId()==0){				
			params.setWorkplaceId((long) 0);	
		}		
		
		if(params.getBaselineId() ==null || params.getBaselineId()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}					
		
		try {
	    	return new BaseResponse<List<Improvement>>(mainService.getLeaderImprovementList(params));
    	
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
    public BaseResponse<Amount> getAccidentsPrevention(HttpServletRequest request, @RequestBody Amount params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getWorkplaceId() ==null || params.getWorkplaceId()==0){				
			params.setWorkplaceId((long) 0);	
		}		
		
		if(params.getBaselineId() ==null || params.getBaselineId()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}					
		
		try {
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
    	@ApiImplicitParam(name = "response", value = "improvemetRate")
    })	       
    public BaseResponse<Amount> getImprovemetLawOrder(HttpServletRequest request, @RequestBody Amount params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getWorkplaceId() ==null || params.getWorkplaceId()==0){				
			params.setWorkplaceId((long) 0);	
		}		
		
		if(params.getBaselineId() ==null || params.getBaselineId()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}			
		
		try {
			Amount result = mainService.getImprovemetLawOrder(params);
			return new BaseResponse<Amount>(result); 	       
        	
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
    }       
    
    
    
    /**
     * 관계법령에 의무이행의 관리조치 %
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/getRelatedLawRate")
    @ApiOperation(value = "getRelatedLawRate information data", notes = "get RelatedRawRate information data")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "relatedLawRate")
    })	       
    public BaseResponse<Amount> getRelatedLawRate(HttpServletRequest request, @RequestBody PramAmount params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getWorkplaceId() ==null || params.getWorkplaceId()==0){				
			params.setWorkplaceId((long) 0);	
		}		
		
		if(params.getBaselineId() ==null || params.getBaselineId()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}			
		
		try {
			Amount result = mainService.getRelatedLawRate(params);
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
    	@ApiImplicitParam(name = "response", value = "{baselineStart : '2022-08-16'}")
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
     * 사업장 의무조치내역 업로드 
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/insertEssentialDutyUser")
    @ApiOperation(value = "insert EssentialDutyUser information data", notes = "insert EssentialDutyUser information data")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "{workplaceId: '2',baselineId: '2'}")
    })	       
    public BaseResponse<Integer> insertEssentialDutyUser(HttpServletRequest request, @RequestBody MainExcelData params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}

		if(params.getWorkplaceId() ==null || params.getWorkplaceId()==0){				
			params.setWorkplaceId((long) 0);	
		}
		
		if(params.getBaselineId() ==null || params.getBaselineId()==0){
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}
				
		
		if(params.getBaselineStart() ==null || "".equals(params.getBaselineStart())){
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}
		
		if(params.getBaselineEnd() ==null || "".equals(params.getBaselineEnd())){
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}		
		
		int result = 0;
		try {
			
			params.setCompanyId(login.getCompanyId());
			result = mainService.insertEssentialDutyUser(params);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
		
		if(result==1) {
			return new BaseResponse<Integer>(BaseResponseCode.SAVE_SUCCESS);
		}else {
			return new BaseResponse<Integer>(BaseResponseCode.SAVE_ERROR);
		}		
    }      
    
    
    

    
    
	/**
     * 점검서류등목록 점수생성 
     * 
     * @param parameter
     * @return Company
     */
	@PostMapping("/updateScore")
	@ApiOperation(value = "Update Score",notes = "Update Score value is format 10;7;5")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "201")
    })	 	
	public BaseResponse<Integer> updateScore(HttpServletRequest request, @RequestBody ParamDutyCyle params) {
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getEvaluation() ==null || "".equals(params.getEvaluation())){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}	
		
		if(params.getArticleNo() ==null || params.getArticleNo()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}		
        
        try {
        	mainService.updateScore(params);    	
        	return new BaseResponse<Integer>(BaseResponseCode.SAVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});            
        }
		
    }
	
	/**
     * 점검서류등목록 파일생성 
     * 
     * @param parameter
     * @return Company
     */
	@PostMapping("/updateDocumentFileId")
	@ApiOperation(value = "Update Document FileId",notes = "Update DocumentId fileId value is format 1")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "201")
    })	 	
	public BaseResponse<Integer> updateDocumentFileId(HttpServletRequest request, @RequestBody ParamDutyCyle params) {
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getFileId() ==null || "".equals(params.getFileId())){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}			
		
        try {
        	mainService.updateDocumentFileId(params);    	
            return new BaseResponse<Integer>(BaseResponseCode.SAVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(BaseResponseCode.SAVE_ERROR,
                    new String[] {"등록 중에 오류가 발행했습니다. (" + e.getMessage() + ")"});
        }
		
    }	
	
	
	/**
     * 관계법령 체크
     * 
     * @param parameter
     * @return Company
     */
	@PostMapping("/updateRelatedArticle")
	@ApiOperation(value = "Update RelatedArticle",notes = "Update RelatedArticle value is format 10;7;5")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "201")
    })	 	
	public BaseResponse<Integer> updateRelatedArticle(HttpServletRequest request, @RequestBody ParamDutyCyle params) {
		
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
        
		
		if(params.getArticleNo() ==null || params.getArticleNo()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}			
		
		if(params.getManagerChecked() ==null || "".equals(params.getManagerChecked())){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}	
		
        try {
        	mainService.updateRelatedArticle(params);    	
            return new BaseResponse<Integer>(BaseResponseCode.SAVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(BaseResponseCode.SAVE_ERROR,
                    new String[] {"등록 중에 오류가 발행했습니다. (" + e.getMessage() + ")"});
        }
		
    }	
	
		
	
    /**
     * 필수 의무조치 내역 시행율 
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/getEssentialRate")
    @ApiOperation(value = "EssentialRate information data", notes = "get EssentialRate information data")
    public BaseResponse<EssentialInfo> getEssentialRate(HttpServletRequest request, @RequestBody PramAmount params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getBaselineId() ==null || params.getBaselineId()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}	
		
		//if(!"001".equals(login.getRoleCd())) {
			if(params.getWorkplaceId() ==null || params.getWorkplaceId()==0){				
				params.setWorkplaceId((long) 0);	
			}				
		//}
		
		try {
			EssentialInfo result = mainService.getEssentialRate(params);
			return new BaseResponse<EssentialInfo>(result); 	       
        	
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
    }       	
	
    
    
    /**
     *   의무조치별 상세 점검 항목  
     * 
     * @param param
     * @return List<MainExcelData>
     */
    @PostMapping("/getDutyDetailList")
    @ApiOperation(value = "List of DutyDetailList", notes = "This function returns the list of DutyDetailList")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "articleNo, detailedItems")
    })	     
    public BaseResponse<List<MainExcelData>> getDutyDetailList(HttpServletRequest request, @RequestBody ParamMainExcelData params) {
        
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getGroupId() ==null || params.getGroupId()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}			
		
		if(params.getBaselineId() ==null || params.getBaselineId()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}	
		
		try {
	    	return new BaseResponse<List<MainExcelData>>(mainService.getDutyDetailList(params));
    	
	    } catch (Exception e) {
	        throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
	    }        	
    }    
    
	
    
    /**
     *  점검서류 등 목록
     * 
     * @param param
     * @return List<MainExcelData>
     */
    @PostMapping("/getInspectiondocs")
    @ApiOperation(value = "List of DutyDetailList", notes = "This function returns the list of DutyDetailList")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "articleNo, shGoal, fileId")
    })	          
    public BaseResponse<List<MainExcelData>> getInspectiondocs(HttpServletRequest request, @RequestBody MainExcelData params) {
        
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getArticleNo() ==null || params.getArticleNo()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}		
		
		try {
	    	return new BaseResponse<List<MainExcelData>>(mainService.getInspectiondocs(params));
    	
	    } catch (Exception e) {
	        throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
	    }        	
    }    
    
	

    

    
    /**
     *  이행주기
     * 
     * @param param
     * @return List<MainExcelData>
     */
    @PostMapping("/getDutyCyle")
    @ApiOperation(value = "List of DutyDetailList", notes = "This function returns the list of DutyDetailList")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "articleNo, dutyCycle")
    })	          
    public BaseResponse<List<MainExcelData>> getDutyCyle(HttpServletRequest request, @RequestBody ParamDutyCyle params) {
        
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getArticleNo() ==null || params.getArticleNo()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}		
		
		try {
	    	return new BaseResponse<List<MainExcelData>>(mainService.getDutyCyle(params));
    	
	    } catch (Exception e) {
	        throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
	    }        	
    }    
    
	


	    
    /**
     *  준수대상
     * 
     * @param param
     * @return List<MainExcelData>
     */
    @PostMapping("/getDutyAssigned")
    @ApiOperation(value = "List of DutyDetailList", notes = "This function returns the list of DutyDetailList")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "articleNo,dutyAssigned ")
    })	          
    public BaseResponse<List<MainExcelData>> getDutyAssigned(HttpServletRequest request, @RequestBody ParamDutyCyle params) {
        
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}

		if(params.getArticleNo() ==null || params.getArticleNo()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}		
		
		try {
	    	return new BaseResponse<List<MainExcelData>>(mainService.getDutyAssigned(params));
    	
	    } catch (Exception e) {
	        throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
	    }        	
    }    
    
	
	
    
    /**
     *   관계법령
     * 
     * @param param
     * @return List<MainExcelData>
     */
    @PostMapping("/getRelatedArticle")
    @ApiOperation(value = "List of RelatedArticle", notes = "This function returns the list of RelatedArticle")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "articleNo,relatedArticle,managerChecked")
    })	          
    public BaseResponse<List<MainExcelData>> getRelatedArticle(HttpServletRequest request, @RequestBody ParamDutyCyle params) {
        
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getArticleNo() ==null || params.getArticleNo()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}		
		
		try {
	    	return new BaseResponse<List<MainExcelData>>(mainService.getRelatedArticle(params));
    	
	    } catch (Exception e) {
	        throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
	    }        	
    }    
    
	

	    
    /**
     *  현장작동성 평가 작성 지침서 
     * 
     * @param param
     * @return List<MainExcelData>
     */
    @PostMapping("/getGuideLine")
    @ApiOperation(value = "List of GuideLine", notes = "This function returns the list of GuideLine")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "articleNo,guideline")
    })	          
    public BaseResponse<List<MainExcelData>> getGuideLine(HttpServletRequest request, @RequestBody ParamDutyCyle params) {
        
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getArticleNo() ==null || params.getArticleNo()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}			
		
		try {
	    	return new BaseResponse<List<MainExcelData>>(mainService.getGuideLine(params));
    	
	    } catch (Exception e) {
	        throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
	    }        	
    }    
    
	
    
    
    /**
     *   산업재해 누적 집계 
     * 
     * @param param
     * @return List<AccidentsAmount>
     */
    @PostMapping("/getAccidentTotal")
    @ApiOperation(value = "List of AccidentTotal", notes = "This function returns the list of AccidentTotal")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "{\"workplaceId\" : \"4\",\"baselineId\" : \"1\"}")
    })	          
    public BaseResponse<AccidentsAmount> getAccidentTotal(HttpServletRequest request, @RequestBody AccidentsAmount params) {
        
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getWorkplaceId() ==null || params.getWorkplaceId()==0){				
			params.setWorkplaceId((long) 0);	
		}		
		
		if(params.getBaselineId() ==null || params.getBaselineId()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}			
		
		try {
			
			AccidentsAmount result = mainService.getAccidentTotal(params);
	    	return new BaseResponse<AccidentsAmount>(result);
    	
	    } catch (Exception e) {
	        throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
	    }        	
    }      
	
    
    
    /**
     * 안전작업허가 공사내역
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/getSafeWorkHistoryList")
    @ApiOperation(value = "getSafeWorkHistoryList information data", notes = "get SafeWorkHistoryList information data")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "nowDate,nowDay,fire,closeness ,blackout,excavation,radiation,Sue")
    })	       
    public BaseResponse<SafeWork> getSafeWorkHistoryList(HttpServletRequest request, @RequestBody ParamSafeWork params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getWorkplaceId() ==null || params.getWorkplaceId()==0){				
			params.setWorkplaceId((long) 0);	
		}		
		
		if(params.getBaselineId() ==null || params.getBaselineId()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}
		
		//관리차수
    	Baseline bl = new Baseline();
    	bl.setCompanyId(login.getCompanyId());
    	bl.setBaselineId(params.getBaselineId());
		Baseline baseLineInfo = mainService.getBaseline(bl);
		if(baseLineInfo==null){				
			throw new BaseException(BaseResponseCode.DATA_IS_NULL);	
		}		
		
		try {
			params.setBaselineId(baseLineInfo.getBaselineId());
			params.setBaselineStart(baseLineInfo.getBaselineStart());
			params.setBaselineEnd(baseLineInfo.getBaselineEnd());
			
			
			SafeWork result = mainService.getSafeWorkHistoryList(params);
			return new BaseResponse<SafeWork>(result); 	       
        	
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
    }        
	    

    
    
	/**
     * 사용자 정보 설정 등록
     * 
     * @param parameter
     * @return Company
     */
	@PostMapping("/updateUserCompany")
	@ApiOperation(value = "",notes = "get SafetyFile cont")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "201 : update yes , 0800: udpate fail")
    })	 	
	public BaseResponse<Integer> updateUserCompany(HttpServletRequest request, @RequestBody Setting params) {
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getAttachFileId() ==null || params.getAttachFileId()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}		
		
		if(params.getSafetyGoal() ==null || "".equals(params.getSafetyGoal())){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}		
		
		if(params.getMissionStatements() ==null || "".equals(params.getMissionStatements())){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}				
		
        try {
        	params.setCompanyId(login.getCompanyId());
        	params.setUpdateId(login.getUserId());
        	params.setWorkplaceId(login.getWorkplaceId());
        	mainService.updateUserCompany(params);
        	return new BaseResponse<Integer>(BaseResponseCode.SAVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(BaseResponseCode.SAVE_ERROR, new String[] {e.getMessage()});            
        }
        
    }    
    
    
    
	/**
     * 안전작업허가서 양식 유무 확인
     * 
     * @param parameter
     * @return Company
     */
	@PostMapping("/getSafetyFileCnt")
	@ApiOperation(value = "get SafetyFile count",notes = "get SafetyFile count")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "1 : file yes , 0: file no")
    })	 	
	public BaseResponse<Integer> getSafetyFileCnt(HttpServletRequest request, @RequestBody Setting params) {
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
        try {
        	params.setWorkplaceId(login.getWorkplaceId());
        	return new BaseResponse<Integer>(mainService.getSafetyFileCnt(params));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});            
        }
		
    }

	
    
	/**
     * 안전작업허가서 양식 유무 확인
     * 
     * @param parameter
     * @return Company
     */
	@PostMapping("/updateSafetyFile")
	@ApiOperation(value = "get SafetyFile count",notes = "get SafetyFile count")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "1 : file yes , 0: file no")
    })	 	
	public BaseResponse<Integer> updateSafetyFile(HttpServletRequest request, @RequestBody Setting params) {
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getAttachFileId() ==null || params.getAttachFileId()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}					
		
        try {
        	params.setUpdateId(login.getUserId());
        	params.setWorkplaceId(login.getWorkplaceId());
        	mainService.updateSafetyFile(params);
        	return new BaseResponse<Integer>(BaseResponseCode.SAVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});            
        }
		
    }	
    	
	
	
	/**
     * 관리차수 등록
     * 
     * @param parameter
     * @return 
     */
	@PostMapping("/insertBaseline")
	@ApiOperation(value = "Add a new baseline",notes = "This function adds a new baseline")
	public BaseResponse<Integer> insertBaseline(HttpServletRequest request, @RequestBody Setting params) {
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(params.getBaselineName() ==null || "".equals(params.getBaselineName())){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}	
		
		if(params.getBaselineStart() ==null || "".equals(params.getBaselineStart())){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}					
		
		if(params.getBaselineEnd() ==null || "".equals(params.getBaselineEnd())){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}					
		
		try {
			params.setCompanyId(login.getCompanyId());
			params.setInsertId(login.getUserId());
			params.setUpdateId(login.getUserId());
			mainService.insertBaseline(params);
			return new BaseResponse<Integer>(BaseResponseCode.SAVE_SUCCESS);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
		
    }	
	
	
    /**
     * 관리차수 복사 
     * 
     * @param param
     * @return Company
     * @throws Exception 
     */
    @PostMapping("/insertBaseLineDataCopy")
    @ApiOperation(value = "insert baseline data copy", notes = "insert baseline data copy")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "{workplaceId: '2',baselineId: '2'}")
    })	       
    public BaseResponse<Integer> insertBaseLineDataCopy(HttpServletRequest request, @RequestBody MainExcelData params) throws Exception {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		//타겟 baseline
		if(params.getTargetBaselineId() ==null || params.getTargetBaselineId()==0){
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);
		}
		
		//복사할 baseline
		if(params.getBaselineId() ==null || params.getBaselineId()==0){
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}
				
		
		params.setWorkplaceId(login.getWorkplaceId());
		params.setCompanyId(login.getCompanyId());
		params.setInsertId(login.getUserId());
		return mainService.insertBaseLineDataCopy(params);
    }      
    	
    
	/**
     * 관리차수 마감
     * 
     * @apiNote 현재 로그인한 사용자의 ID를 받아와야함
     * @param companyId, baselineId
     * @return 
     */
	@PostMapping("/close")
	@ApiOperation(value = "Delete a baseline",notes = "This function delete a baseline")
	public BaseResponse<Boolean> closeBaseline(HttpServletRequest request, @RequestBody CommonSearchParameter params) {

		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}

		//관리차수
    	Baseline bl = new Baseline();
    	bl.setCompanyId(login.getCompanyId());
		Baseline baseLineInfo = mainService.getRecentBaseline(bl);
		if(baseLineInfo==null){				
			throw new BaseException(BaseResponseCode.DATA_IS_NULL);	
		}		
		
		try {
			mainService.closeBaseline(login.getCompanyId(), baseLineInfo.getBaselineId(), login.getUserId());
        	return new BaseResponse<Boolean>(true);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
		
    }    
	
	
    
    /**
     * 사업장 의무조치내역 최신화
     * (안전보건관리체계의 구축 및 이행 항목 업데이트)
     * @param param
     * @return Company
     */
    @PostMapping("/insertBaseLineDataUpdate")
    @ApiOperation(value = "insert EssentialDutyUser update information data", notes = "insert EssentialDutyUser update information data")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response", value = "{workplaceId: '2',baselineId: '2'}")
    })	       
    public BaseResponse<Integer> insertBaseLineDataUpdate(HttpServletRequest request, @RequestBody MainExcelData params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}

		//관리차수
    	Baseline bl = new Baseline();
    	bl.setCompanyId(login.getCompanyId());
		Baseline baseLineInfo = mainService.getRecentBaseline(bl);
		if(baseLineInfo==null){				
			throw new BaseException(BaseResponseCode.DATA_IS_NULL);	
		}	
		
		int result = 0;
		try {
			params.setBaselineId(baseLineInfo.getBaselineId());
			params.setCompanyId(login.getCompanyId());
			params.setWorkplaceId(login.getWorkplaceId());
			result = mainService.insertBaseLineDataUpdate(params);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
		
		if(result==1) {
			return new BaseResponse<Integer>(BaseResponseCode.SAVE_SUCCESS);
		}else {
			return new BaseResponse<Integer>(BaseResponseCode.SAVE_ERROR);
		}		
    }   
    
    
    /**
     * 날씨조회 
     * 
     * @param param
     * @return 
    */   
    @PostMapping("/getWeather")
    @ApiOperation(value = "weather information", notes = "weather information")
    public BaseResponse<Weather> getWeather(HttpServletRequest request, @RequestBody Weather params) {
        
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		
		if(params.getLatitude() ==null || params.getLatitude()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}	
		
		if(params.getLongitude() ==null || params.getLongitude()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}			
		
		try {
			Weather w = new Weather();
			w.setTemperature("26");
			w.setAddress("구로디지털단지");
			w.setWeatherImgUrl("/home/jun/apps/riskfree/webapps/static_file/fine.png");
			return new BaseResponse<Weather>(w);			
	    } catch (Exception e) {
	        throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
	    }        	
    }        
    
	/**
     * 안전보건관리체계의 구축 및 이행 항목 데이터 확인 
     * 
     * @param parameter
     * @return MainExcelData
     
	@PostMapping("/getBaseLineDataCnt")
	@ApiOperation(value = "get BaseLineData count",notes = "get BaseLineData count")
	public BaseResponse<Integer> getBaseLineDataCnt(HttpServletRequest request, @RequestBody MainExcelData params) {
		Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		//관리차수
    	Baseline bl = new Baseline();
    	bl.setCompanyId(login.getCompanyId());
		Baseline baseLineInfo = mainService.getRecentBaseline(bl);
		if(baseLineInfo==null){				
			throw new BaseException(BaseResponseCode.DATA_IS_NULL);	
		}		
		
        try {
        	params.setWorkplaceId(login.getWorkplaceId());
        	params.setBaselineId(baseLineInfo.getBaselineId());
        	return new BaseResponse<Integer>(mainService.getBaseLineDataCnt(params));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});            
        }
		
    }*/    
    	
}
