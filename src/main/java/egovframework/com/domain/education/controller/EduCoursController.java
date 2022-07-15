package egovframework.com.domain.education.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import egovframework.com.domain.education.domain.EduClass;
import egovframework.com.domain.education.domain.EduCours;
import egovframework.com.domain.education.domain.EduPlan;
import egovframework.com.domain.education.domain.PlanDB;
import egovframework.com.domain.education.parameter.EduCoursParameter;
import egovframework.com.domain.education.parameter.EduCoursSearchParameter;
import egovframework.com.domain.education.service.EduCoursService;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/edu/eduCourses")
@Api(tags = "Education Management API")
public class EduCoursController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EduCoursController.class);

    @Autowired
    private EduCoursService eduCoursService;

    @PostMapping("/listCount")
    @ApiOperation(value = "Number of education courses",
            notes = "This function returns the number of education course records.")
    public BaseResponse<Long> getCount(@ApiParam @RequestBody EduCoursSearchParameter parameter) {

        try {
            Long listCount = eduCoursService.getListCount(parameter);
            return new BaseResponse<Long>(listCount);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }

    }

    @PostMapping("/list")
    @ApiOperation(value = "List of education courses",
            notes = "This function returns the list of education course records.")
    public BaseResponse<List<EduCours>> getList(
            @ApiParam @RequestBody EduCoursSearchParameter parameter) {

        try {
            return new BaseResponse<List<EduCours>>(eduCoursService.getList(parameter));
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }

    }

    @GetMapping("/{eduCourseId}")
    @ApiOperation(value = "Get the eduCours",
            notes = "This function returns the specified education course.")
    @ApiImplicitParams({@ApiImplicitParam(name = "eduCourseId",
            value = "Id of the education course", dataType = "long")})
    public BaseResponse<EduCours> get(@PathVariable Long eduCourseId) {
        // null 처리
        if (eduCourseId == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL,
                    new String[] {"eduCourseId (" + eduCourseId + ")"});
        }
        try {
            EduCours eduCours = eduCoursService.get(eduCourseId);
            return new BaseResponse<EduCours>(eduCours);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }

    }

    @PutMapping("/eduCourses")
    @ApiOperation(value = "Add a new education course",
            notes = "This function adds a new education course.")
    public BaseResponse<Long> create(@ApiParam @RequestBody EduCoursParameter parameter) {
        if (StringUtils.isEmpty(parameter.getEduClassId())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"eduClassId", "교육과목ID"});
        }
        if (StringUtils.isEmpty(parameter.getEduClassSeq())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"eduClassSeq", "교육회차"});
        }
        if (StringUtils.isEmpty(parameter.getEduLecturerName())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"eduLectureName", "강사명"});
        }
        if (StringUtils.isEmpty(parameter.getEduPeriodCd())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"eduPeriodCd", "교육기간코드"});
        }
        if (StringUtils.isEmpty(parameter.getEduPlaceCd())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"eduPlaceCd", "교육장소코드"});
        }
        // if (StringUtils.isEmpty(parameter.getUseYn())) {
        // throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
        // new String[] {"eduUseYn", "사용여부"});
        // }
        try {
            eduCoursService.insert(parameter);
            return new BaseResponse<Long>(parameter.getEduCourseId());
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.SAVE_ERROR,
                    new String[] {"등록 중에 오류가 발생하였습니다 (" + e.getMessage() + ")"});
        }
    }

    @PatchMapping("/{eduCourseId}")
    @ApiOperation(value = "Update the eduCours",
            notes = "This function updates the specified education course.")
    public BaseResponse<Long> modify(@PathVariable Long eduCourseId,
            @ApiParam @RequestBody EduCoursParameter parameter) {


        parameter.setEduCourseId(eduCourseId);
        EduCours eduCours = eduCoursService.get(parameter.getEduCourseId());
        if (eduCours == null) {
            throw new BaseException(BaseResponseCode.SAVE_ERROR,
                    new String[] {"eduCourseId", "수정할 정보가 없습니다."});
        } else {
            if (StringUtils.isEmpty(parameter.getEduClassId())) {
                throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                        new String[] {"eduClassId", "교육과목ID"});
            }
            if (StringUtils.isEmpty(parameter.getEduClassSeq())) {
                throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                        new String[] {"eduClassSeq", "교육회차"});
            }
            if (StringUtils.isEmpty(parameter.getEduLecturerName())) {
                throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                        new String[] {"eduLectureName", "강사명"});
            }
            if (StringUtils.isEmpty(parameter.getEduPeriodCd())) {
                throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                        new String[] {"eduPeriodCd", "교육기간코드"});
            }
            if (StringUtils.isEmpty(parameter.getEduPlaceCd())) {
                throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                        new String[] {"eduPlaceCd", "교육장소코드"});
            }
            // if (StringUtils.isEmpty(parameter.getUseYn())) {
            // throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
            // new String[] {"eduUseYn", "사용여부"});
            // }
            try {
                eduCoursService.update(parameter);
                return new BaseResponse<Long>(eduCourseId);
            } catch (Exception e) {
                throw new BaseException(BaseResponseCode.SAVE_ERROR,
                        new String[] {"등록 중에 오류가 발생하였습니다 (" + e.getMessage() + ")"});
            }
        }


    }

    @PostMapping("/deleteArr")
    @ApiOperation(value = "Delete eduCourss by the list",
            notes = "This function deletes the specified education courses by the list.")
    public BaseResponse<Boolean> deleteArr(@RequestParam(value = "deleteList") String deleteList) {
        if (ObjectUtils.isEmpty(deleteList)) {
            throw new BaseException(BaseResponseCode.DELETE_ERROR,
                    new String[] {"eduCourseId", "삭제할 정보가 없습니다."});
        }
        try {
            String[] deleteSplit = deleteList.split(",");
            List<Long> eduCoursList = new ArrayList<Long>();

            for (int i = 0; i < deleteSplit.length; i++) {
                eduCoursList.add(Long.parseLong(deleteSplit[i]));
            }
            eduCoursService.deleteArr(eduCoursList);
            return new BaseResponse<Boolean>(true);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.DELETE_ERROR,
                    new String[] {"삭제 중에 오류가 발행했습니다. (" + e.getMessage() + ")"});
        }
    }

    @GetMapping("/{ccyy}/eduCourses")
    @ApiOperation(value = "List of Course by ccyy.",
            notes = "This function returns the list of Course by ccyy.")
    @ResponseBody
    public BaseResponse<List<EduPlan>> getEduCourses(HttpServletRequest request,
            @PathVariable String ccyy) {

        try {

            List<PlanDB> planDbList = eduCoursService.getEduCourse(ccyy);
            List<EduPlan> eduPlanList = new ArrayList<EduPlan>();

            EduPlan eduPlan = null;
            EduClass eduClass = null;
            EduCours eduCours = null;
            List<EduCours> eduCoursList = null;
            Long classId = null;

            for (PlanDB pd : planDbList) {
                if (classId != pd.getEduClassId()) {
                    if (eduClass != null) {
                        eduPlan = new EduPlan();
                        eduPlan.setEduClass(eduClass);
                        eduPlan.setEduCours(eduCoursList);
                        eduPlanList.add(eduPlan);
                    }

                    classId = pd.getEduClassId();

                    eduClass = new EduClass();

                    eduClass.setEduClassId(pd.getEduClassId());
                    eduClass.setEduTypeId(pd.getEduTypeId());
                    eduClass.setEduClassName(pd.getEduClassName());
                    eduClass.setEduClassTargetCd(pd.getEduClassTargetCd());
                    eduClass.setEduClassTargetName(pd.getEduClassTargetName());
                    eduClass.setEduClassHours(pd.getEduClassHours());
                    eduClass.setEduClassHRHours(pd.getEduClassHRHours());
                    eduClass.setEduClassFileId(pd.getEduClassFileId());

                    eduCours = new EduCours();

                    eduCours.setEduCourseId(pd.getEduCourseId());
                    eduCours.setEduClassSeq(pd.getEduClassSeq());
                    eduCours.setEduLecturerName(pd.getEduLecturerName());
                    eduCours.setEduPeriodCd(pd.getEduPeriodCd());
                    eduCours.setEduPeriodName(pd.getEduPeriodName());
                    eduCours.setEduPlaceCd(pd.getEduPlaceCd());
                    eduCours.setEduPlaceName(pd.getEduPlaceName());
                    eduCours.setEduStartDt(pd.getEduStartDt());
                    eduCours.setEduEndDt(pd.getEduEndDt());
                    eduCours.setEduCoursRsltFileId(pd.getEduCoursRsltFileId());

                    eduCoursList = new ArrayList<EduCours>();
                    eduCoursList.add(eduCours);
                } else {

                    eduCours = new EduCours();

                    eduCours.setEduCourseId(pd.getEduCourseId());
                    eduCours.setEduClassSeq(pd.getEduClassSeq());
                    eduCours.setEduLecturerName(pd.getEduLecturerName());
                    eduCours.setEduPeriodCd(pd.getEduPeriodCd());
                    eduCours.setEduPeriodName(pd.getEduPeriodName());
                    eduCours.setEduPlaceCd(pd.getEduPlaceCd());
                    eduCours.setEduPlaceName(pd.getEduPlaceName());
                    eduCours.setEduStartDt(pd.getEduStartDt());
                    eduCours.setEduEndDt(pd.getEduEndDt());
                    eduCours.setEduCoursRsltFileId(pd.getEduCoursRsltFileId());

                    eduCoursList.add(eduCours);

                }
            }
            eduPlan = new EduPlan();
            eduPlan.setEduClass(eduClass);
            eduPlan.setEduCours(eduCoursList);
            eduPlanList.add(eduPlan);

            return new BaseResponse<List<EduPlan>>(eduPlanList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
    }

    @GetMapping("/{ccyy}/eduCourses/eduTypes/{eduTypeId}")
    @ApiOperation(value = "List of Course by ccyy and eduTypeId.",
            notes = "This function returns the list of Course by ccyy and eduTypeId. The sample data of eduTypeId is 1 or 2.")
    @ResponseBody
    public BaseResponse<List<EduPlan>> getEduCCoursesByEduType(HttpServletRequest request,
            @PathVariable String ccyy, @PathVariable String eduTypeId) {

        LOGGER.info("ccyy      ]" + ccyy + "[");
        LOGGER.info("eduTypeId ]" + eduTypeId + "[");

        try {

            List<EduPlan> eduPlanList = new ArrayList<EduPlan>();

            EduPlan eduPlan = null;
            EduClass eduClass = null;
            EduCours eduCours = null;
            List<EduCours> eduCoursList = null;
            Long classId = null;

            List<PlanDB> planDbList = eduCoursService.getEduCCoursesByEduType(ccyy, eduTypeId);

            for (PlanDB pd : planDbList) {
                if (classId != pd.getEduClassId()) {

                    classId = pd.getEduClassId();

                    eduClass = new EduClass();

                    eduClass.setEduClassId(pd.getEduClassId());
                    eduClass.setEduTypeId(pd.getEduTypeId());
                    eduClass.setEduClassName(pd.getEduClassName());
                    eduClass.setEduClassTargetCd(pd.getEduClassTargetCd());
                    eduClass.setEduClassTargetName(pd.getEduClassTargetName());
                    eduClass.setEduClassHours(pd.getEduClassHours());
                    eduClass.setEduClassHRHours(pd.getEduClassHRHours());
                    eduClass.setEduClassFileId(pd.getEduClassFileId());

                    eduCours = new EduCours();

                    eduCours.setEduCourseId(pd.getEduCourseId());
                    eduCours.setEduClassSeq(pd.getEduClassSeq());
                    eduCours.setEduLecturerName(pd.getEduLecturerName());
                    eduCours.setEduPeriodCd(pd.getEduPeriodCd());
                    eduCours.setEduPeriodName(pd.getEduPeriodName());
                    eduCours.setEduPlaceCd(pd.getEduPlaceCd());
                    eduCours.setEduPlaceName(pd.getEduPlaceName());
                    eduCours.setEduStartDt(pd.getEduStartDt());
                    eduCours.setEduEndDt(pd.getEduEndDt());
                    eduCours.setEduCoursRsltFileId(pd.getEduCoursRsltFileId());

                    eduCoursList = new ArrayList<EduCours>();
                    eduCoursList.add(eduCours);

                } else {

                    eduCours = new EduCours();

                    eduCours.setEduCourseId(pd.getEduCourseId());
                    eduCours.setEduClassSeq(pd.getEduClassSeq());
                    eduCours.setEduLecturerName(pd.getEduLecturerName());
                    eduCours.setEduPeriodCd(pd.getEduPeriodCd());
                    eduCours.setEduPeriodName(pd.getEduPeriodName());
                    eduCours.setEduPlaceCd(pd.getEduPlaceCd());
                    eduCours.setEduPlaceName(pd.getEduPlaceName());
                    eduCours.setEduStartDt(pd.getEduStartDt());
                    eduCours.setEduEndDt(pd.getEduEndDt());
                    eduCours.setEduCoursRsltFileId(pd.getEduCoursRsltFileId());

                    eduCoursList.add(eduCours);
                }
            }

            eduPlan = new EduPlan();
            eduPlan.setEduClass(eduClass);
            eduPlan.setEduCours(eduCoursList);
            eduPlanList.add(eduPlan);

            return new BaseResponse<List<EduPlan>>(eduPlanList);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, new String[] {e.getMessage()});
        }
    }

    @PostMapping("/{ccyy}/users/{userId}/registedClasses/{eduCourseId}")
    @ResponseBody
    public BaseResponse<Boolean> postRegistedClasses(HttpServletRequest request,
            @PathVariable String ccyy, @PathVariable String userId,
            @PathVariable String eduCourseId) {
        LOGGER.info("ccyy        ]" + ccyy + "[");
        LOGGER.info("userId      ]" + userId + "[");
        LOGGER.info("eduCourseId ]" + eduCourseId + "[");

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("eduCourseId", eduCourseId);

        try {
            eduCoursService.insertRegistedClasses(paramMap);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {e.getMessage()});
        }

        return new BaseResponse<Boolean>(true);
    }

    @GetMapping("/{ccyy}/users/{userId}/registedClasses")
    @ResponseBody
    public BaseResponse<List<EduPlan>> getRegistedClasses(HttpServletRequest request,
            @PathVariable("ccyy") String ccyy, @PathVariable("userId") String userId) {
        LOGGER.info("ccyy ]" + ccyy + "[");
        LOGGER.info("userId ]" + userId + "[");

        List<EduPlan> eduPlanList = new ArrayList<EduPlan>();


        EduPlan eduPlan = null;
        EduClass eduClass = null;
        EduCours eduCours = null;
        List<EduCours> eduCoursList = null;

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("ccyy", ccyy);
        paramMap.put("userId", userId);

        List<PlanDB> planDbList = eduCoursService.getRegistedClasses(paramMap);

        for (PlanDB pd : planDbList) {
            if (eduClass != null) {
                eduPlan = new EduPlan();
                eduPlan.setEduClass(eduClass);
                eduPlan.setEduCours(eduCoursList);
                eduPlanList.add(eduPlan);
            }

            eduClass = new EduClass();

            eduClass.setEduClassId(pd.getEduClassId());
            eduClass.setEduTypeId(pd.getEduTypeId());
            eduClass.setEduClassName(pd.getEduClassName());
            eduClass.setEduClassTargetCd(pd.getEduClassTargetCd());
            eduClass.setEduClassTargetName(pd.getEduClassTargetName());
            eduClass.setEduClassHours(pd.getEduClassHours());
            eduClass.setEduClassHRHours(pd.getEduClassHRHours());
            eduClass.setEduClassFileId(pd.getEduClassFileId());

            eduCours = new EduCours();

            eduCours.setEduCourseId(pd.getEduCourseId());
            eduCours.setEduClassSeq(pd.getEduClassSeq());
            eduCours.setEduLecturerName(pd.getEduLecturerName());
            eduCours.setEduPeriodCd(pd.getEduPeriodCd());
            eduCours.setEduPeriodName(pd.getEduPeriodName());
            eduCours.setEduPlaceCd(pd.getEduPlaceCd());
            eduCours.setEduPlaceName(pd.getEduPlaceName());
            eduCours.setEduStartDt(pd.getEduStartDt());
            eduCours.setEduEndDt(pd.getEduEndDt());
            eduCours.setEduCoursRsltFileId(pd.getEduCoursRsltFileId());
            eduCours.setEduProgress(pd.getEduProgress());
            eduCours.setEduCourseResultId(pd.getEduCourseResultId());
            eduCours.setEduUserId(pd.getEduUserId());

            eduCoursList = new ArrayList<EduCours>();
            eduCoursList.add(eduCours);
        }
        eduPlan = new EduPlan();
        eduPlan.setEduClass(eduClass);
        eduPlan.setEduCours(eduCoursList);
        eduPlanList.add(eduPlan);

        return new BaseResponse<List<EduPlan>>(eduPlanList);
    }

    @PostMapping("/{ccyy}/users/{userId}/takenClasses/{eduCourseId}")
    @ResponseBody
    public BaseResponse<Boolean> postTakenClasses(HttpServletRequest request,
            @PathVariable("ccyy") String ccyy, @PathVariable("userId") String userId,
            @PathVariable String eduCourseId) {
        LOGGER.info("ccyy        ]" + ccyy + "[");
        LOGGER.info("userId      ]" + userId + "[");
        LOGGER.info("eduCourseId ]" + eduCourseId + "[");

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("userId", userId);
        paramMap.put("eduCourseId", eduCourseId);

        try {
            eduCoursService.updateTakenClasses(paramMap);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {e.getMessage()});
        }

        return new BaseResponse<Boolean>(true);
    }

    @GetMapping("/{ccyy}/users/{userId}/takenClasses")
    @ResponseBody
    public BaseResponse<List<EduPlan>> getTakenClasses(HttpServletRequest request,
            @PathVariable("ccyy") String ccyy, @PathVariable("userId") String userId) {
        LOGGER.info("ccyy ]" + ccyy + "[");
        LOGGER.info("userId ]" + userId + "[");

        List<EduPlan> eduPlanList = new ArrayList<EduPlan>();

        EduPlan eduPlan = null;
        EduClass eduClass = null;
        EduCours eduCours = null;
        List<EduCours> eduCoursList = null;

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("ccyy", ccyy);
        paramMap.put("userId", userId);

        List<PlanDB> planDbList = eduCoursService.getTakenClasses(paramMap);

        for (PlanDB pd : planDbList) {
            if (eduClass != null) {
                eduPlan = new EduPlan();
                eduPlan.setEduClass(eduClass);
                eduPlan.setEduCours(eduCoursList);
                eduPlanList.add(eduPlan);
            }

            eduClass = new EduClass();

            eduClass.setEduClassId(pd.getEduClassId());
            eduClass.setEduTypeId(pd.getEduTypeId());
            eduClass.setEduClassName(pd.getEduClassName());
            eduClass.setEduClassTargetCd(pd.getEduClassTargetCd());
            eduClass.setEduClassTargetName(pd.getEduClassTargetName());
            eduClass.setEduClassHours(pd.getEduClassHours());
            eduClass.setEduClassHRHours(pd.getEduClassHRHours());
            eduClass.setEduClassFileId(pd.getEduClassFileId());

            eduCours = new EduCours();

            eduCours.setEduCourseId(pd.getEduCourseId());
            eduCours.setEduClassSeq(pd.getEduClassSeq());
            eduCours.setEduLecturerName(pd.getEduLecturerName());
            eduCours.setEduPeriodCd(pd.getEduPeriodCd());
            eduCours.setEduPeriodName(pd.getEduPeriodName());
            eduCours.setEduPlaceCd(pd.getEduPlaceCd());
            eduCours.setEduPlaceName(pd.getEduPlaceName());
            eduCours.setEduStartDt(pd.getEduStartDt());
            eduCours.setEduEndDt(pd.getEduEndDt());
            eduCours.setEduCoursRsltFileId(pd.getEduCoursRsltFileId());
            eduCours.setEduProgress(pd.getEduProgress());
            eduCours.setEduCourseResultId(pd.getEduCourseResultId());
            eduCours.setEduUserId(pd.getEduUserId());

            eduCoursList = new ArrayList<EduCours>();
            eduCoursList.add(eduCours);
        }
        eduPlan = new EduPlan();
        eduPlan.setEduClass(eduClass);
        eduPlan.setEduCours(eduCoursList);
        eduPlanList.add(eduPlan);


        return new BaseResponse<List<EduPlan>>(eduPlanList);
    }

    @PostMapping("/edu/eduCourses/{ccyy}/users/{userId}/finishedClasses/{eduCourseId}")
    @ResponseBody
    public BaseResponse<Boolean> postFinishedClasses(HttpServletRequest request,
            @PathVariable("ccyy") String ccyy, @PathVariable("userId") String userId,
            @PathVariable("eduCourseId") String eduCourseId,
            @ApiParam @RequestBody EduCoursParameter parameter) {
        LOGGER.info("ccyy        ]" + ccyy + "[");
        LOGGER.info("userId      ]" + userId + "[");
        LOGGER.info("eduCourseId ]" + eduCourseId + "[");

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("eduCourseId", eduCourseId);

        if (StringUtils.isEmpty(parameter.getPlayBackYn())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"playBackYn", "재생종료여부"});
        }

        if (parameter.getPlayBackYn().equals("N") && parameter.getEduProgress() == null
                || parameter.getEduProgress() == 0) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"eduProgress", "progress"});
        }

        try {
            if (parameter.getPlayBackYn().equals("Y")) {
                eduCoursService.updateFinishedClasses(paramMap);
            } else {
                paramMap.put("progress", parameter.getEduProgress());
                eduCoursService.updateProgress(paramMap);
            }
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {e.getMessage()});
        }

        return new BaseResponse<Boolean>(true);
    }

    @GetMapping("/{ccyy}/users/{userId}/finishedClasses")
    @ResponseBody
    public BaseResponse<List<EduPlan>> getFinishedClasses(HttpServletRequest request,
            @PathVariable String ccyy, @PathVariable String userId) {
        LOGGER.info("ccyy ]" + ccyy + "[");
        LOGGER.info("userId ]" + userId + "[");

        List<EduPlan> eduPlanList = new ArrayList<EduPlan>();

        EduPlan eduPlan = null;
        EduClass eduClass = null;
        EduCours eduCours = null;
        List<EduCours> eduCoursList = null;

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("ccyy", ccyy);
        paramMap.put("userId", userId);

        List<PlanDB> planDbList = eduCoursService.getFinishedClasses(paramMap);

        for (PlanDB pd : planDbList) {
            if (eduClass != null) {
                eduPlan = new EduPlan();
                eduPlan.setEduClass(eduClass);
                eduPlan.setEduCours(eduCoursList);
                eduPlanList.add(eduPlan);
            }

            eduClass = new EduClass();

            eduClass.setEduClassId(pd.getEduClassId());
            eduClass.setEduTypeId(pd.getEduTypeId());
            eduClass.setEduClassName(pd.getEduClassName());
            eduClass.setEduClassTargetCd(pd.getEduClassTargetCd());
            eduClass.setEduClassTargetName(pd.getEduClassTargetName());
            eduClass.setEduClassHours(pd.getEduClassHours());
            eduClass.setEduClassHRHours(pd.getEduClassHRHours());
            eduClass.setEduClassFileId(pd.getEduClassFileId());

            eduCours = new EduCours();

            eduCours.setEduCourseId(pd.getEduCourseId());
            eduCours.setEduClassSeq(pd.getEduClassSeq());
            eduCours.setEduLecturerName(pd.getEduLecturerName());
            eduCours.setEduPeriodCd(pd.getEduPeriodCd());
            eduCours.setEduPeriodName(pd.getEduPeriodName());
            eduCours.setEduPlaceCd(pd.getEduPlaceCd());
            eduCours.setEduPlaceName(pd.getEduPlaceName());
            eduCours.setEduStartDt(pd.getEduStartDt());
            eduCours.setEduEndDt(pd.getEduEndDt());
            eduCours.setEduCoursRsltFileId(pd.getEduCoursRsltFileId());
            eduCours.setEduProgress(pd.getEduProgress());
            eduCours.setEduCourseResultId(pd.getEduCourseResultId());
            eduCours.setEduUserId(pd.getEduUserId());

            eduCoursList = new ArrayList<EduCours>();
            eduCoursList.add(eduCours);
        }
        eduPlan = new EduPlan();
        eduPlan.setEduClass(eduClass);
        eduPlan.setEduCours(eduCoursList);
        eduPlanList.add(eduPlan);

        return new BaseResponse<List<EduPlan>>(eduPlanList);
    }

    @GetMapping("/{ccyy}/users/{userId}/takenClasses/{eduCourseId}/certificate")
    @ResponseBody
    public BaseResponse<String> getTakenClassesCert(HttpServletRequest request,
            @PathVariable String ccyy, @PathVariable String userId,
            @PathVariable String eduCourseId) {
        String cert = "";

        LOGGER.info("ccyy        ]" + ccyy + "[");
        LOGGER.info("userId      ]" + userId + "[");
        LOGGER.info("eduCourseId ]" + eduCourseId + "[");

        try {
            cert = "<html>Hello.<br>This is a cert content.</html>";
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {e.getMessage()});
        }

        return new BaseResponse<String>(cert);
    }
}
