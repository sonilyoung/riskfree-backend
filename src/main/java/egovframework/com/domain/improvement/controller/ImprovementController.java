package egovframework.com.domain.improvement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.com.domain.improvement.domain.Improvement;
import egovframework.com.domain.improvement.parameter.ImprovementParameter;
import egovframework.com.domain.improvement.parameter.ImprovementSearchParameter;
import egovframework.com.domain.improvement.service.ImprovementService;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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

	/**
     * 개선조치 사항 목록 및 총 갯수 리턴
     * 
     * @param param
     * @return List<Improvement>
     */
    @GetMapping("/{companyId}/infos")
    @ApiOperation(value = "List of Improvements message headers of the companyId",
    	 notes = "This function returns the list of Improvements message headers of the companyId")
    @ApiImplicitParams({@ApiImplicitParam(name = "companyId", value = "Id of the company",
            dataType = "long")})
    public BaseResponse<List<Improvement>> getImprovementList(HttpServletRequest request,
            @PathVariable Long companyId,
            ImprovementSearchParameter parameter) {
        parameter.setCompanyId(companyId);
        return new BaseResponse<List<Improvement>>(improvementService.getImprovementList(parameter));
    }
    
    /**
     * 개선조치 등록
     * 
     * @param param
     * @return 
     */
    @PostMapping("/{companyId}/infos")
    @ApiOperation(value = "Add a new improvements", notes = "This function adds a new improvements.")
    public BaseResponse<Long> insertImprovement(@PathVariable Long companyId, @ApiParam @RequestBody ImprovementParameter parameter) {
    	if (ObjectUtils.isEmpty(parameter.getWorkplaceId())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"workplaceId", "사업장ID"});
        }
    	
    	if (ObjectUtils.isEmpty(parameter.getReqUserId())) {
    		throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
    				new String[] {"reqUserId", "요청자"});
    	}

        if (StringUtils.hasText(parameter.getImproveNo())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"improveNo", "개선조치 NO"});
        }

        if (StringUtils.hasText(parameter.getReqContents())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"reqContents", "개선.조치.내용"});
        }
        
        if (StringUtils.hasText(parameter.getReqDate())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"reqDate", "요청일자"});
        }
        
        parameter.setCompanyId(companyId);
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
     * @param improveNo
     * @return Improvement
     */
    @GetMapping("/{companyId}/infos/{improveSeq}")
    @ApiOperation(value = "Get the Improvement",
            notes = "This function returns the specified Improvement.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "improveNo", value = "Id of the improvement", dataType = "long")})
    public BaseResponse<Improvement> getImprovement(@PathVariable Long companyId, @PathVariable Long improveSeq) {
        // null 처리
        if (improveSeq == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL,
                    new String[] {"improveSeq (" + improveSeq + ")"});
        }
        try {
        	Improvement improvement = improvementService.getImprovement(companyId, improveSeq);
            return new BaseResponse<Improvement>(improvement);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }

    }
    

	/**
     * 개선조치 수정
     * 
     * @param parameter
     * @return 
     */
    @PutMapping("/{companyId}/infos/{improveSeq}")
    @ApiOperation(value = "Update a new improvements", notes = "This function updates the specified improvements.")
    public BaseResponse<Long> modifyImprovement(@PathVariable Long companyId, @PathVariable Long improveSeq, @ApiParam @RequestBody ImprovementParameter parameter) {
    	if (ObjectUtils.isEmpty(parameter.getWorkplaceId())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"workplaceId", "사업장ID"});
        }
    	
    	if (ObjectUtils.isEmpty(parameter.getReqUserId())) {
    		throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
    				new String[] {"reqUserId", "요청자"});
    	}

        if (StringUtils.hasText(parameter.getImproveNo())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"improveNo", "개선조치 NO"});
        }

        if (StringUtils.hasText(parameter.getReqContents())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"reqContents", "개선.조치.내용"});
        }
        
        if (StringUtils.hasText(parameter.getReqDate())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"reqDate", "요청일자"});
        }

        parameter.setCompanyId(companyId);
        parameter.setImproveSeq(improveSeq);
        try {
        	improvementService.modifyImprovement(parameter);
            return new BaseResponse<Long>(parameter.getCompanyId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(BaseResponseCode.SAVE_ERROR,
                    new String[] {"등록 중에 오류가 발행했습니다. (" + e.getMessage() + ")"});
        }

    }
    
	/**
     * 개선조치 삭제
     * 
     * @param companyId, improveId
     * @return 
     */
    @DeleteMapping("/{companyId}/infos/{improveSeq}")
    @ApiOperation(value = "Delete eduClasses by the list",
    notes = "This function deletes the specified education classes by the list.")
    public BaseResponse<Boolean> deleteImprovement(@PathVariable Long companyId, @PathVariable Long improveId) {
        try {
        	improvementService.deleteImprovement(companyId,improveId);
            return new BaseResponse<Boolean>(true);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.DELETE_ERROR,
                    new String[] {"삭제 중에 오류가 발행했습니다. (" + e.getMessage() + ")"});
        }
    }
    
//    @PostMapping("/{companyId}/infos/excel-download")
//    @ApiOperation(value = "Delete eduClasses by the list",
//    notes = "This function deletes the specified education classes by the list.")
    
    
}
