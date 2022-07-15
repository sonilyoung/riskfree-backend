package egovframework.com.domain.portal.portlet.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import egovframework.com.domain.org.domain.UserVO;
import egovframework.com.domain.org.service.UserManageService;
import egovframework.com.domain.portal.portlet.dao.PortletDAO;
import egovframework.com.domain.portal.portlet.domain.Portlet;
import egovframework.com.global.AbstractServiceImpl;
import egovframework.com.global.genid.GenIdService;


/**
 * 포틀릿 관리를 위한 서비스 구현 클래스
 * 
 * @author 박선희
 * @since 2018.08.14
 * 
 * 
 */
@Service("PortletService")
public class PortletServiceImpl extends AbstractServiceImpl implements PortletService {

    @Autowired
    private PortletDAO portletDAO;

    @Autowired
    private UserManageService userService;

    private GenIdService genIdService;

    public List<Portlet> getPortletList(Portlet portlet) throws Exception {

        List<Portlet> result = portletDAO.selectPortletList(portlet);
        return result;
    }

    public Portlet getPortlet(String id) throws Exception {

        return portletDAO.selectPortlet(id);
    }

    public void insertPortlet(Portlet portlet, String companyId) throws Exception {

        portletDAO.insertPortlet(portlet);

        // 기관 사용자 목록 조회
        List<UserVO> userManageList = null;// userService.getAllUserListByCompany(companyId);

        for (UserVO user : userManageList) {
            Portlet savePortlet = new Portlet();
            String userPortletId = genIdService.selectPrtlPortletNextId();
            savePortlet.setUserPortletId(userPortletId);
            savePortlet.setPortletId(portlet.getPortletId());
            savePortlet.setEsntlId(user.getUserUniqId());
            savePortlet.setDisplayTypeCd(portlet.getDisplayTypeCd());
            int seq = portletDAO.selectMaxUserPortletDisplayOrder(user.getUserUniqId());
            savePortlet.setDisplayOrder(seq + 1);
            portletDAO.updateUserPortlet(savePortlet);
        }
    }

    public void updatePortlet(Portlet portlet) throws Exception {

        portletDAO.updatePortlet(portlet);
    }

    public List<Portlet> getUserPortletList(String userId) throws Exception {
        List<Portlet> result = portletDAO.selectUserPortletList(userId);
        Collections.sort(result, new Comparator<Portlet>() {
            public int compare(Portlet o1, Portlet o2) {
                return o1.getDisplayOrder() - o2.getDisplayOrder();
            }
        });
        return result;

    }

    public List<Portlet> getUserPortletList_4(String userId) throws Exception {
        List<Portlet> result = portletDAO.selectUserPortletList_4(userId);
        Collections.sort(result, new Comparator<Portlet>() {
            public int compare(Portlet o1, Portlet o2) {
                return o1.getDisplayOrder() - o2.getDisplayOrder();
            }
        });
        return result;

    }

    @Transactional
    public void deletePortlet(HashMap<String, Object> deletePortlets) throws Exception {
        portletDAO.deletePortlet(deletePortlets);
        portletDAO.deleteUserPortletList(deletePortlets);
    }

}
