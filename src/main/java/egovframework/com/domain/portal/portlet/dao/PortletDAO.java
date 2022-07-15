
package egovframework.com.domain.portal.portlet.dao;

import java.util.HashMap;
import java.util.List;
import egovframework.com.domain.portal.portlet.domain.Portlet;

/**
 * 포틀릿 관리를 위한 데이터 접근 클래스
 * 
 * @author 박선희
 * @since 2018.08.13
 * 
 */
public interface PortletDAO {

    @SuppressWarnings("unchecked")
    public List<Portlet> selectPortletList(Portlet portlet) throws Exception;

    public Portlet selectPortlet(String id) throws Exception;

    public int insertPortlet(Portlet portlet);

    public int selectMaxUserPortletDisplayOrder(String userUniqId) throws Exception;

    public int updatePortlet(Portlet portlet);

    public List<Portlet> selectUserPortletList(String userId);

    public List<Portlet> selectUserPortletList_4(String userId);

    public List<Portlet> selectAvailablePortletList(String userId) throws Exception;

    public int updateUserPortlet(Portlet portlet);

    public int deleteUserPortlet(Portlet portlet);

    public int deletePortlet(HashMap<String, Object> deletePortlets);

    public int deleteUserPortletList(HashMap<String, Object> deletePortlets);
}
