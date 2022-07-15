package egovframework.com.domain.education.service;

import java.util.List;
import java.util.Map;
import egovframework.com.domain.education.domain.EduCours;
import egovframework.com.domain.education.domain.PlanDB;
import egovframework.com.domain.education.parameter.EduCoursParameter;
import egovframework.com.domain.education.parameter.EduCoursSearchParameter;

public interface EduCoursService {

    public Long getListCount(EduCoursSearchParameter param);

    public List<EduCours> getList(EduCoursSearchParameter param);

    public List<EduCours> getListExcel(EduCoursSearchParameter param);

    public EduCours get(Long id);

    public void insert(EduCoursParameter param);

    public void update(EduCoursParameter param);

    public void delete(Long id);

    public void deleteArr(List<Long> idList);

    public List<PlanDB> getEduCourse(String ccyy);

    public List<PlanDB> getEduCCoursesByEduType(String ccyy, String eduTypeId);

    public void insertRegistedClasses(Map<String, Object> paramMap);

    public void updateTakenClasses(Map<String, String> paramMap);

    public void updateFinishedClasses(Map<String, Object> paramMap);

    public void updateProgress(Map<String, Object> paramMap);

    public List<PlanDB> getRegistedClasses(Map<String, String> paramMap);

    public List<PlanDB> getTakenClasses(Map<String, String> paramMap);

    public List<PlanDB> getFinishedClasses(Map<String, String> paramMap);

}
