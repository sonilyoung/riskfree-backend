package egovframework.com.domain.portal.system.service;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import egovframework.com.domain.org.domain.UserVO;
import egovframework.com.domain.org.service.UserManageService;
import egovframework.com.domain.portal.system.dao.SystemDAO;
import egovframework.com.domain.portal.system.domain.SystemMenu;
import egovframework.com.global.AbstractServiceImpl;
import egovframework.com.global.genid.GenIdService;


/**
 * 시스템메뉴 관리를 위한 서비스 구현 클래스
 * 
 * @author 박선희
 * @since 2018.08.13
 * 
 * 
 */
@Service("SystemService")
public class SystemServiceImpl extends AbstractServiceImpl implements SystemService {

    @Autowired
    private SystemDAO systemDAO;

    private GenIdService genIdService;

    private UserManageService userService;

    @Override
    public List<SystemMenu> getSystemList() throws Exception {

        List<SystemMenu> result = systemDAO.selectSystemList();
        return result;
    }

    @Override
    public SystemMenu getSystem(String id) throws Exception {

        return systemDAO.selectSystem(id);
    }

    @Override
    public void insertSystem(SystemMenu system, String companyId) throws Exception {
        systemDAO.insertSystem(system);

        // 기관 사용자 목록 조회
        List<UserVO> userManageList = null;// userService.getAllUserListByCompany(companyId);

        for (UserVO user : userManageList) {
            SystemMenu saveSystem = new SystemMenu();
            String userSystemId = genIdService.selectPrtlSystemNextId();
            saveSystem.setUserSystemId(userSystemId);
            saveSystem.setSystemId(system.getSystemId());
            saveSystem.setEsntlId(user.getUserUniqId());
            int seq = systemDAO.selectMaxUserSystemDisplayOrder(user.getUserUniqId());
            saveSystem.setSeq(seq + 1);
            systemDAO.updateUserSystem(saveSystem);
        }
    }

    @Override
    public void updateSystem(SystemMenu system) throws Exception {
        systemDAO.updateSystem(system);
    }

    @Transactional
    public void deleteSystem(HashMap<String, Object> deleteSystems) throws Exception {
        systemDAO.deleteSystem(deleteSystems);
        systemDAO.deleteUserSystem(deleteSystems);
    }

    @Override
    public List<SystemMenu> getSystemListByUserId(String userId) throws Exception {
        return systemDAO.selectSystemListByUserId(userId);
    }

}
