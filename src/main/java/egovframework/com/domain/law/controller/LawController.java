package egovframework.com.domain.law.controller;

import java.util.List;
import java.util.Map;

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

import egovframework.com.domain.law.domain.Law;
import egovframework.com.domain.law.parameter.LawParameter;
import egovframework.com.domain.law.parameter.LawSearchParameter;
import egovframework.com.domain.law.service.LawService;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.domain.portal.logn.service.LoginService;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 관계법령에 따른 개선시정 명령조치 현황 API 컨트롤러
 * 
 * @author KimJuHwan
 *
 */
@RestController
@RequestMapping("/improvement/law")
@Api(tags = "Law Improvements Management API")
public class LawController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LawController.class);
	
	@Autowired
	private LawService lawService;
	
	@Autowired
	private LoginService loginService;

	/**
     * 관계법령에 따른 개선시정 명령조치 목록 리턴
     * 
     * @param parameter
     * @return List<Law>
     */
    @PostMapping("/select")
    @ApiOperation(value = "List of suggestions for improvement according to laws and regulations",
    	 notes = "This function returns a list of improvements by law by companyId.")
    public BaseResponse<List<Law>> getLawImprovementList(HttpServletRequest request, @RequestBody LawSearchParameter parameter) {
    	
    	LOGGER.info("select");
    	LOGGER.info(parameter.toString());
    	
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		parameter.setCompanyId(login.getCompanyId());
    	
        return new BaseResponse<List<Law>>(lawService.getLawImprovementList(parameter));
    }
    
    /**
     * 관계법령에 따른 개선시정 명령조치 등록
     * 
     * @param parameter
     * @return int
     */
    @PostMapping("/insert")
    @ApiOperation(value = "Add a new law improvement", notes = "This function adds a new law improvement.")
    public BaseResponse<Integer> insertLawImprovement(HttpServletRequest request,  @RequestBody LawParameter parameter) {
    	
    	LOGGER.info("insert");
    	LOGGER.info(parameter.toString());
    	
    	if (ObjectUtils.isEmpty(parameter.getWorkplaceId())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"workplaceId", "사업장ID"});
        }
    	
    	if (!StringUtils.hasText(parameter.getRecvDate())) {
    		throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
    				new String[] {"recvDate", "접수일자"});
    	}

    	if (!StringUtils.hasText(parameter.getRecvUserName())) {
    		throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
    				new String[] {"recvUserName", "접수자명"});
    	}
    	
    	if (!StringUtils.hasText(parameter.getRecvCd())) {
    		throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
    				new String[] {"recvCd", "접수형태CD"});
    	}
    	
    	if (!StringUtils.hasText(parameter.getImproveIssueCn())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"improveIssueCn", "개선.조치.내용"});
        }
        
        if (!StringUtils.hasText(parameter.getOccurPlace())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"occurPlace", "발생장소"});
        }
        
        if (!StringUtils.hasText(parameter.getIssueReason())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"issueReason", "지적원인"});
        }
        
        // 조치상태 입력
        if (parameter.getPerformAfterId() != null) {
        	// 조치 후 사진이 있는 경우는 조치완료 코드를 넣어줌
        	parameter.setStatusCd("006");
        } else {
        	// 조치 후 사진이 없는 경우는 조치중 코드를 넣어줌
        	parameter.setStatusCd("005");
        }
        
        
        Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		parameter.setCompanyId(login.getCompanyId());
        parameter.setInsertId(login.getUserId());
        parameter.setUpdateId(login.getUserId());
        
        try {
        	int cnt = lawService.insertLawImprovement(parameter);
            return new BaseResponse<Integer>(cnt);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(BaseResponseCode.SAVE_ERROR,
                    new String[] {"등록 중에 오류가 발행했습니다. (" + e.getMessage() + ")"});
        }

    }
    
    /**
     * 관계법령에 따른 개선시정 명령조치 상세조회
     * 
     * @param parameter
     * @return Improvement
     */
    @PostMapping("/view")
    @ApiOperation(value = "Get the law improvement", notes = "This function returns the specified law improvement.")
    public BaseResponse<Law> getLawImprovement(HttpServletRequest request, @RequestBody LawSearchParameter parameter) {
        
    	LOGGER.info("view");
    	LOGGER.info(parameter.toString());
    	
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
    	
    	// null 처리
        if (parameter.getLawImproveId() == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL,
                    new String[] {"improveId (" + parameter.getLawImproveId()+ ")"});
        }
        
        Law law = lawService.getLawImprovement(login.getCompanyId(), parameter.getLawImproveId());
        return new BaseResponse<Law>(law);

    }
    
    /**
     * 관계법령에 따른 개선시정 명령조치 수정
     * 
     * @param parameter
     * @return int
     */
    @PostMapping("/update")
    @ApiOperation(value = "Update a law improvement", notes = "This function update a law improvement.")
    public BaseResponse<Integer> updateLawImprovement(HttpServletRequest request,  @RequestBody LawParameter parameter) {
    	
    	LOGGER.info("update");
    	LOGGER.info(parameter.toString());
    	
    	if (ObjectUtils.isEmpty(parameter.getWorkplaceId())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"workplaceId", "사업장ID"});
        }
    	
    	if (!StringUtils.hasText(parameter.getRecvDate())) {
    		throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
    				new String[] {"recvDate", "접수일자"});
    	}

    	if (!StringUtils.hasText(parameter.getRecvUserName())) {
    		throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
    				new String[] {"recvUserName", "접수자명"});
    	}
    	
    	if (!StringUtils.hasText(parameter.getRecvCd())) {
    		throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
    				new String[] {"recvCd", "접수형태CD"});
    	}
    	
    	if (!StringUtils.hasText(parameter.getImproveIssueCn())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"improveIssueCn", "개선.조치.내용"});
        }
        
        if (!StringUtils.hasText(parameter.getOccurPlace())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"occurPlace", "발생장소"});
        }
        
        if (!StringUtils.hasText(parameter.getIssueReason())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"issueReason", "지적원인"});
        }
        
        // 조치상태 입력
        if (parameter.getPerformAfterId() != null) {
        	// 조치 후 사진이 있는 경우는 조치완료 코드를 넣어줌
        	parameter.setStatusCd("006");
        } else {
        	// 조치 후 사진이 없는 경우는 조치중 코드를 넣어줌
        	parameter.setStatusCd("005");
        }
        
        
        Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		parameter.setCompanyId(login.getCompanyId());
        parameter.setUpdateId(login.getUserId());
        
        try {
        	int cnt = lawService.updateLawImprovement(parameter);
            return new BaseResponse<Integer>(cnt);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(BaseResponseCode.SAVE_ERROR,
                    new String[] {"수정 중에 오류가 발행했습니다. (" + e.getMessage() + ")"});
        }

    }
    
    /**
     * 관계법령에 따른 개선시정 명령조치 삭제
     * 
     * @param parameter
     * @return Improvement
     */
    @PostMapping("/delete")
    @ApiOperation(value = "Delete law improvement", notes = "This function deletes the specified law improvement.")
    public BaseResponse<Integer> deleteLawImprovement(HttpServletRequest request, @RequestBody LawSearchParameter parameter) {
        
    	LOGGER.info("delete");
    	LOGGER.info(parameter.toString());
    	
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
    	
    	// null 처리
        if (parameter.getLawImproveId() == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL,
                    new String[] {"improveId (" + parameter.getLawImproveId()+ ")"});
        }
        
        int cnt = lawService.deleteLawImprovement(login.getCompanyId(), login.getUserId(), parameter.getLawImproveId());
        return new BaseResponse<Integer>(cnt);

    }
    
    /**
     * 지적원인 리스트
     * 
     * @return <List<Map<String,String>>>
     */
    @PostMapping("/issueReason/select")
    @ApiOperation(value = "List of issue reason",
    	 notes = "This function returns a list of issue reason by company ID.")
    public BaseResponse<List<Map<String,String>>> getIssueReasonList(HttpServletRequest request) {
    	
    	LOGGER.info("/issueReason/select");
    	
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
    	
        return new BaseResponse<List<Map<String,String>>>(lawService.getIssueReasonList(login.getCompanyId()));
    }

       
	
}
