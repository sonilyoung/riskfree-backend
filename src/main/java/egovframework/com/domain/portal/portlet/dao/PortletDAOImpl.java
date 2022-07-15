
package egovframework.com.domain.portal.portlet.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.com.domain.portal.portlet.domain.Portlet;

/**
 * 포틀릿 관리를 위한 데이터 접근 클래스
 * 
 * @author 박선희
 * @since 2018.08.13
 * 
 */
@Repository
public class PortletDAOImpl implements PortletDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String Namespace =
            "egovframework.com.domain.portal.logn.mapper.loginMapper";

    public List<Portlet> selectPortletList(Portlet portlet) throws Exception {
        return sqlSession.selectList(Namespace + "selectPortletList", portlet);
    }

    public Portlet selectPortlet(String id) throws Exception {
        return (Portlet) sqlSession.selectOne(Namespace + "PortletDAO.selectPortlet", id);
    }

    public int insertPortlet(Portlet portlet) {
        return sqlSession.insert("PortletDAO.insertPortlet", portlet);
    }

    public int selectMaxUserPortletDisplayOrder(String userUniqId) throws Exception {
        return sqlSession.selectOne("PortletDAO.selectMaxUserPortletDisplayOrder", userUniqId);
    }

    public int updatePortlet(Portlet portlet) {
        return sqlSession.insert("PortletDAO.updatePortlet", portlet);
    }

    public List<Portlet> selectUserPortletList(String userId) {
        return sqlSession.selectList(Namespace + "selectUserPortletList", userId);
    }

    public List<Portlet> selectUserPortletList_4(String userId) {
        return sqlSession.selectList(Namespace + "selectUserPortletList_4", userId);
    }

    public List<Portlet> selectAvailablePortletList(String userId) throws Exception {
        return sqlSession.selectList(Namespace + "selectAvailablePortlets", userId);
    }

    public int updateUserPortlet(Portlet portlet) {
        return sqlSession.insert("PortletDAO.updateUserPortlet", portlet);
    }

    public int deleteUserPortlet(Portlet portlet) {
        return sqlSession.delete("PortletDAO.deleteUserPortlet", portlet);
    }

    public int deletePortlet(HashMap<String, Object> deletePortlets) {
        return sqlSession.update("PortletDAO.deletePortlet", deletePortlets);
    }

    public int deleteUserPortletList(HashMap<String, Object> deletePortlets) {
        return sqlSession.update("PortletDAO.deleteUserPortletList", deletePortlets);
    }
}
