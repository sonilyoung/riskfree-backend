package egovframework.com.domain.org.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import egovframework.com.domain.org.dao.OrgnztManageDAO;
import egovframework.com.domain.org.domain.DutyVO;
import egovframework.com.domain.org.domain.OrgnztVO;
import egovframework.com.domain.org.domain.UserVO;
import egovframework.com.global.AbstractServiceImpl;
import egovframework.com.global.DataSource;
import egovframework.com.global.common.domain.ComDefaultConstants;
import egovframework.com.global.common.domain.TreeNodeVO;


/**
 * 조직 관리를 처리하는 비즈니스 인터페이스 클래스
 * 
 * @author paul
 * @since 2016.02.04
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일           수정자          수정내용
 *  -------    ---------    ---------------------------
 *  2016.02.04   paul        최초 생성
 *  2017.04.05   suji.h      변수명 정리
 *      </pre>
 */
@Service("OrgnztManageService")
public class OrgnztManageServiceImpl extends AbstractServiceImpl implements OrgnztManageService {

    @Autowired
    private OrgnztManageDAO orgnztManageDAO;

    /*
     * Get organization information
     */
    @Override
    public OrgnztVO getOrgnzt(String orgnztId) throws Exception {
        OrgnztVO vo = new OrgnztVO();
        vo.setOrgnztId(orgnztId);

        return orgnztManageDAO.selectOrgnzt(vo);
    }

    public List<OrgnztVO> getOrgnztList(String orgnztId) throws Exception {
        return orgnztManageDAO.selectOrgList(orgnztId);
    }

    public List<OrgnztVO> getOrgnztInfoList(String orgnztId) throws Exception {
        return orgnztManageDAO.selectOrgnztInfoList(orgnztId);
    }

    @Override
    @DataSource("COMM")
    public List<OrgnztVO> getOrgnztInfoListForPki(String orgID) throws Exception {
        // TODO Auto-generated method stub
        return orgnztManageDAO.selectOrgnztInfoListForPki(orgID);
    }

    /*
     * Get department information as a tree type
     */
    @Override
    public List<OrgnztVO> getDeptTree(String orgnztId) throws Exception {
        List<OrgnztVO> deptTree = new ArrayList<OrgnztVO>();
        OrgnztVO vo = new OrgnztVO();
        vo.setOrgnztId(orgnztId);

        OrgnztVO deptInfo = orgnztManageDAO.selectOrgnzt(vo);
        deptTree.add(0, deptInfo);
        deptTree.addAll(orgnztManageDAO.selectDeptTree(vo));
        return deptTree;
    }

    /*
     * Show list of departments by tree type
     */
    @Override
    public List<OrgnztVO> getOrgnztList(String communityID, String base, int scope)
            throws Exception {
        List<OrgnztVO> list = null;
        if (scope == ComDefaultConstants.SCOPE_PATH) {
            list = orgnztManageDAO.selectDeptTree(communityID, communityID, scope);

            List<OrgnztVO> newlist = null;
            boolean foundBase = false;
            int baseDeptDepth = 0;

            for (Object obj : list) {
                OrgnztVO orgnzt = (OrgnztVO) obj;
                if (orgnzt.getOrgnztLevel() == 1) {
                    // foundBase = false;
                    if (foundBase == true) {
                        break;
                    } else {
                        newlist = new ArrayList<OrgnztVO>();
                    }
                }

                if (foundBase) {
                    if (baseDeptDepth >= orgnzt.getOrgnztLevel()) {
                        baseDeptDepth = Integer.MAX_VALUE;
                    }
                }

                if (newlist != null) {
                    newlist.add(orgnzt);
                }

                if (StringUtils.equals(orgnzt.getOrgnztId(), base)) {
                    foundBase = true;
                    baseDeptDepth = orgnzt.getOrgnztLevel();
                }
            }
            list = newlist;
        } else {
            list = orgnztManageDAO.selectDeptTree(communityID, base, scope);
        }
        return list;
    }

    /*
     * Show list of External Orgnzt by tree type
     */
    @Override
    public List<OrgnztVO> getExternalOrgnztList(String communityID, String base, int scope,
            String notShowOrgnzt) throws Exception {
        List<OrgnztVO> list = null;
        list = orgnztManageDAO.selectDeptTree(communityID, base, scope);
        if (StringUtils.isNotBlank(notShowOrgnzt)) {
            // 보여주지 않을 조직은 삭제한다
            for (int i = 0; i < list.size(); i++) {
                if (notShowOrgnzt.equals(list.get(i).getOrgnztId())
                        || notShowOrgnzt.equals(list.get(i).getOrgnztParId())) {
                    list.remove(i);
                }
            }
        }
        return list;
    }

    /*
     * Get information from the top level department
     */
    @Override
    public OrgnztVO getTopOrgnzt(String orgnztId) throws Exception {
        OrgnztVO orgnzt = getOrgnzt(orgnztId);
        if (orgnzt == null)
            return null;
        if ("1".equals(orgnzt.getOrgnztTopF())) {
            return orgnzt;
        }

        return getTopOrgnzt(orgnzt.getOrgnztParId());
    }

    /**
     * 개요 : 트리 화면을 위한 조직도 목록 조회
     * 
     * @param userVO 조직도 조회 기준이 되는 정보
     * @return
     */
    @Override
    public List<TreeNodeVO> getOrganizationTreeList(UserVO userVO) {

        List<TreeNodeVO> result = orgnztManageDAO.selectOrganizationTreeList(userVO);

        return result;
    }

    /**
     * 개요 : 직책 목록 조회
     * 
     * @param orgnztId 기준이 되는 부서ID
     * @return
     * @throws Exception
     */
    public List<DutyVO> getDutyList(String orgnztId) throws Exception {
        return orgnztManageDAO.selectDutyList(orgnztId);
    }

    /**
     * 개요 : 직책 정보 조회
     * 
     * @param dutyId 직책ID
     * @return
     * @throws Exception
     */
    public DutyVO getDutyInfo(String dutyId) throws Exception {
        return orgnztManageDAO.selectDuty(dutyId);
    }
}
