package egovframework.com.domain.portal.system.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.com.domain.portal.system.domain.SystemMenu;

/**
 * 시스템메뉴 관리를 위한 데이터 접근 클래스
 * 
 * @author 박선희
 * @since 2018.08.13
 * 
 */
@Repository
public class SystemDAOImpl implements SystemDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String Namespace =
            "egovframework.com.domain.portal.logn.mapper.loginMapper";

    public List<SystemMenu> selectSystemList() throws Exception {
        return sqlSession.selectList(Namespace + "selectSystemList");
    }

    public SystemMenu selectSystem(String id) throws Exception {
        return (SystemMenu) sqlSession.selectOne(Namespace + "SystemDAO.selectSystem", id);
    }

    public int insertSystem(SystemMenu system) {
        return sqlSession.insert(Namespace + "SystemDAO.insertSystem", system);
    }

    public int selectMaxUserSystemDisplayOrder(String userUniqId) throws Exception {
        return sqlSession.selectOne(Namespace + "SystemDAO.selectMaxUserSystemDisplayOrder",
                userUniqId);
    }

    public int updateSystem(SystemMenu system) {
        return sqlSession.update(Namespace + "SystemDAO.updateSystem", system);
    }

    public int deleteSystem(HashMap<String, Object> deleteSystems) {
        return sqlSession.update(Namespace + "SystemDAO.deleteSystem", deleteSystems);
    }

    public int deleteUserSystem(HashMap<String, Object> deleteSystems) {
        return sqlSession.update(Namespace + "SystemDAO.deleteUserSystem", deleteSystems);
    }

    public int deleteUserSystemByUserId(SystemMenu system) {
        return sqlSession.delete(Namespace + "SystemDAO.deleteUserSystemByUserId", system);
    }

    public List<SystemMenu> selectSystemListByUserId(String userId) {
        return sqlSession.selectList(Namespace + "selectSystemListByUserId", userId);
    }

    public List<SystemMenu> selectAvailableSystemList(String userId) throws Exception {
        return sqlSession.selectList(Namespace + "selectAvailableSystems", userId);
    }

    public int updateUserSystem(SystemMenu system) {
        return sqlSession.insert(Namespace + "SystemDAO.updateUserSystem", system);
    }
}
