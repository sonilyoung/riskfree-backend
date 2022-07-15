package egovframework.com.domain.board.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.com.domain.board.domain.FreeBoard;
import egovframework.com.domain.board.parameter.FreeBoardParameter;
import egovframework.com.domain.board.parameter.FreeBoardSearchParameter;

@Repository
public class FreeBoardDAOImpl implements FreeBoardDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String Namespace = "egovframework.com.domain.board.dao.FreeBoardDAO";

    @Override
    public Long getListCount(FreeBoardSearchParameter parameter) {
        return sqlSession.selectOne(Namespace + ".getListCount", parameter);
    }

    @Override
    public List<FreeBoard> getList(FreeBoardSearchParameter parameter) {
        return sqlSession.selectList(Namespace + ".getList", parameter);
    }

    @Override
    public List<FreeBoard> getListExcel(FreeBoardSearchParameter parameter) {
        return sqlSession.selectList(Namespace + ".getListExcel", parameter);
    }

    @Override
    public FreeBoard get(Long boardSeq) {
        return sqlSession.selectOne(Namespace + ".get", boardSeq);
    }

    @Override
    public int insert(FreeBoardParameter board) {
        return sqlSession.insert(Namespace + ".insert", board);

    }

    @Override
    public int update(FreeBoardParameter board) {
        return sqlSession.update(Namespace + ".update", board);

    }

    @Override
    public int delete(Long boardSeq) {
        return sqlSession.delete(Namespace + ".delete", boardSeq);
    }

    @Override
    public int deleteArr(List<Long> boardSeq) {
        return sqlSession.delete(Namespace + ".deleteArr", boardSeq);
    }

}
