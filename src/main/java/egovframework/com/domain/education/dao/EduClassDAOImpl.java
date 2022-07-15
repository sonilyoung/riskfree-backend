package egovframework.com.domain.education.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.com.domain.education.domain.EduClass;
import egovframework.com.domain.education.parameter.EduClassParameter;
import egovframework.com.domain.education.parameter.EduClassSearchParameter;

@Repository
public class EduClassDAOImpl implements EduClassDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String Namespace = "egovframework.com.domain.education.dao.EduClassDAO";

    @Override
    public Long getListCount(EduClassSearchParameter param) {
        return sqlSession.selectOne(Namespace + ".getListCount", param);
    }

    @Override
    public List<EduClass> getList(EduClassSearchParameter param) {
        return sqlSession.selectList(Namespace + ".getList", param);
    }

    @Override
    public List<EduClass> getListExcel(EduClassSearchParameter param) {
        return sqlSession.selectList(Namespace + ".getListExcel", param);
    }

    @Override
    public EduClass get(Long id) {
        return sqlSession.selectOne(Namespace + ".get", id);
    }

    @Override
    public int insert(EduClassParameter param) {
        return sqlSession.insert(Namespace + ".insert", param);

    }

    @Override
    public int update(EduClassParameter param) {
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
}
