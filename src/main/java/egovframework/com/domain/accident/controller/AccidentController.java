package egovframework.com.domain.accident.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.com.domain.accident.domain.Accident;
import egovframework.com.domain.accident.parameter.AccidentParameter;
import egovframework.com.domain.accident.parameter.AccidentSearchParameter;
import egovframework.com.domain.accident.service.AccidentService;
import egovframework.com.domain.company.parameter.WorkplaceParameter;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 재해발생 및 방지대책 등 이행현황 API 컨트롤러
 * 
 * @author KimJuHwan
 *
 */
@RestController
@RequestMapping("/accidents")
@Api(tags = "Accident Management API")
public class AccidentController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccidentController.class);

	@Autowired
	private AccidentService accidentService;
	
	/**
     * 재해발생 이행조치 목록
     * 
     * @param companyId
     * @return Company
     */
	@GetMapping("/{companyId}/infos")
	@ApiOperation(value = "List of accidents of the company",notes = "This function returns a list of accidents for the specified company.")
	@ApiImplicitParams({@ApiImplicitParam(name = "companyId", value = "Id of the company",dataType = "long")})
	public BaseResponse<List<Accident>> getAccidentList(HttpServletRequest request, @PathVariable Long companyId, AccidentSearchParameter parameter) {

		try {
			parameter.setCompanyId(companyId);
        	
        	return new BaseResponse<List<Accident>>(accidentService.getAccidentList(parameter));
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
    }
	
	
	/**
     * 재해발생 이행조치 상세정보
     * 
     * @param companyId, accidentId
     * @return Accident
     */
	@GetMapping("/{companyId}/infos/{accidentId}")
	@ApiOperation(value = "Get the accident",notes = "This function returns the specified accident")
	@ApiImplicitParams({@ApiImplicitParam(name = "companyId", value = "Id of the company",dataType = "long"),
						@ApiImplicitParam(name = "accidentId", value = "Id of the accident",dataType = "long")})
	public BaseResponse<Accident> getAccident(HttpServletRequest request, @PathVariable Long companyId, @PathVariable Long accidentId) {

		try {
        	return new BaseResponse<Accident>(accidentService.getAccident(companyId, accidentId));
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
		
    }
	
	/**
     * 재해발생 이행조치 등록
     * 
     * @param parameter
     * @return 
     */
	@PostMapping("/{companyId}/infos")
	@ApiOperation(value = "Add a new accident",notes = "This function adds a new accident")
	@ApiImplicitParams({@ApiImplicitParam(name = "companyId", value = "Id of the company",dataType = "long")})
	public BaseResponse<Long> insertAccident(HttpServletRequest request, @PathVariable Long companyId, 
			@RequestBody AccidentParameter parameter) {

		try {
        	parameter.setCompanyId(companyId);
        	// session에서 ID를 받아와야함
        	parameter.setInsertId(1L);
        	accidentService.insertAccident(parameter);
        	return new BaseResponse<Long>(parameter.getCompanyId());
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
		
    }
	
	/**
     * 재해발생 이행조치 수정
     * 
     * @param parameter
     * @return 
     */
	@PutMapping("/{companyId}/infos/{accidentId}")
	@ApiOperation(value = "Update a accident",notes = "This function update a accident")
	@ApiImplicitParams({@ApiImplicitParam(name = "companyId", value = "Id of the company",dataType = "long"),
						@ApiImplicitParam(name = "accidentId", value = "Id of the accident",dataType = "long")})
	public BaseResponse<Long> modifyAccident(HttpServletRequest request, @PathVariable Long companyId,  @PathVariable Long accidentId,
			@RequestBody AccidentParameter parameter) {

		try {
        	parameter.setCompanyId(companyId);
        	parameter.setAccidentId(accidentId);
        	// session에서 ID를 받아와야함
        	parameter.setInsertId(1L);
        	accidentService.modifyAccident(parameter);
        	return new BaseResponse<Long>(parameter.getAccidentId());
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
		
    }
	
	/**
     * 재해발생 이행조치 삭제
     * 
     * @apiNote 현재 로그인한 사용자의 ID를 받아와야함
     * @param companyId, workplaceId
     * @return 
     */
	@DeleteMapping("/{companyId}/infos/{accidentId}")
	@ApiOperation(value = "Delete a accident",notes = "This function delete a accident")
	@ApiImplicitParams({@ApiImplicitParam(name = "companyId", value = "Id of the company",dataType = "long"),
						@ApiImplicitParam(name = "accidentId", value = "Id of the accident",dataType = "long")})
	public BaseResponse<Boolean> deleteAccident(HttpServletRequest request, @PathVariable Long companyId, @PathVariable Long accidentId) {

		try {
			Long insertId = 1L;
			accidentService.deleteAccident(companyId, accidentId, insertId);
        	return new BaseResponse<Boolean>(true);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
		
    }
	
}
