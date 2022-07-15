package egovframework.com.domain.education.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import egovframework.com.domain.education.domain.EduClass;
import egovframework.com.domain.education.parameter.EduClassParameter;
import egovframework.com.domain.education.parameter.EduClassSearchParameter;
import egovframework.com.domain.education.service.EduClassService;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/edu/eduClasses")
@Api(tags = "Education Management API")
public class EduClassController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EduClassController.class);

    @Autowired
    private EduClassService eduClassService;

    @PostMapping("/listCount")
    @ApiOperation(value = "Number of education classes",
            notes = "This function returns the number of education class records.")
    public BaseResponse<Long> getCount(@ApiParam @RequestBody EduClassSearchParameter parameter) {

        try {
            Long listCount = eduClassService.getListCount(parameter);
            return new BaseResponse<Long>(listCount);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }


    }

    @PostMapping("/list")
    @ApiOperation(value = "List of education classes",
            notes = "This function returns the list of education class records.")
    public BaseResponse<List<EduClass>> getList(
            @ApiParam @RequestBody EduClassSearchParameter parameter) {

        try {
            return new BaseResponse<List<EduClass>>(eduClassService.getList(parameter));
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }

    }

    @GetMapping("/{eduClassId}")
    @ApiOperation(value = "Get the eduClass",
            notes = "This function returns the specified education class.")
    @ApiImplicitParams({@ApiImplicitParam(name = "eduClassId", value = "Id of the education class",
            dataType = "long")})
    public BaseResponse<EduClass> get(@PathVariable Long eduClassId) {
        // null 처리
        if (eduClassId == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL,
                    new String[] {"eduClassId (" + eduClassId + ")"});
        }
        try {
            EduClass eduClass = eduClassService.get(eduClassId);
            return new BaseResponse<EduClass>(eduClass);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }

    }

    @PutMapping("/eduClasses")
    @ApiOperation(value = "Add a new education class",
            notes = "This function adds a new education class.")
    public BaseResponse<Long> create(@ApiParam @RequestBody EduClassParameter parameter) {
        if (StringUtils.isEmpty(parameter.getEduClassName())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"eduClassName", "교육분류명"});
        }

        try {
            eduClassService.insert(parameter);
            return new BaseResponse<Long>(parameter.getEduClassId());
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.SAVE_ERROR,
                    new String[] {"등록 중에 오류가 발행했습니다. (" + e.getMessage() + ")"});
        }

    }

    @PatchMapping("/{eduClassId}")
    @ApiOperation(value = "Update the eduClass",
            notes = "This function updates the specified education class.")
    public BaseResponse<Long> modify(@PathVariable Long eduClassId,
            @ApiParam @RequestBody EduClassParameter parameter) {
        try {
            parameter.setEduClassId(eduClassId);
            EduClass eduClass = eduClassService.get(parameter.getEduClassId());
            if (eduClass == null) {
                throw new BaseException(BaseResponseCode.SAVE_ERROR,
                        new String[] {"eduClassId", "수정할 정보가 없습니다."});
            } else {
                if (StringUtils.isEmpty(parameter.getEduClassName())) {
                    throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                            new String[] {"eduClassName", "교육분류명"});
                }
                eduClassService.update(parameter);
            }
            return new BaseResponse<Long>(eduClassId);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.SAVE_ERROR,
                    new String[] {"등록 중에 오류가 발행했습니다. (" + e.getMessage() + ")"});
        }

    }

    @PostMapping("/deleteArr")
    @ApiOperation(value = "Delete eduClasses by the list",
            notes = "This function deletes the specified education classes by the list.")
    public BaseResponse<Boolean> deleteArr(@RequestParam(value = "deleteList") String deleteList) {
        if (ObjectUtils.isEmpty(deleteList)) {
            throw new BaseException(BaseResponseCode.DELETE_ERROR,
                    new String[] {"eduClassId", "삭제할 정보가 없습니다."});
        }
        try {
            String[] deleteSplit = deleteList.split(",");
            List<Long> eduClassList = new ArrayList<Long>();

            for (int i = 0; i < deleteSplit.length; i++) {
                eduClassList.add(Long.parseLong(deleteSplit[i]));
            }
            eduClassService.deleteArr(eduClassList);
            return new BaseResponse<Boolean>(true);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.DELETE_ERROR,
                    new String[] {"삭제 중에 오류가 발행했습니다. (" + e.getMessage() + ")"});
        }
    }
}
