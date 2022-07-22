package egovframework.com.domain.accident.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.com.domain.accident.domain.Accident;
import egovframework.com.domain.accident.parameter.AccidentSearchParameter;
import egovframework.com.domain.accident.service.AccidentService;
import egovframework.com.domain.company.domain.Company;
import egovframework.com.domain.company.domain.Workplace;
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
}
