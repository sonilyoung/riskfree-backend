package egovframework.com.domain.commcode.dao;

import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import egovframework.com.domain.commcode.domain.CommCode;
import egovframework.com.domain.commcode.parameter.CommCodeSearchParameter;

/**
 * 공통 코드 DAO
 *
 * @fileName : CommCodeDAOImpl.java
 * @author : YeongJun Lee
 * @date : 2022.04.05
 */
@Repository
public class CommCodeDAOImpl implements CommCodeDAO {
    @Inject
    private SqlSession sqlSession;

    private static final String Namespace = "egovframework.com.domain.commcode.dao.CommCodeDAO";

    @Override
    public List<CommCode> getCommCodeList(CommCodeSearchParameter param) {
        return sqlSession.selectList(Namespace + ".getCommCodeList", param);
    }

    @Override
    public List<CommCode> getCSCCodeList(CommCodeSearchParameter param) {
        return sqlSession.selectList(Namespace + ".getCSCCodeList", param);
    }
}
