package egovframework.com.domain.education.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.com.domain.education.domain.EduCours;
import egovframework.com.domain.education.domain.PlanDB;
import egovframework.com.domain.education.parameter.EduCoursParameter;
import egovframework.com.domain.education.parameter.EduCoursSearchParameter;

@Repository
public class EduCoursDAOImpl implements EduCoursDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String Namespace = "egovframework.com.domain.education.dao.EduCoursDAO";

    @Override
    public Long getListCount(EduCoursSearchParameter param) {
        return sqlSession.selectOne(Namespace + ".getListCount", param);
    }

    @Override
    public List<EduCours> getList(EduCoursSearchParameter param) {
        return sqlSession.selectList(Namespace + ".getList", param);
    }

    @Override
    public List<EduCours> getListExcel(EduCoursSearchParameter param) {
        return sqlSession.selectList(Namespace + ".getListExcel", param);
    }

    @Override
    public EduCours get(Long id) {
        return sqlSession.selectOne(Namespace + ".get", id);
    }

    @Override
    public int insert(EduCoursParameter param) {
        return sqlSession.insert(Namespace + ".insert", param);

    }

    @Override
    public int update(EduCoursParameter param) {
        return sqlSession.update(Namespace + ".update", param);

    }

    @Override
    public int delete(Long id) {
        return sqlSession.delete(Namespace + ".delete", id);
    }

    @Override
    public int deleteArr(List<Long> idList) {
        return sqlSession.delete(Namespace + ".deleteArr", idList);
    }

    @Override
    public List<PlanDB> getEduCourse(String ccyy) {
        return sqlSession.selectList(Namespace + ".getEduCourse", ccyy);
    }

    @Override
    public List<PlanDB> getEduCCoursesByEduType(String ccyy, String eduTypeId) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ccyy", ccyy);
        map.put("eduTypeId", eduTypeId);
        return sqlSession.selectList(Namespace + ".getEduCCoursesByEduType", map);
    }

    @Override
    public int insertRegistedClasses(Map<String, Object> paramMap) {
        return sqlSession.insert(Namespace + ".insertRegistedClasses", paramMap);
    }

    @Override
    public int updateTakenClasses(Map<String, String> paramMap) {
        return sqlSession.update(Namespace + ".updateTakenClasses", paramMap);
    }

    @Override
    public int updateFinishedClasses(Map<String, Object> paramMap) {
        return sqlSession.update(Namespace + ".updateFinishedClasses", paramMap);
    }

    @Override
    public int updateProgress(Map<String, Object> paramMap) {
        return sqlSession.update(Namespace + ".updateProgress", paramMap);
    }

    @Override
    public List<PlanDB> getRegistedClasses(Map<String, String> paramMap) {
        return sqlSession.selectList(Namespace + ".getRegistedClasses", paramMap);
    }

    @Override
    public List<PlanDB> getTakenClasses(Map<String, String> paramMap) {
        return sqlSession.selectList(Namespace + ".getTakenClasses", paramMap);
    }

    @Override
    public List<PlanDB> getFinishedClasses(Map<String, String> paramMap) {
        return sqlSession.selectList(Namespace + ".getFinishedClasses", paramMap);
    }

}
