package egovframework.com.domain.improvement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.com.domain.improvement.domain.Improvement;
import egovframework.com.domain.improvement.parameter.ImprovementParameter;
import egovframework.com.domain.improvement.parameter.ImprovementSearchParameter;
import egovframework.com.domain.improvement.service.ImprovementService;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.domain.portal.logn.service.LoginService;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 개선사항 API 컨트롤러
 * 
 * @author KimJuHwan
 *
 */
@RestController
@RequestMapping("/improvements")
@Api(tags = "Improvements Management API")
public class ImprovementController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImprovementController.class);
	
	@Autowired
    private ImprovementService improvementService;
	
	@Autowired
	private LoginService loginService;

	/**
     * 개선조치 사항 목록 및 총 갯수 리턴
     * 
     * @param param
     * @return List<Improvement>
     */
    @PostMapping("/select")
    @ApiOperation(value = "List of Improvements message headers of the companyId",
    	 notes = "This function returns the list of Improvements message headers of the companyId")
    public BaseResponse<List<Improvement>> getImprovementList(HttpServletRequest request, @RequestBody ImprovementSearchParameter parameter) {
    	
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		parameter.setCompanyId(login.getCompanyId());
    	
        return new BaseResponse<List<Improvement>>(improvementService.getImprovementList(parameter));
    }
    
    /**
     * 개선조치 등록
     * 
     * @param parameter
     * @return 
     */
    @PostMapping("/insert")
    @ApiOperation(value = "Add a new improvement", notes = "This function adds a new improvement.")
    public BaseResponse<Long> insertImprovement(HttpServletRequest request, @ApiParam @RequestBody ImprovementParameter parameter) {
    	
    	if (ObjectUtils.isEmpty(parameter.getWorkplaceId())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"workplaceId", "사업장ID"});
        }
    	
    	if (ObjectUtils.isEmpty(parameter.getReqUserName())) {
    		throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
    				new String[] {"reqUserName", "요청자"});
    	}

        if (!StringUtils.hasText(parameter.getImproveCn())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"reqContents", "개선.조치.내용"});
        }
        
        if (!StringUtils.hasText(parameter.getReqDate())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"reqDate", "요청일자"});
        }
        
        Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		parameter.setCompanyId(login.getCompanyId());
        parameter.setInsertId(login.getUserId());
        parameter.setUpdateId(login.getUserId());
        
        try {
        	improvementService.insertImprovement(parameter);
            return new BaseResponse<Long>(parameter.getCompanyId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(BaseResponseCode.SAVE_ERROR,
                    new String[] {"등록 중에 오류가 발행했습니다. (" + e.getMessage() + ")"});
        }

    }
    

	/**
     * 개선조치 상세조회
     * 
     * @param parameter
     * @return Improvement
     */
    @PostMapping("/view")
    @ApiOperation(value = "Get the Improvement", notes = "This function returns the specified Improvement.")
    public BaseResponse<Improvement> getImprovement(HttpServletRequest request, @RequestBody Long improveId) {
        
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
    	
    	// null 처리
        if (improveId == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL,
                    new String[] {"improveId (" + improveId + ")"});
        }
        
    	Improvement improvement = improvementService.getImprovement(login.getCompanyId(), improveId);
        return new BaseResponse<Improvement>(improvement);

    }
    

	/**
     * 개선조치 수정
     * 
     * @param parameter
     * @return 
     */
    @PostMapping("/update")
    @ApiOperation(value = "Update a new improvement", notes = "This function updates the specified improvement.")
    public BaseResponse<Long> modifyImprovement(HttpServletRequest request, @ApiParam @RequestBody ImprovementParameter parameter) {
    	
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		parameter.setCompanyId(login.getCompanyId());
	    parameter.setUpdateId(login.getUserId());
    	
    	if (ObjectUtils.isEmpty(parameter.getWorkplaceId())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"workplaceId", "사업장ID"});
        }
    	
    	if (ObjectUtils.isEmpty(parameter.getReqUserName())) {
    		throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
    				new String[] {"reqUserId", "요청자"});
    	}

        if (!StringUtils.hasText(parameter.getImproveNo())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"improveNo", "개선조치 NO"});
        }

        if (!StringUtils.hasText(parameter.getImproveCn())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"reqContents", "개선.조치.내용"});
        }
        
        if (!StringUtils.hasText(parameter.getReqDate())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"reqDate", "요청일자"});
        }

    	improvementService.modifyImprovement(parameter);
        return new BaseResponse<Long>(parameter.getCompanyId());

    }
    
	/**
     * 개선조치 삭제
     * 
     * @param parameter
     * @return 
     */
    @PostMapping("/delete")
    @ApiOperation(value = "Delete improvement", notes = "This function deletes the specified improvement.")
    public BaseResponse<Boolean> deleteImprovement(HttpServletRequest request, @ApiParam @RequestBody Long improveId) {
        	
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
    	
    	improvementService.deleteImprovement(login.getCompanyId(), improveId);
        return new BaseResponse<Boolean>(true);
    }
    
//    @PostMapping("/{companyId}/infos/excel-download")
//    @ApiOperation(value = "Delete eduClasses by the list",
//    notes = "This function deletes the specified education classes by the list.")
    
    
}
