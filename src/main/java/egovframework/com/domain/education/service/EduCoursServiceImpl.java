package egovframework.com.domain.education.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import egovframework.com.domain.education.dao.EduCoursDAO;
import egovframework.com.domain.education.domain.EduCours;
import egovframework.com.domain.education.domain.PlanDB;
import egovframework.com.domain.education.parameter.EduCoursParameter;
import egovframework.com.domain.education.parameter.EduCoursSearchParameter;

@Service
@Transactional(readOnly = true)
public class EduCoursServiceImpl implements EduCoursService {

    @Autowired
    private EduCoursDAO repository;

    /**
     * 목록 갯수
     * 
     * @param param
     * @return
     */
    @Override
    public Long getListCount(EduCoursSearchParameter param) {
        return repository.getListCount(param);
    }

    /**
     * 목록 리턴
     * 
     * @param param
     * @return
     */
    @Override
    public List<EduCours> getList(EduCoursSearchParameter param) {
        return repository.getList(param);
    }

    /**
     * 엑셀용 목록 리턴
     */
    @Override
    public List<EduCours> getListExcel(EduCoursSearchParameter param) {
        return repository.getListExcel(param);
    }

    /**
     * 상세정보 리턴
     * 
     * @param Seq
     * @return
     */
    @Override
    public EduCours get(Long id) {
        return repository.get(id);
    }

    /**
     * 등록처리
     * 
     * @param param
     */
    @Override
    @Transactional
    public void insert(EduCoursParameter param) {
        repository.insert(param);

    }

    /**
     * 수정처리
     * 
     * @param param
     */
    @Override
    @Transactional
    public void update(EduCoursParameter param) {
        repository.update(param);
    }

    /**
     * 삭제처리
     * 
     * @param id
     */
    @Override
    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    /**
     * 삭제처리(여러개)
     */
    @Override
    public void deleteArr(List<Long> idList) {
        repository.deleteArr(idList);
    }

    /**
     * 년도별 Plan 목록 리턴
     * 
     * @param ccyy
     */
    @Override
    public List<PlanDB> getEduCourse(String ccyy) {
        return repository.getEduCourse(ccyy);
    }


    /**
     * 년도, 교육 분류별 Plan 목록
     * 
     * @param ccyy, eduTypeId
     */
    @Override
    public List<PlanDB> getEduCCoursesByEduType(String ccyy, String eduTypeId) {
        return repository.getEduCCoursesByEduType(ccyy, eduTypeId);
    }

    /**
     * 내 수강목록 등록처리
     * 
     * @param paramMap
     */
    @Override
    public void insertRegistedClasses(Map<String, Object> paramMap) {
        repository.insertRegistedClasses(paramMap);

    }

    /**
     * 수강신청한 교육 코스 수강 시작
     * 
     * @param paramMap
     */
    @Override
    public void updateTakenClasses(Map<String, String> paramMap) {
        repository.updateTakenClasses(paramMap);
    }

    /**
     * 교육 코스 수강 완료
     * 
     * @param paramMap
     */
    @Override
    public void updateFinishedClasses(Map<String, Object> paramMap) {
        repository.updateFinishedClasses(paramMap);
    }

    /**
     * 교육 코스 수강 프로그래스 업데이트
     * 
     * @param paramMap
     */
    @Override
    public void updateProgress(Map<String, Object> paramMap) {
        repository.updateProgress(paramMap);
    }

    /**
     * 사용자가 수강 신청한 교육 코스 리턴
     * 
     * @param paramMap
     */
    @Override
    public List<PlanDB> getRegistedClasses(Map<String, String> paramMap) {
        return repository.getRegistedClasses(paramMap);
    }

    /**
     * 사용자가 수강중인 교육 코스 리턴
     * 
     * @param paramMap
     */
    @Override
    public List<PlanDB> getTakenClasses(Map<String, String> paramMap) {
        return repository.getTakenClasses(paramMap);
    }

    /**
     * 사용자가 수강완료한 교육 코스 리턴
     * 
     * @param paramMap
     */
    @Override
    public List<PlanDB> getFinishedClasses(Map<String, String> paramMap) {
        return repository.getFinishedClasses(paramMap);
    }
}
