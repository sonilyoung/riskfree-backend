package egovframework.com.domain.relatedlaw.controller;

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
import egovframework.com.domain.relatedlaw.domain.DutyBotton;
import egovframework.com.domain.relatedlaw.domain.RelatedLaw;
import egovframework.com.domain.relatedlaw.service.RelatedLawService;
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
@RequestMapping("/relatedlaw")
@Api(tags = "RelatedLaw API")
public class RelatedLawController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RelatedLawController.class);

    @Autowired
    private RelatedLawService relatedLawService;
    
    @Autowired
    private LoginService loginService;

    
    /**
     * 사업장 의무조치내역 업로드 
     * 
     * @param param
     * @return DutyBotton
     */
    @PostMapping("/insertDutyButton")
    @ApiOperation(value = "insert DutyBotton information data", notes = "insert DutyBotton information data")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "params", value = "{lawName: '화평법'}")
    })	       
    public BaseResponse<Integer> insertDutyButton(HttpServletRequest request, @RequestBody DutyBotton params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		int result = 0;
		try {
			params.setInsertId(login.getUserId());
			params.setCompanyId(login.getCompanyId());
			params.setWorkplaceId(login.getWorkplaceId());						
			result = relatedLawService.insertDutyButton(params);
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
     * 관계법령에 의무이행의 관리상의 조치목록조회
     * 
     * @param param
     * @return RelatedLaw
     */
    @PostMapping("/getRelatedRaw")
    @ApiOperation(value = "RelatedRaw information", notes = "RelatedRaw information")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response"
    			, value = "dutyImproveId,companyId,workplaceId,baselineId,lawId,relatedArticle,articleItem,seriousAccdntDecree,violatedArticle,violatedActivity,violationDetail1,violationDetail2,baseArticle,stPenalty1,stPenalty2,stPenalty3,acctionCn")
    })	
    public BaseResponse<List<RelatedLaw>> getRelatedRaw(HttpServletRequest request, @RequestBody RelatedLaw params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}

		
		if(params.getLawId() ==null || params.getLawId()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}
				
		try {

			List<RelatedLaw> result = relatedLawService.getRelatedRaw(params);
			return new BaseResponse<List<RelatedLaw>>(result); 	       
        	
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
    }    
     
    
    /**
     * 관계법령 의무이행 의무이행 메세지 적용
     * 
     * @param param
     * @return RelatedLaw
     */
    @PostMapping("/updateRelatedRaw")
    @ApiOperation(value = "update RelatedRaw", notes = "update RelatedRaw")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response"
    			, value = "0201")
    })	
    public BaseResponse<Integer> updateRelatedRaw(HttpServletRequest request, @RequestBody RelatedLaw params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		
		if(params.getUpdateList().size() < 1){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}		
		
		int result = 0;
		try {
			result = relatedLawService.updateRelatedRaw(params);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.SAVE_ERROR, new String[] {e.getMessage()});
        }
		
		if(result==1) {
			return new BaseResponse<Integer>(BaseResponseCode.SAVE_SUCCESS);
		}else {
			return new BaseResponse<Integer>(BaseResponseCode.SAVE_ERROR);
		} 		
    }      
    
    
    
    /**
     * 관계법령에 버튼 가져오기
     * 
     * @param param
     * @return RelatedLaw
     */
    @PostMapping("/getRelatedRawButton")
    @ApiOperation(value = "get RelatedRawButton information", notes = "get RelatedRawButton information")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "response"
    			, value = "buttonId,lawName")
    })	
    public BaseResponse<List<DutyBotton>> getRelatedRawButton(HttpServletRequest request, @RequestBody DutyBotton params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
				
		params.setCompanyId(login.getCompanyId());
		params.setWorkplaceId(login.getWorkplaceId());
		try {

			List<DutyBotton> result = relatedLawService.getRelatedRawButton(params);
			return new BaseResponse<List<DutyBotton>>(result); 	       
        	
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
    }        

    	
}
