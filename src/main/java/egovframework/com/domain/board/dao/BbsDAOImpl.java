package egovframework.com.domain.board.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.com.domain.board.domain.BbsMssg;
import egovframework.com.domain.board.domain.BbsMssgBody;
import egovframework.com.domain.board.parameter.BbsMssgBodyParameter;
import egovframework.com.domain.board.parameter.BbsMssgSearchParameter;

@Repository
public class BbsDAOImpl implements BbsDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String Namespace = "egovframework.com.domain.board.dao.BbsDAO";

    @Override
    public Long getMssgListCount(BbsMssgSearchParameter param) {
        return sqlSession.selectOne(Namespace + ".getMssgListCount", param);
    }

    @Override
    public List<BbsMssg> getMssgList(BbsMssgSearchParameter param) {
        return sqlSession.selectList(Namespace + ".getMssgList", param);
    }

    @Override
    public List<BbsMssg> getMssgListExcel(BbsMssgSearchParameter param) {
        return sqlSession.selectList(Namespace + ".getMssgListExcel", param);
    }

    @Override
    public BbsMssgBody getMssg(Long bbsBoardId, Long bbsMssgId) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("bbsBoardId", bbsBoardId);
        map.put("bbsMssgId", bbsMssgId);
        return sqlSession.selectOne(Namespace + ".getMssg", map);
    }

    @Override
    public int insertMssg(BbsMssgBodyParameter param) {
        return sqlSession.insert(Namespace + ".insertMssg", param);

    }

    @Override
    public int updateMssg(BbsMssgBodyParameter param) {
        return sqlSession.update(Namespace + ".updateMssg", param);

    }

    @Override
    public int deleteMssg(Long bbsBoardId, Long bbsMssgId) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("bbsBoardId", bbsBoardId);
        map.put("bbsMssgId", bbsMssgId);
        return sqlSession.delete(Namespace + ".deleteMssg", map);
    }

    @Override
    public int deleteArrMssg(Long bbsBoardId, List<Long> idList) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("bbsBoardId", bbsBoardId);
        map.put("idList", idList);
        return sqlSession.delete(Namespace + ".deleteArrMssg", map);
    }

}
