package egovframework.com.domain.education.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.com.domain.education.domain.EduType;
import egovframework.com.domain.education.parameter.EduTypeParameter;
import egovframework.com.domain.education.parameter.EduTypeSearchParameter;

@Repository
public class EduTypeDAOImpl implements EduTypeDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String Namespace = "egovframework.com.domain.education.dao.EduTypeDAO";

    @Override
    public Long getListCount() {
        return sqlSession.selectOne(Namespace + ".getListCount");
    }

    @Override
    public List<EduType> getList(EduTypeSearchParameter param) {
        return sqlSession.selectList(Namespace + ".getList", param);
    }

    @Override
    public List<EduType> getListExcel(EduTypeSearchParameter param) {
        return sqlSession.selectList(Namespace + ".getListExcel", param);
    }

    @Override
    public EduType get(Long id) {
        return sqlSession.selectOne(Namespace + ".get", id);
    }

    @Override
    public int insert(EduTypeParameter param) {
        return sqlSession.insert(Namespace + ".insert", param);

    }

    @Override
    public int update(EduTypeParameter param) {
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
