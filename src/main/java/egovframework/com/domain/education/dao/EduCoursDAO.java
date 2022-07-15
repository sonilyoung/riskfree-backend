package egovframework.com.domain.education.dao;

import java.util.List;
import java.util.Map;
import egovframework.com.domain.education.domain.EduCours;
import egovframework.com.domain.education.domain.PlanDB;
import egovframework.com.domain.education.parameter.EduCoursParameter;
import egovframework.com.domain.education.parameter.EduCoursSearchParameter;

public interface EduCoursDAO {
    Long getListCount(EduCoursSearchParameter param);

    List<EduCours> getList(EduCoursSearchParameter param);

    List<EduCours> getListExcel(EduCoursSearchParameter param);

    EduCours get(Long id);

    int insert(EduCoursParameter param);

    int update(EduCoursParameter param);

    int delete(Long id);

    int deleteArr(List<Long> idList);

    List<PlanDB> getEduCourse(String ccyy);

    List<PlanDB> getEduCCoursesByEduType(String ccyy, String eduTypeId);

    int insertRegistedClasses(Map<String, Object> paramMap);

    int updateTakenClasses(Map<String, String> paramMap);

    int updateFinishedClasses(Map<String, Object> paramMap);

    int updateProgress(Map<String, Object> paramMap);

    List<PlanDB> getRegistedClasses(Map<String, String> paramMap);

    List<PlanDB> getTakenClasses(Map<String, String> paramMap);

    List<PlanDB> getFinishedClasses(Map<String, String> paramMap);
}
