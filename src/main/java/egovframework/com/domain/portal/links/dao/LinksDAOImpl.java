package egovframework.com.domain.portal.links.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.com.domain.portal.links.domain.Links;

/**
 * Links 관리를 위한 데이터 접근 클래스
 * 
 * @author
 * @since
 * 
 */
@Repository
public class LinksDAOImpl implements LinksDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String Namespace =
            "egovframework.com.domain.portal.logn.mapper.loginMapper";

    public List<Links> selectLinksList(Links links) throws Exception {
        return sqlSession.selectList(Namespace + "LinksDAO.selectLinksList", links);
    }

    public Links selectLinksDetail(Links linksd) throws Exception {
        return (Links) sqlSession.selectList(Namespace + "LinksDAO.selectLinksDetail", linksd);
    }

    public int insertLinks(Links links) {
        return sqlSession.insert("LinksDAO.insertLinks", links);
    }

    public int updateLinks(Links links) {
        return sqlSession.update("LinksDAO.updateLinks", links);
    }

    public int deleteLinks(HashMap<String, Object> deleteLinks) {
        return sqlSession.delete("LinksDAO.deleteLinks", deleteLinks);
    }
}
