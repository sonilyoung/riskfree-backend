package egovframework.com.domain.portal.system.dao;

import java.util.HashMap;
import java.util.List;
import egovframework.com.domain.portal.system.domain.SystemMenu;

/**
 * 시스템메뉴 관리를 위한 데이터 접근 클래스
 * 
 * @author 박선희
 * @since 2018.08.13
 * 
 */
public interface SystemDAO {

    public List<SystemMenu> selectSystemList() throws Exception;

    public SystemMenu selectSystem(String id) throws Exception;

    public int insertSystem(SystemMenu system);

    public int selectMaxUserSystemDisplayOrder(String userUniqId) throws Exception;

    public int updateSystem(SystemMenu system);

    public int deleteSystem(HashMap<String, Object> deleteSystems);

    public int deleteUserSystem(HashMap<String, Object> deleteSystems);

    public int deleteUserSystemByUserId(SystemMenu system);

    public List<SystemMenu> selectSystemListByUserId(String userId);

    public List<SystemMenu> selectAvailableSystemList(String userId) throws Exception;

    public int updateUserSystem(SystemMenu system);
}
