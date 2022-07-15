package egovframework.com.domain.education.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import egovframework.com.domain.education.domain.EduType;
import egovframework.com.domain.education.parameter.EduTypeParameter;
import egovframework.com.domain.education.parameter.EduTypeSearchParameter;
import egovframework.com.domain.education.service.EduTypeService;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.domain.portal.logn.service.LoginService;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/edu/eduTypes")
@Api(tags = "Education Management API")
public class EduTypeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EduTypeController.class);

    @Autowired
    private EduTypeService eduTypeService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/listCount")
    @ApiOperation(value = "Number of education types",
            notes = "This function returns the number of education type records.")
    public BaseResponse<Long> getCount(HttpServletRequest request) {
        Login login = loginService.getLoginInfo(request);
        if (login == null) {
            throw new BaseException(BaseResponseCode.AUTH_FAIL, new String[] {"Invalid token"});
        }

        try {
            Long listCount = eduTypeService.getListCount();
            return new BaseResponse<Long>(listCount);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }

    }

    @PostMapping("/list")
    @ApiOperation(value = "List of education types",
            notes = "This function returns the list of education type records.")
    public BaseResponse<List<EduType>> getList(HttpServletRequest request,
            @ApiParam @RequestBody EduTypeSearchParameter parameter) {
        // Login login = loginService.getLoginInfo(request);
        // if (login == null) {
        // throw new BaseException(BaseResponseCode.AUTH_FAIL, new String[] {"Invalid token"});
        // }

        try {
            return new BaseResponse<List<EduType>>(eduTypeService.getList(parameter));
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
    }

    @GetMapping("/{eduTypeId}")
    @ApiOperation(value = "Get the eduType",
            notes = "This function returns the specified education type.")
    @ApiImplicitParams({@ApiImplicitParam(name = "eduTypeId", value = "Id of the education type",
            dataType = "long")})
    public BaseResponse<EduType> get(HttpServletRequest request, @PathVariable Long eduTypeId) {

        try {
            Login login = loginService.getLoginInfo(request);
            if (login == null) {
                throw new BaseException(BaseResponseCode.AUTH_FAIL, new String[] {"Invalid token"});
            }

            EduType eduType = eduTypeService.get(eduTypeId);
            // null 처리
            if (eduTypeId == null) {
                throw new BaseException(BaseResponseCode.DATA_IS_NULL,
                        new String[] {"ID(" + eduTypeId + ")"});
            }
            return new BaseResponse<EduType>(eduType);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }

    }

    @PutMapping("/eduTypes")
    @ApiOperation(value = "Add a new education type",
            notes = "This function adds a new education type.")
    @ApiImplicitParams({@ApiImplicitParam(name = "eduTypeName", value = "Name of education type",
            dataType = "String")})
    public BaseResponse<Long> create(HttpServletRequest request,
            @ApiParam @RequestBody EduTypeParameter parameter) {
        try {
            Login login = loginService.getLoginInfo(request);
            if (login == null) {
                throw new BaseException(BaseResponseCode.AUTH_FAIL, new String[] {"Invalid token"});
            }

            if (StringUtils.isEmpty(parameter.getEduTypeName())) {
                throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                        new String[] {"eduTypeName", "교육분류명"});
            }
            // if (StringUtils.isEmpty(parameter.getUseYn())) {
            // throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
            // new String[] {"useYn", "사용여부"});
            // }
            eduTypeService.insert(parameter);
            return new BaseResponse<Long>(parameter.getEduTypeId());
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.SAVE_ERROR,
                    new String[] {"등록 중에 오류가 발생하였습니다 (" + e.getMessage() + ")"});
        }
    }

    @PatchMapping("/{eduTypeId}")
    @ApiOperation(value = "Update the eduType",
            notes = "This function updates the specified education type.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eduTypeId", value = "Id of the education type",
                    dataType = "long"),
            @ApiImplicitParam(name = "eduTypeName", value = "Name of the education type",
                    dataType = "String")})
    public BaseResponse<Long> modify(HttpServletRequest request, @PathVariable Long eduTypeId,
            @ApiParam @RequestBody EduTypeParameter parameter) {
        try {


            Login login = loginService.getLoginInfo(request);
            if (login == null) {
                throw new BaseException(BaseResponseCode.AUTH_FAIL, new String[] {"Invalid token"});
            }

            EduType eduType = eduTypeService.get(eduTypeId);
            if (eduType == null) {
                throw new BaseException(BaseResponseCode.SAVE_ERROR);
            } else {
                if (parameter.getEduTypeId() == null) {
                    throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                            new String[] {"eduTypeId", "교육분류 번호"});
                }
                if (StringUtils.isEmpty(parameter.getEduTypeName())) {
                    throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                            new String[] {"eduTypeName", "교육분류명"});
                }
                // if (StringUtils.isEmpty(parameter.getUseYn())) {
                // throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                // new String[] {"useYn", "사용여부"});
                // }
                eduTypeService.update(parameter);
            }
            return new BaseResponse<Long>(eduTypeId);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.SAVE_ERROR,
                    new String[] {"등록 중에 오류가 발생하였습니다 (" + e.getMessage() + ")"});
        }
    }

    @DeleteMapping("/eduTypes/{eduTypeId}")
    @ApiOperation(value = "Delete the eduType",
            notes = "This function deletes the specified education type.")
    @ApiImplicitParams({@ApiImplicitParam(name = "eduTypeId", value = "Id of the education type",
            dataType = "long")})
    public BaseResponse<Boolean> delete(HttpServletRequest request, @PathVariable Long eduTypeId) {
        try {

            Login login = loginService.getLoginInfo(request);
            if (login == null) {
                throw new BaseException(BaseResponseCode.AUTH_FAIL, new String[] {"Invalid token"});
            }

            EduType eduType = eduTypeService.get(eduTypeId);
            if (eduType == null) {
                throw new BaseException(BaseResponseCode.DELETE_ERROR,
                        new String[] {"eduTypeId", "교육분류 번호"});
            }
            eduTypeService.delete(eduTypeId);
            return new BaseResponse<Boolean>(true);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.DELETE_ERROR,
                    new String[] {"삭제 중에 오류가 발행했습니다. (" + e.getMessage() + ")"});
        }
    }

    @PostMapping("/deleteArr")
    @ApiOperation(value = "Delete eduTypes by the list",
            notes = "This function deletes the specified education types by the list.")
    public BaseResponse<Boolean> deleteArr(HttpServletRequest request,
            @RequestParam(value = "deleteList") String deleteList) {
        Login login = loginService.getLoginInfo(request);
        if (login == null) {
            throw new BaseException(BaseResponseCode.AUTH_FAIL, new String[] {"Invalid token"});
        }

        if (ObjectUtils.isEmpty(deleteList)) {
            throw new BaseException(BaseResponseCode.DELETE_ERROR,
                    new String[] {"eduTypeId", "삭제할 정보가 없습니다."});
        }

        try {
            String[] deleteSplit = deleteList.split(",");
            List<Long> eduTypeList = new ArrayList<Long>();

            for (int i = 0; i < deleteSplit.length; i++) {
                eduTypeList.add(Long.parseLong(deleteSplit[i]));
            }
            eduTypeService.deleteArr(eduTypeList);
            return new BaseResponse<Boolean>(true);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.DELETE_ERROR,
                    new String[] {"삭제 중에 오류가 발행했습니다. (" + e.getMessage() + ")"});
        }
    }

}
